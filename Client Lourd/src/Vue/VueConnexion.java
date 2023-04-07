package Vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.C_Users;
import controleur.PPEEvent;
import controleur.Users;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {

	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JPanel panelConnexion = new JPanel();
	private JLabel Email = new JLabel("Email :");
	private JLabel Mdp = new JLabel("Mot de passe :");
	private JLabel Marque = new JLabel("TRAVELIN");

	public VueConnexion()
	{
		this.setTitle("Cherchez votre coin de paradis chez Travelin");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100, 100, 320, 340);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color (46, 147, 214));
		this.setLayout(null);
		
		//instanciation de l'image logo
		ImageIcon uneImage = new ImageIcon("src/images/LogoTravelin.png");
		JLabel unLabel = new JLabel(uneImage);
		unLabel.setBounds(80, 10, 140, 140);
		this.add(unLabel);
		
		//instanciation du nom de la marque
		this.add(Marque);
		Marque.setBounds(125, 90, 140, 140);
		Marque.setForeground(Color.WHITE);
		
		// instanciation du panel connexion
		this.panelConnexion.setBounds(10, 175, 285, 120);
		this.panelConnexion.setBackground(new Color(17, 24, 39));
		this.panelConnexion.setLayout(new GridLayout(3, 2, 5, 5)); //3 lignes, 2 colonnes
		this.panelConnexion.add(Email);
		this.panelConnexion.add(this.txtEmail);
		Email.setForeground(Color.WHITE);
		this.panelConnexion.add(Mdp);
		this.panelConnexion.add(this.txtMdp);
		Mdp.setForeground(Color.WHITE);
		this.panelConnexion.add(this.btAnnuler);
		this.panelConnexion.add(this.btSeConnecter);
		this.add(this.panelConnexion);
		
		//rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//rendre les txt ecoutables
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}else if (e.getSource() == this.btSeConnecter)
		{
			this.traitement();
		}
		
	}
	public void traitement()
	{
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		String role_client = "admin";
		Users unUser = C_Users.selectWhereUserAdmin(email, mdp, role_client);
		if (unUser == null)
		{
			JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
		}else {
			JOptionPane.showMessageDialog(this, "Heureux de vous revoir "+unUser.getprenom()+" "+unUser.getnom());
			
			//ouverture de la JFrame générale
			PPEEvent.rendreVisibleVueConnexion(false);
			PPEEvent.rendreVisibleVueGenerale(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// gestion de la frappe de touche
		if (e.getKeyCode() == KeyEvent.VK_ENTER) //touche entrée
		{
			this.traitement ();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
