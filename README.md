# AutoLoc — Atelier 3

## Présentation

AutoLoc est une API de gestion de location de véhicules construite avec Spring Boot. Cette étape complète le modèle JPA en ajoutant des interfaces de dépôt Spring Data pour accéder aux principales ressources persistées.

## Fonctionnalités

- Modèle métier JPA pour les véhicules, agences, clients, employés, réservations, contrats, paiements, équipements et maintenances.
- Dépôts Spring Data JPA pour les principales entités, afin de préparer les opérations de persistance.
- Initialisation de deux véhicules de démonstration si aucun véhicule n'est encore enregistré.

Les services métier et les contrôleurs HTTP ne font pas encore partie de cette étape ; les dépôts sont la couche d'accès aux données, pas des routes REST.

## Technologies et prérequis

- Java 17
- Maven
- Spring Boot 3.2.3, Spring Data JPA et Hibernate
- MySQL

## Configuration et démarrage

Configurez la base MySQL dans `src/main/resources/application.properties`. Les valeurs par défaut visent `localhost:3306`, la base `autoloc_db`, l'utilisateur `root` et un mot de passe vide.

Depuis la racine du projet :

```bash
mvn spring-boot:run
```

Le serveur écoute sur le port `8081`. Les services et points d'entrée REST sont développés dans les étapes suivantes.
