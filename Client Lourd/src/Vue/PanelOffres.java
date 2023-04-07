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

import controleur.C_Offres;
import controleur.Offres;
import controleur.Tableau;

public class PanelOffres extends PanelPrincipal implements ActionListener{
			
	private JPanel panelForm = new JPanel();
	private JPanel panelFiltre = new JPanel();
	
    private JTextField txtFiltre = new JTextField();
	private JTextField txtoffre_prix = new JTextField();
	private JTextField txtdestination = new JTextField();
	private JTextField txttransports = new JTextField();
	private JTextField txtlogements = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
    private JButton btFiltrer = new JButton("Filtrer");
    private JButton btSupprimer = new JButton("Supprimer");
    
	JLabel Filtre = new JLabel("Filtrer par :");			
	JLabel PrixOffre = new JLabel("Prix de l'offre :");
	JLabel Destinations = new JLabel("Destination :");
	JLabel Logements = new JLabel("Logement :");
	JLabel Transports = new JLabel("Transport :");
	
	private JTable tableOffre;
	private Tableau unTableau ;
	
	public PanelOffres(){
		super();
		this.titre.setText("GESTION DES OFFRES");
		this.titre.setBounds(530, 10, 350, 40);
		
		//construction du panel Form
		this.panelForm.setBounds(60,135,450,350);
		this.panelForm.setBackground(new Color (17, 24, 39));
		this.panelForm.setLayout(new GridLayout(7,2, 10,10));
		this.panelForm.add(Destinations);
		this.panelForm.add(this.txtdestination);
		Destinations.setForeground(Color.WHITE);
		this.panelForm.add(Transports);
		this.panelForm.add(this.txttransports);
		Transports.setForeground(Color.WHITE);
		this.panelForm.add(Logements);
		this.panelForm.add(this.txtlogements);
		Logements.setForeground(Color.WHITE);
		this.panelForm.add(PrixOffre);
		this.panelForm.add(this.txtoffre_prix);
		PrixOffre.setForeground(Color.WHITE);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.add(this.btSupprimer);
		//ajout du panelForm au panel Offres
		this.add(this.panelForm);
		this.panelForm.setVisible(true);
		
		//construction de la JTbale 
		String entetes [] = {"ID offres ", "Destination", "Transports", "Logements", "Prix de l'offre"};
		Object [][] donnees = this.getDonnees (""); 
		
		this.unTableau = new Tableau (donnees, entetes);
		this.tableOffre = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.tableOffre); 
		uneScroll.setBounds(590, 80, 560, 420);
		this.add(uneScroll);
		
		this.tableOffre.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e ){
                int numLigne = tableOffre.getSelectedRow();     
                if(e.getClickCount() == 1 ){
                    //on remplie les champs pour la modification
                	txtoffre_prix.setText(tableOffre.getValueAt(numLigne, 1).toString());
                	txtdestination.setText(tableOffre.getValueAt(numLigne, 2).toString());
                	txttransports.setText(tableOffre.getValueAt(numLigne, 3).toString());
                	txtlogements.setText(tableOffre.getValueAt(numLigne, 4).toString());
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
		ArrayList<Offres> lesOffres = C_Offres.SelectAllOffres(); 
		Object [][] matrice = new Object [lesOffres.size()][5]; 
		int i=0; 
		for (Offres uneOffre : lesOffres)
		{
			matrice[i][0]  = uneOffre.getoffre_id(); 
			matrice[i][1]  = uneOffre.getdestination_id(); 
			matrice[i][2]  = uneOffre.gettransport_id();
			matrice[i][3]  = uneOffre.getlogement_id();
			matrice[i][4]  = uneOffre.getprice();
			i++;
		}
		return matrice;
	}

	public void ViderChamps() {
		this.txtdestination.setText("");
		this.txttransports.setText("");
		this.txtlogements.setText("");
		this.txtoffre_prix.setText("");		
		btEnregistrer.setText("Enregistrer");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.ViderChamps();
		}else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")){
			String destination_id = this.txtdestination.getText();
			String transport_id = this.txttransports.getText();
			String logement_id = this.txtlogements.getText();
			String price = this.txtoffre_prix.getText();
			
			//instancier une offre
			Offres uneOffre = new Offres( destination_id, transport_id, logement_id, price);
		     
			// on l'enregistre dans la db
			C_Offres.InsertOffres(uneOffre); 
			
			//ajout de l'offre dans le Tableau 
			Object ligne[] = {uneOffre.getoffre_id(), uneOffre.getdestination_id(), 
					uneOffre.gettransport_id(), uneOffre.getlogement_id(), uneOffre.getprice()};
			this.unTableau.insertLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Offres ajoutée avec succès");
			this.ViderChamps();
			
		}
        else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")){
        	String destination_id = this.txtdestination.getText();
			String transport_id = this.txttransports.getText();
			String logement_id = this.txtlogements.getText();
			String price = this.txtoffre_prix.getText();
            int numLigne = tableOffre.getSelectedRow();

            //instancier un objet de type Offres
			Offres uneOffre = new Offres(destination_id, transport_id, logement_id, price);
            //on l'update dans la base de données
            C_Offres.UpdateOffres(uneOffre);
            //on recharge la JTable
            Object ligne[] = {uneOffre.getoffre_id(),uneOffre.getdestination_id(),uneOffre.gettransport_id(),uneOffre.getlogement_id(),uneOffre.getprice()};
            this.unTableau.updateLigne(numLigne, ligne);

            JOptionPane.showMessageDialog(this, "Offre modifiée avec succès");
            this.ViderChamps();
        }
        else if (e.getSource() == btSupprimer){
        	int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cette offre ?","Suppression Offre",JOptionPane.YES_NO_OPTION);
            if(retour == 0){
            String destination_id = this.txtdestination.getText();
			String transport_id = this.txttransports.getText();
			String logement_id = this.txtlogements.getText();
			String price = this.txtoffre_prix.getText();
            int numLigne = tableOffre.getSelectedRow();
            int offre_id = Integer.parseInt(tableOffre.getValueAt(numLigne, 0).toString());

            //instancier un objet de type Offre
            Offres uneOffre = new Offres(destination_id, transport_id, logement_id, price);
            //on l'update dans la base de données
            C_Offres.DeleteOffres(offre_id);
            //on recharge la JTable
            Object ligne[] = {uneOffre.getoffre_id(),uneOffre.getdestination_id(),uneOffre.gettransport_id(),uneOffre.getlogement_id(),uneOffre.getprice()};
            this.unTableau.updateLigne(numLigne, ligne);

            JOptionPane.showMessageDialog(this, "Offre supprimée avec succès");
            this.ViderChamps();
            }
        }
        else if(e.getSource() == this.btFiltrer){
            String filtre = this.txtFiltre.getText();
            //récupération des offres de la base de données
            Object données[][] = this.getDonnees(filtre);
            this.unTableau.setDonnees(données);
        }
		
	}
}
