package com.example.boulangeriepos.model

class RegistreVentes {

    private val historique = mutableListOf<Transaction>()

    fun ajouterTransaction(transaction: Transaction){
        historique.add(transaction)
        println("Transaction sauvegarder, total : ${transaction.total}$ ")
    }

    fun obtenirListeTransactions(): List<Transaction>{
        return historique.toList()
    }

//    // Fonction pour le filtre de l'Activité 2
//    fun filtrerParDate(dateRecherchee: LocalDate): List<Transaction> {
//        return historique.filter { transaction ->
//            // On compare juste la date (année, mois, jour), sans l'heure précise
//            transaction.date.toLocalDate() == dateRecherchee
//        }
//    }
}