# Projet_java_JDBC  

entity : Contient les entité de l'application (Les classes)

repository : Contient les classes de la couche d'accès aux données

service : Contient les classes de gestion du service métier

DataSourceProvider : Classe créé pour fournir le datasource de connexion à la BD mysql, ce qui exige l'ajout du dépendance ci-dessous dans pom.xml :	
	"<dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-dbcp2</artifactId>
            <version>2.8.0</version>
        </dependency>"	
Cette classe contient une methode static pour la gesion de la connexion à la BD (méthode de classe, à appeler directement avec son nom)    
				
Architecture SOA (Service oriented architecture) : Interface (Vue) <-> Controleur <-> Service métier <-> Repository (data) ...

Les limites de JDBC : 
impose de connaitre le moteur de DB alors que les requêtes change selon le moteur de DB
l'evolution de la base (modification du nom d'une table, type...) il faut évoluer les code Java correspondant.

Framework ORM : object-relational mapping est un FRAMEWORK qui se place en interface entre un programme applicatif et une base de données relationnelle 

HibernateUtil : Class qui contient la session hibernate essentielle pour ouvrir pour la communication avec la BD (sessionFactory from hibernate.cfg.xml)

Hibernate peut rendre le code portable peu importe la base de donnée utilisé (en identifiant le parametre dialect dans hibernate.cfg.xml)

Tant que la session hibernate est ouvert l'objet reste persistent (on peux faire des modif jusqu'au session.close) >> Exemple : joueur.setNom modifie le nom dans la BD
