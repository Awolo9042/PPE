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

import controleur.C_Promotions;
import controleur.Promotions;
import controleur.Tableau;

public class PanelPromotions extends PanelPrincipal implements ActionListener{
			
	private JPanel panelForm 			= new JPanel();
	private JPanel panelFiltre 			= new JPanel();
	
    private JTextField txtFiltre 		= new JTextField();
	private JTextField txtoffre 		= new JTextField();
	private JTextField txtdate_debut 	= new JTextField();
	private JTextField txtdate_fin 		= new JTextField();
	private JTextField txtpourcentage 	= new JTextField();
	
	private JButton btAnnuler 			= new JButton("Annuler");
	private JButton btEnregistrer 		= new JButton("Enregistrer");
    private JButton btFiltrer 			= new JButton("Filtrer");
    private JButton btSupprimer 		= new JButton("Supprimer");
    
	JLabel Filtre 						= new JLabel("Filtrer par :");			
	JLabel Offre 						= new JLabel("Offre :");
	JLabel DateDebut 					= new JLabel("Date de début :");
	JLabel DateFin 						= new JLabel("Date de retour :");
	JLabel Pourcentage 					= new JLabel("Pourcentage de remise :");
	
	private JTable tablePromotions;
	private Tableau unTableau ;
	
	public PanelPromotions(){
		super();
		this.titre.setText("GESTION DES PROMOTIONS");
		this.titre.setBounds(525, 10, 350, 40);
		
		//construction du panel Form
		this.panelForm.setBounds(60,135,450,350);
		this.panelForm.setBackground(new Color (17, 24, 39));
		this.panelForm.setLayout(new GridLayout(7,2, 10,10));
		this.panelForm.add(Offre);
		this.panelForm.add(this.txtoffre);
		Offre.setForeground(Color.WHITE);
		this.panelForm.add(DateDebut);
		this.panelForm.add(this.txtdate_debut);
		DateDebut.setForeground(Color.WHITE);
		this.panelForm.add(DateFin);
		this.panelForm.add(this.txtdate_fin);
		DateFin.setForeground(Color.WHITE);
		this.panelForm.add(Pourcentage);
		this.panelForm.add(this.txtpourcentage);
		Pourcentage.setForeground(Color.WHITE);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.add(this.btSupprimer);
		//ajout du panelForm au panelPromotions
		this.add(this.panelForm);
		this.panelForm.setVisible(true);
		
		//construction de la JTbale 
		String entetes [] = {"ID Promotion ", "Offre", "Date de début", "Date de retour", "Pourcentage"};
		Object [][] donnees = this.getDonnees (""); 
		
		this.unTableau = new Tableau (donnees, entetes);
		this.tablePromotions = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.tablePromotions); 
		uneScroll.setBounds(590, 80, 560, 420);
		this.add(uneScroll);
		
