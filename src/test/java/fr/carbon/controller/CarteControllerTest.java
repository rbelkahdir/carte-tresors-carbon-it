package fr.carbon.controller;

import fr.carbon.model.*;
import fr.carbon.pattern.factory.CaseFactory;
import fr.carbon.service.CarteService;
import fr.carbon.util.CarteUtil;
import fr.carbon.util.FichierManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarteControllerTest {

    private Carte carte;
    private CarteService carteService;
    private CarteController carteController;
    private FichierManager fichierManager;

    /**
     * Initialisation de la carte et du controller pour chaque test.
     */
    @BeforeEach
    public void setUp() {
        carte = new Carte(3, 3);  // Exemple de carte 3x3
        CarteUtil.initialiserCases(carte);
        fichierManager = new FichierManager();
        carteService = new CarteService();
        carteController = new CarteController(carte);
    }

    /**
     * Vérifie que chaque aventurier est correctement déplacé selon sa séquence de mouvements
     * et que le fichier de sortie est généré avec succès.
     *
     * @throws IOException si une erreur d'écriture de fichier survient
     */
    @Test
    public void testExecuterAvecAventuriers() throws IOException {
        Aventurier aventurier1 = new Aventurier("A1", 0, 0, Orientation.SUD, "AAD");
        Aventurier aventurier2 = new Aventurier("A2", 1, 1, Orientation.NORD, "AGG");
        carteService.ajouterAventurier(aventurier1, carte);
        carteService.ajouterAventurier(aventurier2, carte);

        carteController.executer();

        assertEquals(0, aventurier1.getX(), "L'axe X de aventurier1 devrait être 0 après le déplacement.");
        assertEquals(2, aventurier1.getY(), "L'axe Y de aventurier1 devrait être 2 après un déplacement de 2 cases vers le sud.");
        assertEquals(Orientation.OUEST, aventurier1.getOrientation(), "L'orientation de l'aventurier 1 devrait être OUEST après rotation.");

        assertEquals(1, aventurier2.getX(), "L'axe X de aventurier2 devrait être 1 après le déplacement.");
        assertEquals(0, aventurier2.getY(), "L'axe Y de aventurier2 devrait être 0 après le déplacement avec rotation.");

        Path outputPath = Path.of("src/test/resources/output.txt");
        assertTrue(Files.exists(outputPath), "Le fichier de sortie devrait avoir été créé.");
        Files.deleteIfExists(outputPath); // Nettoyage après test
    }

    /**
     * Teste l'exécution de la simulation sans aventuriers sur la carte.
     * Vérifie que le fichier de sortie est généré même sans aventuriers
     * et que la liste d'aventuriers de la carte reste vide.
     *
     * @throws IOException si une erreur d'écriture de fichier survient
     */
    @Test
    public void testExecuterSansAventuriers() throws IOException {
        carteController.executer();

        assertTrue(carte.getAventuriers().isEmpty(), "La liste des aventuriers sur la carte devrait être vide.");

        Path outputPath = Path.of("src/test/resources/output.txt");
        assertTrue(Files.exists(outputPath), "Le fichier de sortie devrait avoir été créé même sans aventuriers.");
        Files.deleteIfExists(outputPath);
    }

    /**
     * Teste le comportement des déplacements des aventuriers face à des obstacles.
     * Place un aventurier devant une montagne et vérifie qu'il ne peut pas avancer
     * à travers l'obstacle.
     *
     * @throws IOException si une erreur d'écriture de fichier survient
     */
    @Test
    public void testDeplacementsAventurierAvecObstacle() throws IOException {
        carteService.ajouterMontagne((Montagne) CaseFactory.createCase(CaseType.MONTAGNE, 0, 1), carte);
        Aventurier aventurier = new Aventurier("AventurierObstacle", 0, 0, Orientation.SUD, "AA");
        carteService.ajouterAventurier(aventurier, carte);

        carteController.executer();

        assertEquals(0, aventurier.getX(), "L'axe X de l'aventurier devrait rester à 0 car le mouvement est bloqué par une montagne.");
        assertEquals(0, aventurier.getY(), "L'axe Y de l'aventurier devrait rester à 0 car le mouvement est bloqué par une montagne.");
    }

    /**
     * Teste l'écriture dans le fichier de sortie après les déplacements.
     * Vérifie que le fichier contient la position finale correcte des aventuriers
     * après leur séquence de déplacements.
     *
     * @throws IOException si une erreur d'écriture de fichier survient
     */
    @Test
    public void testEcritureFichierApresDeplacements() throws IOException {
        Aventurier aventurier = new Aventurier("AventurierFichier", 2, 2, Orientation.OUEST, "ADA");
        carteService.ajouterAventurier(aventurier, carte);

        carteController.executer();

        Path outputPath = Path.of("src/test/resources/output.txt");
        assertTrue(Files.exists(outputPath), "Le fichier de sortie devrait avoir été créé après les déplacements.");

        List<String> lignes = Files.readAllLines(outputPath);
        assertTrue(lignes.stream().anyMatch(ligne -> ligne.contains("AventurierFichier") && ligne.contains("1 - 1 - NORD - 0")),
                "Le fichier de sortie devrait contenir la position finale de l'aventurier avec les bonnes coordonnées et orientation.");

        Files.deleteIfExists(outputPath);
    }
}