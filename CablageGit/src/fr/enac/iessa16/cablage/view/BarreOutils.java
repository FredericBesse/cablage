package fr.enac.iessa16.cablage.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import fr.enac.iessa16.cablage.controller.Controleur;

public class BarreOutils extends JToolBar{
	private JButton nouveau;
	private JButton ouvrir;
	private JButton enregistrer;
	private JButton imprimer;

	
	public BarreOutils(Controleur controleur) {
		super();
		// TODO Auto-generated constructor stub
		 
        setOrientation(JToolBar.HORIZONTAL);
        
        ImageIcon iconnouveau = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/new.png");
        this.nouveau = new JButton(iconnouveau);
        nouveau.setToolTipText("Nouveau Graphe Vide");
	    nouveau.setActionCommand("GrapheVide");
	    nouveau.addActionListener(controleur.getControleMenu());
	    this.add(nouveau);
   
        
	    ImageIcon iconEnregistrer = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/enregistrer.jpg");
        this.ouvrir = new JButton(iconEnregistrer);
        ouvrir.setToolTipText("Enregistrer sous");
        ouvrir.setActionCommand("EnregistrerSous");
        ouvrir.addActionListener(controleur.getControleMenu());
	    this.add(ouvrir);
	   
	    ImageIcon iconouvrir = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/ouvrir.jpeg");
        this.ouvrir = new JButton(iconouvrir);
        ouvrir.setToolTipText("Ouvrir");
        ouvrir.setActionCommand("Ouvrir");
        ouvrir.addActionListener(controleur.getControleMenu());
	    this.add(ouvrir);
	
	    ImageIcon iconImprimer = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/imprimer.jpeg");
        this.imprimer = new JButton(iconImprimer);
        imprimer.setToolTipText("Imprimer");
        imprimer.setActionCommand("Imprimer");
        imprimer.addActionListener(controleur.getControleMenu());
	    this.add(imprimer);
	    
	    
   
 
      
     
 
 
 
    }
	
}
		
		
		
		

	
	

