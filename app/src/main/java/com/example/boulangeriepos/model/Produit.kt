package com.example.boulangeriepos.model

import java.util.UUID

/**
 * Représente les grandes familles d'articles vendus à la boulangerie.
 * Utilisé principalement pour filtrer l'affichage sur le terminal de vente.
 */
enum class Categorie {
    PAIN, VIENNOISERIE, PATISSERIE
}

data class Produit(
    val id : String = UUID.randomUUID().toString(),
    val nom : String,
    val prix : Double,
    var quantite : Int = 1,
    var stock : Int,
    val categorie: Categorie) {
    init {
        require(nom.isNotBlank()) { "Le nom du produit ne peut pas être vide" }
    }
}