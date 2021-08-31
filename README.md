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

@column pour le mapping d'une colonne qui ne porte pas le même nom dans la base / @transient : pour dire à hibernate d'exclure la propriété du mapping

@ManyToOne pour dire qu'il s'agit d'une association 1-n dans la BD (il sera indispensable d'ajouter @JoinColumn pour identifier la clé etrangère, ex : @JoinColumn(name="id_tournoi). 
@ManyToOne(fetch=FetchType.LAZY) active le mode lazy loading fait que hibernate ne charge pas la jointure au depart mais il relance automatiquement une requéte complémentaire en cas de besoin des informations de la 2eme table 
Autres association : @OneToOne \ @ManyToMany (il faut utiliser une proprieté de type collection pour la table associative)
Pour indiquer une association bidirectionnelle on utilise l'attribut mappedBy. ex: @OneToOne(mappedBy:"match") 
d'autres propriétés utiles : (cascade=CascadeType.PERSIST) ou cascade=CascadeType.ALL ET ,orphanRemoval = true pour la suppression dans l'association

L'utilisation de DTO a des consequences importante sur l'architecture de l'app (surtout la couche service) et de la facon de travailler
Le concept de DTO est utilisé dans les controleurs et représente l'information à l'écran alors que les entités s utilisent dans le repository et représente les infos de la BD. Pour une information de BDD on peut avoir un paquet d'ecran qui represente cette info de differente facon. 
ex: pour la classe Epreuve nous avons créé EpreuveFullDto et EpreuveLightDto
