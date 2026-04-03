package com.example.boulangeriepos.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.UUID

class Transaction @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: String = UUID.randomUUID().toString(),
    val date: LocalDateTime = LocalDateTime.now(),
    val produitsVendus: List<Produit>,
    val total: Double
    ){
}