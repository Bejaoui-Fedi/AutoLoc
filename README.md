# AutoLoc — Atelier 1

## Présentation

AutoLoc est le point de départ d'une application de gestion de location de véhicules. Cette première étape met en place le projet Spring Boot, les premières entités métier JPA et le dépôt des véhicules.

## Fonctionnalités de cette étape

- Démarrage de l'application avec Spring Boot.
- Modèle métier initial : véhicules, clients, agences, réservations, contrats, employés, équipements, maintenances et paiements, avec leurs catégories, statuts et rôles.
- Accès aux véhicules avec Spring Data JPA.
- Chargement de deux véhicules de démonstration lorsque la table des véhicules est vide.

Cette étape prépare la couche de persistance ; elle ne contient pas encore les services métier ni les contrôleurs REST des étapes suivantes.

## Technologies et prérequis

- Java 17
- Maven
- Spring Boot 3.2.3, Spring Web et Spring Data JPA
- MySQL

## Configuration et lancement

La configuration MySQL par défaut se trouve dans `src/main/resources/application.properties` : base `autoloc_db`, serveur `localhost:3306`, utilisateur `root` et mot de passe vide. Adaptez ces propriétés à votre environnement et démarrez MySQL avant l'application.

À la racine du projet, lancez :

```bash
mvn spring-boot:run
```

Le serveur utilise le port Spring Boot par défaut (`8080`). Cette étape ne définit pas encore de routes REST métier.
