package com.example.boulangeriepos.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boulangeriepos.R
import com.example.boulangeriepos.model.Produit

class PanierAdapter(
    private var produits: List<Produit>,
    private val onChangementQuantite: (Produit, Int) -> Unit
) : RecyclerView.Adapter<PanierAdapter.PanierViewHolder>() {

    class PanierViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val texteNom: TextView = view.findViewById(R.id.texteNomProduitPanier)
        val texteQuantite: TextView = view.findViewById(R.id.texteQuantitePanier)
        val textePrixTotal: TextView = view.findViewById(R.id.textePrixTotalLigne)
        val boutonMoins: Button = view.findViewById(R.id.boutonMoins)
        val boutonPlus: Button = view.findViewById(R.id.boutonPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanierViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_panier, parent, false)
        return PanierViewHolder(view)
    }

    override fun onBindViewHolder(holder: PanierViewHolder, position: Int) {
        val produit = produits[position]

        holder.texteNom.text = produit.nom
        holder.texteQuantite.text = produit.quantite.toString()

        val prixLigne = produit.prix * produit.quantite
        holder.textePrixTotal.text = String.format("%.2f $", prixLigne)

        holder.boutonPlus.setOnClickListener {
            onChangementQuantite(produit, produit.quantite + 1)
        }

        holder.boutonMoins.setOnClickListener {
            if (produit.quantite > 1) {
                onChangementQuantite(produit, produit.quantite - 1)
            }
        }
    }

    override fun getItemCount(): Int = produits.size


    fun mettreAJourListe(nouvelleListe: List<Produit>) {
        produits = nouvelleListe
        notifyDataSetChanged()
    }
}