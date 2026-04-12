package com.example.boulangeriepos.presenter

import com.example.boulangeriepos.model.Produit
import com.example.boulangeriepos.model.Categorie
import com.example.boulangeriepos.model.Panier

interface TerminalVenteContract {

    interface View{
        /**
         * Représente l'interface utilisateur (MainActivity).
         * Son rôle est strictement passif : elle ne fait qu'afficher ce que le Presenter lui ordonne.
         */
        fun afficherProduits(produits : List<Produit>)
        fun afficherPanier(produitPanier: List<Produit>)
        fun afficherTotalPanier(total : Double)
        fun afficherMessage(message : String)

    }

    /**
     * Représente le "cerveau" de l'écran de vente.
     * Son rôle est d'écouter les actions de la View, d'interagir avec les Models (Panier, Inventaire),
     * et de renvoyer les instructions d'affichage à la View.
     */
    interface Presenter{
        fun cliquerProduit(produit: Produit)
        fun cliquerCategorie(categorie: Categorie)
        fun modifierQuantite(idProduit : String, nouvelleQuantite : Int)
        fun supprimerProduit(idProduit: String)
        fun cliquerAnnulerTransaction()
        fun cliquerValiderPanier()
        fun rechercherProduit(nom: String)
    }
}