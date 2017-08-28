package com.formation.wiki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionManager {
	
	public static String url="jdbc:mysql://localhost:3306/wiki";
	public static String user="root";
	public static String password="";
	
	public static Connection getConnexion() {
		Connection con=null;
		try {
			// pour charger le Driver JDBC
			Class.forName("com.mysql.jdbc.Driver");
			
			// pour recuperer une connexion
		    con=DriverManager.getConnection(url, user, password);
			System.out.println("connexion etablit avec success");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur lors du chargement du DRIVER");
		} catch (SQLException e) {
			System.out.println("Erreur lors de la connexio a la BDD");
		}
		return con;
	}

}
