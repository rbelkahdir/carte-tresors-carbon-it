package fr.carbon.service;

import fr.carbon.model.Aventurier;
import fr.carbon.model.Carte;
import fr.carbon.pattern.strategy.Avancer;
import fr.carbon.pattern.strategy.MouvementStrategy;
import fr.carbon.pattern.strategy.TournerDroite;
import fr.carbon.pattern.strategy.TournerGauche;

import java.util.HashMap;
import java.util.Map;

/**
 * Service pour gérer les déplacements des aventuriers sur la carte.
 */
public class AventurierService {
    private final Aventurier aventurier;
    private final Map<Character, MouvementStrategy> mouvements;

    public AventurierService(Aventurier aventurier) {
        this.aventurier = aventurier;
        this.mouvements = new HashMap<>();
        mouvements.put('A', new Avancer());
        mouvements.put('G', new TournerGauche());
        mouvements.put('D', new TournerDroite());
    }

    /**
     * Exécute les mouvements de l'aventurier en fonction de sa séquence de mouvements.
     *
     * @param carte La carte sur laquelle se déplace l'aventurier.
     */
    public void deplacer(Carte carte) {
        if (aventurier.getMouvements() == null) return;

        aventurier.getMouvements().chars().forEach(mouvement -> {
            MouvementStrategy strategy = mouvements.get((char) mouvement);
            if (strategy != null) {
                strategy.deplacer(aventurier, carte);
            } else {
                throw new IllegalArgumentException("Mouvement invalide : " + (char) mouvement);
            }
        });
    }
}
