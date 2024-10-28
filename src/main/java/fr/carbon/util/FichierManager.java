package fr.carbon.util;

import fr.carbon.model.*;
import fr.carbon.pattern.factory.CaseFactory;
import fr.carbon.service.CarteService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FichierManager {
    /**
     * Lit le fichier d'entrée et crée une carte en fonction des informations lues.
     *
     * @param chemin Le chemin du fichier d'entrée.
     * @return Une instance de {@link Carte} configurée.
     * @throws IOException en cas d'erreur de lecture du fichier.
     */

    public static Optional<Carte> lireFichier(String chemin) throws IOException {
        try (InputStream inputStream = FichierManager.class.getClassLoader().getResourceAsStream(chemin)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Fichier non trouvé : " + chemin);
            }

            return Optional.ofNullable(lireCarte(inputStream));
        }
    }

    private static Carte lireCarte(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CarteService carteService = new CarteService();
        Carte carte = null;
        List<Aventurier> aventuriers = new ArrayList<>();
        String ligne;

        while ((ligne = reader.readLine()) != null) {
            if (ligne.startsWith("#")) continue; // Ignore les commentaires
            String[] tokens = ligne.split(" - ");
            switch (tokens[0]) {
                case "C" -> {
                    carte = carteService.creerCarte(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                }
                case "M" -> {
                    Montagne montagne = (Montagne) CaseFactory.createCase(CaseType.MONTAGNE, Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    carte = carteService.ajouterMontagne(montagne, carte);
                }
                case "T" -> {
                    Tresor tresor = (Tresor) CaseFactory.createCase(CaseType.TRESOR, Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                    carte = carteService.ajouterTresor(tresor, carte);
                }
                case "A" -> {
                    Aventurier aventurier = new Aventurier(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]),
                            Orientation.fromChar(tokens[4].charAt(0)), tokens[5]);
                    carte.getCases()[aventurier.getY()][aventurier.getX()] = aventurier;
                    aventuriers.add(aventurier);
                }
            }
        }

        if (carte != null) {
            carte.setAventuriers(aventuriers);
        }

        return carte;
    }

    private static void traiterLigne(String ligne, List<Aventurier> aventuriers, Carte carte) {


    }

    /**
     * Écrit l'état final de la carte et des aventuriers dans un fichier de sortie.
     *
     * @param carte  La carte avec l'état final des aventuriers et des trésors.
     * @param chemin Le chemin du fichier de sortie.
     * @throws IOException en cas d'erreur d'écriture dans le fichier.
     */
    public static void ecrireFichier(Carte carte, String chemin) throws IOException {
        // Créer le chemin du fichier et s'assurer que les répertoires existent
        Path path = Paths.get(chemin);
        Files.createDirectories(path.getParent()); // Crée les répertoires parents si nécessaires

        // Écrire dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            writer.write("C - " + carte.getLargeur() + " - " + carte.getHauteur() + " \n");
            writer.write(afficherListeMontagne(carte) + " \n");
            writer.write(afficherListeTresor(carte) + " \n");

            carte.getAventuriers().forEach(aventurier -> {
                try {
                    writer.write(String.format("A - %s - %d - %d - %s - %d\n",
                            aventurier.getNom(),
                            aventurier.getX(),
                            aventurier.getY(),
                            aventurier.getOrientation(),
                            aventurier.getTresorsCollectes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static String afficherListeMontagne(Carte carte) {
        // Récupérer les coordonnées des montagnes
        return Arrays.stream(carte.getCases())
                .flatMap(Arrays::stream) // Aplatir le tableau 2D
                .filter(c -> c instanceof Montagne)
                .map(c -> {
                    Montagne montagne = (Montagne) c; // Cast vers Montagne
                    return String.format("M - %d - %d", montagne.getX(), montagne.getY());
                })
                .collect(Collectors.joining("\n"));
    }

    /**
     * Récupérer les coordonnées des trésors
     */

    public static String afficherListeTresor(Carte carte) {
        return Arrays.stream(carte.getCases())
                .flatMap(Arrays::stream) // Aplatir le tableau 2D
                .filter(c -> c instanceof Tresor)
                .map(c -> {
                    Tresor tresor = (Tresor) c; // Cast vers Tresor
                    return String.format("T - %d - %d - %d", tresor.getX(), tresor.getY(), tresor.getQuantite());
                })
                .collect(Collectors.joining("\n"));
    }
}