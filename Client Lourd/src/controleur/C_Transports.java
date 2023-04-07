package controleur;
import java.util.ArrayList; 
import modele.ModeleTransports;

public class C_Transports {

				public static void inserttransport(Transports unTransport)
				{
					ModeleTransports.inserttransports(unTransport);
				}
				public static ArrayList<Transports> selectAllTransports ()
				{
					return ModeleTransports.selectAllTransports();
				}
				public static void deletetransports(int transport_id)
				{
					ModeleTransports.deletetransports(transport_id);
				}
				public static void updatetransports(Transports unTransport)
				{
					ModeleTransports.updatetransports(unTransport);
				}
				public static Transports selectWhereTransport(int transport_id)
				{
					return ModeleTransports.selectWhereTransport(transport_id);
				}
}
