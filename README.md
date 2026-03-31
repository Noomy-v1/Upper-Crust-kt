# Upper-Crust-kt 🥖

Une application native Android de point de vente (POS) conçue spécifiquement pour la gestion fluide et rapide d'une boulangerie artisanale (Maison Gil).

*(Note : Une version iOS, `Upper-Crust`, est prévue pour accompagner ce projet).*

## À propos du projet
Ce projet a été développé pour fournir une interface de caisse tactile, moderne et intuitive, facilitant la prise de commande en boutique. L'objectif principal est d'allier une excellente expérience utilisateur (UX) avec une architecture de code solide et évolutive.

## Fonctionnalités principales
* **Catalogue dynamique :** Navigation fluide entre les catégories (Pains, Viennoiseries, Pâtisseries) avec un affichage en grille optimisé pour tablette et mobile.
* **Gestion du panier :** Tiroir interactif superposé permettant l'ajout d'articles, la modification des quantités en temps réel (boutons +/-) et le calcul automatique du total.
* **Alertes de stock :** Indicateurs visuels (pastilles rouges) intégrés directement sur les tuiles des produits pour signaler les stocks critiques.
* **Interface UI/UX :** Design personnalisé respectant la charte graphique de la boulangerie, utilisant des `Material Components` pour une interaction naturelle.

## Technologies et Architecture
* **Langage :** Kotlin
* **Architecture :** MVP (Model-View-Presenter). Cette architecture a été rigoureusement appliquée pour séparer la logique d'affaires (calculs, gestion du panier) de l'interface graphique (Activités, Adapters), rendant le code hautement testable et maintenable.
* **Interface (UI) :** XML natif (utilisation avancée de `ConstraintLayout`, `LinearLayout`, et `RecyclerView` avec `GridLayoutManager` et `LinearLayoutManager`).
* **IDE :** Android Studio

## Aperçu de l'application
<img width="450" height="1006" alt="image" src="https://github.com/user-attachments/assets/e098da1a-cdae-4249-b0b9-7abd1f7ac1e2" />
<img width="453" height="1007" alt="image" src="https://github.com/user-attachments/assets/f6639b91-cb32-4e02-9f48-b9a2ec892260" />
