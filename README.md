# Système de Gestion de Conférences - Architecture Microservices

## Description

Application complète de gestion de conférences basée sur une architecture microservices, développée pour un examen universitaire de Systèmes Distribués. Le système comprend la gestion des keynotes (intervenants), des conférences et des reviews (avis).

## Architecture

```
┌─────────────────┐
│   Next.js App   │
│  (Frontend)     │
└────────┬────────┘
         │
         │ OAuth2/OIDC
         │
    ┌────▼─────┐
    │ Keycloak │
    │  (Auth)  │
    └──────────┘
         │
    ┌────▼────────┐
    │   Gateway   │
    │  Service    │
    └─────┬───────┘
          │
    ┌─────▼──────────────────┐
    │  Discovery Service     │
    │    (Eureka)            │
    └───────┬────────────────┘
            │
    ┌───────┴────────┐
    │                │
┌───▼────┐    ┌─────▼──────┐
│Keynote │    │ Conference │
│Service │◄───┤  Service   │
└────────┘    └────────────┘
              (Feign Client)
```

## Technologies Utilisées

### Backend
- **Java**: 17 LTS
- **Maven**: 3.9.9
- **Spring Boot**: 3.5.9
- **Spring Cloud**: 2024.0.0
  - Eureka (Discovery)
  - Config Server
  - Gateway
  - OpenFeign
  - Resilience4J (Circuit Breaker)
- **Base de données**: H2 (in-memory)
- **Sécurité**: Keycloak (OAuth2 + OIDC + JWT)
- **Documentation**: OpenAPI 3 (Swagger)

### Frontend
- **Next.js**: 15.1.6
- **React**: 19.0.0
- **TypeScript**: 5.x
- **NextAuth.js**: 5.0.0-beta.25

### Infrastructure
- **Docker**: Containerisation de tous les services
- **Docker Compose**: Orchestration

## Services

### 1. Discovery Service (Port 8761)
Service d'enregistrement et de découverte basé sur Eureka Server.

### 2. Config Service (Port 8888)
Service de configuration centralisée utilisant Spring Cloud Config.
- Repository Git: https://github.com/omarelkd/config-repo

### 3. Gateway Service (Port 8090)
API Gateway avec routage, sécurité OAuth2 et circuit breaker.
- Routage vers keynote-service et conference-service
- Circuit breaker Resilience4J
- Exposition de Swagger UI

### 4. Keynote Service (Port 8081)
Gestion des keynotes (intervenants).

**Entité Keynote:**
- id: Long
- nom: String
- prenom: String
- email: String
- fonction: String

**Endpoints:**
- `GET /api/keynotes` - Liste tous les keynotes
- `GET /api/keynotes/{id}` - Récupère un keynote
- `POST /api/keynotes` - Crée un keynote
- `PUT /api/keynotes/{id}` - Met à jour un keynote
- `DELETE /api/keynotes/{id}` - Supprime un keynote

### 5. Conference Service (Port 8082)
Gestion des conférences et reviews.

**Entité Conference:**
- id: Long
- titre: String
- type: ConferenceType (ACADEMIQUE | COMMERCIALE)
- date: LocalDate
- duree: Integer
- nombreInscrits: Integer
- score: Double

**Entité Review:**
- id: Long
- date: LocalDate
- texte: String
- stars: Integer (1-5)
- conference: Conference

**Endpoints:**
- `GET /api/conferences` - Liste toutes les conférences
- `GET /api/conferences/{id}` - Récupère une conférence
- `POST /api/conferences` - Crée une conférence
- `PUT /api/conferences/{id}` - Met à jour une conférence
- `DELETE /api/conferences/{id}` - Supprime une conférence
- `GET /api/reviews` - Liste toutes les reviews
- `GET /api/reviews?conferenceId={id}` - Reviews d'une conférence
- `POST /api/reviews` - Crée une review
- `PUT /api/reviews/{id}` - Met à jour une review
- `DELETE /api/reviews/{id}` - Supprime une review

**Communication Inter-services:**
- Utilise OpenFeign pour communiquer avec keynote-service
- Circuit breaker pour la résilience

### 6. Next.js Frontend (Port 3000)
Interface utilisateur avec authentification Keycloak.

**Pages:**
- `/login` - Page de connexion
- `/` - Dashboard principal
- `/keynotes` - Liste des keynotes
- `/conferences` - Liste des conférences
- `/reviews` - Liste des reviews

## Installation et Lancement

### Prérequis
- Docker et Docker Compose installés
- Java 17 (pour développement local)
- Maven 3.9.9 (pour développement local)
- Node.js 22.x (pour développement local)

### Lancement avec Docker Compose

1. **Cloner le projet**
```bash
git clone <repository-url>
cd conference-system
```

2. **Construire et démarrer tous les services**
```bash
docker-compose up --build
```

3. **Accéder aux services**
- **Keycloak Admin**: http://localhost:8080/admin
  - Username: `admin`
  - Password: `admin`
- **Discovery Service (Eureka)**: http://localhost:8761
- **Gateway Service**: http://localhost:8090
- **Swagger UI Keynote**: http://localhost:8090/swagger-ui.html
- **Frontend Next.js**: http://localhost:3000

### Configuration Keycloak

Avant d'utiliser l'application, configurez Keycloak:

