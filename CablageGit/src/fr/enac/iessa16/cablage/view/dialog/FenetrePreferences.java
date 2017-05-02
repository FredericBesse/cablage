package fr.enac.iessa16.cablage.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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

public class FenetrePreferences extends JDialog implements ActionListener {

	 private boolean sendData;
	  private JLabel nomLabel, sexeLabel, cheveuxLabel, ageLabel, tailleLabel,taille2Label, icon;
	  private JRadioButton tranche1, tranche2, tranche3, tranche4;
	  private JComboBox sexe, cheveux;
	  JButton nom;
	private JTextField taille;
	
	
	public FenetrePreferences() {
		
		 super();
		 
		 this.setTitle("Preferences");
		
		//Color initialColor = Color.red;
	    //Color newColor = JColorChooser.showDialog(null, "Dialog Title", initialColor);
	    //System.out.println(initialColor+" "+newColor);
	
	
	 

	  
	   
	    this.setSize(550, 270);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	  //  this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	    this.setVisible(true);      

	  }

	 
	  private void initComponent(){
	   


	    // Les couleurs nom
	    JPanel panNom = new JPanel();
	    //panNom.setBackground(Color.white);
	   // panNom.setPreferredSize(new Dimension(220, 60));
	    nom = new JButton();
	    nom.setBackground(Parametres.couleurSommet);
	    nom.addActionListener(this);
	    //nom.setPreferredSize(new Dimension(100, 25));
	    panNom.setBorder(BorderFactory.createTitledBorder("Choix des couleurs"));
	    nomLabel = new JLabel("Sommet");
	    panNom.add(nomLabel);
	    panNom.add(nom);

	   
	   
	    
	    JPanel control = new JPanel();
	    JButton okBouton = new JButton("OK");
	    okBouton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent arg0) {
	    setVisible(false);
	    }
	    });
	    control.add(okBouton);
	    control.add(cancelBouton);


	    //this.getContentPane().add(panIcon, BorderLayout.WEST);
	    this.getContentPane().add(panNom, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	  }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton source = (JButton) e.getSource();
		Color initialColor = source.getBackground();
	    Color newColor = JColorChooser.showDialog(null, "Couleur", initialColor);
	    if (newColor != null)
	    	source.setBackground(newColor);
	    //System.out.println(initialColor+" "+newColor);
	
		
	}  
}
