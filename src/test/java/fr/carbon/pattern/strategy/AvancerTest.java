package fr.carbon.pattern.strategy;

import fr.carbon.model.*;
import fr.carbon.pattern.factory.CaseFactory;
import fr.carbon.util.CarteUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvancerTest {

    private Carte carte;
    private Aventurier aventurier;
    private MouvementStrategy strategieAvancer;

    @BeforeEach
    void setUp() {
        carte = new Carte(5, 5);
        CarteUtil.initialiserCases(carte);
        aventurier = new Aventurier("A1", 2, 2, Orientation.NORD, "A");
        strategieAvancer = new Avancer();
    }

    /**
     * Vérifie que l'aventurier peut se déplacer vers une case vide (plaine).
     */
    @Test
    void testDeplacerVersCaseVide() {
        carte.getCases()[0][2] = CaseFactory.createCase(CaseType.PLAINE, 0, 2); // Case vide
        carte.getCases()[1][2] = CaseFactory.createCase(CaseType.PLAINE, 1, 2); // Case vide

        strategieAvancer.deplacer(aventurier, carte);
        assertEquals(2, aventurier.getX(), "L'aventurier ne devrait pas se déplacer vers le nord");
        assertEquals(1, aventurier.getY(), "L'aventurier devrait se déplacer d'une case vers le nord");
    }

    /**
     * Vérifie que l'aventurier ne peut pas se déplacer hors des limites de la carte.
     */
    @Test
    void testDeplacerHorsLimites() {
        aventurier.setY(0); // Position à la limite supérieure
        carte.getCases()[0][2] = CaseFactory.createCase(CaseType.PLAINE, 0, 2); // Case vide

        strategieAvancer.deplacer(aventurier, carte);
        assertEquals(0, aventurier.getY(), "L'aventurier ne devrait pas avancer");
    }

    /**
     * Vérifie que l'aventurier ne peut pas se déplacer vers une case non franchissable (montagne).
     */
    @Test
    void testDeplacerVersCaseNonFranchissable() {
        carte.getCases()[1][2] = CaseFactory.createCase(CaseType.MONTAGNE, 2, 1); // Case montagne
        strategieAvancer.deplacer(aventurier, carte);
        assertEquals(2, aventurier.getY(), "L'aventurier devrait rester à sa position actuelle");
    }

    /**
     * Vérifie que l'aventurier collecte un trésor lorsqu'il se déplace sur une case contenant un trésor.
     */
    @Test
    void testCollecterTresor() {
        Tresor tresor = (Tresor) CaseFactory.createCase(CaseType.TRESOR, 2, 1,1); // Crée un trésor avec 1 unité
        carte.getCases()[1][2] = tresor;
        aventurier.setOrientation(Orientation.EST);
        aventurier.setX(1);
        aventurier.setY(1);

        strategieAvancer.deplacer(aventurier, carte);
        assertEquals(1, aventurier.getTresorsCollectes(), "L'aventurier devrait avoir collecté 1 trésor");
        assertEquals(0, tresor.getQuantite(), "La quantité de trésor sur la case devrait être 0 après collecte");
    }

    /**
     * Vérifie que l'aventurier ne collecte pas de trésor si la case est vide.
     */
    @Test
    void testNePasCollecterTresorSurCaseVide() {
        aventurier.setOrientation(Orientation.EST);
        aventurier.setX(1);
        aventurier.setY(1);

        strategieAvancer.deplacer(aventurier, carte);
        assertEquals(0, aventurier.getTresorsCollectes(), "L'aventurier ne devrait pas avoir collecté de trésor");
    }

    /**
     * Vérifie que l'aventurier ne collecte pas de trésor si la case est occuper par un aventurier.
     */
    @Test
    void testNePasCollecterTresorSurCaseOcupperParAventurier() {
        Tresor tresor = (Tresor) CaseFactory.createCase(CaseType.TRESOR, 2, 1,1);
        Aventurier aventurier = new Aventurier("A2", 2, 1, Orientation.NORD, "AGD");
        aventurier.setOrientation(Orientation.EST);
        aventurier.setX(1);
        aventurier.setY(1);

        strategieAvancer.deplacer(aventurier, carte);
        assertEquals(0, aventurier.getTresorsCollectes(), "L'aventurier ne devrait pas avoir collecté de trésor");
    }
}