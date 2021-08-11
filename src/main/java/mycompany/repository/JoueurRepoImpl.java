package mycompany.repository;

import mycompany.HibernateUtil;
import mycompany.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JoueurRepoImpl {

    public void create(Joueur joueur)  {
        Session session=null;
        Transaction tx=null;
        try {
            session=HibernateUtil.getSessionFactory().openSession(); // Créer une session hibernate
            tx=session.beginTransaction();
            session.persist(joueur); // insert into
            // Lancer les mise à jours faite dans la session hibernate (hibernate exsige une transaction qd il s'agit de insert)
            tx.commit();
            System.out.println("Joueur créé");

        } catch (Exception e) {
            if (tx!=null)
            {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null)
            {
                session.close();
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

    public Joueur getById(Long id)  {

        Joueur joueur=null;
        Session session=null;

        try {
            session=HibernateUtil.getSessionFactory().openSession(); // Créer une session hibernate
            joueur=session.get(Joueur.class,id);
            System.out.println("Joueur lu");

        } catch (Throwable e) {
            e.printStackTrace();
        }
        finally {
            if (session!=null)
            {
                session.close();
            }
        }
        return joueur;
    }

}
