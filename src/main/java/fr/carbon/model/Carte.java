package fr.carbon.model;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    /**
     * Nb. de case en largeur
     */
    private int largeur;
    /**
     * Nb. de case en hauteur
     */
    private int hauteur;
    /**
     * La liste des cases sur la carte
     */
    private Case[][] cases;
    /**
     * Liste de aventurier sur la carte
     */
    private List<Aventurier> aventuriers;

    public Carte(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.cases = new Case[hauteur][largeur];
        aventuriers = new ArrayList<>();
    }

    /*
     * Getters et Setters
     */

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public Case[][] getCases() {
        return cases;
    }

    public List<Aventurier> getAventuriers() {
        return aventuriers;
    }

    public void setAventuriers(List<Aventurier> aventuriers) {
        this.aventuriers = aventuriers;
    }
}
