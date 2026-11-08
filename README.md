# AutoLoc — Atelier 2

## Présentation

AutoLoc est une application Spring Boot destinée à la gestion d'une agence de location de véhicules. L'atelier 2 développe le modèle de données JPA : les entités métier et leurs relations forment la base de la persistance MySQL.

## Périmètre

Le modèle couvre notamment les véhicules, catégories et statuts, les agences, les clients, les employés et leurs rôles, les réservations, contrats, paiements et modes de paiement, ainsi que les équipements et maintenances. Hibernate synchronise le modèle avec la base grâce à la configuration JPA.

Le projet inclut un dépôt Spring Data pour les véhicules et l'initialisation de véhicules de démonstration lorsque la table correspondante est vide. Les interfaces de dépôt pour les autres ressources, les services métier et les contrôleurs REST sont ajoutés dans les ateliers suivants.

## Technologies et prérequis

- Java 17
- Maven
- Spring Boot 3.2.3, Spring Web, Spring Data JPA et Hibernate
- MySQL

## Configuration et lancement

La configuration par défaut est dans `src/main/resources/application.properties` : MySQL sur `localhost:3306`, base `autoloc_db`, utilisateur `root`, mot de passe vide. Créez ou configurez votre base, puis adaptez les propriétés `spring.datasource.*` si nécessaire.

Lancer depuis la racine du projet :

```bash
mvn spring-boot:run
```

L'application utilise le port Spring Boot par défaut (`8080`). À cette étape, les opérations sur les données sont préparées par le modèle et la persistance ; les routes REST métier seront ajoutées ensuite.
