package mycompany;

import mycompany.entity.Joueur;
import mycompany.service.JoueurService;

public class CrudExampleService {
    public static void main(String... args) {

        // Creation
        Joueur nouveauJoueur = new Joueur();
        nouveauJoueur.setSexe('H');
        nouveauJoueur.setNom("SIF");
        nouveauJoueur.setPrenom("SALAHIDINE");
        JoueurService joueurService= new JoueurService();
        joueurService.createJoueurService(nouveauJoueur);

    }
}


