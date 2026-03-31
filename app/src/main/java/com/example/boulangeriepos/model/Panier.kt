package com.example.boulangeriepos.model

class Panier {

    val listeProduit = mutableListOf<Produit>()

    fun ajouterProduit(produit: Produit){
        val produitExist = listeProduit.find { it.id == produit.id }
        if(produitExist != null){
            produit.quantite+=1
        }
        else {
            listeProduit.add(produit)
        }

        println("Produit ajouter au panier !")
    }

    fun supprimerProduit(produit: Produit){
        listeProduit.remove(produit)
        println("Produit supprimer du panier !")
    }

    fun modifierQuantite(idProduit: String, nouvelleQuantite : Int){
        listeProduit.find { it.id == idProduit }?.quantite = nouvelleQuantite
    }

    fun calculerTotal(): Double{
        var total : Double = 0.0
        for(p in listeProduit){
            total += (p.prix * p.quantite)
        }
        return total
    }

    fun viderPanier(){
        listeProduit.clear()
        println("Le panier est vide!")
    }
}