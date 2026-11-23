package tn.esprit.autoloc.service;

import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.dto.VehiculeDTO;

import java.time.LocalDate;

public interface IAffectationService {

    ReservationResponseDTO affecterReservationAClient(Long idReservation, String nom, String prenom);

    VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement);

    VehiculeDTO affecterMaintenanceAvehicule(String description, String immatriculation);

    ContratDTO affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin);

    ReservationResponseDTO creerReservationEtContratAssocie(ReservationWithContratRequestDTO requestDTO);
}