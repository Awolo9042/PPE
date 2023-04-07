package controleur;
import java.util.ArrayList;
import modele.ModeleOffres;

public class C_Offres {

			public static void InsertOffres(Offres uneOffre)
			{
				ModeleOffres.InsertOffres(uneOffre);
			}
			public static ArrayList<Offres> SelectAllOffres ()
			{
				return ModeleOffres.SelectAllOffres();
			}
			public static ArrayList<Offres> SelectAllOffres(String filtre) 
			{
		        return ModeleOffres.SelectAllOffres(filtre);
		    }
			public static void DeleteOffres(int offre_id)
			{
				ModeleOffres.DeleteOffres(offre_id);
			}
			public static void UpdateOffres(Offres uneOffre)
			{
				ModeleOffres.UpdateOffres(uneOffre);
			}
			public static Offres SelectWhereOffres(int offre_id)
			{
				return ModeleOffres.SelectWhereOffres(offre_id);
			}
}
