# Maison Gil - Système de Point de Vente boulangerie (POS)

Il s'agit d'un système de point de vente (caisse) interactif conçu pour une boulangerie.

## Fonctionnalités

**1. Écran de Caisse (Terminal de vente)**
* Catalogue de produits divisé en 3 catégories (Pains, Viennoiseries, Pâtisseries).
* Ajout d'articles au panier et modification des quantités avec les boutons `+` et `-`.
* Calcul automatique et instantané du total de la commande.
* Validation du panier pour enregistrer la vente.

**2. Historique des Ventes**
* Liste complète de toutes les transactions terminées.
* Affichage de la date, de l'heure, de l'identifiant unique et du total de chaque facture.
* Affichage d'un reçu détaillé (liste des produits vendus) lorsqu'on clique sur une transaction.
* Calendrier intégré permettant de filtrer l'historique pour ne voir que les ventes d'une journée précise.

## Outils et Technologies

* **Langage :** Kotlin
* **Environnement :** Android Studio
* **Interface (XML) :** Utilisation de `RecyclerView`, `ConstraintLayout` et `DatePickerDialog`.
* **Architecture :** Modèle-Vue-Présentateur (MVP) pour séparer l'affichage de la logique.
* **Tests :** Le projet inclut des tests unitaires (JUnit) pour la logique mathématique, et des tests d'interface automatisés (Espresso) pour simuler le parcours utilisateur.

## Comment tester le projet

1. Téléchargez ou clonez ce projet sur votre ordinateur.
2. Ouvrez le dossier du projet avec **Android Studio**.
3. Laissez Android Studio faire sa synchronisation initiale (barre de chargement en bas).
4. Cliquez sur le bouton vert **Play (Run 'app')** en haut de l'écran pour lancer l'application sur un émulateur.

*(Note pour la correction : Pour lancer les tests d'interface Espresso sans erreur, il est recommandé d'utiliser un émulateur sous API 33 ou 34 mais aussi d'avoir la version junitVersion = "1.2.1"
espressoCore = "3.6.1").*
