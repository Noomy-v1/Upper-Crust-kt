package com.example.boulangeriepos.presenter

import com.example.boulangeriepos.model.Transaction
import java.time.LocalDate

interface TransactionContract {
    interface View{
        fun afficherTransaction(transactions: List<Transaction>)
    }

    interface Presenter{
        fun afficherHistorique()
        fun filtrerParDate(dateRecherche: LocalDate)
        fun effacerFiltre()
    }
}