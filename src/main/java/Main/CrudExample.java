package Main;



import entity.Joueur;

import org.apache.commons.dbcp2.BasicDataSource;
import repository.JoueurRepoImpl;

import java.sql.*;

public class CrudExample {
    public static void main(String... args){

       // Creation
       Joueur nouveauJoueur=new Joueur();
       JoueurRepoImpl nouveauJoueurDB=new JoueurRepoImpl();
       nouveauJoueur.setSexe('H');
       nouveauJoueur.setNom("SIF");
       nouveauJoueur.setPrenom("SALAHIDINE");
       nouveauJoueurDB.create(nouveauJoueur);
       System.out.println("Insertion >> Nom:"+nouveauJoueur.getNom()+" Prenom:"+nouveauJoueur.getPrenom());

        // Select
        Joueur selectedJoueur=nouveauJoueurDB.getByPrenom(nouveauJoueur.getPrenom());
        System.out.println("ID:"+selectedJoueur.getId()+" Nom:"+selectedJoueur.getNom()+" Prenom:"+selectedJoueur.getPrenom());

        // MAJ
        String nouveauPrenom="SALAH";
        nouveauJoueurDB.update(nouveauJoueur ,nouveauPrenom);
        System.out.println("MAJ >> Ancien prenom:"+nouveauJoueur.getPrenom()+" Nouveau prenom:"+nouveauPrenom);

        // Delete
       nouveauJoueurDB.delete(selectedJoueur.getId());

        if (false) {
            String nom,prenom;
            int id=12;
            Connection conn = null;
            try {
                // **** Utilisation de pool de connexion BasicDatasource pour la connexion / Avantage : Création de connexions simultanés
                BasicDataSource dataSource= new BasicDataSource();
                dataSource.setInitialSize(5);   // 5 connexion dans le pool vont être ouverte mise à dispo de l'application
                dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
                dataSource.setUsername("COURSDB");
                dataSource.setPassword("COURSDB");
                conn=dataSource.getConnection();

                // **** Utilisation de dataSource pour la connexion
            /* MysqlDataSource dataSource= new MysqlDataSource();
            //dataSource.setURL("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("tennis");
            dataSource.setUseSSL(false);
            dataSource.setUser("COURSDB");
            dataSource.setPassword("COURSDB");
            conn=dataSource.getConnection();*/

                //Seulement avant Java 7/JDBC 4
                //Class.forName(DRIVER_CLASS_NAME);

                // **** Utilisation de MySQL driver MySQL Connector
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false","COURSDB","COURSDB");

                // Annuler la validation du transaction automatique prelancé
                conn.setAutoCommit(false);

                // Select
                PreparedStatement statement = conn.prepareStatement("Select ID,NOM,PRENOM from JOUEUR where ID=?");
                statement.setLong(1,id);
                ResultSet rs=statement.executeQuery();
                while (rs.next()) {
                    nom=rs.getString("NOM");
                    prenom=rs.getString("PRENOM");
                    id=rs.getInt("ID");
                    System.out.println("ID:"+id+" Nom:"+nom+" Prenom:"+prenom);
                }

            /*// Update
            statement = conn.prepareStatement("Update JOUEUR set PRENOM=? where ID=?");
            statement.setString(1,"Salah");
            statement.setLong(2,12);
            int nbrEnregistrementsModifie=statement.executeUpdate();

            // Insert
            statement = conn.prepareStatement("insert into JOUEUR (PRENOM) Values (?)");
            statement.setString(1,"Salah");

            statement = conn.prepareStatement("insert into JOUEUR (PRENOM) Values (?)");
            statement.setString(1,"Salah2");
            */
                // Valider la transaction si tout va bien (sinon annuler la transaction dans exception tt en bas)
                conn.commit();

                //Oracle Driver officiel OJDBC Thin
                //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
                //Postgres Driver officiel
                //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","COURSDB","COURSDB");
                System.out.println("success");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (conn!=null) {
                        conn.rollback(); // annuler la transaction si exception (rollback sur les update et insert)
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}

}


