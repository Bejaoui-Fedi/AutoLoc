# AutoLoc API

AutoLoc est une API REST de gestion d'une agence de location de véhicules. Le projet permet de gérer les véhicules et les principales opérations et ressources associées à la location : agences, clients, employés, réservations, contrats, paiements, équipements et maintenances.

## Technologies

- Java 17
- Spring Boot 3.2.3
- Spring Web
- Spring Data JPA et Hibernate
- MySQL
- springdoc-openapi / Swagger UI
- Maven

## Prérequis

- JDK 17 ou version compatible
- Maven
- MySQL en cours d'exécution

## Configuration et lancement

La configuration par défaut se trouve dans `src/main/resources/application.properties` :

- Base de données : `autoloc_db` sur `localhost:3306`
- Utilisateur MySQL : `root`
- Mot de passe : vide
- Port HTTP : `8081`
- Préfixe de l'API : `/api`

Le paramètre `createDatabaseIfNotExist=true` permet à MySQL de créer la base si elle n'existe pas. Si votre configuration MySQL est différente, adaptez les propriétés `spring.datasource.*` avant le démarrage.

Depuis la racine du projet, lancez l'application avec :

```bash
mvn spring-boot:run
```

L'API est alors accessible à l'adresse `http://localhost:8081/api`.

## API REST

Les contrôleurs exposent des routes pour les ressources suivantes :

| Ressource | Route |
| --- | --- |
| Agences | `/api/agences` |
| Clients | `/api/clients` |
| Contrats | `/api/contrats` |
| Employés | `/api/employes` |
| Équipements | `/api/equipements` |
| Maintenances | `/api/maintenances` |
| Paiements | `/api/paiements` |
| Réservations | `/api/reservations` |
| Véhicules | `/api/vehicules` |

Selon la ressource, les routes permettent de consulter, créer, modifier ou supprimer des éléments. Les routes de détail utilisent généralement un identifiant, par exemple `GET /api/vehicules/{id}`.

La documentation interactive Swagger UI est disponible à l'adresse `http://localhost:8081/api/swagger-ui/index.html`.

## Modèle métier

Les entités principales comprennent les véhicules, catégories et statuts de véhicule, agences, clients, employés et rôles, réservations, contrats, paiements et modes de paiement, équipements et maintenances. Les données sont persistées dans MySQL via JPA/Hibernate.

Au premier démarrage, l'application ajoute deux véhicules de démonstration si la table des véhicules est vide.

## Tests

Pour lancer les tests avec Maven :

```bash
mvn test
```
