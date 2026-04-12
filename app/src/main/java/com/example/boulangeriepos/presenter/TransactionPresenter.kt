package com.example.boulangeriepos.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.boulangeriepos.model.RegistreVentes
import com.example.boulangeriepos.model.Transaction
import java.time.LocalDate

class TransactionPresenter(
    private val view : TransactionContract.View) : TransactionContract.Presenter{
    override fun afficherHistorique() {
        val toutesLesTransactions = RegistreVentes.obtenirListeTransactions()
        view.afficherTransaction(toutesLesTransactions.reversed())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun filtrerParDate(dateRecherche: LocalDate) {
        val transactionsFiltrees = RegistreVentes.filtrerParDate(dateRecherche)
        view.afficherTransaction(transactionsFiltrees.reversed())
    }

    override fun effacerFiltre() {
        afficherHistorique()
    }



}