package fr.carbon.model;

import fr.carbon.controller.CarteController;
import fr.carbon.util.FichierManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {
    private Carte carte;
    private FichierManager fichierManager;

    @BeforeEach
    public void setUp() throws IOException {
        fichierManager = new FichierManager();
        Optional<Carte> optionalCarte = fichierManager.lireFichier("in/input.txt");
        assertTrue(optionalCarte.isPresent(), "La carte doit être présente après la lecture du fichier.");
        carte = optionalCarte.get();
    }

    @Test
    public void testDeplacementAventurier() {
        CarteController simulation = new CarteController(carte);
        simulation.executer();
        // Assertions pour vérifier la position et le nombre de trésors collectés
    }

    // Autres tests...
}