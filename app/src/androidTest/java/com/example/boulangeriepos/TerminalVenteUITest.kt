package com.example.boulangeriepos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.boulangeriepos.view.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TerminalVenteUITests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test1_VenteDunProduit() {
        // 0. IL MANQUAIT CETTE ÉTAPE : Choisir la catégorie d'abord !
        onView(withText("Pains")).perform(click())

        // 1. Sélectionner un pain (maintenant visible)
        onView(allOf(withId(R.id.texteNomProduit), withText("Baguette"))).perform(click())

        // 2. Ouvrir le panier
        onView(withId(R.id.imagePanier)).perform(click())

        // 3. Vérifier le total
        onView(withId(R.id.texteTotalFinal)).check(matches(withText("1.20 $")))

        // 4. Valider la vente
        onView(withId(R.id.boutonValiderPanier)).perform(click())
    }

    @Test
    fun test2_ParcoursDeFiltrage() {
        // 1. Filtrer pour ne voir que les Viennoiseries
        onView(withText("Viennoiseries")).perform(click())

        // 2. Sélectionner un croissant (on cible précisément le texte du catalogue)
        onView(allOf(withId(R.id.texteNomProduit), withText("Croissant"))).perform(click())

        // 3. Ouvrir le panier
        onView(withId(R.id.imagePanier)).perform(click())

        // 4. Vérifier que le panier contient le bon élément (on cible le texte du panier)
        onView(allOf(withId(R.id.texteNomProduitPanier), withText("Croissant"))).check(matches(isDisplayed()))

        // 5. Finaliser la vente
        onView(withId(R.id.boutonValiderPanier)).perform(click())
    }
}