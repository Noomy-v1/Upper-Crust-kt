package com.example.boulangeriepos.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

//Code genere a l'aide de Gemini -
// Anciennement Class RegistreVentes mais remplacer par object ResgitreVentes car creer une erreur
// pour appeler les fonctions dans la classe TransactionPresenter
object RegistreVentes {

    private val historique = mutableListOf<Transaction>()

    fun ajouterTransaction(transaction: Transaction){
        historique.add(transaction)
        println("Transaction sauvegarder, total : ${transaction.total}$ ")
    }

    fun obtenirListeTransactions(): List<Transaction>{
        return historique.toList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun filtrerParDate(dateRecherchee: LocalDate): List<Transaction> {
        return historique.filter { transaction ->
            // On compare juste la date (année, mois, jour), sans l'heure précise
            transaction.date.toLocalDate() == dateRecherchee
       }
   }
}