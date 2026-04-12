package com.example.boulangeriepos.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.boulangeriepos.model.RegistreVentes
import com.example.boulangeriepos.model.Transaction
import java.time.LocalDate

/**
 * Le "Cerveau" (Presenter) de l'écran de l'historique des ventes.
 * Il fait le pont entre la base de données (RegistreVentes) et l'interface utilisateur.
 */
class TransactionPresenter(
    private val view : TransactionContract.View) : TransactionContract.Presenter{

    /**
     * Récupère l'historique complet et l'envoie à la Vue.
     * Utilise .reversed() pour s'assurer que les transactions les plus récentes
     * apparaissent toujours en haut de l'écran (meilleure ergonomie).
     */
    override fun afficherHistorique() {
        val toutesLesTransactions = RegistreVentes.obtenirListeTransactions()
        view.afficherTransaction(toutesLesTransactions.reversed())
    }

    //Code generer par Gemini
    //Permet de faire afficher un calendrier pour le filtre des transactions
    @RequiresApi(Build.VERSION_CODES.O)
    override fun filtrerParDate(dateRecherche: LocalDate) {
        val transactionsFiltrees = RegistreVentes.filtrerParDate(dateRecherche)
        view.afficherTransaction(transactionsFiltrees.reversed())
    }

    override fun effacerFiltre() {
        afficherHistorique()
    }



}