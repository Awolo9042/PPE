package Vue;

import java.awt.Color;   
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controleur.PPEEvent;

public class VueGenerale extends JFrame implements ActionListener, MouseListener{

		private JPanel panelMenu = new JPanel();
		private JButton btClient = new JButton("Clients");
		private JButton btPromotions = new JButton("Promotions");
		private JButton btDestinations = new JButton("Destinations");
		private JButton btReservations = new JButton("Réservations");
		private JButton btOffres = new JButton("Offres");
		private JButton btTransports = new JButton("Transports");
		private JButton btLogements = new JButton("Logements");
		private JButton btAdministrateurs = new JButton("Administrateurs");
		private static JButton btQuitter = new JButton("Me déconnecter");

		
		//liste des Panels
		private PanelClients unPanelClient = new PanelClients();
		private PanelPromotions unPanelPromotion = new PanelPromotions();
		private PanelDestinations unPanelDestination = new PanelDestinations();
		private PanelReservations unPanelReservations = new PanelReservations();
		private PanelOffres unPanelOffres = new PanelOffres();
		private PanelTransports unPanelTransports = new PanelTransports();
		private PanelLogements unPanelLogements = new PanelLogements();
		private PanelAdmin unPanelAdmin = new PanelAdmin();

		public VueGenerale() {
			this.setTitle("Gestion");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setBounds(100, 100, 1250, 700);
			this.setLocationRelativeTo(null);
			this.getContentPane().setBackground(new Color (46, 147, 214));
			this.setLayout(null);
			
			//installation du panel Menu
			this.panelMenu.setBounds(0, 0, 1250, 50);
			this.panelMenu.setBackground(new Color (17, 24, 39));
			this.panelMenu.setLayout(new GridLayout(1, 6));
			this.panelMenu.add(this.btClient);
			this.panelMenu.add(this.btPromotions);
			this.panelMenu.add(this.btDestinations);
			this.panelMenu.add(this.btReservations);
			this.panelMenu.add(this.btOffres);
			this.panelMenu.add(this.btTransports);
			this.panelMenu.add(this.btLogements);
			this.panelMenu.add(this.btAdministrateurs);
			this.panelMenu.add(VueGenerale.btQuitter);
			
			this.add(this.panelMenu);

			//rendre les boutons écoutables
			this.btClient.addActionListener(this);
			this.btPromotions.addActionListener(this);
			this.btDestinations.addActionListener(this);
			this.btReservations.addActionListener(this);
			this.btOffres.addActionListener(this);
			this.btTransports.addActionListener(this);
			this.btLogements.addActionListener(this);
			this.btAdministrateurs.addActionListener(this);
			VueGenerale.btQuitter.addActionListener(this);
			
			//ajout des Panels dans la fenêtre
			this.add(this.unPanelClient);
			this.add(this.unPanelPromotion);
			this.add(this.unPanelDestination);
			this.add(this.unPanelReservations);
			this.add(this.unPanelOffres);
			this.add(this.unPanelTransports);
			this.add(this.unPanelLogements);
			this.add(this.unPanelAdmin);
			
			this.setVisible(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == VueGenerale.btQuitter)
			{
				int retour = JOptionPane.showConfirmDialog(this, 
						"Souhaitez-vous être déconnecter ?", "Quitter l'application", 
						JOptionPane.YES_NO_OPTION);
				if (retour == 0) {
					PPEEvent.rendreVisibleVueGenerale(false);
					PPEEvent.rendreVisibleVueConnexion(true);
				}

			}
			else if (e.getSource() == this.btClient)
			{
				this.afficherPanel (1);
			}
			else if (e.getSource() == this.btPromotions)
			{
				this.afficherPanel (2);
			}
			else if (e.getSource() == this.btDestinations)
			{
				this.afficherPanel (3);
			}
			else if (e.getSource() == this.btReservations)
			{
				this.afficherPanel (4);
			}
			else if (e.getSource() == this.btOffres)
			{
				this.afficherPanel (5);
			}
			else if (e.getSource() == this.btTransports)
			{
				this.afficherPanel (6);
			}
			else if (e.getSource() == this.btLogements)
			{
				this.afficherPanel (7);
			}
			else if (e.getSource() == this.btAdministrateurs)
			{
				this.afficherPanel (8);
			}
		}
		
		public static void main(String[] args) {
			VueGenerale PanelAccueilConnecte = new VueGenerale();
			PanelAccueilConnecte.setVisible(true);
	    }
		
		public void afficherPanel (int numero)
		{
			this.unPanelClient.setVisible(false);
			this.unPanelPromotion.setVisible(false);
			this.unPanelDestination.setVisible(false);
			this.unPanelReservations.setVisible(false);
			this.unPanelOffres.setVisible(false);
			this.unPanelTransports.setVisible(false);
			this.unPanelLogements.setVisible(false);
			this.unPanelAdmin.setVisible(false);
			
			switch(numero) {
			case 1:
				this.unPanelClient.setVisible(true);
				break;
			case 2:
				this.unPanelPromotion.setVisible(true);
				break;
			case 3:
				this.unPanelDestination.setVisible(true);
				break;
			case 4:
				this.unPanelReservations.setVisible(true);
				break;
			case 5:
				this.unPanelOffres.setVisible(true);
				break;
			case 6:
				this.unPanelTransports.setVisible(true);
				break;
			case 7:
				this.unPanelLogements.setVisible(true);
				break;
			case 8:
				this.unPanelAdmin.setVisible(true);
				break;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
			
		}
}
