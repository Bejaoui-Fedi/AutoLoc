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
                Vehicule v1 = new Vehicule(null, "200-TUN-1234", "Peugeot", "208", CategorieVehicule.CITADINE, new BigDecimal("90.00"), StatutVehicule.DISPONIBLE);
                Vehicule v2 = new Vehicule(null, "210-TUN-5678", "Volkswagen", "Tiguan", CategorieVehicule.SUV, new BigDecimal("180.00"), StatutVehicule.DISPONIBLE);

                vehiculeRepository.save(v1);
                vehiculeRepository.save(v2);

                System.out.println(">>> Données de démonstration chargées avec succès !");
            }
        };
    }
}