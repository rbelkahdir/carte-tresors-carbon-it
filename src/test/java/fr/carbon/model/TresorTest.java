package fr.carbon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TresorTest {

    private Tresor tresor;

    @BeforeEach
    void setUp() {
        tresor = new Tresor(2, 3, 5); // Initialisation avec 5 trésors
    }

    @Test
    void testInitialisation() {
        // Vérifie que la case trésor est initialisée avec les bonnes coordonnées et quantité
        assertEquals(2, tresor.getX(), "La coordonnée X du trésor devrait être 2");
        assertEquals(3, tresor.getY(), "La coordonnée Y du trésor devrait être 3");
        assertFalse(tresor.isOccupe(), "La case trésor ne devrait pas être occupée");
        assertEquals(5, tresor.getQuantite(), "La quantité de trésors devrait être 5");
    }

    @Test
    void testGetQuantite() {
        // Vérifie que la méthode getQuantite() retourne la quantité correcte
        assertEquals(5, tresor.getQuantite(), "La quantité de trésors devrait être 5");
    }

    @Test
    void testDecrementerQuantite() {
        // Vérifie que la méthode decrementerQuantite() fonctionne correctement
        tresor.decrementerQuantite();
        assertEquals(4, tresor.getQuantite(), "La quantité de trésors devrait être 4 après décrémentation");
    }

    @Test
    void testDecrementerQuantiteMultiple() {
        // Vérifie que la méthode decrementerQuantite() peut être appelée plusieurs fois
        tresor.decrementerQuantite(); // 4
        tresor.decrementerQuantite(); // 3
        tresor.decrementerQuantite(); // 2
        assertEquals(2, tresor.getQuantite(), "La quantité de trésors devrait être 2 après trois décrémentations");
    }

    @Test
    void testDecrementerQuantiteZero() {
        // Vérifie que la méthode ne décremente pas en dessous de zéro
        tresor.decrementerQuantite(); // 4
        tresor.decrementerQuantite(); // 3
        tresor.decrementerQuantite(); // 2
        tresor.decrementerQuantite(); // 1
        tresor.decrementerQuantite(); // 0
        tresor.decrementerQuantite(); // Doit rester à 0
        assertEquals(0, tresor.getQuantite(), "La quantité de trésors ne devrait pas être négative");
    }

    @Test
    void testInitialisationAvecQuantiteZero() {
        // Test de création avec une quantité de trésors à zéro
        Tresor tresorZero = new Tresor(1, 1, 0);
        assertEquals(0, tresorZero.getQuantite(), "La quantité de trésors devrait être 0");
        tresorZero.decrementerQuantite(); // Doit rester à 0
        assertEquals(0, tresorZero.getQuantite(), "La quantité de trésors ne devrait pas être négative");
    }

    @Test
    void testSetX() {
        // Vérifie que la méthode setX() modifie correctement la coordonnée X
        tresor.setX(10);
        assertEquals(10, tresor.getX(), "La coordonnée X du trésor devrait être 10 après modification");
    }

    @Test
    void testSetY() {
        // Vérifie que la méthode setY() modifie correctement la coordonnée Y
        tresor.setY(15);
        assertEquals(15, tresor.getY(), "La coordonnée Y du trésor devrait être 15 après modification");
    }
}