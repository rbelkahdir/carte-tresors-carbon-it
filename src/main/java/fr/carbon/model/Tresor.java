package fr.carbon.model;

/**
 * Représente une case de type trésor sur la carte.
 */
public class Tresor extends Case {

    /**
     * Le nombre de trésors présents dans cette case.
     * */
    private int quantite;

    public Tresor(int x, int y, int quantite) {
        super(x, y, false);  // La case n'est pas occupée
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    /**
     * Décrémente de 1 le nombre de trésors disponibles dans cette case.
     * Si la quantité est déjà à 0, cette méthode ne fait rien.
     */
    public void decrementerQuantite() {
        if (quantite > 0) {
            quantite--;
        }
    }
}