1. Accéder à http://localhost:8080/admin
2. Se connecter avec admin/admin
3. Créer un nouveau realm: `realm-conference`
4. Créer un client:
   - Client ID: `conference-client`
   - Client Type: `OpenID Connect`
   - Client Authentication: ON
   - Valid Redirect URIs: `http://localhost:3000/*`
   - Web Origins: `http://localhost:3000`
5. Créer un utilisateur de test dans le realm

### Arrêt des services

```bash
docker-compose down
```

Pour supprimer également les volumes:
```bash
docker-compose down -v
```

## Ports

| Service | Port |
|---------|------|
| Keycloak | 8080 |
| Discovery Service | 8761 |
| Config Service | 8888 |
| Keynote Service | 8081 |
| Conference Service | 8082 |
| Gateway Service | 8090 |
| Frontend Next.js | 3000 |

## Sécurité

### OAuth2 + OIDC + JWT
- Tous les services backend sont sécurisés avec OAuth2
- Authentification via Keycloak
- JWT pour les tokens d'accès
- Le frontend utilise NextAuth.js pour la gestion de session

### Configuration
- Realm: `realm-conference`
- Client ID: `conference-client`
- Issuer URI: `http://keycloak:8080/realms/realm-conference`

## Documentation API

La documentation OpenAPI (Swagger) est disponible via le Gateway:
- http://localhost:8090/swagger-ui.html

Documentation par service:
- Keynote Service: http://localhost:8090/keynote/v3/api-docs
- Conference Service: http://localhost:8090/conference/v3/api-docs

## Structure du Projet

```
conference-system/
├── discovery-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── config-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── keynote-service/
│   ├── src/
│   │   └── main/
│   │       └── java/ccn/elkadiri/keynoteservice/
│   │           ├── config/
│   │           ├── controller/
│   │           ├── dto/
│   │           ├── entity/
│   │           ├── exception/
│   │           ├── mapper/
│   │           ├── repository/
│   │           └── service/
│   ├── pom.xml
│   └── Dockerfile
├── conference-service/
│   ├── src/
│   │   └── main/
│   │       └── java/ccn/elkadiri/conferenceservice/
│   │           ├── config/
│   │           ├── controller/
│   │           ├── dto/
│   │           ├── entity/
│   │           ├── enums/
│   │           ├── exception/
│   │           ├── feign/
│   │           ├── mapper/
│   │           ├── repository/
│   │           └── service/
│   ├── pom.xml
│   └── Dockerfile
├── gateway-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── nextjs-front-app/
│   ├── app/
│   ├── auth.ts
│   ├── middleware.ts
│   ├── package.json
│   └── Dockerfile
├── docker-compose.yml
└── README.md
```

## Bonnes Pratiques Implémentées

1. **Architecture Clean/Layered**
   - Séparation claire des couches (Controller, Service, Repository)
   - DTOs pour la communication externe
   - Mappers pour la conversion entité-DTO

2. **Gestion des Erreurs**
   - GlobalExceptionHandler dans chaque service
   - Messages d'erreur cohérents
   - Logs structurés

3. **Validation**
   - Validation des entrées avec Jakarta Validation
   - Messages d'erreur personnalisés

4. **Sécurité**
   - OAuth2 + JWT sur tous les endpoints
   - Configuration sécurisée de Keycloak
   - Tokens d'accès pour l'authentification

5. **Résilience**
   - Circuit Breaker Resilience4J
   - Fallback methods pour la dégradation gracieuse
   - Health checks dans Docker

6. **Documentation**
   - OpenAPI 3 (Swagger) pour tous les services
   - README complet
   - Commentaires dans le code

7. **Containerisation**
   - Multi-stage builds pour optimisation
   - Health checks
   - Networks isolés

## Tests

Pour tester l'application:

1. **Créer un keynote**
```bash
curl -X POST http://localhost:8090/api/keynotes \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Doe",
    "prenom": "John",
    "email": "john.doe@example.com",
    "fonction": "Expert IA"
  }'
```

2. **Créer une conférence**
```bash
curl -X POST http://localhost:8090/api/conferences \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "titre": "Introduction à Spring Boot",
    "type": "ACADEMIQUE",
    "date": "2025-06-15",
    "duree": 120,
    "nombreInscrits": 50,
    "score": 4.5
  }'
```

3. **Créer une review**
```bash
curl -X POST http://localhost:8090/api/reviews \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2025-06-16",
    "texte": "Excellente conférence !",
    "stars": 5,
    "conferenceId": 1
  }'
```

## Troubleshooting

### Les services ne démarrent pas
- Vérifier que les ports ne sont pas déjà utilisés
- Vérifier les logs: `docker-compose logs <service-name>`
- Attendre que Keycloak et Discovery Service soient complètement démarrés

### Erreur d'authentification
- Vérifier que le realm `realm-conference` existe dans Keycloak
- Vérifier que le client `conference-client` est configuré correctement
- Vérifier que l'utilisateur existe et a les bonnes permissions

### Circuit breaker activé
- Vérifier que tous les services sont bien démarrés
- Vérifier la configuration réseau Docker
- Consulter les logs pour identifier le service défaillant

## Auteur

CCN El Kadiri - Examen Systèmes Distribués 2025

## Licence

Ce projet est réalisé dans un cadre académique.
