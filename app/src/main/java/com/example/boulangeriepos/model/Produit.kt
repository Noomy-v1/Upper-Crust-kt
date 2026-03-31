package com.example.boulangeriepos.model

import java.util.UUID

enum class Categorie {
    PAIN, VIENNOISERIE, PATISSERIE
}

data class Produit(
    val id : String = UUID.randomUUID().toString(),
    val nom : String,
    val prix : Double,
    var quantite : Int = 0,
    var stock : Int,
    val categorie: Categorie)