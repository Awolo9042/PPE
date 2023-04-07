package Vue;

import java.awt.Color; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Logements;
import controleur.C_Users;
import controleur.Logements;
import controleur.Tableau;
import controleur.Users;

public class PanelAdmin extends PanelPrincipal implements ActionListener{
			
			private JPanel panelForm = new JPanel();
			
			private JTextField txtNom = new JTextField();
			private JTextField txtPrenom = new JTextField();
			private JTextField txtEmail = new JTextField();
			private JTextField txtTelephone = new JTextField();
			
			private JButton btAnnuler = new JButton("Annuler");
			private JButton btEnregistrer = new JButton("Enregistrer");
	        private JButton btSupprimer = new JButton("Supprimer");
			
			JLabel NomClient = new JLabel("Nom de l'admin :");
			JLabel PrenomClient = new JLabel("Prénom de l'admin :");
			JLabel Email = new JLabel("Email :");
			JLabel Telephone = new JLabel("Téléphone :");
			JLabel Filtre = new JLabel("Filtrer par :");
			
			private JTable tableAdmin;
			private Tableau unTableau ;
			
			private JPanel panelFiltre = new JPanel();
	        private JTextField txtFiltre = new JTextField();
	        private JButton btFiltrer = new JButton("Filtrer");
			
			public PanelAdmin(){
				super();
				this.titre.setText("GESTION DES ADMINISTRATEURS");
				this.titre.setBounds(510, 10, 350, 40);
				
				//construction du panel Form
				this.panelForm.setBounds(60,115,450,350);
				this.panelForm.setBackground(new Color (17, 24, 39));
				this.panelForm.setLayout(new GridLayout(6,2, 10,10));
				this.panelForm.add(NomClient);
				this.panelForm.add(this.txtNom);
				NomClient.setForeground(Color.WHITE);
				this.panelForm.add(PrenomClient);
				this.panelForm.add(this.txtPrenom);
				PrenomClient.setForeground(Color.WHITE);
				this.panelForm.add(Email);
				this.panelForm.add(this.txtEmail);
				Email.setForeground(Color.WHITE);
				this.panelForm.add(Telephone);
				this.panelForm.add(this.txtTelephone);
				Telephone.setForeground(Color.WHITE);
				this.panelForm.add(this.btAnnuler);
				this.panelForm.add(this.btEnregistrer);
				this.panelForm.add(this.btSupprimer);
				//ajout du panelForm au panelClients
				this.add(this.panelForm);
				this.panelForm.setVisible(true);
				
				//construction de la JTbale 
				String entetes [] = {"ID Admin ", "Nom", "Prénom", "Email", "Téléphone"};
				Object [][] donnees = this.getDonnees ("");
		        
				this.unTableau = new Tableau (donnees, entetes);
				this.tableAdmin = new JTable(this.unTableau);
				JScrollPane uneScroll = new JScrollPane(this.tableAdmin); 
				uneScroll.setBounds(590, 80, 560, 420);
				this.add(uneScroll);
			
	            this.tableAdmin.addMouseListener(new MouseAdapter(){

	            @Override
	            public void mouseClicked(MouseEvent e ){
	                int numLigne = tableAdmin.getSelectedRow();
	               
	                if(e.getClickCount() == 1 ){
	                    //on remplie les champs pour la modification
	                    txtNom.setText(tableAdmin.getValueAt(numLigne, 1).toString());
	                    txtPrenom.setText(tableAdmin.getValueAt(numLigne, 2).toString());
	                    txtEmail.setText(tableAdmin.getValueAt(numLigne, 3).toString());
	                    txtTelephone.setText(tableAdmin.getValueAt(numLigne,4).toString());
	                    btEnregistrer.setText("Modifier");
	                }
	            }

	            @Override
	            public void mousePressed(MouseEvent e) {
	                // TODO Auto-generated method stub
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	                // TODO Auto-generated method stub
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // TODO Auto-generated method stub
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // TODO Auto-generated method stub
	            }});
				
				//placement du panel Filtre
	            this.panelFiltre.setBounds(672, 505, 350, 40);
	            this.panelFiltre.setBackground(new Color(17, 24, 39));
	            this.panelFiltre.setLayout(new GridLayout(1,3));
	            this.panelFiltre.add(Filtre);
	            Filtre.setForeground(Color.WHITE);
	            this.panelFiltre.add(this.txtFiltre);
	            this.panelFiltre.add(this.btFiltrer);
	            this.add(this.panelFiltre);

	            //rendre les boutons cliquables
	            this.btAnnuler.addActionListener(this);
	            this.btEnregistrer.addActionListener(this);
	            this.btFiltrer.addActionListener(this);
	            this.btSupprimer.addActionListener(this);
			}
			
			
			public Object[][] getDonneesFiltre(String filtre) {
			    ArrayList<Users> lesUsers = C_Users.selectAlluser();
			    ArrayList<Users> UsersFiltres = new ArrayList<>();

			    // Filtrer les administrateurs en fonction du texte de filtre
			    for (Users user : lesUsers) {
			        if (user.getnom().toLowerCase().contains(filtre.toLowerCase()) ||
			            user.getprenom().toLowerCase().contains(filtre.toLowerCase()) ||
			            user.getemail().toLowerCase().contains(filtre.toLowerCase()) ||
			            user.gettelephone().toLowerCase().contains(filtre.toLowerCase())) {
			        	UsersFiltres.add(user);
			        }
			    }

			    Object[][] matrice = new Object[UsersFiltres.size()][5];
			    int i = 0;
			    for (Users user : UsersFiltres) {
			        matrice[i][0] = user.getid_client();
			        matrice[i][1] = user.getnom();
			        matrice[i][2] = user.getprenom();
			        matrice[i][3] = user.getemail();
			        matrice[i][4] = user.gettelephone();
			        i++;
			    }

			    return matrice;
			}
				public Object[][] getDonnees(String filtre) {
				    ArrayList<Users> lesUsers = C_Users.selectAlluser();
				    Object[][] matrice = new Object[lesUsers.size()][5];
				    int i = 0;
				    for (Users unUser : lesUsers) {
				    	if(unUser.getrole_client().equals("admin")) {// Vérifie si l'utilisateur est un admin
				            matrice[i][0] = unUser.getid_client();
				            matrice[i][1] = unUser.getnom();
				            matrice[i][2] = unUser.getprenom();
				            matrice[i][3] = unUser.getemail();
				            matrice[i][4] = unUser.gettelephone();
				            i++;
				        }
				    }
				    // Réduire la taille de la matrice si elle contient des lignes vides
				    return Arrays.copyOf(matrice, i);
				}

