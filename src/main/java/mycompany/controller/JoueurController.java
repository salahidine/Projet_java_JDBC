package mycompany.controller;

import mycompany.entity.Joueur;
import mycompany.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurService=new JoueurService();

    public JoueurController() {
        this.joueurService = new JoueurService();
    }

    // Select
    public void afficheDetailJoueur() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Saisir l'id du joueur : ");
        long identifiant=scanner.nextLong();
        Joueur joueur=joueurService.getJoueur(identifiant);
        System.out.println("Joueur sélectionné est : "+joueur.getPrenom() +" "+joueur.getNom());

    }

    // Creation
    public void creationJoueur() {
        Joueur nouveauJoueur = new Joueur();
        nouveauJoueur.setSexe('H');
        nouveauJoueur.setNom("SIF");
        nouveauJoueur.setPrenom("SALAHIDINE");
        joueurService.createJoueur(nouveauJoueur);
    }
}
