package fr.carbon.service;

import fr.carbon.exception.PositionInvalideException;
import fr.carbon.model.*;
import fr.carbon.pattern.factory.CaseFactory;
import fr.carbon.util.CarteUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarteServiceTest {

    private CarteService carteService;
    private Carte carte;

    @BeforeEach
    void setUp() {
        carteService = new CarteService();
        carte = carteService.creerCarte(5, 5);
    }

    /**
     * Vérifie que la carte est correctement créée avec les dimensions spécifiées.
     */
    @Test
    void testCreerCarte() {
        assertEquals(5, carte.getLargeur(), "La largeur de la carte devrait être 5");
        assertEquals(5, carte.getHauteur(), "La hauteur de la carte devrait être 5");
    }

    /**
     * Vérifie l'ajout d'une montagne à la carte.
     */
    @Test
    void testAjouterMontagne() {
        Montagne montagne = (Montagne) CaseFactory.createCase(CaseType.MONTAGNE, 1, 1);
        carteService.ajouterMontagne(montagne, carte);

        assertTrue(carte.getCases()[1][1].isOccupe(), "La case (1, 1) devrait être occupée par une montagne");
    }

    /**
     * Vérifie que l'ajout d'une montagne à une position invalide lance une exception.
     */
    @Test
    void testAjouterMontagnePositionInvalide() {
        Montagne montagne = (Montagne) CaseFactory.createCase(CaseType.MONTAGNE, 5, 5); // Position invalide
        assertThrows(PositionInvalideException.class, () -> carteService.ajouterMontagne(montagne, carte), "Une exception devrait être levée pour une position invalide");
    }

    /**
     * Vérifie l'ajout d'un trésor à la carte.
     */
    @Test
    void testAjouterTresor() {
        Tresor tresor = (Tresor) CaseFactory.createCase(CaseType.TRESOR, 2, 2, 5);
        carteService.ajouterTresor(tresor, carte);

        assertFalse(carte.getCases()[2][2].isOccupe(), "La case (2, 2) ne devrait pas être occupée");
    }

    /**
     * Vérifie que l'ajout d'un trésor à une position invalide lance une exception.
     */
    @Test
    void testAjouterTresorPositionInvalide() {
        Tresor tresor = (Tresor) CaseFactory.createCase(CaseType.TRESOR, 5, 5, 5); // Position invalide
        assertThrows(PositionInvalideException.class, () -> carteService.ajouterTresor(tresor, carte), "Une exception devrait être levée pour une position invalide");
    }
}