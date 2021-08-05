package SqlConnexion;



import java.sql.*;

public class TestDeConnexion {
    public static void main(String... args){
        String nom,prenom;
        int id=12;
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false","COURSDB","COURSDB");

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

            // Update
            statement = conn.prepareStatement("Update JOUEUR set PRENOM=? where ID=?");
            statement.setString(1,"Salah");
            statement.setLong(2,12);
            int nbrEnregistrementsModifie=statement.executeUpdate();

            // Insert
            statement = conn.prepareStatement("insert into JOUEUR (PRENOM) Values (?)");
            statement.setString(1,"Salah");

            statement = conn.prepareStatement("insert into JOUEUR (PRENOM) Values (?)");
            statement.setString(1,"Salah2");

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

