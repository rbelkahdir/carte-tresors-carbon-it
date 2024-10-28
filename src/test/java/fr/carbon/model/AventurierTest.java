package fr.carbon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AventurierTest {

    private Aventurier aventurier;

    /**
     * Initialisation de l'aventurier avant chaque test.
     */
    @BeforeEach
    void setUp() {
        aventurier = new Aventurier("A1", 1, 1, Orientation.NORD, "DGD");
    }

    /**
     * Vérifie que l'aventurier est correctement initialisé avec les attributs fournis.
     */
    @Test
    void testInitialisation() {
        assertEquals("A1", aventurier.getNom(), "Le nom de l'aventurier devrait être 'A1'");
        assertEquals(1, aventurier.getX(), "La coordonnée X de l'aventurier devrait être 1");
        assertEquals(1, aventurier.getY(), "La coordonnée Y de l'aventurier devrait être 1");
        assertEquals(Orientation.NORD, aventurier.getOrientation(), "L'orientation de l'aventurier devrait être NORD");
        assertEquals("DGD", aventurier.getMouvements(), "La séquence de mouvements devrait être 'DGD'");
        assertEquals(0, aventurier.getTresorsCollectes(), "Le nombre de trésors collectés devrait être 0");
    }

    /**
     * Vérifie que l'aventurier peut récupérer des trésors.
     */
    @Test
    void testIncrementerTresorsCollectes() {
        aventurier.incrementerTresorsCollectes();
        assertEquals(1, aventurier.getTresorsCollectes(), "Le nombre de trésors collectés devrait être 1 après une collecte");

        aventurier.incrementerTresorsCollectes();
        assertEquals(2, aventurier.getTresorsCollectes(), "Le nombre de trésors collectés devrait être 2 après deux collectes");
    }

    /**
     * Vérifie que les getters et setters fonctionnent correctement pour le nom.
     */
    @Test
    void testSetNom() {
        aventurier.setNom("A1 Modifié");
        assertEquals("A1 Modifié", aventurier.getNom(), "Le nom de l'aventurier devrait être 'Lara'");
    }

    /**
     * Vérifie que les getters et setters fonctionnent correctement pour l'orientation.
     */
    @Test
    void testSetOrientation() {
        aventurier.setOrientation(Orientation.EST);
        assertEquals(Orientation.EST, aventurier.getOrientation(), "L'orientation de l'aventurier devrait être EST");
    }

    /**
     * Vérifie que les getters et setters fonctionnent correctement pour les mouvements.
     */
    @Test
    void testSetMouvements() {
        aventurier.setMouvements("GDD");
        assertEquals("GDD", aventurier.getMouvements(), "La séquence de mouvements devrait être 'GDD'");
    }

    /**
     * Vérifie que les getters et setters fonctionnent correctement pour le nombre de trésors collectés.
     */
    @Test
    void testSetTresorsCollectes() {
        aventurier.setTresorsCollectes(5);
        assertEquals(5, aventurier.getTresorsCollectes(), "Le nombre de trésors collectés devrait être 5 après modification");
    }

    /**
     * Vérifie que l'aventurier est occupé par défaut lors de l'initialisation.
     */
    @Test
    void testEstOccupeParDefaut() {
        assertTrue(aventurier.isOccupe(), "La case de l'aventurier devrait être occupé par défaut");
    }
}