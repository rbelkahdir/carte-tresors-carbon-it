package fr.carbon.model;

/**
 * Représente les différents types de cases sur la carte.
 *
 * Ces types seront utilisés dans le cadre du pattern Factory pour créer des instances
 * spécifiques de cases en fonction de leur type.
 */
public enum CaseType {
    /**
     * Représente une {@link Montagne}
     */
    MONTAGNE,
    /**
     * Représente un {@link Tresor}
     */
    TRESOR,
    /**
     * Représente une {@link Plaine}
     */
    PLAINE
}
