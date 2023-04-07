package controleur;
import java.util.ArrayList;
import modele.ModelePromotions;

public class C_Promotions {

			public static void InsertPromotions(Promotions unePromotion)
			{
				ModelePromotions.InsertPromotions(unePromotion);
			}
			public static ArrayList<Promotions> SelectAllPromotions ()
			{
				return ModelePromotions.SelectAllPromotions();
			}
			public static void DeletePromotions(int promotions_id)
			{
				ModelePromotions.DeletePromotions(promotions_id);
			}
			public static void UpdatePromotions(Promotions unePromotion)
			{
				ModelePromotions.UpdatePromotions(unePromotion);
			}
			public static Promotions SelectWherePromotions(int promotions_id)
			{
				return ModelePromotions.SelectWherePromotions(promotions_id);
			}
}
