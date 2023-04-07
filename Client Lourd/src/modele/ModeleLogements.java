package modele;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Logements;

public class ModeleLogements {
	    private static Bdd unBdd = new Bdd("localhost:3307", "travelin_Event", "root", "");

	    public static void InsertLogements(Logements unLogement) {
	        String requete = "INSERT INTO logements VALUES ( null,'"
	                + unLogement.getlogement_name() + "', '"
	                + unLogement.getlogement_prix() + "');";
	        try {
	            unBdd.seConnecter();
	            Statement unStat = unBdd.getMaConnexion().createStatement();
	            unStat.execute(requete);
	            unStat.close();
	            unBdd.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'execution de la requete : " + requete);
	        }
	    }

	    public static void UpdateLogements(Logements unLogement) {
	        String requete = "UPDATE logements SET"
	                + " logement_name = '" + unLogement.getlogement_name() + "',"
	                + " logement_prix = '" + unLogement.getlogement_prix() + "'"
	                + " WHERE logement_id = " + unLogement.getlogement_id() + ";";
	        try {
	            unBdd.seConnecter();
	            Statement unStat = unBdd.getMaConnexion().createStatement();
	            unStat.execute(requete);
	            unStat.close();
	            unBdd.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'execution de la requete : " + requete);
	        }
	    }

	    public static void DeleteLogements(int logement_id) {
	        String requete = "DELETE FROM logements WHERE logement_id = " + logement_id + ";";
	        try {
	            unBdd.seConnecter();
	            Statement unStat = unBdd.getMaConnexion().createStatement();
	            unStat.execute(requete);
	            unStat.close();
	            unBdd.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'execution de la requete : " + requete);
	        }
	    }

	    public static ArrayList<Logements> SelectAllLogements() {
	        ArrayList<Logements> lesLogements = new ArrayList<Logements>();
	        String requete = "SELECT * FROM logements";
	        try {
	            unBdd.seConnecter();
	            Statement unStat = unBdd.getMaConnexion().createStatement();
	            // récupération des résultats
	            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
	            // on parcourt les résultats et on instancie les Transports
	            while (desResultats.next()) {
	            	Logements unLogement = new Logements(desResultats.getInt("logement_id"),
	                        desResultats.getString("logement_name"),
	                        desResultats.getString("logement_prix"));
	                // on ajoute le transport dans l'ArrayList
	            	lesLogements.add(unLogement);
	            }
	            unStat.close();
	            unBdd.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'execution de la requete : " + requete);
	        }
	        return lesLogements;
	    }

	    public static Logements SelectWhereLogements(int logement_id) {
	    	Logements unLogement = null;
	        String requete = "SELECT * FROM logements WHERE logement_id = " + logement_id + ";";
	        try {
	            unBdd.seConnecter();
	            Statement unStat = unBdd.getMaConnexion().createStatement();
	            // récupération du Transport
	            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
	            // on teste si le Transport existe
	            if (unResultat.next()) {
	            	unLogement = new Logements(unResultat.getInt("logements_id"),
	                        unResultat.getString("logement_name"),
	                        unResultat.getString("logement_prix"));
	            }
	            unStat.close();
	            unBdd.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'execution de la requete : " + requete);
	        }
	        return unLogement;
	    }
}
