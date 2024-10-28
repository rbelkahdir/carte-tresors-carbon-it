package fr.carbon.service;

import fr.carbon.exception.PositionInvalideException;
import fr.carbon.model.*;
import fr.carbon.pattern.factory.CaseFactory;
import fr.carbon.util.CarteUtil;

/**
 * Service pour gérer la création et la configuration de la carte.
 */
public class CarteService {
    public CarteService() {}

    /**
     * Crée une nouvelle carte avec les dimensions spécifiées.
     *
     * @param largeur Largeur de la carte.
     * @param hauteur Hauteur de la carte.
     * @return La carte créée {@link Carte}.
     */
    public Carte creerCarte(int largeur, int hauteur) {
        Carte carte = new Carte(largeur, hauteur);
        CarteUtil.initialiserCases(carte);
        return carte;
    }

    /**
     * Ajoute une montagne à la carte.
     *
     * @param montagne La montagne à ajouter.
     * @throws PositionInvalideException si la position est en dehors des limites de la carte.
     */
    public Carte ajouterMontagne(Montagne montagne, Carte carte) {
        verifierPosition(montagne.getX(), montagne.getY(), carte);
        carte.getCases()[montagne.getY()][montagne.getX()] = montagne;
        return carte;
    }

    /**
     * Ajoute un trésor à la carte.
     *
     * @param tresor Le trésor à ajouter.
     * @throws PositionInvalideException si la position est en dehors des limites.
     */
    public Carte ajouterTresor(Tresor tresor, Carte carte) {
        verifierPosition(tresor.getX(), tresor.getY(), carte);
        carte.getCases()[tresor.getY()][tresor.getX()] = tresor;
        return carte;
    }

    /**
     * Ajoute un aventurier à la carte si la position est valide et libre.
     *
     * @param aventurier L'aventurier à ajouter.
     * @throws PositionInvalideException si la position est en dehors des limites de la carte.
     */
    public Carte ajouterAventurier(Aventurier aventurier, Carte carte) {
        int x = aventurier.getX();
        int y = aventurier.getY();

        verifierPosition(x, y, carte);
        if (!carte.getCases()[y][x].isOccupe()) {
            carte.getCases()[aventurier.getY()][aventurier.getX()] = aventurier;
            carte.getAventuriers().add(aventurier);
        }

        return carte;
    }

    /**
     * Vérifie que la position (x, y) est dans les limites de la carte.
     *
     * @param x La coordonnée x.
     * @param y La coordonnée y.
     * @throws PositionInvalideException si les coordonnées sont en dehors des limites de la carte.
     */
    private void verifierPosition(int x, int y, Carte carte) {
        if (!CarteUtil.isPositionValide(x, y, carte)) {
            throw new PositionInvalideException("Position hors limites de la carte : (" + x + ", " + y + ").");
        }
    }
}
