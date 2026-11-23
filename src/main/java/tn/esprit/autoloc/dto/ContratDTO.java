package tn.esprit.autoloc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContratDTO {

    private Long id;
    private LocalDate dateSignature;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double caution;
    private Long idReservation;
}