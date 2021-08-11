package mycompany.service;

import mycompany.entity.Joueur;
import mycompany.repository.JoueurRepoImpl;

public class JoueurService {
    private JoueurRepoImpl joueurRepo=new JoueurRepoImpl();

    public void createJoueur(Joueur joueur) {

        joueurRepo.create(joueur);
    }

    public Joueur getJoueur(Long id) {

        return joueurRepo.getById(id);
    }
}
