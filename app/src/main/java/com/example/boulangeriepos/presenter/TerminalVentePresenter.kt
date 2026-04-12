package com.example.boulangeriepos.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.boulangeriepos.model.Categorie
import com.example.boulangeriepos.model.Panier
import com.example.boulangeriepos.model.Produit
import com.example.boulangeriepos.model.Transaction
import com.example.boulangeriepos.model.RegistreVentes

class TerminalVentePresenter (private val view: TerminalVenteContract.View) : TerminalVenteContract.Presenter {
    private val panier = Panier()

    private val inventaire = listOf(
        Produit(nom = "Baguette", prix = 1.20, stock = 50, categorie = Categorie.PAIN),
        Produit(nom = "Croissant", prix = 1.50, stock = 3, categorie = Categorie.VIENNOISERIE),
        Produit(nom = "Éclair", prix = 3.50, stock = 0, categorie = Categorie.PATISSERIE)
    )

    init {
        view.afficherProduits(inventaire)
    }

    override fun cliquerProduit(produit: Produit) {
        val quantiteDansPanier = panier.listeProduit.find { it.id == produit.id }?.quantite ?: 0

        if (produit.stock > quantiteDansPanier) {
            panier.ajouterProduit(produit)
            mettreAJourVuePanier()
        }else {
            view.afficherMessage("Désolé, ce produit (${produit.nom}) est en rupture de stock")
        }
    }

    override fun cliquerCategorie(categorie: Categorie) {
        val produitsFiltrer = inventaire.filter { it.categorie == categorie }

        view.afficherProduits(produitsFiltrer)
    }

    override fun modifierQuantite(idProduit: String, nouvelleQuantite: Int) {
        val produitInventaire = inventaire.find { it.id == idProduit }

        if (produitInventaire != null) {
            if (nouvelleQuantite <= produitInventaire.stock) {
                panier.modifierQuantite(idProduit, nouvelleQuantite)
                mettreAJourVuePanier()
            } else {
                view.afficherMessage("Action impossible : Il ne reste que ${produitInventaire.stock} en stock.")
            }
        }
    }

    override fun supprimerProduit(idProduit: String) {
        panier.supprimerProduit(idProduit)
        mettreAJourVuePanier()
        view.afficherMessage("Produit supprimé")
    }


    override fun cliquerAnnulerTransaction() {
        if(panier.listeProduit.isNotEmpty()){
            panier.viderPanier()
            mettreAJourVuePanier()
            view.afficherMessage("Transaction annulée!")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun cliquerValiderPanier() {
        if(panier.listeProduit.isEmpty()){
            view.afficherMessage("Panier vide")
            return
        }

        //Sinon on creer la facture
        val copiePanier = panier.listeProduit.map { it.copy() }
        val totalAPayer = panier.calculerTotal()
        val nouvelleTransaction = Transaction(produitsVendus = copiePanier, total = totalAPayer)

        //soustraire le stock
        for (produitVendu in panier.listeProduit) {
            val produitInventaire = inventaire.find { it.id == produitVendu.id }
            produitInventaire?.let { it.stock -= produitVendu.quantite }
        }
        RegistreVentes.ajouterTransaction(nouvelleTransaction)

        //On vide le panier une fois la transaction completee
        panier.viderPanier()
        view.afficherProduits(inventaire)
        mettreAJourVuePanier()
        view.afficherMessage("Merci pour votre commande !")
    }

    override fun rechercherProduit(nom: String) {
        val resultats = inventaire.filter { it.nom.contains(nom, ignoreCase = true) }
        view.afficherProduits(resultats)
    }

  private fun mettreAJourVuePanier() {
       view.afficherPanier(panier.listeProduit)
      view.afficherTotalPanier(panier.calculerTotal())
  }
}