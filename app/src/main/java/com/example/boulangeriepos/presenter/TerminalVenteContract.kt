package com.example.boulangeriepos.presenter

import com.example.boulangeriepos.model.Produit
import com.example.boulangeriepos.model.Categorie
import com.example.boulangeriepos.model.Panier

interface TerminalVenteContract {

    interface View{
        fun afficherProduits(produits : List<Produit>)
        fun afficherPanier(produitPanier: List<Produit>)
        fun afficherTotalPanier(total : Double)
        fun afficherMessage(message : String)

    }

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