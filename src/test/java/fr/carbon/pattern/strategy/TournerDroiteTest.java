package fr.carbon.pattern.strategy;

import fr.carbon.model.Aventurier;
import fr.carbon.model.Carte;
import fr.carbon.model.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TournerDroiteTest {

    private Aventurier aventurier;
    private MouvementStrategy strategieTournerDroite;

    @BeforeEach
    void setUp() {
        aventurier = new Aventurier("Lara", 0, 0, Orientation.NORD, "D");
        strategieTournerDroite = new TournerDroite();
    }

    /**
     * Vérifie que l'aventurier tourne correctement vers la droite.
     */
    @Test
    void testTournerDroite() {
        strategieTournerDroite.deplacer(aventurier, null);
        assertEquals(Orientation.EST, aventurier.getOrientation(), "L'aventurier devrait être orienté vers l'est après avoir tourné à droite");
    }

    /**
     * Vérifie que l'aventurier tourne correctement vers la droite plusieurs fois.
     */
    @Test
    void testTournerDroiteMultiple() {
        for (int i = 0; i < 3; i++) {
            strategieTournerDroite.deplacer(aventurier, null);
        }
        assertEquals(Orientation.OUEST, aventurier.getOrientation(), "L'aventurier devrait revenir à l'orientation nord après quatre tours à droite");
    }
}