package controleur;

public class Users {
		private int id_client;
		private String nom, prenom, telephone, email, mdp, role_client, statut, siret;

		public Users(int idclient, String nom, String prenom, String telephone, String email, String mdp, String role_client, String statut, String siret) {
			this.id_client = idclient;
			this.nom = nom;
			this.prenom = prenom;
			this.telephone = telephone;
			this.email = email;
			this.mdp = mdp;
			this.role_client = role_client;
			this.statut = statut;
			this.siret = siret;
		}

		public Users(String nom, String prenom, String telephone, String email,String mdp,String role_client, String statut, String siret) {
			this.id_client = 0;
			this.nom = nom;
			this.prenom = prenom;
			this.telephone = telephone;
			this.email = email;
			this.mdp = mdp;
			this.role_client = role_client;
			this.statut = statut;
			this.siret = siret;
		}

		public Users() {
			this.id_client = 0;
			this.nom = "";
			this.prenom = "";
			this.telephone = "";
			this.email = "";
			this.mdp = "";
			this.role_client = "";
			this.statut = "";
			this.siret = "";
		}

		// Getters and Setters
		public int getid_client() {
			return id_client;
		}

		public void setid_client(int id_client) {
			this.id_client = id_client;
		}

		public String getnom() {
			return nom;
		}

		public void setnom(String nom) {
			this.nom = nom;
		}

		public String getprenom() {
			return prenom;
		}

		public void setprenom(String prenom) {
			this.prenom = prenom;
		}

		public String gettelephone() {
			return telephone;
		}

		public void settelephone(String telephone) {
			this.telephone = telephone;
		}
		
		public String getemail() {
			return email;
		}

		public void setemail(String email) {
			this.email = email;
		}

		public String getmdp() {
			return mdp;
		}

		public void setmdp(String mdp) {
			this.mdp = mdp;
		}
		public String getrole_client() {
			return role_client;
		}

		public void setrole_client(String role_client) {
			this.role_client = role_client;
		}
		public String getstatut() {
			return statut;
		}

		public void setstatut(String statut) {
			this.statut = statut;
		}
		public String getsiret() {
			return siret;
		}

		public void setsiret(String siret) {
			this.siret = siret;
		}
}
