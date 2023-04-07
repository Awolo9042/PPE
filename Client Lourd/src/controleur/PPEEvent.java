package controleur;
import Vue.VueConnexion;
import Vue.VueGenerale;

public class PPEEvent {
	private static VueGenerale uneVueGenerale;
	private static VueConnexion uneVueConnexion;
		
	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
		uneVueGenerale = new VueGenerale();
	}
	public static void rendreVisibleVueGenerale (boolean action)
	{
		uneVueGenerale.setVisible(action);
	}
	public static void rendreVisibleVueConnexion (boolean action)
	{
		uneVueConnexion.setVisible(action);
	}
}

