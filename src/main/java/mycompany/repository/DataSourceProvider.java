package mycompany.repository; /**
 * Utilisation de pool de connexion BasicDatasource pour la connexion
 * Avantage : Création de connexions simultanés
 */

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceProvider {

    private static BasicDataSource theDataSource;

    public static BasicDataSource createDatasource() {
        if (theDataSource==null)
            theDataSource= new BasicDataSource();
            theDataSource.setInitialSize(5);   // 5 connexion dans le pool vont être ouverte mise à dispo de l'application
            theDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            theDataSource.setUsername("COURSDB");
            theDataSource.setPassword("COURSDB");

            return theDataSource;
    }
}
