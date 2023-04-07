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

import controleur.C_Reservations;
import controleur.Reservations;
import controleur.Tableau;

public class PanelReservations extends PanelPrincipal implements ActionListener{
			
	private JPanel panelForm 			= new JPanel();
	private JPanel panelFiltre 			= new JPanel();
	
	private JTextField txtDestination	= new JTextField();
	private JTextField txtDateDepart 	= new JTextField();
	private JTextField txtDateRetour 	= new JTextField();
	private JTextField txtNbpersonnes 	= new JTextField();
	private JTextField txtLogement 		= new JTextField();
	private JTextField txtTransport 	= new JTextField();
	private JTextField txtPrix 			= new JTextField();
	private JTextField txtClient 		= new JTextField();
	private JTextField txtEmail 		= new JTextField();
    private JTextField txtFiltre		= new JTextField();
	
	private JButton btAnnuler 			= new JButton("Annuler");
	private JButton btEnregistrer	 	= new JButton("Enregistrer");
    private JButton btSupprimer 		= new JButton("Supprimer");
    private JButton btFiltrer 			= new JButton("Filtrer");
	
	JLabel NomDestination 				= new JLabel("Destination :");
	JLabel DateDepart 					= new JLabel("Date de départ :");
	JLabel DateRetour 					= new JLabel("Date de retour :");
	JLabel NbPersonnes 					= new JLabel("Nombres de personne :");
	JLabel Logement 					= new JLabel("Logement :");
	JLabel Transport 					= new JLabel("Transport :");
	JLabel Prix 						= new JLabel("Prix total :");
	JLabel Client 						= new JLabel("Nom du client :");
	JLabel Email 						= new JLabel("Email :");
	JLabel Filtre 						= new JLabel("Filtrer par :");
	
	private JTable tableReservations;
	private Tableau unTableau ;
				
	public PanelReservations(){
		super();
		this.titre.setText("GESTION DES RESERVATIONS");
		this.titre.setBounds(525, 10, 350, 40);
		
		//construction du panel Form
		this.panelForm.setBounds(60,115,450,350);
		this.panelForm.setBackground(new Color (17, 24, 39));
		this.panelForm.setLayout(new GridLayout(11,2, 10,10));
		this.panelForm.add(NomDestination);
		this.panelForm.add(this.txtDestination);
		NomDestination.setForeground(Color.WHITE);
		this.panelForm.add(DateDepart);
		this.panelForm.add(this.txtDateDepart);
		DateDepart.setForeground(Color.WHITE);
		this.panelForm.add(DateRetour);
		this.panelForm.add(this.txtDateRetour);
		DateRetour.setForeground(Color.WHITE);
		this.panelForm.add(NbPersonnes);
		this.panelForm.add(this.txtNbpersonnes);
		NbPersonnes.setForeground(Color.WHITE);
		this.panelForm.add(Logement);
		this.panelForm.add(this.txtLogement);
		Logement.setForeground(Color.WHITE);
		this.panelForm.add(Transport);
		this.panelForm.add(this.txtTransport);
		Transport.setForeground(Color.WHITE);
		this.panelForm.add(Prix);
		this.panelForm.add(this.txtPrix);
		Prix.setForeground(Color.WHITE);
		this.panelForm.add(Client);
		this.panelForm.add(this.txtClient);
		Client.setForeground(Color.WHITE);
		this.panelForm.add(Email);
		this.panelForm.add(this.txtEmail);
		Email.setForeground(Color.WHITE);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.add(this.btSupprimer);
		//ajout du panelForm au panelReservations
		this.add(this.panelForm);
		this.panelForm.setVisible(true);
		
		//construction de la JTbale 
		String entetes [] = {"Destination ", "Date départ", "Date retour", "Nb personnes", "Logement", "Transport", "Prix total", "Nom du client", "Email"};
		Object [][] donnees = this.getDonnees (""); 
        
		this.unTableau = new Tableau (donnees, entetes);
		this.tableReservations = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.tableReservations); 
		uneScroll.setBounds(590, 80, 560, 420);
		this.add(uneScroll);
	
