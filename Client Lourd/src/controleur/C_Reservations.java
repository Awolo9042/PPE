package controleur;
import java.util.ArrayList;
import modele.ModeleReservations;

public class C_Reservations {

			public static void insertReservations(Reservations uneReservation)
			{
				ModeleReservations.insertReservations(uneReservation);
			}
			public static ArrayList<Reservations> selectAllReservations ()
			{
				return ModeleReservations.selectAllReservations();
			}
			public static void deleteReservations(int reservation_id)
			{
				ModeleReservations.deleteReservations(reservation_id);
			}
			public static void updateReservations(Reservations uneReservation)
			{
				ModeleReservations.updateReservations(uneReservation);
			}
			public static Reservations selectWhereReservations(int reservation_id)
			{
				return ModeleReservations.selectWhereReservations(reservation_id);
			}
}
