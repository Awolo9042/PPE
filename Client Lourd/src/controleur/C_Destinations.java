package controleur;
import java.util.ArrayList; 
import modele.ModeleDestinations;

public class C_Destinations {

				public static void InsertDestinations(Destinations uneDestination)
				{
					ModeleDestinations.insertdestinations(uneDestination);
				}
				public static ArrayList<Destinations> selectAllDestination ()
				{
					return ModeleDestinations.selectAllDestination();
				}
				public static void deletedestinations(int destination_id)
				{
					ModeleDestinations.deletedestinations(destination_id);
				}
				public static void updatedestinations(Destinations uneDestination)
				{
					ModeleDestinations.updatedestinations(uneDestination);
				}
				public static Destinations selectWhereDestination(int destination_id)
				{
					return ModeleDestinations.selectWhereDestination(destination_id);
				}
				public static Destinations selectWhereDestination(String destination_name)
				{
					return ModeleDestinations.selectWhereDestination(destination_name);
				}
}