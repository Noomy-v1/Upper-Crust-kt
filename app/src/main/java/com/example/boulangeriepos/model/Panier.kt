package com.example.boulangeriepos.model

/**
 * Représente le panier d'achat actuel d'un client.
 * Gère le stockage temporaire des produits avant la validation de la transaction.
 */
class Panier {

    val listeProduit = mutableListOf<Produit>()

    /**
     * Ajoute un produit au panier.
     * Si le produit est déjà présent, on incrémente simplement sa quantité.
     * @param produit Le produit sélectionné depuis l'inventaire.
     */
    fun ajouterProduit(produit: Produit){
        val produitExist = listeProduit.find { it.id == produit.id }
        if(produitExist != null){
            produitExist.quantite+=1
        }
        else {
            produit.quantite = 1
            listeProduit.add(produit)
        }

        println("Produit ajouter au panier !")
    }

    /**
     * Retire complètement un produit du panier, peu importe sa quantité.
     * @param idProduit L'identifiant unique du produit à retirer.
     */
    fun supprimerProduit(idProduit: String) {
        listeProduit.removeAll { it.id == idProduit }
    }

    /**
     * Met à jour manuellement la quantité d'un produit déjà dans le panier.
     * @param idProduit L'identifiant unique du produit.
     * @param nouvelleQuantite La quantité désirée (déjà validée par rapport au stock).
     */
    fun modifierQuantite(idProduit: String, nouvelleQuantite : Int){
        listeProduit.find { it.id == idProduit }?.quantite = nouvelleQuantite
    }

    /**
     * Calcule le montant total de tous les articles présents dans le panier.
     * @return Le sous-total de la facture.
     */
    fun calculerTotal(): Double{
        var total = 0.0
        for(p in listeProduit){
            total += (p.prix * p.quantite)
        }
        return total
    }

    /**
     * Réinitialise le panier pour une nouvelle commande.
     */
    fun viderPanier(){
        listeProduit.clear()
        println("Le panier est vide!")
    }
}