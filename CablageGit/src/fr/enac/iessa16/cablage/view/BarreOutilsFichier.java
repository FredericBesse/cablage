package fr.enac.iessa16.cablage.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import fr.enac.iessa16.cablage.controller.Controleur;

public class BarreOutilsFichier extends JToolBar{
	private JButton nouveau;
	private JButton ouvrir;
	private JButton enregistrer;
	private JButton imprimer;
	private JButton ajoutSommet;
	private JButton suppSommet;
	private JButton ajouterArete;
	private JButton suppArete;
	
	public BarreOutilsFichier(Controleur controleur) {
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
        ouvrir.setToolTipText(ParametresNomFr.enregistrerSous);
        ouvrir.setActionCommand("EnregistrerSous");
        ouvrir.addActionListener(controleur.getControleMenu());
	    this.add(ouvrir);
	   
	    ImageIcon iconouvrir = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/ouvrir.jpeg");
        this.ouvrir = new JButton(iconouvrir);
        ouvrir.setToolTipText(ParametresNomFr.ouvrir);
        ouvrir.setActionCommand("Ouvrir");
        ouvrir.addActionListener(controleur.getControleMenu());
	    this.add(ouvrir);
	
	    ImageIcon iconImprimer = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/imprimer.jpeg");
        this.imprimer = new JButton(iconImprimer);
        imprimer.setToolTipText(ParametresNomFr.imprimer);
        imprimer.setActionCommand("Imprimer");
        imprimer.addActionListener(controleur.getControleMenu());
	    this.add(imprimer);
	    
	    
	    
	    ImageIcon iconAjoutSommet = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/sommetajout.jpeg");
        this.ajoutSommet = new JButton(iconAjoutSommet);
        ajoutSommet.setToolTipText(ParametresNomFr.ajouterSommet);
	    ajoutSommet.setActionCommand("ajouterUnSommet");
	    ajoutSommet.addActionListener(controleur.getControleCreationGraphe());
	    this.add(ajoutSommet);
   
        
	    ImageIcon iconSupprimerSommet = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/SuppSomet.pngajout.jpeg");
        this.suppSommet = new JButton(iconSupprimerSommet);
        suppSommet.setToolTipText(ParametresNomFr.supprimerSommet);
        suppSommet.setActionCommand("supprimerSommet");
        suppSommet.addActionListener(controleur.getControleCreationGraphe());
	    this.add(suppSommet);
	   
	    ImageIcon iconAjouterArete = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/ajouterArete.jpeg.png");
        this.ajouterArete = new JButton(iconAjouterArete);
        ajouterArete.setToolTipText(ParametresNomFr.ajouterArete);
        ajouterArete.setActionCommand("ajoutArete");
        ajouterArete.addActionListener(controleur.getControleCreationGraphe());
	    this.add(ajouterArete);
	
	    ImageIcon iconSupprimerArete = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/suppArete.jpeg");
        this.suppArete = new JButton(iconSupprimerArete);
        suppArete.setToolTipText(ParametresNomFr.supprimerArete);
        suppArete.setActionCommand("suppArete");
        suppArete.addActionListener(controleur.getControleCreationGraphe());
	    this.add(suppArete);
	    
	    this.setFloatable(true);
   
 
      
     
 
 
 
    }
	
}
		
		
		
		

	
	

