package fr.carbon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MontagneTest {

    private Montagne montagne;

    /**
     * Initialisation de la montagne avant chaque test.
     */
    @BeforeEach
    void setUp() {
        montagne = new Montagne(2, 3);
    }

    /**
     * Vérifie que la montagne est initialisée avec les bonnes coordonnées et état d'occupation.
     */
    @Test
    void testInitialisation() {
        assertEquals(2, montagne.getX(), "La coordonnée X de la montagne devrait être 2");
        assertEquals(3, montagne.getY(), "La coordonnée Y de la montagne devrait être 3");
        assertTrue(montagne.isOccupe(), "La montagne doit être occupée par défaut");
    }

    /**
     * Vérifie que la méthode setX() modifie correctement la coordonnée X.
     */
    @Test
    void testSetX() {
        montagne.setX(5);
        assertEquals(5, montagne.getX(), "La coordonnée X de la montagne devrait être 5 après modification");
    }

    /**
     * Vérifie que la méthode setY() modifie correctement la coordonnée Y.
     */
    @Test
    void testSetY() {
        montagne.setY(4);
        assertEquals(4, montagne.getY(), "La coordonnée Y de la montagne devrait être 4 après modification");
    }
}