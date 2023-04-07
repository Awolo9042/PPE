package controleur;

public class Transports {
				private int transport_id;
				private String transport_name, transport_prix;

				public Transports(int transport_id, String transport_name, String transport_prix) {
					this.transport_id = transport_id;
					this.transport_name = transport_name;
					this.transport_prix = transport_prix;
				}

				public Transports(String transport_name, String transport_prix) {
					this.transport_id = 0;
					this.transport_name = transport_name;
					this.transport_prix = transport_prix;
				}

				public Transports() {
					this.transport_id = 0;
					this.transport_name = "";
					this.transport_prix = "";
				}

				// Getters and Setters
				public int gettransport_id() {
					return transport_id;
				}

				public void settransport_id(int transport_id) {
					this.transport_id = transport_id;
				}

				public String gettransport_name() {
					return transport_name;
				}

				public void settransport_name(String transport_name) {
					this.transport_name = transport_name;
				}

				public String gettransport_prix() {
					return transport_prix;
				}

				public void settransport_prix(String transport_prix) {
					this.transport_prix = transport_prix;
				}
}
