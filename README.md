Markdown
# 🏅 JO Dakar 2026 - Plateforme Officielle de Gestion

Bienvenue sur le dépôt du projet de gestion et de suivi des **Jeux Olympiques de la Jeunesse (JOJ) Dakar 2026**. Cette application web full-stack permet d'administrer les athlètes, les disciplines, les épreuves, de saisir les résultats et de visualiser en temps réel le tableau des médailles ainsi que le classement général par points.

---

## 📐 Architecture du Projet

Le projet repose sur une architecture moderne découplée (SPA / API REST) :
- **Backend** : Développé avec **Spring Boot**. Il suit une architecture en couches propre (Controllers, Services, Repositories, DTOs, Entities) garantissant la séparation des responsabilités.
- **Frontend** : Application Single Page développée avec **Angular 17+**. Elle propose une interface réactive et moderne basée sur Bootstrap.
- **Persistance** : Gestion des données via Spring Data JPA / Oracle DB.

---

## 🛠️ Technologies Utilisées

### Backend
- **Langage** : Java 17+
- **Framework** : Spring Boot, Spring Data JPA
- **Base de données** : Oracle Database / H2
- **Outils** : Maven, Lombok

### Frontend
- **Framework** : Angular (TypeScript)
- **UI / Design** : Bootstrap 5, Bootstrap Icons, CSS3 (animations & dégradés sur mesure)

### API & Tests
- Collection Postman pour les tests d'intégration des Web Services REST.

---

## 📁 Structure du Dépôt

jo-dakar-2026/
├── backend/                  # Application Spring Boot (API REST)
│   ├── src/main/java/sn/jo/dakar/
│   │   ├── controller/       # Endpoints REST (Athlete, Epreuve, Dashboard...)
│   │   ├── dto/              # Objects de transfert de données
│   │   ├── entity/           # Entités JPA (Athlete, Discipline, Resultat...)
│   │   ├── repository/       # Interfaces Spring Data JPA
│   │   └── service/          # Logique métier
│   └── pom.xml
│
├── frontend/                 # Application Client Angular
│   ├── src/app/
│   │   ├── components/       # Composants (Dashboard, Athletes, Epreuves...)
│   │   └── services/         # Services d'appel API HTTP
│   └── package.json
│
├── postman/                  # Collection de tests Postman (JSON)
└── README.md


---

## 🚀 Installation et Lancement

### Prérequis
- Java 17 ou supérieur
- Node.js (v18+) & npm
- Angular CLI (`npm install -g @angular/cli`)
- Maven

### 1. Lancement du Backend (Spring Boot)
```bash
cd backend
mvn clean install
mvn spring-boot:run
L'API REST sera accessible à l'adresse : http://localhost:8080

2. Lancement du Frontend (Angular)
Bash
cd frontend
npm install
ng serve
L'application web sera accessible à l'adresse : http://localhost:4200

📬 Collection Postman
Les tests d'intégration de l'ensemble des endpoints REST sont disponibles dans le dossier /postman. Vous pouvez importer le fichier JSON directement dans Postman pour tester les fonctionnalités CRUD et la récupération des statistiques du tableau de bord.

👩‍💻 Auteur
Projet développé dans le cadre du module Web Services / Fullstack - JO Dakar 2026.
