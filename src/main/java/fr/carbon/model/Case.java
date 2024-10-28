package fr.carbon.model;

/**
 * Représente une case sur la carte.
 */
public abstract class Case {
    /**
     * Axe horizontal de la case sur la carte
     */
    protected int x;
    /**
     * Axe vertical de la case sur la carte
     */
    protected int y;
    /**
     * Indique si la case est occupée (Ex: @link {@link Montagne}, @link {@link Aventurier})
     */
    protected boolean occupe;

    public Case(int x, int y, boolean occupe) {
        this.x = x;
        this.y = y;
        this.occupe = occupe;
    }

    /*
     * Getters et Setters
     */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isOccupe() {
        return occupe;
    }

}
