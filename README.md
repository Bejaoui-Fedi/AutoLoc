# AutoLoc — Atelier 4

## Présentation

AutoLoc est une application de gestion de location de véhicules. Dans cet atelier, une couche de services est ajoutée entre les dépôts Spring Data JPA et les futurs contrôleurs REST afin de regrouper les opérations métier.

## Fonctionnalités

- Gestion des véhicules, agences, clients, employés, contrats, réservations, équipements, maintenances et paiements par des interfaces et implémentations de services.
- Utilisation des dépôts JPA pour lire et persister les données.
- Gestion des ressources introuvables avec `ResourceNotFoundException`.
- Chargement de véhicules de démonstration si la base ne contient encore aucun véhicule.

Les contrôleurs REST ne sont pas encore présents dans cette étape ; l'API HTTP complète est ajoutée à l'atelier suivant.

## Technologies et prérequis

- Java 17
- Maven
- Spring Boot 3.2.3, Spring Data JPA et Hibernate
- MySQL

## Configuration et lancement

La configuration de la base se trouve dans `src/main/resources/application.properties`. Par défaut, elle cible MySQL sur `localhost:3306`, la base `autoloc_db`, l'utilisateur `root` et un mot de passe vide. Ajustez les paramètres selon votre installation et démarrez MySQL.

Lancer le projet depuis sa racine :

```bash
mvn spring-boot:run
```

Le serveur écoute sur le port `8081`. Les routes REST seront disponibles dans l'étape qui ajoute les contrôleurs.
