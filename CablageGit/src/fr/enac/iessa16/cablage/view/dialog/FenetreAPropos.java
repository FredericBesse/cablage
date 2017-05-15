package fr.enac.iessa16.cablage.view.dialog;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.enac.iessa16.cablage.view.Parametres;

/**
 * Classe définissant la fenêtre à propos
 * 
 * @author Racha Hedidi et Frédéric Besse
 */
@SuppressWarnings("serial")
public class FenetreAPropos extends JDialog {

	
	
	/**
	 * Constructeur
	 */
	public FenetreAPropos() {
		
		 super();
		 
		 this.setTitle("A propos");
		
		 this.setLocationRelativeTo(null);
		 this.setResizable(false);

		 this.initComponent();
	    
		 this.pack();
	    
		 this.setVisible(true);      
	  }

	 
	  private void initComponent(){
	     
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	   
	    JLabel nomLabel = new JLabel("Application Cablage");
	    panel.add(nomLabel);
	    
	    JLabel versionLabel = new JLabel("Version : "+Parametres.version);
	    panel.add(versionLabel);
	    
	    JLabel auteurLabel = new JLabel("Auteurs : Racha Hedidi et Frédéric Besse");
	    panel.add(auteurLabel);
	    
	    this.getContentPane().add(panel, BorderLayout.CENTER);
	  }
}
