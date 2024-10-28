package fr.carbon.pattern.strategy;

import fr.carbon.model.Aventurier;
import fr.carbon.model.Carte;

/**
 *  Stratégie de mouvement pour faire tourner l'aventurier vers la droite.
 */
public class TournerDroite implements MouvementStrategy {
    @Override
    public void deplacer(Aventurier aventurier, Carte carte) {
        aventurier.setOrientation(aventurier.getOrientation().tournerDroite());
    }
}