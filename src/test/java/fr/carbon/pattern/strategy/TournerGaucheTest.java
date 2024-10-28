package fr.carbon.pattern.strategy;

import fr.carbon.model.Aventurier;
import fr.carbon.model.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TournerGaucheTest {

    private Aventurier aventurier;
    private MouvementStrategy strategieTournerGauche;

    @BeforeEach
    void setUp() {
        aventurier = new Aventurier("Lara", 0, 0, Orientation.NORD, "D");
        strategieTournerGauche = new TournerGauche();
    }

    /**
     * Vérifie que l'aventurier tourne correctement vers la gauche.
     */
    @Test
    void testTournerGauche() {
        strategieTournerGauche.deplacer(aventurier, null);
        assertEquals(Orientation.OUEST, aventurier.getOrientation(), "L'aventurier devrait être orienté vers l'ouest après avoir tourné à gauche");
    }

    /**
     * Vérifie que l'aventurier tourne correctement vers la gauche plusieurs fois.
     */
    @Test
    void testTournerGaucheMultiple() {
        for (int i = 0; i < 3; i++) {
            strategieTournerGauche.deplacer(aventurier, null);
        }
        assertEquals(Orientation.EST, aventurier.getOrientation(), "L'aventurier devrait revenir à l'orientation nord après quatre tours à gauche");
    }
}