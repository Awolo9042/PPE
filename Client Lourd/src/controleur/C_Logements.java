package controleur;
import java.util.ArrayList; 
import modele.ModeleLogements;

public class C_Logements {

				public static void InsertLogements(Logements unLogement)
				{
					ModeleLogements.InsertLogements(unLogement);
				}
				public static ArrayList<Logements> SelectAllLogements ()
				{
					return ModeleLogements.SelectAllLogements();
				}
				public static void DeleteLogements(int logement_id)
				{
					ModeleLogements.DeleteLogements(logement_id);
				}
				public static void UpdateLogements(Logements unLogement)
				{
					ModeleLogements.UpdateLogements(unLogement);
				}
				public static Logements SelectWhereLogements(int logement_id)
				{
					return ModeleLogements.SelectWhereLogements(logement_id);
				}
}
