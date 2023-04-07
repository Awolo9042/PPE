package modele;
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Users;

public class ModeleUsers {
		    private static Bdd uneBdd = new Bdd("localhost:3307", "travelin_Event", "root", "");

		    public static void insertUser(Users unUser) {
		        String requete = "INSERT INTO users VALUES (null ,'"
		                + unUser.getnom() + "', '"
		                + unUser.getprenom() + "', '"
		                + unUser.getemail() + "', '"
		                + unUser.gettelephone() + "', '"
		                + unUser.getmdp() + "', '"
		                + unUser.getrole_client() + "', '"
		                + unUser.getstatut() + "', '"
		        		+ unUser.getsiret() + "');";
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
		    
		    public static void updateUser(Users unUser) {
		        String requete = "UPDATE users SET"
		                + " nom = '" + unUser.getnom() + "',"
		                + " prenom = '" + unUser.getprenom() + "',"
		                + " telephone = '" + unUser.gettelephone() + "',"
				        + " email = '" + unUser.getemail() + "',"
				        + " statut = '" + unUser.getstatut() +"',"
				        + " siret = '" + unUser.getsiret() + "',"
		                + " WHERE id_client = " + unUser.getid_client() + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            unStat.execute(requete);
		            unStat.close();
		            uneBdd.seDeconnecter();
		            System.out.println(requete);
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		    }

		    public static void deleteUser(int id_client) {
		        String requete = "DELETE FROM users WHERE id_client = " + id_client + ";";
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

		    public static ArrayList<Users> selectAllUsers() {
		        ArrayList<Users> lesUsers = new ArrayList<Users>();
		        String requete = "SELECT * FROM users;";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération des résultats
		            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
		            // on parcourt les résultats et on instancie les utilisateurs
		            while (desResultats.next()) {
		                Users unUser = new Users(desResultats.getInt("id_client"),
		                        desResultats.getString("nom"),
		                        desResultats.getString("prenom"),
		                        desResultats.getString("telephone"),
		                        desResultats.getString("email"),
		                        desResultats.getString("mdp"),
		                        desResultats.getString("role_client"),
		                        desResultats.getString("statut"),
		                        desResultats.getString("siret"));
		                // on ajoute l'utilisateur dans l'ArrayList
		                lesUsers.add(unUser);
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return lesUsers;
		    }
		    
//		    public static ArrayList<Users> selectAllUsers(String filtre) {
//		        ArrayList<Users> lesUsers = new ArrayList<Users>();
//		        String requete = "";
//		        if(filtre.equals("")){
//		            requete = "SELECT * FROM users;";
//		        }else{
//		            requete = "SELECT * FROM users WHERE nom LIKE '%" + filtre + "%' OR prenom LIKE '%" + filtre + "%' OR adresse LIKE '%" + filtre + "%' OR email LIKE '%" + filtre + "%';";
//		        }
//		        try {
//		            uneBdd.seConnecter();
//		            Statement unStat = uneBdd.getMaConnexion().createStatement();
//		            // récupération des résultats
//		            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
//		            // on parcourt les résultats et on instancie les Clients
//		            while (desResultats.next()) {
//		                Users unUser = new Users(desResultats.getInt("idclient"),
//		                        desResultats.getString("nom"),
//		                        desResultats.getString("prenom"),
//		                        desResultats.getString("email"),
//		                        desResultats.getString("telephone"),
//		                		desResultats.getString("mdp"));
//		                // on ajoute le Client dans l'ArrayList
//		                lesUsers.add(unUser);
//		            }
//		            unStat.close();
//		            uneBdd.seDeconnecter();
//		        } catch (SQLException exp) {
//		            System.out.println("Erreur d'execution de la requete : " + requete);
//		        }
//		        return lesUsers;
//		    }

		    public static Users selectWhereUserAdmin(String email, String mdp, String role_client) {
		    	Users unUser = null;
		        String requete = "SELECT * FROM users WHERE email='" + email +"' and mdp = '"+mdp+"' and role_client = '"+role_client+"';";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de l'utilisateur
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si l'utilisateur existe
		            if (unResultat.next()) {
		            	unUser = new Users(unResultat.getInt("id_client"),
		            			unResultat.getString("nom"),
		            			unResultat.getString("prenom"),
			                        unResultat.getString("telephone"),
			                        unResultat.getString("email"),
			                        unResultat.getString("mdp"),
			                        unResultat.getString("role_client"),
			                        unResultat.getString("statut"),
			                        unResultat.getString("siret"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return unUser;
		    }
		    
		    public static Users selectWhereUserClient(String nom, String prenom) {
		    	Users unUser = null;
		        String requete = "SELECT * FROM users WHERE nom='" + nom +"' and prenom = '"+prenom+"';";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de l'utilisateur
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si l'utilisateur existe
		            if (unResultat.next()) {
		            	unUser = new Users(unResultat.getInt("id_client"),
		            			unResultat.getString("nom"),
		            			unResultat.getString("prenom"),
			                        unResultat.getString("telephone"),
			                        unResultat.getString("email"),
			                        unResultat.getString("mdp"),
			                        unResultat.getString("role_client"),
			                        unResultat.getString("statut"),
			                        unResultat.getString("siret"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return unUser;
		    }
		    
		    public static Users selectWhereUser(int id_client) {
		    	Users unUser = null;
		        String requete = "SELECT * FROM users WHERE id_client = " + id_client + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de l'utilisateur
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si l'utilisateur existe
		            if (unResultat.next()) {
		            	unUser = new Users(unResultat.getInt("id_client"),
		            			unResultat.getString("nom"),
		            			unResultat.getString("prenom"),
			                        unResultat.getString("telephone"),
			                        unResultat.getString("email"),
			                        unResultat.getString("mdp"),
			                        unResultat.getString("role_client"),
			                        unResultat.getString("statut"),
			                        unResultat.getString("siret"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return unUser;
		    }
		    public static Users selectWhereUser(String role_client) {
		    	Users unUser = null;
		        String requete = "SELECT * FROM users WHERE role_client = " + role_client + ";";
		        try {
		            uneBdd.seConnecter();
		            Statement unStat = uneBdd.getMaConnexion().createStatement();
		            // récupération de l'utilisateur
		            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
		            // on teste si l'utilisateur existe
		            if (unResultat.next()) {
		            	unUser = new Users(unResultat.getInt("id_client"),
		            			unResultat.getString("nom"),
		            			unResultat.getString("prenom"),
			                        unResultat.getString("telephone"),
			                        unResultat.getString("email"),
			                        unResultat.getString("mdp"),
			                        unResultat.getString("role_client"),
			                        unResultat.getString("statut"),
			                        unResultat.getString("siret"));
		            }
		            unStat.close();
		            uneBdd.seDeconnecter();
		        } catch (SQLException exp) {
		            System.out.println("Erreur d'execution de la requete : " + requete);
		        }
		        return unUser;
		    }
}
