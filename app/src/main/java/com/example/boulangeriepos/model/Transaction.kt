package com.example.boulangeriepos.model

import java.time.LocalDateTime
import java.util.UUID

class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val date: LocalDateTime = LocalDateTime.now(),
    val produitsVendus: List<Produit>,
    val total: Double
    ){
}