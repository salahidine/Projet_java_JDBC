package mycompany.service;

import mycompany.entity.Joueur;
import mycompany.repository.JoueurRepoImpl;

public class JoueurService {
    private JoueurRepoImpl joueurRepo=new JoueurRepoImpl();

    public void createJoueurService(Joueur joueur) {

        joueurRepo.create(joueur);
    }
}
