package controleur;

public class Promotions {
				private int promotion_id;
				private String offre_id, date_debut, date_fin, pourcentage;

				public Promotions(int promotion_id, String offre_id, String date_debut, String date_fin, String pourcentage) {
					this.promotion_id = promotion_id;
					this.offre_id = offre_id;
					this.date_debut = date_debut;
					this.date_fin = date_fin;
					this.pourcentage = pourcentage;
				}

				public Promotions(String offre_id, String date_debut, String date_fin, String pourcentage) {
					this.promotion_id = 0;
					this.offre_id = offre_id;
					this.date_debut = date_debut;
					this.date_fin = date_fin;
					this.pourcentage = pourcentage;
				}

				public Promotions() {
					this.promotion_id = 0;
					this.offre_id = "";
					this.date_debut = "";
					this.date_fin = "";
					this.pourcentage = "";
				}

				// Getters and Setters
				public int getpromotion_id() {
					return promotion_id;
				}

				public void setpromotion_id(int promotion_id) {
					this.promotion_id = promotion_id;
				}

				public String getoffre_id() {
					return offre_id;
				}

				public void setoffre_id(String offre_id) {
					this.offre_id = offre_id;
				}

				public String getdate_debut() {
					return date_debut;
				}

				public void setdate_debut(String date_debut) {
					this.date_debut = date_debut;
				}
				public String getdate_fin() {
					return date_fin;
				}

				public void setdate_fin(String date_fin) {
					this.date_fin = date_fin;
				}
				public String getpourcentage() {
					return pourcentage;
				}

				public void setpourcentage(String pourcentage) {
					this.pourcentage = pourcentage;
				}
}
