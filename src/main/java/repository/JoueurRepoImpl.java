package repository;

import entity.Joueur;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepoImpl {

    public void create(Joueur joueur)  {
        Connection conn = null;

        // Creation de data source
        DataSource dataSource= DataSourceProvider.createDatasource();
        try {
            conn=dataSource.getConnection();
            // Insert
            PreparedStatement statement = conn.prepareStatement("insert into JOUEUR (PRENOM,NOM,SEXE) Values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,joueur.getPrenom());
            statement.setString(2,joueur.getNom());
            statement.setString(3,joueur.getSexe().toString());
            statement.executeUpdate();

            // Récupérer l'id inséré
            ResultSet rs=statement.getGeneratedKeys(); // Récupérer tt les autoincrement de cette insertion
            if (rs.next()) {
                joueur.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Joueur joueur, String prenom)  {
        Connection conn = null;

        // Creation de data source
        DataSource dataSource= DataSourceProvider.createDatasource();
        try {
            conn=dataSource.getConnection();

            // Insert
            PreparedStatement statement = conn.prepareStatement("update JOUEUR set PRENOM=? where PRENOM=?");
            statement.setString(1,prenom);
            statement.setString(2,joueur.getPrenom());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Joueur getByPrenom(String prenom)  {
        Connection conn = null;
        Joueur joueur=new Joueur();

        // Creation de data source
        DataSource dataSource= DataSourceProvider.createDatasource();

        try {
            conn=dataSource.getConnection();

            // Select
            PreparedStatement statement = conn.prepareStatement("Select ID,NOM,PRENOM,SEXE from JOUEUR where Prenom=?");
            statement.setString(1,prenom);
            ResultSet rs=statement.executeQuery();
            if (rs.next()) {
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0)); // Premier caractère
            }
            else
                System.out.println("Pas de réponse");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueur;
    }

    public List<Joueur> listJoueurs()  {
        Connection conn = null;
        List<Joueur> joueurs=new ArrayList<Joueur>();

        // Creation de data source
        DataSource dataSource= DataSourceProvider.createDatasource();

        try {
            conn=dataSource.getConnection();

            // Select
            PreparedStatement statement =conn.prepareStatement("Select ID,NOM,PRENOM,SEXE from JOUEUR");
            ResultSet rs=statement.executeQuery();
            while (rs.next()) {
                Joueur joueurTemp=new Joueur();
                joueurTemp.setId(rs.getLong("ID"));
                joueurTemp.setNom(rs.getString("NOM"));
                joueurTemp.setPrenom(rs.getString("PRENOM"));
                joueurTemp.setSexe(rs.getString("SEXE").charAt(0)); // Premier caractère
                joueurs.add(joueurTemp); // Ajouter dans la liste
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueurs;
    }

    public void delete(Long id) {

        Connection conn = null;

        // Creation de data source
        DataSource dataSource= DataSourceProvider.createDatasource();

        try {
            conn=dataSource.getConnection();

            // Select
            PreparedStatement statement =statement = conn.prepareStatement("DELETE from JOUEUR where ID=?");
            statement.setLong(1,id);
            statement.executeUpdate();
            System.out.println("joueur "+id+" supprimé de la base");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
