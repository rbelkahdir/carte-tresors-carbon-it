package fr.carbon.util;

import fr.carbon.model.*;
import fr.carbon.pattern.factory.CaseFactory;
import fr.carbon.service.CarteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FichierManagerTest {
    CarteService carteService;

    private static final String CHEMIN_FICHIER_INPUT_TEST = "in/input.txt";
    private static final String CHEMIN_FICHIER_OUTPUT_TEST = "src/test/resources/out/output.txt";

    @BeforeEach
    void setUp() {
        carteService = new CarteService();
    }
    /**
     * Vérifie que le fichier est correctement lu et que la carte est créée.
     */
    @Test
    void testLireFichier() throws IOException {
        Optional<Carte> carteOptional = FichierManager.lireFichier(CHEMIN_FICHIER_INPUT_TEST);
        assertTrue(carteOptional.isPresent(), "La carte devrait être présente après la lecture du fichier");

        Carte carte = carteOptional.get();
        assertEquals(3, carte.getLargeur(), "La largeur de la carte devrait être 5");
        assertEquals(4, carte.getHauteur(), "La hauteur de la carte devrait être 5");
        assertTrue(carte.getCases()[0][1].isOccupe(), "La case (0, 1) devrait être occupée par une montagne");
        assertTrue(carte.getCases()[1][1].isOccupe(), "La case (1, 1) ne devrait pas être occupée par un aventurier");
    }

    /**
     * Vérifie que l'écriture dans un fichier fonctionne correctement.
     */
    @Test
    void testEcrireFichier() throws IOException {
        Carte carte = new Carte(5, 5);
        carte = CarteUtil.initialiserCases(carte);

        carteService.ajouterMontagne((Montagne) CaseFactory.createCase(CaseType.MONTAGNE, 2, 3), carte); // Ajout d'une montagne

        carteService.ajouterTresor((Tresor) CaseFactory.createCase(CaseType.TRESOR, 1, 0, 3), carte); // Ajout d'un trésor
        carteService.ajouterAventurier(new Aventurier("A1", 0, 0, Orientation.EST, "ADAGGA"), carte);

        FichierManager.ecrireFichier(carte, CHEMIN_FICHIER_OUTPUT_TEST);
        String contenu = Files.readString(Path.of(CHEMIN_FICHIER_OUTPUT_TEST));
        assertTrue(contenu.contains("C - 5 - 5"), "Le contenu du fichier devrait inclure les dimensions de la carte");
        assertTrue(contenu.contains("M - 2 - 3"), "Le contenu du fichier devrait inclure le montagne ajouté");
        assertTrue(contenu.contains("T - 1 - 0 - 3"), "Le contenu du fichier devrait inclure le trésor ajouté");
    }
}