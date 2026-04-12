package com.example.boulangeriepos.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.UUID

/**
 * Représente l'archive d'une vente finalisée (l'équivalent d'une facture).
 * C'est une photographie figée dans le temps des articles achetés et du montant payé.
 *
 * L'utilisation d'une 'data class' est idéale ici car cette classe
 * sert uniquement à transporter et stocker des données.
 */

class Transaction @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: String = UUID.randomUUID().toString(),
    val date: LocalDateTime = LocalDateTime.now(),
    val produitsVendus: List<Produit>,
    val total: Double
    ){
}