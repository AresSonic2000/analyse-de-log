import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Mini-projet BTS CIEL — Surveillance d'un parc de machines
 * 
 *
 * Format d'une ligne de log :
 * date;heure;ip_source;ip_destination;login;statut
 * Exemple : 2025-01-15;08:20:11;192.168.1.12;10.10.0.10;admin;ECHEC
 *
 */
public class Squelette {

    static final String[] IPS_DESTINATION_CONNUES = {
            "10.10.0.1", "10.10.0.2", "10.10.0.10", "10.10.0.11",
            "10.10.0.20", "10.10.0.21", "10.10.0.30", "10.10.0.31"
    };

    static final String[] UTILISATEURS_CONNUS = {
            "mleroy", "jdupont", "admin", "scarre", "ppetit", "tnoel", "invite"
    };

       public static void main(String[] args) {        
        String cheminFichier = "D:\\Cours\\Matière professionelle\\Codage\\Programme coder\\Langage de programmation\\Codage Java\\BTS CIEL IR\\NetBeansProjects\\squelette\\src\\squelette\\logs_connexion.txt";
        lireFichierLogs(cheminFichier);

    }

    static void lireFichierLogs(String chemin) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            String[] champs;
            while ((ligne = lecteur.readLine()) != null) {
                champs= ligne.split(";");
                //Affichage
                
                for (String champ : champs) {
                    System.out.print(champ + " ");
                }
                System.out.println("");
                
            }
        } catch (IOException e) {
            System.out.println("Erreur : impossible de lire le fichier " + chemin);
            System.out.println("Détail : " + e.getMessage());
        }
    }

  
}