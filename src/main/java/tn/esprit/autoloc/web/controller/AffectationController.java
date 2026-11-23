package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.service.IAffectationService;

import java.time.LocalDate;

@RestController
@RequestMapping("/affectations")
@RequiredArgsConstructor
@Tag(name = "Affectations", description = "Services métier d'affectation et de création composite")
public class AffectationController {

    private final IAffectationService affectationService;

    @PutMapping("/reservations/{idReservation}/client")
    @Operation(summary = "Affecter une réservation à un client par nom et prénom")
    public ReservationResponseDTO affecterReservationAClient(
            @PathVariable Long idReservation,
            @RequestParam String nom,
            @RequestParam String prenom) {
        return affectationService.affecterReservationAClient(idReservation, nom, prenom);
    }

    @PutMapping("/vehicules/{idVehicule}/equipements/{idEquipement}")
    @Operation(summary = "Affecter un équipement à un véhicule")
    public VehiculeDTO affecterVehiculeAEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {
        return affectationService.affecterVehiculeAEquipement(idVehicule, idEquipement);
    }

    @PostMapping("/maintenances")
    @Operation(summary = "Affecter une maintenance à un véhicule via son immatriculation")
    public VehiculeDTO affecterMaintenanceAvehicule(
            @RequestParam String description,
            @RequestParam String immatriculation) {
        return affectationService.affecterMaintenanceAvehicule(description, immatriculation);
    }

    @PostMapping("/contrats/reservation")
    @Operation(summary = "Affecter un contrat à une réservation selon les dates")
    public ContratDTO affecterContratAReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSignature,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return affectationService.affecterContratAReservation(dateSignature, dateDebut, dateFin);
    }

    @PostMapping("/reservations-avec-contrat")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une réservation et son contrat associé")
    public ReservationResponseDTO creerReservationEtContratAssocie(
            @RequestBody ReservationWithContratRequestDTO requestDTO) {
        return affectationService.creerReservationEtContratAssocie(requestDTO);
    }
}