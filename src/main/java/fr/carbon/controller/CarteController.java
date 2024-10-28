package fr.carbon.controller;

import fr.carbon.model.Aventurier;
import fr.carbon.model.Carte;
import fr.carbon.service.AventurierService;
import fr.carbon.util.FichierManager;

import java.io.IOException;
import java.util.List;

public class CarteController {
    private final Carte carte;

    public CarteController(Carte carte) {
        this.carte = carte;
    }

    public void executer() {
        List<Aventurier> aventuriers = carte.getAventuriers();

        // Utilisation de streams pour déplacer les aventuriers
        aventuriers.forEach(aventurier -> {
            AventurierService aventurierService = new AventurierService(aventurier);
            aventurierService.deplacer(carte);
        });

        // Écriture des résultats dans un fichier
        try {
            FichierManager.ecrireFichier(carte, "src/test/resources/output.txt");
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture du fichier : " + e.getMessage(), e);
        }
    }
}