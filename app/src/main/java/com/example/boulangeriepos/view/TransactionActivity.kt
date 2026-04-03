package com.example.boulangeriepos.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boulangeriepos.R
import com.example.boulangeriepos.model.Transaction
import com.example.boulangeriepos.presenter.TransactionContract
import com.example.boulangeriepos.presenter.TransactionPresenter
import java.time.LocalDate
import java.util.Calendar

class HistoriqueActivity : AppCompatActivity(), TransactionContract.View {

    private lateinit var presenter: TransactionPresenter
    private lateinit var adapter: TransactionAdapter

    private lateinit var boutonChoisirDate: Button
    private lateinit var boutonEffacerFiltre: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        presenter = TransactionPresenter(this)

        boutonChoisirDate = findViewById(R.id.boutonChoisirDate)
        boutonEffacerFiltre = findViewById(R.id.boutonEffacerFiltre)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewHistorique)

        adapter = TransactionAdapter(emptyList()) { transactionCliquee ->
            afficherDetailsTransaction(transactionCliquee)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        findViewById<Button>(R.id.boutonRetourCaisse).setOnClickListener {
            finish()
        }

        boutonChoisirDate.setOnClickListener {
            afficherCalendrier()
        }

        boutonEffacerFiltre.setOnClickListener {
            presenter.effacerFiltre()
            boutonChoisirDate.text = "Toutes les dates"
            boutonEffacerFiltre.visibility = View.GONE
        }

        presenter.afficherHistorique()
    }

    override fun afficherTransaction(transactions: List<Transaction>) {
        adapter.mettreAJourListe(transactions)
    }


    //Code generer par Gemini
    //Permet de faire afficher un calendrier pour le filtre des transactions
    @RequiresApi(Build.VERSION_CODES.O)
    private fun afficherCalendrier() {
        val calendrier = Calendar.getInstance()
        val annee = calendrier.get(Calendar.YEAR)
        val mois = calendrier.get(Calendar.MONTH)
        val jour = calendrier.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, anneeChoisie, moisChoisi, jourChoisi ->
                val dateFiltre = LocalDate.of(anneeChoisie, moisChoisi + 1, jourChoisi)

                presenter.filtrerParDate(dateFiltre)

                boutonChoisirDate.text = dateFiltre.toString()
                boutonEffacerFiltre.visibility = View.VISIBLE
            },
            annee, mois, jour
        )
        datePickerDialog.show()
    }

    private fun afficherDetailsTransaction(transaction: Transaction) {
        val recu = StringBuilder()
        transaction.produitsVendus.forEach { produit ->
            val totalLigne = produit.prix * produit.quantite
            recu.append("- ${produit.quantite}x ${produit.nom} : ${String.format("%.2f $", totalLigne)}\n")
        }
        recu.append("\nTotal de la vente : ${String.format("%.2f $", transaction.total)}")

        //Code generer par Gemini
        // Creation d'une boite de dialogue pour les recu sous forme de pop-up
        AlertDialog.Builder(this)
            .setTitle("Reçu ${transaction.id.take(8).uppercase()}")
            .setMessage(recu.toString())
            .setPositiveButton("Fermer") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}