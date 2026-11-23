package tn.esprit.autoloc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDTO {

    private Long id;
    private String immatriculation;
    private String marque;
    private String modele;
    private Double tarifJournalier;
    private Long idAgence;
    private List<Long> equipementIds;
    private List<Long> maintenanceIds;
}