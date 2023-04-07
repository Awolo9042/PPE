package modele;
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Promotions;
	

public class ModelePromotions{
		    private static Bdd uneBdd = new Bdd("localhost:3307", "travelin_Event", "root", "");

		    public static void InsertPromotions(Promotions unePromotion) {
		        String requete = "INSERT INTO promotions VALUES ( null,'"
		                + unePromotion.getoffre_id() + "', '"
		                + unePromotion.getdate_debut() + "', '"
		                + unePromotion.getdate_fin() + "', '"
		                + unePromotion.getpourcentage() + "');";
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

		    public static void UpdatePromotions(Promotions unePromotion) {
		        String requete = "UPDATE promotions SET"
		                + " offre_id = '" + unePromotion.getoffre_id() + "',"
		                + " date_debut = '" + unePromotion.getdate_debut() + "'"
				        + " date_fin = '" + unePromotion.getdate_fin() + "'"
				        + " pourcentage = '" + unePromotion.getpourcentage() + "'"
		                + " WHERE promotion_id = " + unePromotion.getpromotion_id() + ";";
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

		    public static void DeletePromotions(int promotion_id) {
		        String requete = "DELETE FROM promotions WHERE promotion_id = " + promotion_id + ";";
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

		    public static ArrayList<Promotions> SelectAllPromotions() {
		        ArrayList<Promotions> lesPromotions = new ArrayList<Promotions>();
		        String requete = "SELECT * FROM promotions;";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération des résultats
		            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
		            // on parcourt les résultats et on instancie les destinations
		            while (desResultats.next()) {
		            	Promotions unePromotion = new Promotions(desResultats.getInt("promotion_id"),
		                        desResultats.getString("offre_id"),
		                        desResultats.getString("date_debut"),
		                        desResultats.getString("date_fin"),
		                        desResultats.getString("pourcentage"));
		                // on ajoute la destination dans l'ArrayList
		            	lesPromotions.add(unePromotion);
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return lesPromotions;
		    }

		    public static Promotions SelectWherePromotions(int promotion_id) {
		    	Promotions unePromotion = null;
		        String requete = "SELECT * FROM promotions WHERE promotion_id = " + promotion_id + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de la destination
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si la destination existe
		            if (unResultat.next()) {
		            	unePromotion = new Promotions(unResultat.getInt("promotion_id"),
		            			unResultat.getString("offre_id"),
		            			unResultat.getString("date_debut"),
		            			unResultat.getString("date_fin"),
		            			unResultat.getString("pourcentage"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return unePromotion;
		    }
		    public static Promotions SelectWherePromotionsOffres(int offre_id) {
		    	Promotions unePromotion = null;
		        String requete = "SELECT * FROM promotions WHERE offre_id = " + offre_id + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de la destination
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si la destination existe
		            if (unResultat.next()) {
		            	unePromotion = new Promotions(unResultat.getInt("promotion_id"),
		            			unResultat.getString("offre_id"),
		            			unResultat.getString("date_debut"),
		            			unResultat.getString("date_fin"),
		            			unResultat.getString("pourcentage"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return unePromotion;
		    }
}
