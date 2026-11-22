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
        String cheminFichier = "logs_connexion.txt";
        lireFichierLogs(cheminFichier);

    }

    static void lireFichierLogs(String chemin) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            
            int nb_echec = 0;
            int cp_echec =0;
            int nb_succes = 0;
            int nbLigne=0;
            while ((ligne = lecteur.readLine()) != null) {
                String[] champs;
                champs= ligne.split(";");
                nbLigne++;
                //Affichage
                //System.out.print(champs.length + " ");
                String nom = champs[5];
                if(nom.equals("SUCCES")){
                    nb_succes++;
                    if(cp_echec >= 3){
                        System.out.println("\n  "+cp_echec +"  echec a la suite detecter\n");
                        cp_echec = 0;
                    }
                    cp_echec = 0;
                }
                else{
                    nb_echec++;
                    cp_echec++;
                    System.out.println(nbLigne + " Ip source  " + champs[2] + " " + champs[3] + " " + champs[4]);
                    /*for (String champ : champs) {
                        System.out.print(champ + " ");
                    }*/
                    System.out.println("");
                    
                    
         
                    /*if(cp_echec >= 3){
                        System.out.println("\n"+cp_echec +"echec a la suite detecter");
                        cp_echec = 0;
                    }*/
                }
               /*System.out.println(nbLigne + " ");
                for (String champ : champs) {
                    System.out.print(champ + " ");
                }
                System.out.println("");*/
                
                
            }
            System.out.println("Nombre de succes : "+ nb_succes);
            System.out.println("Nombre d'echec : "+ nb_echec);
            
            
        } catch (IOException e) {
            System.out.println("Erreur : impossible de lire le fichier " + chemin);
            System.out.println("Détail : " + e.getMessage());
        }
    }

  
}