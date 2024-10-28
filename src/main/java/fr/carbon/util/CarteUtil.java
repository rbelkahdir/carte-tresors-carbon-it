package fr.carbon.util;

import fr.carbon.model.Carte;
import fr.carbon.model.CaseType;
import fr.carbon.pattern.factory.CaseFactory;

/**
 * Utilitaire pour les opérations de vérification des positions sur la carte.
 */
public class CarteUtil {

    /**
     * Vérifie si une position est dans les limites de la carte.
     *
     * @param x La coordonnée X à vérifier.
     * @param y La coordonnée Y à vérifier.
     * @param carte La carte sur laquelle la position est vérifiée.
     * @return {@code true} si la position est valide, sinon {@code false}.
     */
    public static boolean isPositionValide(int x, int y, Carte carte) {
        return x >= 0
                && x < carte.getLargeur()
                && y >= 0
                && y < carte.getHauteur();
    }

    /**
     * Initialise toutes les cases de la carte en tant que plaines.
     */
    public static Carte initialiserCases(Carte carte) {
        for (int i = 0; i < carte.getHauteur(); i++) {
            for (int j = 0; j < carte.getLargeur(); j++) {
                carte.getCases()[i][j] = CaseFactory.createCase(CaseType.PLAINE, j, i);
            }
        }
        return carte;
    }

}
