package controleur;
import java.util.Date;

public class Reservations {
				private int reservation_id;
				private String destination_id, date_depart, date_retour, nb_personnes, logement_id, transport_id, reservations_prix, id_client, email;

				public Reservations(int reservation_id, String destination_id, String date_depart, String date_retour, String nb_personnes, String logement_id, String transport_id, String reservations_prix, String id_client, String email) {
					this.reservation_id = reservation_id;
					this.destination_id = destination_id;
					this.date_depart = date_depart;
					this.date_retour = date_retour;
					this.nb_personnes = nb_personnes;
					this.logement_id = logement_id;
					this.transport_id = transport_id;
					this.reservations_prix = reservations_prix;
					this.id_client = id_client;
					this.email = email;
				}

				public Reservations(String destination_id, String date_depart, String date_retour, String nb_personnes, String logement_id, String transport_id, String reservations_prix, String id_client, String email) {
					this.reservation_id = 0;
					this.destination_id = destination_id;
					this.date_depart = date_depart;
					this.date_retour = date_retour;
					this.nb_personnes = nb_personnes;
					this.logement_id = logement_id;
					this.transport_id = transport_id;
					this.reservations_prix = reservations_prix;
					this.id_client = id_client;
					this.email = email;
				}

				public Reservations() {
					this.reservation_id = 0;
					this.destination_id = "";
					this.date_depart = "";
					this.date_retour = "";
					this.nb_personnes = "";
					this.logement_id = "";
					this.transport_id = "";
					this.reservations_prix = "";
					this.id_client = "";
					this.email = "";
				}

				// Getters and Setters
				public int getreservation_id() {
					return reservation_id;
				}

				public void setreservation_id(int reservation_id) {
					this.reservation_id = reservation_id;
				}
				
				public String getdestination_id() {
					return destination_id;
				}

				public void setdestination_id(String destination_id) {
					this.destination_id = destination_id;
				}

				public String getdate_depart() {
					return date_depart;
				}

				public void setdate_depart(String date_depart) {
					this.date_depart = date_depart;
				}

				public String getdate_retour() {
					return date_retour;
				}

				public void setdate_retour(String date_retour) {
					this.date_retour = date_retour;
				}
				
				public String getnb_personnes() {
					return nb_personnes;
				}

				public void setnb_personnes(String nb_personnes) {
					this.nb_personnes = nb_personnes;
				}
				
				public String getlogement_id() {
					return logement_id;
				}

				public void setlogement_id(String logement_id) {
					this.logement_id = logement_id;
				}
				
				public String getreservations_prix() {
					return reservations_prix;
				}

				public void setreservations_prix(String reservations_prix) {
					this.reservations_prix = reservations_prix;
				}
				
				public String getid_client() {
					return id_client;
				}

				public void setid_client(String id_client) {
					this.id_client = id_client;
				}
				
				public String getemail() {
					return email;
				}

				public void setemail(String email) {
					this.email = email;
				}

				public Object gettransport_id() {
					// TODO Auto-generated method stub
					return transport_id;
				}
				public void settransport_id(String transport_id) {
					this.transport_id = transport_id;
				}
}
