package com.example.boulangeriepos

import com.example.boulangeriepos.model.Categorie
import com.example.boulangeriepos.model.Panier
import com.example.boulangeriepos.model.Produit
import org.junit.Assert.*
import org.junit.Test

//Code generer par Gemini
class BoulangerieUnitTests {

    @Test
    fun `1 - Verifier la creation dun produit avec des donnees valides`() {
        val pain = Produit(nom = "Baguette", prix = 1.20, stock = 50, categorie = Categorie.PAIN)

        assertEquals("Baguette", pain.nom)
        assertEquals(1.20, pain.prix, 0.0)
        assertEquals(50, pain.stock)
        assertEquals(Categorie.PAIN, pain.categorie)
    }

    @Test
    fun `2 - Verifier que le filtrage par categorie retourne les bons produits`() {
        val inventaire = listOf(
            Produit(nom = "Baguette", prix = 1.20, stock = 50, categorie = Categorie.PAIN),
            Produit(nom = "Croissant", prix = 1.50, stock = 10, categorie = Categorie.VIENNOISERIE)
        )

        val viennoiseries = inventaire.filter { it.categorie == Categorie.VIENNOISERIE }

        assertEquals(1, viennoiseries.size)
        assertEquals("Croissant", viennoiseries[0].nom)
    }

    @Test
    fun `3 - Verifier que le calcul du total de la commande est exact`() {
        val panier = Panier()
        val baguette = Produit(nom = "Baguette", prix = 1.20, stock = 50, categorie = Categorie.PAIN)
        val eclair = Produit(nom = "Éclair", prix = 3.50, stock = 5, categorie = Categorie.PATISSERIE)

        panier.ajouterProduit(baguette) // 1.20 $
        panier.ajouterProduit(eclair) // 3.50 $
        panier.modifierQuantite(eclair.id, 2) // Passe à 2 éclairs (7.00 $)

        // Total attendu : 1.20 + 7.00 = 8.20 $
        assertEquals(8.20, panier.calculerTotal(), 0.01)
    }

    @Test
    fun `4 - Verifier la validation (le nom ne peut pas etre vide)`() {
        try {
            Produit(nom = "", prix = 1.0, stock = 1, categorie = Categorie.PAIN)
            fail("Le produit a été créé sans nom, cela aurait dû planter !")
        } catch (e: IllegalArgumentException) {
            assertEquals("Le nom du produit ne peut pas être vide", e.message)
        }
    }

    @Test
    fun `5 - Verifier la suppression dun item du panier`() {
        val panier = Panier()
        val baguette = Produit(nom = "Baguette", prix = 1.20, stock = 50, categorie = Categorie.PAIN)

        panier.ajouterProduit(baguette)
        assertEquals(1, panier.listeProduit.size) // Vérifie qu'il y a 1 élément

        panier.supprimerProduit(baguette.id)
        assertEquals(0, panier.listeProduit.size) // Vérifie qu'il n'y a plus rien
    }
}