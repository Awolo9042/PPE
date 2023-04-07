package modele;
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Destinations;
	

public class ModeleDestinations {
		    private static Bdd uneBdd = new Bdd("localhost:3307", "travelin_Event", "root", "");

		    public static void insertdestinations(Destinations uneDestination) {
		        String requete = "INSERT INTO destinations VALUES ( null,'"
		                + uneDestination.getdestination_name() + "', '"
		                + uneDestination.getdestination_prix() + "', '"
		                + uneDestination.getdestination_description() + "');";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            unStat.execute(requete);
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		    }

		    public static void updatedestinations(Destinations uneDestination) {
		        String requete = "UPDATE destinations SET"
		                + " destination_name = '" + uneDestination.getdestination_name() + "',"
		                + " destination_prix = '" + uneDestination.getdestination_prix() + "'"
				        + " destination_description = '" + uneDestination.getdestination_description() + "'"
		                + " WHERE destination_id = " + uneDestination.getdestination_id() + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            unStat.execute(requete);
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		    }

		    public static void deletedestinations(int destination_id) {
		        String requete = "DELETE FROM destinations WHERE destination_id = " + destination_id + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            unStat.execute(requete);
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		    }

		    public static ArrayList<Destinations> selectAllDestination() {
		        ArrayList<Destinations> lesDestinations = new ArrayList<Destinations>();
		        String requete = "SELECT * FROM destinations;";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération des résultats
		            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
		            // on parcourt les résultats et on instancie les destinations
		            while (desResultats.next()) {
		            	Destinations uneDestination = new Destinations(desResultats.getInt("destination_id"),
		                        desResultats.getString("destination_name"),
		                        desResultats.getString("destination_prix"),
		                        desResultats.getString("destination_description"));
		                // on ajoute la destination dans l'ArrayList
		            	lesDestinations.add(uneDestination);
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return lesDestinations;
		    }

		    public static Destinations selectWhereDestination(int destination_id) {
		    	Destinations uneDestination = null;
		        String requete = "SELECT * FROM destinations WHERE destination_id = " + destination_id + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de la destination
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si la destination existe
		            if (unResultat.next()) {
		            	uneDestination = new Destinations(unResultat.getInt("destination_id"),
		                        unResultat.getString("destination_name"),
		                        unResultat.getString("destination_prix"),
		                        unResultat.getString("destination_description"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return uneDestination;
		    }
		    public static Destinations selectWhereDestination(String destination_name) {
		    	Destinations uneDestination = null;
		        String requete = "SELECT * FROM destinations WHERE destination_name = " + destination_name + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de la destination
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si la destination existe
		            if (unResultat.next()) {
		            	uneDestination = new Destinations(unResultat.getInt("destination_id"),
		                        unResultat.getString("destination_name"),
		                        unResultat.getString("destination_prix"),
		                        unResultat.getString("destination_description"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return uneDestination;
		    }
}
