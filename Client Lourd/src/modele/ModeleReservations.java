package modele;
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Reservations;
	

public class ModeleReservations {
		    private static Bdd uneBdd = new Bdd("localhost:3307", "travelin_Event", "root", "");

		    public static void insertReservations(Reservations uneReservation) {
		        String requete = "INSERT INTO reservations VALUES ( null,'"
		                + uneReservation.getdestination_id() + "', '"
		                + uneReservation.getdate_depart() + "', '"
		                + uneReservation.getdate_retour() + "', '"
		                + uneReservation.getnb_personnes() + "', '"
		          		+ uneReservation.getlogement_id() + "', '"
		          		+ uneReservation.gettransport_id() + "', '"
		          		+ uneReservation.getreservations_prix() + "', '"
		           		+ uneReservation.getid_client() + "', '"
		             	+ uneReservation.getemail() + "');";
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

		    public static void updateReservations(Reservations uneReservation) {
		        String requete = "UPDATE reservations SET"
		                + " date_depart = '" + uneReservation.getdate_depart() + "',"
		                + " date_retour = '" + uneReservation.getdate_retour() + "'"
				        + " email = '" + uneReservation.getemail() + "'"
		                + " WHERE reservation_id = " + uneReservation.getreservation_id() + ";";
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

		    public static void deleteReservations(int reservation_id) {
		        String requete = "DELETE FROM reservations WHERE reservation_id = " + reservation_id + ";";
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

		    public static ArrayList<Reservations> selectAllReservations() {
		        ArrayList<Reservations> lesReservations = new ArrayList<Reservations>();
		        String requete = "SELECT * FROM reservations;";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération des résultats
		            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
		            // on parcourt les résultats et on instancie les destinations
		            while (desResultats.next()) {
		            	Reservations uneReservation = new Reservations(desResultats.getInt("reservation_id"),
		            			desResultats.getString("destination_id"),
		            			desResultats.getString("date_depart"),
		                        desResultats.getString("date_retour"),
		                        desResultats.getString("nb_personnes"),
		                        desResultats.getString("logement_id"),
		                        desResultats.getString("transport_id"),
		                        desResultats.getString("reservations_prix"),
		                        desResultats.getString("id_client"),
		                        desResultats.getString("email"));
		                // on ajoute la destination dans l'ArrayList
		            	lesReservations.add(uneReservation);
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return lesReservations;
		    }

		    public static Reservations selectWhereReservations(int reservation_id) {
		    	Reservations uneReservation = null;
		        String requete = "SELECT * FROM reservations WHERE reservation_id = " + reservation_id + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de la destination
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si la destination existe
		            if (unResultat.next()) {
		            	uneReservation = new Reservations(unResultat.getInt("reservation_id"),
		            			unResultat.getString("destination_id"),
		            			unResultat.getString("date_depart"),
		            			unResultat.getString("date_retour"),
		            			unResultat.getString("nb_personnes"),
		            			unResultat.getString("logement_id"),
		            			unResultat.getString("transport_id"),
		            			unResultat.getString("reservations_prix"),
		            			unResultat.getString("id_client"),
		            			unResultat.getString("email"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return uneReservation;
		    }
}
