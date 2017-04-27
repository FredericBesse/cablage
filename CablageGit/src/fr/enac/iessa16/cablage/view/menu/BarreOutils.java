package fr.enac.iessa16.cablage.view.menu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe BarreOutilsFichier
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class BarreOutils extends JToolBar{
	
	
	/**
	 * Constructeur de la classe BarreOutilsFichier
	 * 
	 * @param controleur
	 */
	public BarreOutils(Controleur controleur) {
		
		super();
		 
        setOrientation(JToolBar.HORIZONTAL);
        
        ImageIcon iconNouveau = new ImageIcon("icon/new.png");
        JButton nouveau = new JButton(iconNouveau);
        nouveau.setToolTipText(ParametresFenetre.grapheVide);
	    nouveau.setActionCommand(ParametresFenetre.grapheVide);
	    nouveau.addActionListener(controleur.getControleurMenuFichier());
	    this.add(nouveau);   
        
	    ImageIcon iconEnregistrer = new ImageIcon("icon/enregistrer.jpg");
        JButton enregistrer = new JButton(iconEnregistrer);
        enregistrer.setToolTipText(ParametresFenetre.enregistrer);
        enregistrer.setActionCommand(ParametresFenetre.enregistrer);
        enregistrer.addActionListener(controleur.getControleurMenuFichier());
	    this.add(enregistrer);
	   
	    ImageIcon iconouvrir = new ImageIcon("icon/ouvrir.jpeg");
        JButton ouvrir = new JButton(iconouvrir);
        ouvrir.setToolTipText(ParametresFenetre.ouvrir);
        ouvrir.setActionCommand(ParametresFenetre.ouvrir);
        ouvrir.addActionListener(controleur.getControleurMenuFichier());
	    this.add(ouvrir);
	
	    ImageIcon iconImprimer = new ImageIcon("icon/imprimer.jpeg");
        JButton imprimer = new JButton(iconImprimer);
        imprimer.setToolTipText(ParametresFenetre.imprimer);
        imprimer.setActionCommand(ParametresFenetre.imprimer);
        imprimer.addActionListener(controleur.getControleurMenuFichier());
	    this.add(imprimer);
	    
	    
	    
	    ImageIcon iconAjoutSommet = new ImageIcon("icon/sommetajout.jpeg");
        JButton ajoutSommet = new JButton(iconAjoutSommet);
        ajoutSommet.setToolTipText(ParametresFenetre.ajouterSommet);
	    ajoutSommet.setActionCommand(ParametresFenetre.ajouterSommet);
	    ajoutSommet.addActionListener(controleur.getControleurMenuEdition());
	    this.add(ajoutSommet);
   
        
	    ImageIcon iconSupprimerSommet = new ImageIcon("icon/SuppSomet.pngajout.jpeg");
        JButton suppSommet = new JButton(iconSupprimerSommet);
        suppSommet.setToolTipText(ParametresFenetre.supprimerSommet);
        suppSommet.setActionCommand(ParametresFenetre.supprimerSommet);
        suppSommet.addActionListener(controleur.getControleurMenuEdition());
	    this.add(suppSommet);
	   
	    ImageIcon iconAjouterArete = new ImageIcon("icon/ajouterArete.jpeg.png");
        JButton ajouterArete = new JButton(iconAjouterArete);
        ajouterArete.setToolTipText(ParametresFenetre.ajouterArete);
        ajouterArete.setActionCommand(ParametresFenetre.ajouterArete);
        ajouterArete.addActionListener(controleur.getControleurMenuEdition());
	    this.add(ajouterArete);
	
	    ImageIcon iconSupprimerArete = new ImageIcon("icon/suppArete.jpeg");
        JButton suppArete = new JButton(iconSupprimerArete);
        suppArete.setToolTipText(ParametresFenetre.supprimerArete);
        suppArete.setActionCommand(ParametresFenetre.supprimerArete);
        suppArete.addActionListener(controleur.getControleurMenuEdition());
	    this.add(suppArete);
	    
	    this.setFloatable(false);
 
    }
	
}
		
		
		
		

	
	

