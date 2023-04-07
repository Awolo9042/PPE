package Vue;

import java.awt.Color; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Logements;
import controleur.Logements;
import controleur.Tableau;

public class PanelLogements extends PanelPrincipal implements ActionListener{
			
			private JPanel panelForm = new JPanel();
			private JPanel panelFiltre = new JPanel();
			
	        private JTextField txtFiltre = new JTextField();;
			private JTextField txtlogements_name = new JTextField();
			private JTextField txtlogements_prix = new JTextField();
			
			private JButton btAnnuler = new JButton("Annuler");
			private JButton btEnregistrer = new JButton("Enregistrer");
	        private JButton btFiltrer = new JButton("Filtrer");
	        private JButton btSupprimer = new JButton("Supprimer");
	        
			JLabel Filtre = new JLabel("Filtrer par :");			
			JLabel NomLogements = new JLabel("Nom du logement :");
			JLabel PrixLogements = new JLabel("Prix du logement :");
			
			private JTable tableLogements;
			private Tableau unTableau ;
			
			public PanelLogements(){
				super();
				this.titre.setText("GESTION DES LOGEMENTS");
				this.titre.setBounds(530, 10, 350, 40);
				
				//construction du panel Form
				this.panelForm.setBounds(60,155,450,350);
				this.panelForm.setBackground(new Color (17, 24, 39));
				this.panelForm.setLayout(new GridLayout(5,2, 10,10));
				this.panelForm.add(NomLogements);
				this.panelForm.add(this.txtlogements_name);
				NomLogements.setForeground(Color.WHITE);
				this.panelForm.add(PrixLogements);
				this.panelForm.add(this.txtlogements_prix);
				PrixLogements.setForeground(Color.WHITE);
				this.panelForm.add(this.btAnnuler);
				this.panelForm.add(this.btEnregistrer);
				this.panelForm.add(this.btSupprimer);
				//ajout du panelForm au panelClients
				this.add(this.panelForm);
				this.panelForm.setVisible(true);
				
				//construction de la JTbale 
				String entetes [] = {"ID Logements ", "logements_name", "logements_prix"};
				Object [][] donnees = this.getDonnees (""); 
				
				this.unTableau = new Tableau (donnees, entetes);
				this.tableLogements = new JTable(this.unTableau);
				JScrollPane uneScroll = new JScrollPane(this.tableLogements); 
				uneScroll.setBounds(590, 80, 560, 420);
				this.add(uneScroll);
				
				this.tableLogements.addMouseListener(new MouseAdapter(){

		            @Override
		            public void mouseClicked(MouseEvent e ){
		                int numLigne = tableLogements.getSelectedRow();
		                int logements_id = Integer.parseInt(tableLogements.getValueAt(numLigne, 0).toString());
		               
		                if(e.getClickCount() ==2){
		                    int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ce logement ?","Suppression Logement",JOptionPane.YES_NO_OPTION);
		                    if(retour == 0){
		                        C_Logements.DeleteLogements(logements_id);
		                        unTableau.deleteLigne(numLigne);
		                        JOptionPane.showMessageDialog(null, "Logement supprimé avec succès");
		                    }
		                }else if(e.getClickCount() == 1 ){
		                    //on remplie les champs pour la modification
		                    txtlogements_name.setText(tableLogements.getValueAt(numLigne, 1).toString());
		                    txtlogements_prix.setText(tableLogements.getValueAt(numLigne, 2).toString());
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
	            this.btSupprimer.addActionListener(this);
	            this.btFiltrer.addActionListener(this);
			}
			
			public Object [][] getDonnees(String filtre)
			{
				ArrayList<Logements> lesLogements = C_Logements.SelectAllLogements(); 
				Object [][] matrice = new Object [lesLogements.size()][3]; 
				int i=0; 
				for (Logements unLogement : lesLogements)
				{
					matrice[i][0]  = unLogement.getlogement_id(); 
					matrice[i][1]  = unLogement.getlogement_name(); 
					matrice[i][2]  = unLogement.getlogement_prix();
					i++;
				}
				return matrice;
			}

			public void ViderChamps() {
				this.txtlogements_name.setText("");
				this.txtlogements_prix.setText("");
				btEnregistrer.setText("Enregistrer");
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == this.btAnnuler) {
					this.ViderChamps();
				}else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")){
					String logements_name = this.txtlogements_name.getText();
					String logements_prix = this.txtlogements_prix.getText();
					
					//instancier un client
					Logements unLogement = new Logements( logements_name, logements_prix);
				     
					// on l'enregistre dans la db
					C_Logements.InsertLogements(unLogement); 
					
					//ajout du client dans le Tableau 
					Object ligne[] = {unLogement.getlogement_id(), unLogement.getlogement_name(), 
							unLogement.getlogement_prix()};
					this.unTableau.insertLigne(ligne);
					
					JOptionPane.showMessageDialog(this, "Logement ajouté avec succès");
					this.ViderChamps();
					
				}
	            else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")){
	            	String logements_name = this.txtlogements_name.getText();
					String logements_prix = this.txtlogements_prix.getText();
	                int numLigne = tableLogements.getSelectedRow();
	                int logements_id = Integer.parseInt(tableLogements.getValueAt(numLigne, 0).toString());

	                //instancier un objet de type Client
	                Logements unLogement = new Logements(logements_id, logements_name, logements_prix);
	                //on l'update dans la base de données
	                C_Logements.UpdateLogements(unLogement);
	                //on recharge la JTable
	                Object ligne[] = {unLogement.getlogement_id(),unLogement.getlogement_name(),unLogement.getlogement_prix()};
	                this.unTableau.updateLigne(numLigne, ligne);

	                JOptionPane.showMessageDialog(this, "Logement modifié avec succès");
	                this.ViderChamps();
	            }
	            else if (e.getSource() == btSupprimer){
	            	int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ce logement ?","Suppression Logement",JOptionPane.YES_NO_OPTION);
                    if(retour == 0){
	            	String logements_name = this.txtlogements_name.getText();
					String logements_prix = this.txtlogements_prix.getText();
	                int numLigne = tableLogements.getSelectedRow();
	                int logements_id = Integer.parseInt(tableLogements.getValueAt(numLigne, 0).toString());

	                //instancier un objet de type Client
	                Logements unLogement = new Logements(logements_id, logements_name, logements_prix);
	                //on l'update dans la base de données
	                C_Logements.DeleteLogements(logements_id);
	                //on recharge la JTable
	                Object ligne[] = {unLogement.getlogement_id(),unLogement.getlogement_name(),unLogement.getlogement_prix()};
	                this.unTableau.updateLigne(numLigne, ligne);

	                JOptionPane.showMessageDialog(this, "Logement supprimé avec succès");
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
