package fr.carbon.service;

import fr.carbon.model.Aventurier;
import fr.carbon.model.Carte;
import fr.carbon.model.CaseType;
import fr.carbon.model.Orientation;
import fr.carbon.pattern.factory.CaseFactory;
import fr.carbon.pattern.strategy.Avancer;
import fr.carbon.pattern.strategy.MouvementStrategy;
import fr.carbon.util.CarteUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AventurierServiceTest {

    private Aventurier aventurier;
    private Carte carte;
    private AventurierService service;

    @BeforeEach
    void setUp() {
        aventurier = new Aventurier("A1", 0, 0, Orientation.NORD, "ADAA");
        carte = new Carte(5, 5);
        CarteUtil.initialiserCases(carte);
        service = new AventurierService(aventurier);
    }

    /**
     * Vérifie que l'aventurier se déplace sur la carte selon sa séquence de mouvements.
     */
    @Test
    void testDeplacer() {
        service.deplacer(carte);
        assertEquals(2, aventurier.getX(), "L'aventurier devrait se déplacer vers la case (1, 0)");
        assertEquals(0, aventurier.getY(), "L'aventurier devrait rester à la même ligne après le déplacement");
    }

    /**
     * Vérifie la gestion des mouvements invalides.
     */
    @Test
    void testMouvementInvalide() {
        aventurier.setMouvements("Z"); // Mouvement invalide
        assertThrows(IllegalArgumentException.class, () -> service.deplacer(carte), "Une exception devrait être levée pour un mouvement invalide");
    }
}