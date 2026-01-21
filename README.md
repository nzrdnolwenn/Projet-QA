# Projet QA — E2E (Playwright) + BDD (Cucumber/Gherkin)

## Site choisi
- **Apple** : `https://www.apple.com`

## Scénarios testés (Gherkin)
- **Navigation** : chargement de la home, accès Store/iPhone/Mac/iPad/Watch/AirPods, navigation par catégories.
- **Recherche** : recherche de produits (iPhone, MacBook, AirPods, iPad…).
- **Store** : ouverture du Store + parcours “Shop Mac/iPhone/iPad/Watch”.
- **Panier** : consultation du panier vide + retour vers le Store.
- **Mocks** : simulation réseau lent, interception/log des requêtes, simulation d’erreur (500) sur l’API du panier.

## Installation / exécution
### Prérequis
- Java 17+ (ou compatible)
- Le Gradle Wrapper est inclus (`./gradlew`)

### Installer les navigateurs Playwright (une fois)
bash
./gradlew playwright --args="install"
### Lancer les tests
bash
./gradlew test
Optionnel :
- **Smoke tests** :
bash
./gradlew test --tests fr.esiea.qa.runners.SmokeTestRunner
- **Scénarios de mocks** :
bash
./gradlew test --tests fr.esiea.qa.runners.MockTestRunner
### Rapports
- **Cucumber** : `target/cucumber-reports/`
- **JUnit/Gradle** : `build/reports/tests/test/index.html`

## Difficultés rencontrées
- Popups pays/région et cookies sur Apple pouvant bloquer les interactions (gérés côté pages/steps).
- Site très dynamique (sélecteurs/chargements variables) : nécessité d’attentes et de navigation par URL pour fiabiliser.
 
