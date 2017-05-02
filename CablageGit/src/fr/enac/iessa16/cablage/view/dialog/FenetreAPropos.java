package fr.enac.iessa16.cablage.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.enac.iessa16.cablage.view.Parametres;

public class FenetreAPropos extends JDialog {

	
	
	public FenetreAPropos() {
		
		 super();
		 
		 this.setTitle("A propos");
		
		
	  
	   
	   // this.setSize(550, 270);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	  //  this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	    this.pack();
	    this.setVisible(true);      

	  }

	 
	  private void initComponent(){
	   


	    // Les couleurs nom
	    JPanel panNom = new JPanel();
	    panNom.setLayout(new BoxLayout(panNom, BoxLayout.PAGE_AXIS));
	    //panNom.setBackground(Color.white);
	   // panNom.setPreferredSize(new Dimension(220, 60));
	    
	    JLabel nomLabel = new JLabel("Application Cablage");
	    panNom.add(nomLabel);
	    
	    JLabel versionLabel = new JLabel("Version : "+Parametres.version);
	    panNom.add(versionLabel);
	    
	    JLabel auteurLabel = new JLabel("Auteurs : Racha Hedidi et Frédéric Besse");
	    panNom.add(auteurLabel);
	    

	   
	   
	    
	  


	    //this.getContentPane().add(panIcon, BorderLayout.WEST);
	    this.getContentPane().add(panNom, BorderLayout.CENTER);
	  }


	
}
