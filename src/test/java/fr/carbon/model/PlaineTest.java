package fr.carbon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaineTest {

    private Plaine plaine;

    /**
     * Initialisation de la plaine avant chaque test.
     */
    @BeforeEach
    void setUp() {
        plaine = new Plaine(2, 3);
    }

    /**
     * Vérifie que la plaine est initialisée avec les bonnes coordonnées et état d'occupation.
     */
    @Test
    void testInitialisation() {
        assertEquals(2, plaine.getX(), "La coordonnée X de la plaine devrait être 2");
        assertEquals(3, plaine.getY(), "La coordonnée Y de la plaine devrait être 3");
        assertFalse(plaine.isOccupe(), "La case plaine ne devrait pas être occupée");
    }

    /**
     * Vérifie que la méthode setX() modifie correctement la coordonnée X.
     */
    @Test
    void testSetX() {
        plaine.setX(5);
        assertEquals(5, plaine.getX(), "La coordonnée X de la plaine devrait être 5 après modification");
    }

    /**
     * Vérifie que la méthode setY() modifie correctement la coordonnée Y.
     */
    @Test
    void testSetY() {
        plaine.setY(4);
        assertEquals(4, plaine.getY(), "La coordonnée Y de la plaine devrait être 4 après modification");
    }

}