package fr.carbon.model;

/**
 * Représente les différentes orientations possibles.
 */
public enum Orientation {
    NORD,
    SUD,
    EST,
    OUEST;

    /**
     * Tourne l'orientation actuelle de 90° vers la gauche.
     *
     * @return @{@link Orientation} la nouvelle orientation après un tour à gauche
     */
    public Orientation tournerGauche() {
        return switch (this) {
            case NORD -> OUEST;
            case OUEST -> SUD;
            case SUD -> EST;
            case EST -> NORD;
            default -> this;
        };
    }

    /**
     * Tourne l'orientation actuelle de 90° vers la droite.
     *
     * @return {@link Orientation} la nouvelle orientation après un tour à droite
     */
    public Orientation tournerDroite() {
        return switch (this) {
            case NORD -> EST;
            case EST -> SUD;
            case SUD -> OUEST;
            case OUEST -> NORD;
            default -> this;
        };
    }

    /**
     * Renvoie l'orientation correspondante au caractère spécifié.
     *
     * @param c le caractère représentant l'orientation ('N', 'S', 'E', 'O')
     * @return {@link Orientation} l'orientation associée au caractère
     * @throws IllegalArgumentException si le caractère ne correspond à aucune orientation valide
     */
    public static Orientation fromChar(char c) {
        return switch (c) {
            case 'N' -> NORD;
            case 'S' -> SUD;
            case 'E' -> EST;
            case 'O' -> OUEST;
            default -> throw new IllegalArgumentException("Caractère d'orientation invalide : " + c);
        };
    }
}