package fr.carbon.model;

public class Aventurier extends Case {
    /**
     * Nom de l’aventurier
     */
    private String nom;
    /**
     * Orientation de l'aventurier
     */
    private Orientation orientation;
    /**
     * Séquence de mouvement de l'aventurier
     */
    private String mouvements;
    /**
     * Nb. trésors ramassés par l'aventurier
     */
    private int tresorsCollectes = 0;

    public Aventurier(String nom, int x, int y, Orientation orientation, String mouvements) {
        super(x, y, true);
        this.nom = nom;
        this.orientation = orientation;
        this.mouvements = mouvements;
    }

    /**
     * Incrémente le nombre de trésors collectés par l'aventurier.
     */
    public void incrementerTresorsCollectes() {
        tresorsCollectes++;
    }

    /*
     * Getters et Setters
     */

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String getMouvements() {
        return mouvements;
    }

    public void setMouvements(String mouvements) {
        this.mouvements = mouvements;
    }

    public int getTresorsCollectes() {
        return tresorsCollectes;
    }

    public void setTresorsCollectes(int tresorsCollectes) {
        this.tresorsCollectes = tresorsCollectes;
    }
}