        this.tableReservations.addMouseListener(new MouseAdapter(){

        @Override
        public void mouseClicked(MouseEvent e ){
            int numLigne = tableReservations.getSelectedRow();
            int reservation_id = Integer.parseInt(tableReservations.getValueAt(numLigne, 0).toString());
           
            if(e.getClickCount() ==2){
                int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cette reservation ?","Suppression Reservation",JOptionPane.YES_NO_OPTION);
                if(retour == 0){
                    C_Reservations.deleteReservations(reservation_id);
                    unTableau.deleteLigne(numLigne);
                    JOptionPane.showMessageDialog(null, "Reservation supprimée avec succès");
                }
            }else if(e.getClickCount() == 1 ){
    			txtDestination.setText(tableReservations.getValueAt(numLigne, 1).toString());
    			txtDateDepart.setText(tableReservations.getValueAt(numLigne, 2).toString());
    			txtDateRetour.setText(tableReservations.getValueAt(numLigne, 3).toString());
    			txtNbpersonnes.setText(tableReservations.getValueAt(numLigne, 3).toString());
    			txtLogement.setText(tableReservations.getValueAt(numLigne, 4).toString());
    			txtTransport.setText(tableReservations.getValueAt(numLigne, 5).toString());
    			txtPrix.setText(tableReservations.getValueAt(numLigne, 6).toString());
    			txtClient.setText(tableReservations.getValueAt(numLigne, 7).toString());
                txtEmail.setText(tableReservations.getValueAt(numLigne, 8).toString());
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
		ArrayList<Reservations> lesReservations = C_Reservations.selectAllReservations(); 
		Object [][] matrice = new Object [lesReservations.size()][9]; 
		int i=0; 
		for (Reservations uneReservation : lesReservations)
		{
			matrice[i][0]  = uneReservation.getdestination_id(); 
			matrice[i][1]  = uneReservation.getdate_depart(); 
			matrice[i][2]  = uneReservation.getdate_retour(); 
			matrice[i][3]  = uneReservation.getnb_personnes();  
			matrice[i][4]  = uneReservation.getlogement_id();  
			matrice[i][5]  = uneReservation.gettransport_id(); 
			matrice[i][6]  = uneReservation.getreservations_prix();  
			matrice[i][7]  = uneReservation.getid_client();  
			matrice[i][8]  = uneReservation.getemail();
			i++;
		}
		return matrice;
	}

	public void ViderChamps() {
		this.txtDestination.setText("");
		this.txtDateDepart.setText("");
		this.txtDateRetour.setText("");
		this.txtNbpersonnes.setText("");
		this.txtLogement.setText("");
		this.txtTransport.setText("");
		this.txtPrix.setText("");
		this.txtClient.setText("");
		this.txtEmail.setText("");
		btEnregistrer.setText("Enregistrer");
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btAnnuler){
        	this.ViderChamps();
        }else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")){
        	String Destination = this.txtDestination.getText();
			String DateDepart = this.txtDateDepart.getText();
			String DateRetour = this.txtDateRetour.getText();
			String Nbpersonnes = this.txtNbpersonnes.getText();
			String Logement = this.txtLogement.getText();
			String Transport = this.txtTransport.getText();
			String Prix = this.txtPrix.getText();
			String Client = this.txtClient.getText();
			String Email = this.txtEmail.getText();

            //instancier un objet de type Reservation
            Reservations uneReservation = new Reservations( Destination, DateDepart, DateRetour, Nbpersonnes, Logement, Transport,
            		Prix, Client, Email);
            //on l'enregistre dans la base de données
            C_Reservations.insertReservations(uneReservation);
            //on recharge la JTable
            Object ligne[] = {uneReservation.getdestination_id(), uneReservation.getdate_depart(), 
            		uneReservation.getdate_retour(), uneReservation.getnb_personnes(),
            		uneReservation.getlogement_id(),uneReservation.gettransport_id(),
            		uneReservation.getreservations_prix(),uneReservation.getid_client(),
            		uneReservation.getemail()};
	                this.unTableau.insertLigne(ligne);

            JOptionPane.showMessageDialog(this, "Reservation ajoutée avec succès");
            this.ViderChamps();
        }
        else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")){
        	String Destination = this.txtDestination.getText();
			String DateDepart = this.txtDateDepart.getText();
			String DateRetour = this.txtDateRetour.getText();
			String Nbpersonnes = this.txtNbpersonnes.getText();
			String Logement = this.txtLogement.getText();
			String Transport = this.txtTransport.getText();
			String Prix = this.txtPrix.getText();
			String Client = this.txtClient.getText();
			String Email = this.txtEmail.getText();
            int numLigne = tableReservations.getSelectedRow();
            int id_reservation = Integer.parseInt(tableReservations.getValueAt(numLigne, 0).toString());

            //instancier un objet de type Reservation
            Reservations uneReservation = new Reservations(id_reservation, Destination, DateDepart, DateRetour, Nbpersonnes, Logement, Transport,
            		Prix, Client, Email);
            //on l'update dans la base de données
            C_Reservations.updateReservations(uneReservation);
            //on recharge la JTable
            Object ligne[] = {uneReservation.getid_client(), uneReservation.getdestination_id(), uneReservation.getdate_depart(), 
            		uneReservation.getdate_retour(), uneReservation.getnb_personnes(),
            		uneReservation.getlogement_id(),uneReservation.gettransport_id(),
            		uneReservation.getreservations_prix(),uneReservation.getid_client(),
            		uneReservation.getemail()};

            JOptionPane.showMessageDialog(this, "Reservation modifiée avec succès");
            this.ViderChamps();
        }
        else if (e.getSource() == btSupprimer){
        	int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cette reservation ?","Suppression Reservation",JOptionPane.YES_NO_OPTION);
            if(retour == 0){
            	String Destination = this.txtDestination.getText();
				String DateDepart = this.txtDateDepart.getText();
				String DateRetour = this.txtDateRetour.getText();
				String Nbpersonnes = this.txtNbpersonnes.getText();
				String Logement = this.txtLogement.getText();
				String Transport = this.txtTransport.getText();
				String Prix = this.txtPrix.getText();
				String Client = this.txtClient.getText();
				String Email = this.txtEmail.getText();
            int numLigne = tableReservations.getSelectedRow();
            int id_reservation = Integer.parseInt(tableReservations.getValueAt(numLigne, 0).toString());

          //instancier un objet de type Reservation
            Reservations uneReservation = new Reservations(id_reservation, Destination, DateDepart, DateRetour, Nbpersonnes, Logement, Transport,
            		Prix, Client, Email);
            //on l'update dans la base de données
            C_Reservations.deleteReservations(id_reservation);
            //on recharge la JTable
            Object ligne[] = {uneReservation.getid_client(), uneReservation.getdestination_id(), uneReservation.getdate_depart(), 
            		uneReservation.getdate_retour(), uneReservation.getnb_personnes(),
            		uneReservation.getlogement_id(),uneReservation.gettransport_id(),
            		uneReservation.getreservations_prix(),uneReservation.getid_client(),
            		uneReservation.getemail()};

            JOptionPane.showMessageDialog(this, "Reservation supprimée avec succès");
            this.ViderChamps();
            }
        }
        else if(e.getSource() == this.btFiltrer){
            String filtre = this.txtFiltre.getText();
            //récupération des reservations de la base de données
            Object données[][] = this.getDonnees(filtre);
            this.unTableau.setDonnees(données);
        }
    }
}












