package controleur;
import java.util.ArrayList;  
import modele.ModeleUsers;

public class C_Users {

		public static void insertUser(Users unUser)
		{
			ModeleUsers.insertUser(unUser);
		}
		public static ArrayList<Users> selectAlluser ()
		{
			return ModeleUsers.selectAllUsers();
		}
//		public static ArrayList<Users> selectAllUsers(String filtre) {
//	        return ModeleUsers.selectAllUsers(filtre);
//	    }
		public static void deleteUser(int id_client)
		{
			ModeleUsers.deleteUser(id_client);
		}
		public static void updateUser(Users unUser)
		{
			ModeleUsers.updateUser(unUser);
		}
		public static Users selectWhereUser(int id_client)
		{
			return ModeleUsers.selectWhereUser(id_client);
		}
		public static Users selectWhereUserAdmin(String email, String mdp, String role_client)
		{
			return ModeleUsers.selectWhereUserAdmin(email, mdp, role_client);
		}
		public static Users selectWhereUser(String role_client)
		{
			return ModeleUsers.selectWhereUser(role_client);
		}
		public static Users selectWhereUserClient(String nom, String prenom)
		{
			return ModeleUsers.selectWhereUserClient(nom, prenom);
		}
}
