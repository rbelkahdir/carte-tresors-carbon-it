package fr.carbon.pattern.strategy;

import fr.carbon.model.Aventurier;
import fr.carbon.model.Carte;

/**
 * Interface représentant une stratégie de mouvement pour un aventurier.
 */
public interface MouvementStrategy {
    /**
     * Déplace l'aventurier en fonction de la stratégie de mouvement spécifique.
     *
     * @param aventurier l'aventurier à déplacer
     * @param carte la carte sur laquelle se trouve l'aventurier
     */
    void deplacer(Aventurier aventurier, Carte carte);
}