package fr.carbon.pattern.strategy;

import fr.carbon.model.Aventurier;
import fr.carbon.model.Carte;
import fr.carbon.model.Tresor;
import fr.carbon.pattern.strategy.MouvementStrategy;
import fr.carbon.util.CarteUtil;

/**
 * Stratégie de mouvement pour faire avancer l'aventurier sur la carteService.
 */
public class Avancer implements MouvementStrategy {

    /**
     * Déplace l'aventurier d'une case dans la direction de son orientation actuelle.
     *
     * Si la case de destination contient un trésor, l'aventurier collecte un trésor
     * et la quantité de trésors sur cette case est décrémentée.
     * <p>
     * Les règles de déplacement :
     * <ul>
     *     <li>Si la position de destination est en dehors des limites de la carte, le déplacement est annulé.</li>
     *     <li>Si la case de destination est non franchissable, le déplacement est également annulé.</li>
     * </ul>
     * </p>
     *
     * @param aventurier L'aventurier à déplacer.
     * @param carte La carte sur laquelle l'aventurier se déplace.
     */
    @Override
    public void deplacer(Aventurier aventurier, Carte carte) {
        int x = aventurier.getX();
        int y = aventurier.getY();

        // Mise à jour de la position en fonction de l'orientation
        switch (aventurier.getOrientation()) {
            case NORD -> y--; // Déplacement vers le haut
            case SUD -> y++;  // Déplacement vers le bas
            case EST -> x++;  // Déplacement vers la droite
            case OUEST -> x--; // Déplacement vers la gauche
        }

        /*
         * Vérification des limites de la carte
         * Si hors limites annule le déplacement
         */
        if (!CarteUtil.isPositionValide(x, y, carte)) {
            return;
        }

        // Vérification si la case est franchissable (Ne présente pas un montagne ou un aventurier)
        if (!carte.getCases()[y][x].isOccupe()) {
            //Faire avancer l'aventurier vers les nouvelles coordonnées.
            aventurier.setX(x);
            aventurier.setY(y);

            // Collecter des trésors, si applicable
            if (carte.getCases()[y][x] instanceof Tresor) {
                Tresor tresor = (Tresor) carte.getCases()[y][x];
                if (tresor.getQuantite() > 0) {
                    aventurier.incrementerTresorsCollectes();
                    tresor.decrementerQuantite();
                }
            }
        }
    }
}
