package com.example.boulangeriepos.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boulangeriepos.R
import com.example.boulangeriepos.model.Categorie
import com.example.boulangeriepos.model.Produit
import com.example.boulangeriepos.presenter.TerminalVenteContract
import com.example.boulangeriepos.presenter.TerminalVentePresenter

class MainActivity : AppCompatActivity(), TerminalVenteContract.View {

    private lateinit var presenter: TerminalVenteContract.Presenter

    private lateinit var produitAdapter: ProduitAdapter
    private lateinit var panierAdapter: PanierAdapter
    private lateinit var layoutCategories: LinearLayout
    private lateinit var recyclerViewProduits: RecyclerView
    private lateinit var boutonRetour: android.widget.Button

    private lateinit var layoutPanier: View
    private lateinit var imagePanier: ImageView
    private lateinit var boutonFermerPanier: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        layoutCategories = findViewById(R.id.layoutCategories)
        recyclerViewProduits = findViewById(R.id.recyclerViewProduits)
        boutonRetour = findViewById(R.id.boutonRetour)

        //element pour le panier
        layoutPanier = findViewById(R.id.layoutPanier)
        imagePanier = findViewById(R.id.imagePanier)
        boutonFermerPanier = findViewById(R.id.boutonFermerPanier)


        produitAdapter = ProduitAdapter(emptyList()) { produitClique ->
            presenter.cliquerProduit(produitClique)
        }

        //recyclerViewProduits.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProduits.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerViewProduits.adapter = produitAdapter

        presenter = TerminalVentePresenter(this)

        boutonRetour.setOnClickListener {
            basculerVersCategories()
        }

        findViewById<View>(R.id.boutonPain).setOnClickListener {
            basculerVersProduits()
            presenter.cliquerCategorie(Categorie.PAIN)
        }

        findViewById<View>(R.id.boutonViennoiserie).setOnClickListener {
            basculerVersProduits()
            presenter.cliquerCategorie(Categorie.VIENNOISERIE)
        }

        findViewById<View>(R.id.boutonPatisserie).setOnClickListener {
            basculerVersProduits()
            presenter.cliquerCategorie(Categorie.PATISSERIE)
        }

        //click sur l'icone panier
        imagePanier.setOnClickListener {
            layoutPanier.visibility = View.VISIBLE
        }

        boutonFermerPanier.setOnClickListener {
            layoutPanier.visibility = View.GONE
        }

        val recyclerViewPanier = findViewById<RecyclerView>(R.id.recyclerViewPanier)

        panierAdapter = PanierAdapter(emptyList()) { produit, nouvelleQuantite ->
            presenter.modifierQuantite(produit.id, nouvelleQuantite)
        }

        recyclerViewPanier.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerViewPanier.adapter = panierAdapter

        findViewById<Button>(R.id.boutonValiderPanier).setOnClickListener {
            presenter.cliquerValiderPanier()
            layoutPanier.visibility = View.GONE
        }

        //Bouton pour acceder a l'historique des transactions
        val boutonHistorique = findViewById<android.widget.ImageButton>(R.id.boutonHistorique)

        boutonHistorique.setOnClickListener {
            val intent = android.content.Intent(this, HistoriqueActivity::class.java)
            startActivity(intent)
        }
    }

    private fun basculerVersProduits() {
        layoutCategories.visibility = View.GONE
        recyclerViewProduits.visibility = View.VISIBLE
        boutonRetour.visibility = View.VISIBLE
    }

    private fun basculerVersCategories() {
        recyclerViewProduits.visibility = View.GONE
        boutonRetour.visibility = View.GONE
        layoutCategories.visibility = View.VISIBLE
    }

    override fun afficherProduits(produits: List<Produit>) {
        produitAdapter.mettreAJourListe(produits)
    }


    override fun afficherPanier(produitPanier: List<Produit>) {
        panierAdapter.mettreAJourListe(produitPanier)
    }

    override fun afficherTotalPanier(total: Double) {
        val texteTotal = findViewById<TextView>(R.id.texteTotalFinal)
        texteTotal.text = String.format("%.2f $", total)
    }

    override fun afficherMessage(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }

}