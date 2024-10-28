package fr.carbon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    private Carte carte;

    /**
     * Initialisation de la carte avant chaque test.
     */
    @BeforeEach
    void setUp() {
        carte = new Carte(5, 4); // Crée une carte de 5 cases de largeur et 4 cases de hauteur
    }

    /**
     * Vérifie que la carte est initialisée avec les bonnes dimensions.
     */
    @Test
    void testInitialisation() {
        assertEquals(5, carte.getLargeur(), "La largeur de la carte devrait être 5");
        assertEquals(4, carte.getHauteur(), "La hauteur de la carte devrait être 4");
        assertNotNull(carte.getCases(), "La liste des cases ne devrait pas être null");
        assertEquals(4, carte.getCases().length, "Le tableau des cases devrait avoir 4 lignes");
        assertEquals(5, carte.getCases()[0].length, "Le tableau des cases devrait avoir 5 colonnes");
    }

    /**
     * Vérifie que la liste des aventuriers est initialisée comme vide.
     */
    @Test
    void testListeAventuriersInitialisee() {
        assertTrue(carte.getAventuriers().isEmpty(), "La liste des aventuriers devrait être vide au départ");
    }

    /**
     * Vérifie que l'on peut ajouter un aventurier à la carte.
     */
    @Test
    void testAjouterAventurier() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, Orientation.NORD, "DGD");
        carte.getAventuriers().add(aventurier);
        assertEquals(1, carte.getAventuriers().size(), "La liste des aventuriers devrait contenir 1 aventurier après ajout");
        assertEquals(aventurier, carte.getAventuriers().get(0), "Le premier aventurier dans la liste devrait être celui que nous avons ajouté");
    }

    /**
     * Vérifie que l'on peut réinitialiser la liste des aventuriers.
     */
    @Test
    void testSetAventuriers() {
        List<Aventurier> nouveauxAventuriers = new ArrayList<>();
        nouveauxAventuriers.add(new Aventurier("A1", 2, 2, Orientation.SUD, "G"));
        carte.setAventuriers(nouveauxAventuriers);
        assertEquals(1, carte.getAventuriers().size(), "La liste des aventuriers devrait contenir 1 aventurier après réinitialisation");
        assertEquals("A1", carte.getAventuriers().get(0).getNom(), "Le nom du premier aventurier devrait être 'Indiana'");
    }

    /**
     * Vérifie que la carte peut contenir des cases.
     */
    @Test
    void testCasesInitiales() {
        Case[][] cases = carte.getCases();
        for (int i = 0; i < carte.getHauteur(); i++) {
            for (int j = 0; j < carte.getLargeur(); j++) {
                assertNull(cases[i][j], "Chaque case de la carte devrait être null au départ");
            }
        }
    }

    /**
     * Vérifie que la carte a la bonne taille pour les cases.
     */
    @Test
    void testTailleDesCases() {
        assertEquals(4, carte.getCases().length, "Le tableau des cases devrait avoir 4 lignes");
        assertEquals(5, carte.getCases()[0].length, "Le tableau des cases devrait avoir 5 colonnes");
    }

    /**
     * Vérifie que l'ajout d'un aventurier augmente la taille de la liste.
     */
    @Test
    void testTailleListeAventuriersApresAjout() {
        Aventurier aventurier1 = new Aventurier("A2", 1, 1, Orientation.NORD, "DGD");
        Aventurier aventurier2 = new Aventurier("A3", 2, 2, Orientation.SUD, "G");

        carte.getAventuriers().add(aventurier1);
        assertEquals(1, carte.getAventuriers().size(), "La liste des aventuriers devrait contenir 1 aventurier après ajout");

        carte.getAventuriers().add(aventurier2);
        assertEquals(2, carte.getAventuriers().size(), "La liste des aventuriers devrait contenir 2 aventuriers après ajout");
    }

    /**
     * Vérifie que la carte peut être réinitialisée avec de nouvelles dimensions.
     */
    @Test
    void testReinitialiserCarte() {
        carte = new Carte(10, 8);
        assertEquals(10, carte.getLargeur(), "La largeur de la nouvelle carte devrait être 10");
        assertEquals(8, carte.getHauteur(), "La hauteur de la nouvelle carte devrait être 8");
    }
}