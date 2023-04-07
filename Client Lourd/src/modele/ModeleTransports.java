package modele;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Transports;

public class ModeleTransports {
	    private static Bdd unBdd = new Bdd("localhost:3307", "travelin_Event", "root", "");

	    public static void inserttransports(Transports unTransport) {
	        String requete = "INSERT INTO transports VALUES ( null,'"
	                + unTransport.gettransport_name() + "', '"
	                + unTransport.gettransport_prix() + "');";
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

	    public static void updatetransports(Transports unTransport) {
	        String requete = "UPDATE transports SET"
	                + " transport_name = '" + unTransport.gettransport_name() + "',"
	                + " transport_prix = '" + unTransport.gettransport_prix() + "'"
	                + " WHERE transport_id = " + unTransport.gettransport_id() + ";";
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

	    public static void deletetransports(int transport_id) {
	        String requete = "DELETE FROM transports WHERE transport_id = " + transport_id + ";";
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

	    public static ArrayList<Transports> selectAllTransports() {
	        ArrayList<Transports> lesTransports = new ArrayList<Transports>();
	        String requete = "SELECT * FROM transports;";
	        try {
	            unBdd.seConnecter();
	            Statement unStat = unBdd.getMaConnexion().createStatement();
	            // récupération des résultats
	            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
	            // on parcourt les résultats et on instancie les Transports
	            while (desResultats.next()) {
	                Transports unTransport = new Transports(desResultats.getInt("transport_id"),
	                        desResultats.getString("transport_name"),
	                        desResultats.getString("transport_prix"));
	                // on ajoute le transport dans l'ArrayList
	                lesTransports.add(unTransport);
	            }
	            unStat.close();
	            unBdd.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'execution de la requete : " + requete);
	        }
	        return lesTransports;
	    }

	    public static Transports selectWhereTransport(int transport_id) {
	    	Transports unTransport = null;
	        String requete = "SELECT * FROM transports WHERE transport_id = " + transport_id + ";";
	        try {
	            unBdd.seConnecter();
	            Statement unStat = unBdd.getMaConnexion().createStatement();
	            // récupération du Transport
	            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
	            // on teste si le Transport existe
	            if (unResultat.next()) {
	            	unTransport = new Transports(unResultat.getInt("transport_id"),
	                        unResultat.getString("nom"),
	                        unResultat.getString("prix"));
	            }
	            unStat.close();
	            unBdd.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'execution de la requete : " + requete);
	        }
	        return unTransport;
	    }
}
