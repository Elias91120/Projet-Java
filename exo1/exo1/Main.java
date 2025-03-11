package exo1;

// Classe Main pour tester la classe Voiture
public class Main {
    public static void main(String[] args) {
        // Création d'une voiture avec le constructeur
        Voiture voiture = new Voiture("Toyota", "Corolla", "Rouge");

        // Affichage des informations de la voiture
        System.out.println("Marque : " + voiture.getMarque());
        System.out.println("Modèle : " + voiture.getModele());
        System.out.println("Couleur : " + voiture.getCouleur());

        // Test des méthodes
        voiture.demarrer();
        voiture.accelerer();
        voiture.freiner();
    }
}