		this.tablePromotions.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e ){
                int numLigne = tablePromotions.getSelectedRow();
                int promotion_id = Integer.parseInt(tablePromotions.getValueAt(numLigne, 0).toString());
               
                if(e.getClickCount() ==2){
                    int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cette promotion ?","Suppression Promotion",JOptionPane.YES_NO_OPTION);
                    if(retour == 0){
						C_Promotions.DeletePromotions(promotion_id);
                        unTableau.deleteLigne(numLigne);
                        JOptionPane.showMessageDialog(null, "Promotion supprimée avec succès");
                    }
                }else if(e.getClickCount() == 1 ){
                    //on remplie les champs pour la modification
                	txtoffre.setText(tablePromotions.getValueAt(numLigne, 1).toString());
                	txtdate_debut.setText(tablePromotions.getValueAt(numLigne, 2).toString());
                	txtdate_fin.setText(tablePromotions.getValueAt(numLigne, 3).toString());
                	txtpourcentage.setText(tablePromotions.getValueAt(numLigne, 4).toString());
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
		ArrayList<Promotions> lesPromotions = C_Promotions.SelectAllPromotions(); 
		Object [][] matrice = new Object [lesPromotions.size()][5]; 
		int i=0; 
		for (Promotions unePromotion : lesPromotions)
		{
			matrice[i][0]  = unePromotion.getpromotion_id(); 
			matrice[i][1]  = unePromotion.getoffre_id(); 
			matrice[i][2]  = unePromotion.getdate_debut();
			matrice[i][3]  = unePromotion.getdate_fin();
			matrice[i][4]  = unePromotion.getpourcentage();
			i++;
		}
		return matrice;
	}

	public void ViderChamps() {
		this.txtoffre.setText("");
		this.txtdate_debut.setText("");
		this.txtdate_fin.setText("");
		this.txtpourcentage.setText("");			
		btEnregistrer.setText("Enregistrer");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.ViderChamps();
		}else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")){
			String offre = this.txtoffre.getText();
			String date_debut = this.txtdate_debut.getText();
			String date_fin = this.txtdate_fin.getText();
			String pourcentage = this.txtpourcentage.getText();
			
			//instancier une promotion
			Promotions unePromotion = new Promotions( offre, date_debut, date_fin, pourcentage);
		     
			// on l'enregistre dans la db
			C_Promotions.InsertPromotions(unePromotion); 
			
			//ajout de la promotion dans le Tableau 
			Object ligne[] = {unePromotion.getpromotion_id(), unePromotion.getoffre_id(), 
					unePromotion.getdate_debut(), unePromotion.getdate_fin(), unePromotion.getpourcentage()};
			this.unTableau.insertLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Promotion ajoutée avec succès");
			this.ViderChamps();
			
		}
        else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")){
        	String offre = this.txtoffre.getText();
			String date_debut = this.txtdate_debut.getText();
			String date_fin = this.txtdate_fin.getText();
			String pourcentage = this.txtpourcentage.getText();
            int numLigne = tablePromotions.getSelectedRow();
            int offre_id = Integer.parseInt(tablePromotions.getValueAt(numLigne, 0).toString());

			//instancier une promotion
			Promotions unePromotion = new Promotions( offre, date_debut, date_fin, pourcentage);
            //on l'update dans la base de données
            C_Promotions.UpdatePromotions(unePromotion);
            //on recharge la JTable
            Object ligne[] = {unePromotion.getpromotion_id(),unePromotion.getoffre_id(),unePromotion.getdate_debut(),unePromotion.getdate_fin(),unePromotion.getpourcentage()};
            this.unTableau.updateLigne(numLigne, ligne);

            JOptionPane.showMessageDialog(this, "Promotion modifiée avec succès");
            this.ViderChamps();
        }
        else if (e.getSource() == btSupprimer){
        	int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cette promotion ?","Suppression Promotion",JOptionPane.YES_NO_OPTION);
            if(retour == 0){
            	String offre = this.txtoffre.getText();
				String date_debut = this.txtdate_debut.getText();
				String date_fin = this.txtdate_fin.getText();
				String pourcentage = this.txtpourcentage.getText();
			    int numLigne = tablePromotions.getSelectedRow();
                int promotion_id = Integer.parseInt(tablePromotions.getValueAt(numLigne, 0).toString());

              //instancier une promotion
				Promotions unePromotion = new Promotions( offre, date_debut, date_fin, pourcentage);
				//on l'update dans la base de données
				C_Promotions.DeletePromotions(promotion_id);
                //on recharge la JTable
                Object ligne[] = {unePromotion.getpromotion_id(),unePromotion.getoffre_id(),unePromotion.getdate_debut(),unePromotion.getdate_fin(),unePromotion.getpourcentage()};
                this.unTableau.updateLigne(numLigne, ligne);

            JOptionPane.showMessageDialog(this, "Promotion supprimée avec succès");
            this.ViderChamps();
            }
        }
        else if(e.getSource() == this.btFiltrer){
            String filtre = this.txtFiltre.getText();
            //récupération des promotions de la base de données
            Object données[][] = this.getDonnees(filtre);
            this.unTableau.setDonnees(données);
        }
		
	}
}
