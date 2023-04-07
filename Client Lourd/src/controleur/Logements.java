package controleur;

public class Logements {
			private int logement_id;
			private String logement_name, logement_prix;

			public Logements(int logement_id, String logement_name, String logement_prix) {
				this.logement_id = logement_id;
				this.logement_name = logement_name;
				this.logement_prix = logement_prix;
			}

			public Logements(String logement_name, String logement_prix) {
				this.logement_id = 0;
				this.logement_name = logement_name;
				this.logement_prix = logement_prix;
			}

			public Logements() {
				this.logement_id = 0;
				this.logement_name = "";
				this.logement_prix = "";
			}

			// Getters and Setters
			public int getlogement_id() {
				return logement_id;
			}

			public void setlogement_id(int logement_id) {
				this.logement_id = logement_id;
			}

			public String getlogement_name() {
				return logement_name;
			}

			public void setlogement_name(String logement_name) {
				this.logement_name = logement_name;
			}

			public String getlogement_prix() {
				return logement_prix;
			}

			public void setlogement_prix(String logement_prix) {
				this.logement_prix = logement_prix;
			}
}
