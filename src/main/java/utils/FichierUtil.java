package utils;

import exception.FichierException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FichierUtil {

    public static void enregistrerTransaction(String numeroCompte, String ligneTransaction) throws FichierException {
        String nomFichier = "releve_" + numeroCompte + ".txt";

        // Attention à la fermeture de la parenthèse du try juste avant l'accolade {
        try (FileWriter fw = new FileWriter(nomFichier, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(ligneTransaction);

        } catch (IOException e) {
            throw new FichierException("Erreur lors de l'écriture dans le fichier : " + nomFichier);
        }
    }

    public static void lireReleve(String numeroCompte) throws FichierException {
        String nomFichier = "releve_" + numeroCompte + ".txt";

        try (FileReader fr = new FileReader(nomFichier);
             BufferedReader reader = new BufferedReader(fr)) {

            System.out.println("=== RELEVÉ BANCAIRE DU COMPTE : " + numeroCompte + " ===");

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }

            System.out.println("=================================================");

        } catch (IOException e) {
            throw new FichierException("Impossible de lire le relevé bancaire pour le compte : " + numeroCompte);
        }
    }
}