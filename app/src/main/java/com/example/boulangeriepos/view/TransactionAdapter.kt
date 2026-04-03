package com.example.boulangeriepos.view

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.boulangeriepos.R
import com.example.boulangeriepos.model.Transaction
import java.time.format.DateTimeFormatter

class TransactionAdapter(
    private var transactions: List<Transaction>,
    private val onClickTransaction: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val texteDate: TextView = view.findViewById(R.id.texteDateTransaction)
        val texteId: TextView = view.findViewById(R.id.texteIdTransaction)
        val texteTotal: TextView = view.findViewById(R.id.texteTotalTransaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]

        //Code generer par Gemini
        //Les 4 lignes qui suivent
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm")
        holder.texteDate.text = transaction.date.format(formatter)

        holder.texteId.text = "#TRX-${transaction.id.take(8).uppercase()}"
        holder.texteTotal.text = String.format("%.2f $", transaction.total)

        holder.itemView.setOnClickListener {
            onClickTransaction(transaction)
        }
    }

    override fun getItemCount(): Int = transactions.size

    fun mettreAJourListe(nouvelleListe: List<Transaction>) {
        transactions = nouvelleListe
        notifyDataSetChanged()
    }
}