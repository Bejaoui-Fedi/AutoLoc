package tn.esprit.autoloc.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(VehiculeRepository vehiculeRepository) {
        return args -> {
            if (vehiculeRepository.count() == 0) {
                Vehicule v1 = new Vehicule();
                v1.setImmatriculation("200-TUN-1234");
                v1.setMarque("Peugeot");
                v1.setModele("208");
                v1.setCategorie(CategorieVehicule.CITADINE);
                v1.setTarifJournalier(new BigDecimal("90.00"));
                v1.setStatut(StatutVehicule.DISPONIBLE);

                Vehicule v2 = new Vehicule();
                v2.setImmatriculation("210-TUN-5678");
                v2.setMarque("Volkswagen");
                v2.setModele("Tiguan");
                v2.setCategorie(CategorieVehicule.SUV);
                v2.setTarifJournalier(new BigDecimal("180.00"));
                v2.setStatut(StatutVehicule.DISPONIBLE);

                vehiculeRepository.save(v1);
                vehiculeRepository.save(v2);

                System.out.println(">>> Données de démonstration chargées avec succès !");
            }
        };
    }
}