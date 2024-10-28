package fr.carbon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

    /**
     * Vérifie que la méthode tournerGauche() fonctionne correctement.
     * Elle doit tourner l'orientation actuelle de 90° vers la gauche.
     */
    @Test
    void testTournerGauche() {
        assertEquals(Orientation.OUEST, Orientation.NORD.tournerGauche(), "De NORD, tourner à gauche devrait donner OUEST");
        assertEquals(Orientation.SUD, Orientation.OUEST.tournerGauche(), "De OUEST, tourner à gauche devrait donner SUD");
        assertEquals(Orientation.EST, Orientation.SUD.tournerGauche(), "De SUD, tourner à gauche devrait donner EST");
        assertEquals(Orientation.NORD, Orientation.EST.tournerGauche(), "De EST, tourner à gauche devrait donner NORD");
    }

    /**
     * Vérifie que la méthode tournerDroite() fonctionne correctement.
     * Elle doit tourner l'orientation actuelle de 90° vers la droite.
     */
    @Test
    void testTournerDroite() {
        assertEquals(Orientation.EST, Orientation.NORD.tournerDroite(), "De NORD, tourner à droite devrait donner EST");
        assertEquals(Orientation.SUD, Orientation.EST.tournerDroite(), "De EST, tourner à droite devrait donner SUD");
        assertEquals(Orientation.OUEST, Orientation.SUD.tournerDroite(), "De SUD, tourner à droite devrait donner OUEST");
        assertEquals(Orientation.NORD, Orientation.OUEST.tournerDroite(), "De OUEST, tourner à droite devrait donner NORD");
    }

    /**
     * Vérifie que la méthode fromChar() renvoie la bonne orientation pour un caractère donné.
     * Elle doit lever une IllegalArgumentException pour un caractère invalide.
     */
    @Test
    void testFromChar() {
        assertEquals(Orientation.NORD, Orientation.fromChar('N'), "Le caractère 'N' devrait donner NORD");
        assertEquals(Orientation.SUD, Orientation.fromChar('S'), "Le caractère 'S' devrait donner SUD");
        assertEquals(Orientation.EST, Orientation.fromChar('E'), "Le caractère 'E' devrait donner EST");
        assertEquals(Orientation.OUEST, Orientation.fromChar('O'), "Le caractère 'O' devrait donner OUEST");

        // Vérifie que l'exception est levée pour un caractère invalide
        assertThrows(IllegalArgumentException.class, () -> Orientation.fromChar('X'), "Un caractère invalide doit lever une exception");
    }
}