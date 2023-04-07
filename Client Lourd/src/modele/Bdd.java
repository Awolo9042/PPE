package modele;

import java.sql.Connection;

public class Bdd {
	private String serveur, bdd, user, mdp;

	private Connection maConnexion;

	public Bdd(String serveur, String bdd, String user, String mdp) {
		this.maConnexion = null;
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
	}

	public void chargerPilote() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur lors du chargement : le pilote n'a pas été trouvé dans le classpath ! <"
					+ e.getMessage() + ">");
		}
	}

	public void seConnecter() {
		String url = "jdbc:mysql://" + this.serveur + "/" + this.bdd;
		url += "?verifyServerCertificate=false&useSSL=false";
		try {
			this.maConnexion = java.sql.DriverManager.getConnection(url, this.user, this.mdp);
		} catch (java.sql.SQLException e) {
			System.out.println("Erreur lors de la connexion : <" + url + ">");
			e.printStackTrace();
		}
	}

	public void seDeconnecter() {
		try {
			if (this.maConnexion != null) {
				this.maConnexion.close();
			}
		} catch (java.sql.SQLException e) {
			System.out.println("Erreur lors de la deconnexion : <" + e.getMessage() + ">");
		}
	}

	// Getters and Setters
	public Connection getMaConnexion() {
		return maConnexion;
	}

	public void setMaConnexion(Connection maConnexion) {
		this.maConnexion = maConnexion;
	}
}