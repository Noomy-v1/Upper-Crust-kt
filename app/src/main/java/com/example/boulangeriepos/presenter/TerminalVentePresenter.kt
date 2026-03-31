package com.example.boulangeriepos.presenter

import com.example.boulangeriepos.model.Categorie
import com.example.boulangeriepos.model.Panier
import com.example.boulangeriepos.model.Produit
import com.example.boulangeriepos.model.Transaction
import com.example.boulangeriepos.model.RegistreVentes

class TerminalVentePresenter (private val view: TerminalVenteContract.View) : TerminalVenteContract.Presenter {
    private val panier = Panier()

    private val registre = RegistreVentes()

    private val inventaire = listOf(
        Produit(nom = "Baguette", prix = 1.20, stock = 50, categorie = Categorie.PAIN),
        Produit(nom = "Croissant", prix = 1.50, stock = 3, categorie = Categorie.VIENNOISERIE),
        Produit(nom = "Éclair", prix = 3.50, stock = 0, categorie = Categorie.PATISSERIE)
    )

    init {
        view.afficherProduits(inventaire)
    }

    override fun cliquerProduit(produit: Produit) {
        panier.ajouterProduit(produit)
        mettreAJourVuePanier()
    }

    override fun cliquerCategorie(categorie: Categorie) {
        val produitsFiltrer = inventaire.filter { it.categorie == categorie }

        view.afficherProduits(produitsFiltrer)
    }

    override fun modifierQuantite(idProduit: String, nouvelleQuantite: Int) {
        panier.modifierQuantite(idProduit, nouvelleQuantite)
        mettreAJourVuePanier()
    }

    override fun supprimerProduit(idProduit: String) {
        TODO("Not yet implemented")
    }

    override fun cliquerAnnulerTransaction() {
        TODO("Not yet implemented")
    }

    override fun cliquerValiderPanier() {
        if(panier.listeProduit.isEmpty()){
            view.afficherMessage("Panier vide")
            return
        }

        //Sinon on creer la facture
        val copiePanier = panier.listeProduit.toList()
        val totalAPayer = panier.calculerTotal()

        val nouvelleTransaction = Transaction(produitsVendus = copiePanier, total = totalAPayer)

        registre.ajouterTransaction(nouvelleTransaction)

        panier.viderPanier()

        mettreAJourVuePanier()
        view.afficherMessage("Merci pour votre commande !")
    }

  private fun mettreAJourVuePanier() {
       view.afficherPanier(panier.listeProduit)
      view.afficherTotalPanier(panier.calculerTotal())
  }
}