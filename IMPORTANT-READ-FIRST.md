# ⚠️ IMPORTANT - Comment utiliser ce projet

## Ce n'est PAS une application Next.js standard !

Ce projet est un **système de microservices complet** qui doit être exécuté avec **Docker Compose**.

## Structure du Projet

```
/
├── discovery-service/          # Service Eureka (Spring Boot)
├── config-service/             # Config Server (Spring Boot)
├── keynote-service/            # API Keynotes (Spring Boot)
├── conference-service/         # API Conferences (Spring Boot)
├── gateway-service/            # API Gateway (Spring Boot)
├── nextjs-front-app/           # Frontend Next.js (dans un sous-dossier)
├── docker-compose.yml          # Orchestration de tous les services
└── README.md                   # Documentation complète
```

## Comment Lancer le Projet

### Option 1 : Docker Compose (RECOMMANDÉ)

1. **Télécharger le projet**
   - Cliquez sur les 3 points en haut à droite → "Download ZIP"
   - Ou utilisez le CLI shadcn pour installer

2. **Extraire et naviguer**
   ```bash
   unzip conferences.zip
   cd conferences
   ```

3. **Lancer tous les services**
   ```bash
   docker-compose up --build
   ```

4. **Accéder aux services**
   - Frontend: http://localhost:3000
   - Keycloak Admin: http://localhost:8080/admin (admin/admin)
   - Eureka Dashboard: http://localhost:8761
   - API Gateway: http://localhost:8090
   - Swagger UI: http://localhost:8090/swagger-ui.html

### Option 2 : Développement Local (Avancé)

Vous devez lancer **chaque service séparément** :

1. **Keycloak** (requis en premier)
2. **Discovery Service** 
3. **Config Service**
4. **Keynote Service**
5. **Conference Service**
6. **Gateway Service**
7. **Frontend Next.js**

Voir le README.md pour les détails complets.

## Configuration Keycloak (IMPORTANT)

Avant d'utiliser l'application, vous DEVEZ configurer Keycloak :

1. Accéder à http://localhost:8080/admin
2. Login: admin / admin
3. Créer un realm: `realm-conference`
4. Créer un client:
   - Client ID: `conference-client`
   - Client Type: OpenID Connect
   - Client Authentication: ON
   - Valid Redirect URIs: `http://localhost:3000/*`
5. Créer un utilisateur de test

## Pourquoi ça ne fonctionne pas dans la Preview v0 ?

La preview v0 est conçue pour des applications Next.js simples. Ce projet est un **système distribué** avec :
- 5 services backend Spring Boot
- 1 service d'authentification Keycloak
- 1 frontend Next.js
- Communication inter-services
- Base de données H2

Il DOIT être exécuté avec Docker Compose pour que tous les services communiquent correctement.

## Support

Consultez le README.md pour :
- Architecture détaillée
- Documentation des APIs
- Endpoints disponibles
- Troubleshooting
- Tests avec curl

## Licence

Projet académique - Examen Systèmes Distribués 2025
```

```json file="" isHidden
