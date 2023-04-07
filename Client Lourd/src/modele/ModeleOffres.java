package modele;
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Offres;
	

public class ModeleOffres {
		    private static Bdd uneBdd = new Bdd("localhost:3307", "travelin_Event", "root", "");

		    public static void InsertOffres(Offres uneOffre) {
		        String requete = "INSERT INTO offres VALUES ( null,'"
		                + uneOffre.getdestination_id() + "', '"
		                + uneOffre.gettransport_id() + "', '"
		                + uneOffre.getlogement_id() + "', '"
		                + uneOffre.getprice() + "');";
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

		    public static void UpdateOffres(Offres uneOffre) {
		        String requete = "UPDATE offres SET"
		                + " destination_id = '" + uneOffre.getdestination_id() + "',"
		                + " transport_id = '" + uneOffre.gettransport_id() + "'"
				        + " logement_id = '" + uneOffre.getlogement_id() + "'"
				        + " price = '" + uneOffre.getprice() + "'"
		                + " WHERE offre_id = " + uneOffre.getoffre_id() + ";";
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

		    public static void DeleteOffres(int offre_id) {
		        String requete = "DELETE FROM offres WHERE offre_id = " + offre_id + ";";
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

		    public static ArrayList<Offres> SelectAllOffres() {
		        ArrayList<Offres> lesOffres = new ArrayList<Offres>();
		        String requete = "SELECT * FROM offres;";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération des résultats
		            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
		            // on parcourt les résultats et on instancie les destinations
		            while (desResultats.next()) {
		            	Offres uneOffre = new Offres(desResultats.getInt("offre_id"),
		                        desResultats.getString("destination_id"),
		                        desResultats.getString("transport_id"),
		                        desResultats.getString("logement_id"),
		                        desResultats.getString("price"));
		                // on ajoute la destination dans l'ArrayList
		            	lesOffres.add(uneOffre);
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return lesOffres;
		    }
		    
		    public static ArrayList<Offres> SelectAllOffres(String filtre) {
		        ArrayList<Offres> lesOffres = new ArrayList<Offres>();
		        String requete = "";
		        if(filtre.equals("")){
		            requete = "SELECT * FROM offres;";
		        }else{
		            requete = "SELECT * FROM offres WHERE destination_id LIKE '%" + filtre + "%' OR transport_id LIKE '%" + filtre + "%' OR logement_id LIKE '%" + filtre + "%' OR price LIKE '%" + filtre + "%';";
		        }
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération des résultats
		            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
		            // on parcourt les résultats et on instancie les Clients
		            while (desResultats.next()) {
		            	Offres uneOffre = new Offres(desResultats.getInt("offre_id"),
		                        desResultats.getString("destination_id"),
		                        desResultats.getString("transport_id"),
		                        desResultats.getString("logement_id"),
		                        desResultats.getString("price"));
		                // on ajoute le Client dans l'ArrayList
		                lesOffres.add(uneOffre);
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return lesOffres;
		    }

		    public static Offres SelectWhereOffres(int offre_id) {
		    	Offres uneOffre = null;
		        String requete = "SELECT * FROM offres WHERE offre_id = " + offre_id + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de la destination
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si la destination existe
		            if (unResultat.next()) {
		            	uneOffre = new Offres(unResultat.getInt("offre_id"),
		            			unResultat.getString("destination_id"),
		            			unResultat.getString("transport_id"),
		            			unResultat.getString("logement_id"),
		            			unResultat.getString("price"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return uneOffre;
		    }
		    public static Offres SelectWhereOffresDestinations(int destination_id) {
		    	Offres uneOffre = null;
		        String requete = "SELECT * FROM offres WHERE destination_id = " + destination_id + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de la destination
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si la destination existe
		            if (unResultat.next()) {
		            	uneOffre = new Offres(unResultat.getInt("offre_id"),
		            			unResultat.getString("destination_id"),
		            			unResultat.getString("transport_id"),
		            			unResultat.getString("logement_id"),
		            			unResultat.getString("price"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return uneOffre;
		    }
}
