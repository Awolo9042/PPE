package Vue;
import java.awt.Color;  
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel{
		
	protected JLabel titre = new JLabel("");
	
	public PanelPrincipal() {	
		titre.setForeground(Color.WHITE);
		//les caractéristiques communes aux différents Panels
		this.setBounds(10,60,1215,595);
		this.setBackground(new Color (17, 24, 39));
		this.setLayout(null);		
		this.titre.setBounds(350,10,300,20);
		this.add(this.titre);		
		this.setVisible(false);
	}

	private void setResizable(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
