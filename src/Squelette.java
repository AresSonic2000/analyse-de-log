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
        String cheminFichier = "src/squelette/logs_connexion.txt";
        lireFichierLogs(cheminFichier);

    }

    static void lireFichierLogs(String chemin) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            
            int nb_echec = 0;
            int cp_echec =0;
            int nb_succes = 0;
            while ((ligne = lecteur.readLine()) != null) {
                String[] champs = ligne.split(";", -1);
                if (champs.length != 6) {
                    System.out.println("Ligne ignoree : format invalide (" + champs.length + " champs)");
                    continue;
                }
                //Affichage
                String nom = champs[5];
                //Détecte si il y a un succès si "oui" il incrémente le compteur succès et mes à 0, le compteur echec
                if(nom.equals("SUCCES")){
                    nb_succes++;
                    //Affiche un message si il y a plus de 3 échec d'affiler détecter
                    if(cp_echec >= 3){
                        System.out.println("\n  "+cp_echec +"  echec a la suite detecter\n");
                        
                    }
                    cp_echec = 0;
                }
                //Si il n'y a pas de succès détecter, il incrémente un compteur d'échec et fait augmenter le nombre d'échec
                else{
                    nb_echec++;
                    cp_echec++;
                    //Affiche les information sur la ligne où il y a u l'échec
                    System.out.println("IP source : " + champs[2] + "\nIP destination : " + champs[3] + "\nID : " + champs[4] + "\nACCES : " + champs[5]);
                    System.out.println("");
                    
                }
    
            }
            //Affiche le nombre de succès totale et le nombre d'échec
            System.out.println("Nombre de succes : "+ nb_succes);
            System.out.println("Nombre d'echec : "+ nb_echec);
            
            
        } catch (IOException e) {
            System.out.println("Erreur : impossible de lire le fichier " + chemin);
            System.out.println("Détail : " + e.getMessage());
        }
    }

  
}