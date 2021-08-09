package service;

import entity.Joueur;
import repository.JoueurRepoImpl;

public class JoueurService {
    private JoueurRepoImpl joueurRepo=new JoueurRepoImpl();

    public void createJoueurService(Joueur joueur) {
        joueurRepo.create(joueur);
    }
}
