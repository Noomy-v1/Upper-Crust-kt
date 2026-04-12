package com.example.boulangeriepos.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boulangeriepos.R
import com.example.boulangeriepos.model.Categorie
import com.example.boulangeriepos.model.Produit

class ProduitAdapter (private var produits: List<Produit>, private val auClicSurProduit: (Produit) -> Unit
) : RecyclerView.Adapter<ProduitAdapter.ProduitViewHolder>() {

    class ProduitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageProduit: ImageView = view.findViewById(R.id.imageProduit)
        val texteNomProduit: TextView = view.findViewById(R.id.texteNomProduit)
        val textePrixProduit: TextView = view.findViewById(R.id.textePrixProduit)

        val texteStock: TextView = view.findViewById(R.id.texteStockProduit)
        val indicateurStock: View = view.findViewById(R.id.indicateurStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduitViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_produit, parent, false)
    return ProduitViewHolder(view)
}

    override fun onBindViewHolder(holder: ProduitViewHolder, position: Int) {
        val produit = produits[position]

        holder.texteNomProduit.text = produit.nom
        holder.textePrixProduit.text = String.format("%.2f $", produit.prix)

        holder.texteStock.text = "Stock : ${produit.stock}"

        // Si le stock est de 5 ou moins, on affiche la pastille rouge
        if (produit.stock <= 5) {
            holder.indicateurStock.visibility = View.VISIBLE
        } else {
            holder.indicateurStock.visibility = View.INVISIBLE
        }


        when (produit.categorie) {
            Categorie.PAIN -> holder.imageProduit.setImageResource(R.drawable.image_pain)
            Categorie.VIENNOISERIE -> holder.imageProduit.setImageResource(R.drawable.image_croissants)
            Categorie.PATISSERIE -> holder.imageProduit.setImageResource(R.drawable.image_gateau)
        }

        holder.itemView.setOnClickListener {
            auClicSurProduit(produit)
        }
    }

    override fun getItemCount(): Int = produits.size

    fun mettreAJourListe(nouvelleListe: List<Produit>) {
        produits = nouvelleListe
        notifyDataSetChanged()
    }



}