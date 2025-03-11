package exo1;

// Classe Voiture améliorée avec encapsulation
public class Voiture {
    // Attributs privés
    private String marque;
    private String modele;
    private String couleur;

    // Constructeur
    public Voiture(String marque, String modele, String couleur) {
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
    }

    // Getters et Setters
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    // Méthodes
    public void demarrer() {
        System.out.println("La " + marque + " " + modele + " de couleur " + couleur + " démarre.");
    }

    public void accelerer() {
        System.out.println("La " + marque + " " + modele + " accélère.");
    }

    public void freiner() {
        System.out.println("La " + marque + " " + modele + " freine.");
    }
}
