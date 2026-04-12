package com.example.boulangeriepos.presenter

import com.example.boulangeriepos.model.Transaction
import java.time.LocalDate

interface TransactionContract {
    /**
     * Représente la Vue (ex: HistoriqueActivity).
     * Son rôle est strictement passif : elle affiche la liste des factures que le Presenter lui donne.
     */
    interface View{
        fun afficherTransaction(transactions: List<Transaction>)
    }

    /**
     * Représente le "Cerveau" de l'écran d'historique.
     * Gère la récupération des données depuis le registre et applique les filtres de recherche.
     */
    interface Presenter{
        fun afficherHistorique()
        fun filtrerParDate(dateRecherche: LocalDate)
        fun effacerFiltre()
    }
}