			public void ViderChamps() {
				this.txtNom.setText("");
				this.txtPrenom.setText("");
				this.txtEmail.setText("");
				this.txtTelephone.setText("");
				btEnregistrer.setText("Enregistrer");
			}
			
			@Override
	        public void actionPerformed(ActionEvent e) {
	            if(e.getSource() == this.btAnnuler){
	            	this.ViderChamps();
	            }else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")){
	            	String nom = this.txtNom.getText();
					String prenom = this.txtPrenom.getText();
					String telephone = this.txtTelephone.getText();
					String email = this.txtEmail.getText();
					String role_client = "admin";

	                //instancier un objet de type Client
	                Users unUser = null; //new Users( nom, prenom, telephone, email, null, role_client);
	                //on l'enregistre dans la base de données
	                C_Users.insertUser(unUser);
	                //on recharge la JTable
	                Object ligne[] = {unUser.getid_client(), unUser.getnom(), 
							unUser.getprenom(), unUser.gettelephone(),
							unUser.getemail(),null,
							unUser.getrole_client()};
//	                this.unTableau.insertLigne(ligne);

	                JOptionPane.showMessageDialog(this, "Admin ajouté avec succès");
	                this.ViderChamps();
	            }
	            else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")){
	            	String nom = this.txtNom.getText();
					String prenom = this.txtPrenom.getText();
					String email = this.txtEmail.getText();
					String telephone = this.txtTelephone.getText();
	                int numLigne = tableAdmin.getSelectedRow();
	                int idclient = Integer.parseInt(tableAdmin.getValueAt(numLigne, 0).toString());

	                //instancier un objet de type Client
	                Users unUser = null; // new Users(idclient, nom, prenom, telephone, email, null, null);
	                //on l'update dans la base de données
	                C_Users.updateUser(unUser);
	                //on recharge la JTable
	                Object ligne[] = {unUser.getid_client(),unUser.getnom(),unUser.getprenom(),unUser.gettelephone(),unUser.getemail(), null, null};
	                this.unTableau.updateLigne(numLigne, ligne);

	                JOptionPane.showMessageDialog(this, "Admin modifié avec succès");
	                this.ViderChamps();
	            }
	            else if (e.getSource() == btSupprimer){
	            	int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cet admin ?","Suppression Admin",JOptionPane.YES_NO_OPTION);
                    if(retour == 0){
                    	String nom = this.txtNom.getText();
    					String prenom = this.txtPrenom.getText();
    					String telephone = this.txtTelephone.getText();
    					String email = this.txtEmail.getText();
	                int numLigne = tableAdmin.getSelectedRow();
	                int id_client = Integer.parseInt(tableAdmin.getValueAt(numLigne, 0).toString());

	                //instancier un objet de type Client
	                Users unUser = null ;//new Users(nom, prenom, telephone, email, null, null);
	                //on l'update dans la base de données
	                C_Users.deleteUser(id_client);
	                //on recharge la JTable
	                Object ligne[] = {unUser.getnom(),unUser.getprenom(),unUser.gettelephone(),unUser.getemail()};
	                this.unTableau.updateLigne(numLigne, ligne);

	                JOptionPane.showMessageDialog(this, "Admin supprimé avec succès");
	                this.ViderChamps();
                    }
	            }
	            else if(e.getSource() == this.btFiltrer){
	                String filtre = this.txtFiltre.getText();
	                //récupération des clients de la base de données
	                Object données[][] = this.getDonnees(filtre);
	                this.unTableau.setDonnees(données);
	            }
	        }
}












