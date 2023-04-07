package controleur;

public class Offres {
				private int offre_id;
				private String price, destination_id, transport_id, logement_id;

				public Offres(int offre_id, String destination_id, String transport_id, String logement_id, String price) {
					this.offre_id = offre_id;
					this.destination_id = destination_id;
					this.transport_id = transport_id;
					this.logement_id = logement_id;
					this.price = price;
				}

				public Offres(String destination_id, String transport_id, String logement_id, String price) {
					this.offre_id = 0;
					this.destination_id = destination_id;
					this.transport_id = transport_id;
					this.logement_id = logement_id;
					this.price = price;
				}

				public Offres() {
					this.offre_id = 0;
					this.destination_id = "";
					this.transport_id = "";
					this.logement_id = "";
					this.price = "";
				}

				// Getters and Setters
				public int getoffre_id() {
					return offre_id;
				}

				public void setoffre_id(int offre_id) {
					this.offre_id = offre_id;
				}

				public String getdestination_id() {
					return destination_id;
				}

				public void setdestination_id(String destination_id) {
					this.destination_id = destination_id;
				}

				public String gettransport_id() {
					return transport_id;
				}

				public void settransport_id(String transport_id) {
					this.transport_id = transport_id;
				}
				public String getlogement_id() {
					return logement_id;
				}

				public void setlogement_id(String logement_id) {
					this.logement_id = logement_id;
				}
				public String getprice() {
					return price;
				}

				public void setprice(String price) {
					this.price = price;
				}
}
