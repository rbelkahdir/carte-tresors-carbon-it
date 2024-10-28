package fr.carbon.pattern.factory;

import fr.carbon.model.Case;
import fr.carbon.model.Montagne;
import fr.carbon.model.Plaine;
import fr.carbon.model.Tresor;
import fr.carbon.model.CaseType;

/**
 * Factory pour créer des instances de différentes types de cases.
 */
public class CaseFactory {

    /**
     * Crée une case en fonction du type {@link CaseType}
     *
     * @param type Type de la case {@link CaseType}.
     * @param x Coordonnée X de la case.
     * @param y Coordonnée Y de la case.
     * @return Instance de {@link Case}.
     */
    public static Case createCase(CaseType type, int x, int y) {
        return createCase(type, x, y, 0);
    }

    /**
     * Crée une case en fonction du type {@link CaseType}.
     *
     * @param type Type de la case {@link CaseType}.
     * @param x Coordonnée X de la case.
     * @param y Coordonnée Y de la case.
     * @param quantite Quantité de trésor (Pour les cases Trésor).
     * @return Instance de {@link Case}.
     */
    public static Case createCase(CaseType type, int x, int y, int quantite) {
        return switch (type) {
            case MONTAGNE -> new Montagne(x, y);
            case TRESOR -> new Tresor(x, y, quantite);
            case PLAINE -> new Plaine(x, y);
        };
    }
}
