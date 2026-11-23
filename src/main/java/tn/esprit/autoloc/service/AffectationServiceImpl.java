package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IMaintenanceRepository;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AffectationServiceImpl implements IAffectationService {

    private final IReservationRepository reservationRepository;
    private final IClientRepository clientRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final IEquipementRepository equipementRepository;
    private final IMaintenanceRepository maintenanceRepository;
    private final IContratRepository contratRepository;
    private final IAgenceRepository agenceRepository;

    @Override
    public ReservationResponseDTO affecterReservationAClient(Long idReservation, String nom, String prenom) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new NoSuchElementException("Reservation introuvable avec l'ID : " + idReservation));

        Client client = clientRepository.findByNomIgnoreCaseAndPrenomIgnoreCase(nom, prenom)
                .orElseThrow(() -> new NoSuchElementException("Client introuvable : " + nom + " " + prenom));

        reservation.setClient(client);
        return toReservationResponseDTO(reservationRepository.save(reservation));
    }

    @Override
    public VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new NoSuchElementException("Vehicule introuvable avec l'ID : " + idVehicule));

        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new NoSuchElementException("Equipement introuvable avec l'ID : " + idEquipement));

        Set<Equipement> equipements = vehicule.getEquipements();
        if (equipements == null) {
            equipements = new HashSet<>();
            vehicule.setEquipements(equipements);
        }
        equipements.add(equipement);

        return toVehiculeDTO(vehiculeRepository.save(vehicule));
    }

    @Override
    public VehiculeDTO affecterMaintenanceAvehicule(String description, String immatriculation) {
        Vehicule vehicule = vehiculeRepository.findByImmatriculationIgnoreCase(immatriculation)
                .orElseThrow(() -> new NoSuchElementException("Vehicule introuvable avec l'immatriculation : " + immatriculation));

        Maintenance maintenance = Maintenance.builder()
                .dateDebut(LocalDate.now())
                .description(description)
                .vehicule(vehicule)
                .build();

        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        List<Maintenance> maintenances = vehicule.getMaintenances();
        if (maintenances == null) {
            maintenances = new java.util.ArrayList<>();
            vehicule.setMaintenances(maintenances);
        }
        maintenances.add(savedMaintenance);

        return toVehiculeDTO(vehiculeRepository.save(vehicule));
    }

    @Override
    public ContratDTO affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin) {
        Reservation reservation = reservationRepository
                .findFirstByDateDebutAndDateFinOrderByIdReservationDesc(dateDebut, dateFin)
                .orElseThrow(() -> new NoSuchElementException(
                        "Aucune reservation trouvee pour la periode du " + dateDebut + " au " + dateFin));

        Contrat contrat = Contrat.builder()
                .dateSignature(dateSignature)
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .montantTotal(reservation.getMontantTotal())
                .reservation(reservation)
                .build();

        Contrat saved = contratRepository.save(contrat);
        reservation.setContrat(saved);
        reservationRepository.save(reservation);
        return toContratDTO(saved);
    }

    @Override
    public ReservationResponseDTO creerReservationEtContratAssocie(ReservationWithContratRequestDTO requestDTO) {
        Client client = clientRepository.findById(requestDTO.getIdClient())
                .orElseThrow(() -> new NoSuchElementException("Client introuvable avec l'ID : " + requestDTO.getIdClient()));

        Vehicule vehicule = vehiculeRepository.findById(requestDTO.getIdVehicule())
                .orElseThrow(() -> new NoSuchElementException("Vehicule introuvable avec l'ID : " + requestDTO.getIdVehicule()));

        Agence agence = agenceRepository.findById(requestDTO.getIdAgence())
                .orElseThrow(() -> new NoSuchElementException("Agence introuvable avec l'ID : " + requestDTO.getIdAgence()));

        Reservation reservation = Reservation.builder()
                .dateDebut(requestDTO.getDateDebut())
                .dateFin(requestDTO.getDateFin())
                .montantTotal(toBigDecimal(requestDTO.getMontantTotal()))
                .client(client)
                .vehicule(vehicule)
                .agence(agence)
                .build();

        Reservation savedReservation = reservationRepository.save(reservation);

        Contrat contrat = Contrat.builder()
                .dateSignature(requestDTO.getDateSignature() != null ? requestDTO.getDateSignature() : LocalDate.now())
                .dateDebut(requestDTO.getDateDebut())
                .dateFin(requestDTO.getDateFin())
                .caution(toBigDecimal(requestDTO.getCaution()))
                .montantTotal(toBigDecimal(requestDTO.getMontantTotal()))
                .reservation(savedReservation)
                .build();

        Contrat savedContrat = contratRepository.save(contrat);
        savedReservation.setContrat(savedContrat);
        Reservation finalReservation = reservationRepository.save(savedReservation);
        return toReservationResponseDTO(finalReservation);
    }

    private ReservationResponseDTO toReservationResponseDTO(Reservation reservation) {
        Client client = reservation.getClient();
        Vehicule vehicule = reservation.getVehicule();
        Agence agence = reservation.getAgence();
        Contrat contrat = reservation.getContrat();

        return ReservationResponseDTO.builder()
                .id(reservation.getIdReservation())
                .dateDebut(reservation.getDateDebut())
                .dateFin(reservation.getDateFin())
                .montantTotal(toDouble(reservation.getMontantTotal()))
                .idClient(client != null ? client.getIdClient() : null)
                .nomClient(client != null ? client.getNom() : null)
                .prenomClient(client != null ? client.getPrenom() : null)
                .idVehicule(vehicule != null ? vehicule.getIdVehicule() : null)
                .immatriculationVehicule(vehicule != null ? vehicule.getImmatriculation() : null)
                .idAgence(agence != null ? agence.getIdAgence() : null)
                .idContrat(contrat != null ? contrat.getIdContrat() : null)
                .build();
    }

    private VehiculeDTO toVehiculeDTO(Vehicule vehicule) {
        Agence agence = vehicule.getAgence();
        List<Long> equipementIds = vehicule.getEquipements() == null
                ? List.of()
                : vehicule.getEquipements().stream().map(Equipement::getIdEquipement).toList();

        List<Long> maintenanceIds = vehicule.getMaintenances() == null
                ? List.of()
                : vehicule.getMaintenances().stream().map(Maintenance::getIdMaintenance).toList();

        return VehiculeDTO.builder()
                .id(vehicule.getIdVehicule())
                .immatriculation(vehicule.getImmatriculation())
                .marque(vehicule.getMarque())
                .modele(vehicule.getModele())
                .tarifJournalier(toDouble(vehicule.getTarifJournalier()))
                .idAgence(agence != null ? agence.getIdAgence() : null)
                .equipementIds(equipementIds)
                .maintenanceIds(maintenanceIds)
                .build();
    }

    private ContratDTO toContratDTO(Contrat contrat) {
        Reservation reservation = contrat.getReservation();
        return ContratDTO.builder()
                .id(contrat.getIdContrat())
                .dateSignature(contrat.getDateSignature())
                .dateDebut(contrat.getDateDebut())
                .dateFin(contrat.getDateFin())
                .caution(toDouble(contrat.getCaution()))
                .idReservation(reservation != null ? reservation.getIdReservation() : null)
                .build();
    }

    private BigDecimal toBigDecimal(Double value) {
        return value == null ? null : BigDecimal.valueOf(value);
    }

    private Double toDouble(BigDecimal value) {
        return value == null ? null : value.doubleValue();
    }
}
