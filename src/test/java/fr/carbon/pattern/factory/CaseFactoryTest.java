package fr.carbon.pattern.factory;

import fr.carbon.model.Case;
import fr.carbon.model.Montagne;
import fr.carbon.model.Plaine;
import fr.carbon.model.Tresor;
import fr.carbon.model.CaseType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la fabrique de cases {@link CaseFactory}.
 */
public class CaseFactoryTest {

    @Test
    void testCreateMontagne() {
        // Test de la création d'une montagne
        Case montagne = CaseFactory.createCase(CaseType.MONTAGNE, 2, 3);
        assertNotNull(montagne, "La case créée ne devrait pas être nulle");
        assertTrue(montagne instanceof Montagne, "La case devrait être une instance de Montagne");
        assertEquals(2, montagne.getX(), "La coordonnée X devrait être égale à 2");
        assertEquals(3, montagne.getY(), "La coordonnée Y devrait être égale à 3");
    }

    @Test
    void testCreateTresor() {
        // Test de la création d'un trésor
        Case tresor = CaseFactory.createCase(CaseType.TRESOR, 1, 1, 5);
        assertNotNull(tresor, "La case créée ne devrait pas être nulle");
        assertTrue(tresor instanceof Tresor, "La case devrait être une instance de Tresor");
        assertEquals(1, tresor.getX(), "La coordonnée X devrait être égale à 1");
        assertEquals(1, tresor.getY(), "La coordonnée Y devrait être égale à 1");
        assertEquals(5, ((Tresor) tresor).getQuantite(), "La quantité de trésor devrait être de 5");
    }

    @Test
    void testCreatePlaine() {
        // Test de la création d'une plaine
        Case plaine = CaseFactory.createCase(CaseType.PLAINE, 0, 0);
        assertNotNull(plaine, "La case créée ne devrait pas être nulle");
        assertTrue(plaine instanceof Plaine, "La case devrait être une instance de Plaine");
        assertEquals(0, plaine.getX(), "La coordonnée X devrait être égale à 0");
        assertEquals(0, plaine.getY(), "La coordonnée Y devrait être égale à 0");
    }

    @Test
    void testEnumCoverage() {
        // Test pour couvrir toutes les valeurs de l'enum CaseType
        for (CaseType type : CaseType.values()) {
            Case caseResult = CaseFactory.createCase(type, 1, 1, 1);
            assertNotNull(caseResult, "La case créée ne devrait pas être nulle pour le type " + type);
        }
    }
}
