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

/**
 * L'écran principal de l'application de point de vente.
 * Agit en tant que "View" dans l'architecture MVP. Son seul rôle est de capter
 * les actions de l'utilisateur (clics, texte tapé) et d'afficher les données fournies par le Presenter.
 */
class MainActivity : AppCompatActivity(), TerminalVenteContract.View {

    // --- DÉCLARATION DES VARIABLES ---
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

        //Liaison avec les éléments de l'interface (XML)
        layoutCategories = findViewById(R.id.layoutCategories)
        recyclerViewProduits = findViewById(R.id.recyclerViewProduits)
        boutonRetour = findViewById(R.id.boutonRetour)

        // Éléments pour le panneau latéral du panier
        layoutPanier = findViewById(R.id.layoutPanier)
        imagePanier = findViewById(R.id.imagePanier)
        boutonFermerPanier = findViewById(R.id.boutonFermerPanier)


        //Configuration de la liste principale des produits (Inventaire)
        // La lambda (produitClique) envoie le produit sélectionné directement au Presenter
        produitAdapter = ProduitAdapter(emptyList()) { produitClique ->
            presenter.cliquerProduit(produitClique)
        }

        //recyclerViewProduits.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProduits.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerViewProduits.adapter = produitAdapter

        //Initialisation du Cerveau (Presenter)
        presenter = TerminalVentePresenter(this)

        //Configuration des boutons de navigation et de filtres
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

        boutonFermerPanier.setOnClickListener {
            layoutPanier.visibility = View.GONE
        }

        //Configuration de l'affichage du Panier
        imagePanier.setOnClickListener {
            layoutPanier.visibility = View.VISIBLE
        }


        val recyclerViewPanier = findViewById<RecyclerView>(R.id.recyclerViewPanier)

        // Le PanierAdapter renvoie deux infos au clic : le produit et la nouvelle quantité souhaitée
        panierAdapter = PanierAdapter(emptyList()) { produit, nouvelleQuantite ->
            if (nouvelleQuantite == 0) {
                presenter.supprimerProduit(produit.id)
            } else {
                presenter.modifierQuantite(produit.id, nouvelleQuantite)
            }
        }
        recyclerViewPanier.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerViewPanier.adapter = panierAdapter

        //Actions de transaction
        findViewById<Button>(R.id.boutonValiderPanier).setOnClickListener {
            presenter.cliquerValiderPanier()
            layoutPanier.visibility = View.GONE
        }

        findViewById<Button>(R.id.boutonAnnulerPanier).setOnClickListener {
            presenter.cliquerAnnulerTransaction()
        }

        //Bouton pour acceder a l'historique des transactions
        val boutonHistorique = findViewById<android.widget.ImageButton>(R.id.boutonHistorique)

        boutonHistorique.setOnClickListener {
            val intent = android.content.Intent(this, HistoriqueActivity::class.java)
            startActivity(intent)
        }

        //Configuration de la barre de recherche en temps réel
        val barreRecherche = findViewById<android.widget.EditText>(R.id.barreRecherche)

        barreRecherche.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.rechercherProduit(s.toString())
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })
    }

    /**
     * Cache les catégories principales pour afficher la grille/liste des produits spécifiques.
     */
    private fun basculerVersProduits() {
        layoutCategories.visibility = View.GONE
        recyclerViewProduits.visibility = View.VISIBLE
        boutonRetour.visibility = View.VISIBLE
    }

    /**
     * Cache la liste des produits pour revenir au menu principal des catégories.
     */
    private fun basculerVersCategories() {
        recyclerViewProduits.visibility = View.GONE
        boutonRetour.visibility = View.GONE
        layoutCategories.visibility = View.VISIBLE
    }

    // --- IMPLÉMENTATION DU CONTRAT (MÉTHODES CONTRÔLÉES PAR LE PRESENTER) ---
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