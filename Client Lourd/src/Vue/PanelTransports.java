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
import controleur.C_Transports;
import controleur.Logements;
import controleur.Tableau;
import controleur.Transports;

public class PanelTransports extends PanelPrincipal implements ActionListener{
			
			private JPanel panelForm 				= new JPanel();
			private JPanel panelFiltre 				= new JPanel();
			
			private JTextField txttransport_name 	= new JTextField();
			private JTextField txttransport_prix 	= new JTextField();
	        private JTextField txtFiltre 			= new JTextField();
			
			private JButton btAnnuler 				= new JButton("Annuler");
			private JButton btEnregistrer 			= new JButton("Enregistrer");
	        private JButton btSupprimer 			= new JButton("Supprimer");
	        private JButton btFiltrer 				= new JButton("Filtrer");

			JLabel Filtre 							= new JLabel("Filtrer par :");			
			JLabel NomTransport 					= new JLabel("Nom du transport :");
			JLabel PrixTransport 					= new JLabel("Prix du transport :");
			
			private JTable tableTransport;
			private Tableau unTableau ;
			
			public PanelTransports(){
				super();
				this.titre.setText("GESTION DES TRANSPORTS");
				this.titre.setBounds(530, 10, 350, 40);
				
				//construction du panel Form
				this.panelForm.setBounds(60,155,450,350);
				this.panelForm.setBackground(new Color (17, 24, 39));
				this.panelForm.setLayout(new GridLayout(5,2, 10,10));
				this.panelForm.add(NomTransport);
				this.panelForm.add(this.txttransport_name);
				NomTransport.setForeground(Color.WHITE);
				this.panelForm.add(PrixTransport);
				this.panelForm.add(this.txttransport_prix);
				PrixTransport.setForeground(Color.WHITE);
				this.panelForm.add(this.btAnnuler);
				this.panelForm.add(this.btEnregistrer);
				this.panelForm.add(this.btSupprimer);
				//ajout du panelForm au panelTransports
				this.add(this.panelForm);
				this.panelForm.setVisible(true);
				
				//construction de la JTbale 
				String entetes [] = {"ID Transports ", "transport_name", "transport_prix"};
				Object [][] donnees = this.getDonnees (""); 
				
				this.unTableau = new Tableau (donnees, entetes);
				this.tableTransport = new JTable(this.unTableau);
				JScrollPane uneScroll = new JScrollPane(this.tableTransport); 
				uneScroll.setBounds(590, 80, 560, 420);
				this.add(uneScroll);
				
				this.tableTransport.addMouseListener(new MouseAdapter(){

		            @Override
		            public void mouseClicked(MouseEvent e ){
		                int numLigne = tableTransport.getSelectedRow();
		                int transport_id = Integer.parseInt(tableTransport.getValueAt(numLigne, 0).toString());
		               
		                if(e.getClickCount() ==2){
		                    int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ce transport ?","Suppression Transport",JOptionPane.YES_NO_OPTION);
		                    if(retour == 0){
		                        C_Transports.deletetransports(transport_id);
		                        unTableau.deleteLigne(numLigne);
		                        JOptionPane.showMessageDialog(null, "Transport supprimé avec succès");
		                    }
		                }else if(e.getClickCount() == 1 ){
		                    //on remplie les champs pour la modification
		                    txttransport_name.setText(tableTransport.getValueAt(numLigne, 1).toString());
		                    txttransport_prix.setText(tableTransport.getValueAt(numLigne, 2).toString());
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
			
			public Object [][] getDonnees(String filtre)
			{
				ArrayList<Transports> lesTransports = C_Transports.selectAllTransports(); 
				Object [][] matrice = new Object [lesTransports.size()][3]; 
				int i=0; 
				for (Transports unTransport : lesTransports)
				{
					matrice[i][0]  = unTransport.gettransport_id(); 
					matrice[i][1]  = unTransport.gettransport_name(); 
					matrice[i][2]  = unTransport.gettransport_prix();
					i++;
				}
				return matrice;
			}

			public void ViderChamps() {
				this.txttransport_name.setText("");
				this.txttransport_prix.setText("");
				btEnregistrer.setText("Enregistrer");
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == this.btAnnuler) {
					this.ViderChamps();
				}else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")){
					String transport_name = this.txttransport_name.getText();
					String transport_prix = this.txttransport_prix.getText();
					
					//instancier un transport
					Transports unTransport = new Transports( transport_name, transport_prix);
				     
					// on l'enregistre dans la db
					C_Transports.inserttransport(unTransport); 
					
					//ajout du transport dans le Tableau 
					Object ligne[] = {unTransport.gettransport_id(), unTransport.gettransport_name(), 
							unTransport.gettransport_prix()};
					this.unTableau.insertLigne(ligne);
					
					JOptionPane.showMessageDialog(this, "Transport ajouté avec succès");
					this.ViderChamps();
					
				}
	            else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")){
	            	String transport_name = this.txttransport_name.getText();
					String transport_prix = this.txttransport_prix.getText();
	                int numLigne = tableTransport.getSelectedRow();
	                int transport_id = Integer.parseInt(tableTransport.getValueAt(numLigne, 0).toString());

	                //instancier un objet de type transport
	                Transports unTransport = new Transports(transport_id, transport_name, transport_prix);
	                //on l'update dans la base de données
	                C_Transports.updatetransports(unTransport);
	                //on recharge la JTable
	                Object ligne[] = {unTransport.gettransport_id(),unTransport.gettransport_name(),unTransport.gettransport_prix()};
	                this.unTableau.updateLigne(numLigne, ligne);

	                JOptionPane.showMessageDialog(this, "Transport modifié avec succès");
	                this.ViderChamps();
	            }
	            else if (e.getSource() == btSupprimer){
	            	int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ce transport ?","Suppression transport",JOptionPane.YES_NO_OPTION);
                    if(retour == 0){
	            	String transport_name = this.txttransport_name.getText();
					String transport_prix = this.txttransport_prix.getText();
	                int numLigne = tableTransport.getSelectedRow();
	                int transport_id = Integer.parseInt(tableTransport.getValueAt(numLigne, 0).toString());

	                //instancier un objet de type transport
	                Transports unTransport = new Transports(transport_id, transport_name, transport_prix);
	                //on l'update dans la base de données
	                C_Transports.updatetransports(unTransport);
	                //on recharge la JTable
	                Object ligne[] = {unTransport.gettransport_id(),unTransport.gettransport_name(),unTransport.gettransport_prix()};
	                this.unTableau.updateLigne(numLigne, ligne);

	                JOptionPane.showMessageDialog(this, "Transport modifié avec succès");
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
