package mycompany.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Annotation ajouté pr indiquer que dans la BD il est en autoincrement
    private Long id;
    private String nom;
    private String prenom;
    private Character sexe;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Character getSexe() {
        return sexe;
    }
}
