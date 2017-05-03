package fr.enac.iessa16.cablage.view.menu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.Parametres;

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
        
        ImageIcon iconNouveau = new ImageIcon("image/icon/new.png");
        JButton nouveau = new JButton(iconNouveau);
        nouveau.setToolTipText(Parametres.grapheVide);
	    nouveau.setActionCommand(Parametres.grapheVide);
	    nouveau.addActionListener(controleur.getControleurMenuFichier());
	    this.add(nouveau);   
        
	    ImageIcon iconEnregistrer = new ImageIcon("image/icon/enregistrer.jpg");
        JButton enregistrer = new JButton(iconEnregistrer);
        enregistrer.setToolTipText(Parametres.enregistrer);
        enregistrer.setActionCommand(Parametres.enregistrer);
        enregistrer.addActionListener(controleur.getControleurMenuFichier());
	    this.add(enregistrer);
	   
	    ImageIcon iconouvrir = new ImageIcon("image/icon/ouvrir.jpeg");
        JButton ouvrir = new JButton(iconouvrir);
        ouvrir.setToolTipText(Parametres.ouvrir);
        ouvrir.setActionCommand(Parametres.ouvrir);
        ouvrir.addActionListener(controleur.getControleurMenuFichier());
	    this.add(ouvrir);
	
	    ImageIcon iconImprimer = new ImageIcon("image/icon/imprimer.jpeg");
        JButton imprimer = new JButton(iconImprimer);
        imprimer.setToolTipText(Parametres.imprimer);
        imprimer.setActionCommand(Parametres.imprimer);
        imprimer.addActionListener(controleur.getControleurMenuFichier());
	    this.add(imprimer);
	    
	    
	    
	    ImageIcon iconAjoutSommet = new ImageIcon("image/icon/sommetajout.jpeg");
        JButton ajoutSommet = new JButton(iconAjoutSommet);
        ajoutSommet.setToolTipText(Parametres.ajouterSommet);
	    ajoutSommet.setActionCommand(Parametres.ajouterSommet);
	    ajoutSommet.addActionListener(controleur.getControleurMenuEdition());
	    this.add(ajoutSommet);
   
        
	    ImageIcon iconSupprimerSommet = new ImageIcon("image/icon/SuppSomet.pngajout.jpeg");
        JButton suppSommet = new JButton(iconSupprimerSommet);
        suppSommet.setToolTipText(Parametres.supprimerSommet);
        suppSommet.setActionCommand(Parametres.supprimerSommet);
        suppSommet.addActionListener(controleur.getControleurMenuEdition());
	    this.add(suppSommet);
	   
	    ImageIcon iconAjouterArete = new ImageIcon("image/icon/ajouterArete.jpeg.png");
        JButton ajouterArete = new JButton(iconAjouterArete);
        ajouterArete.setToolTipText(Parametres.ajouterArete);
        ajouterArete.setActionCommand(Parametres.ajouterArete);
        ajouterArete.addActionListener(controleur.getControleurMenuEdition());
	    this.add(ajouterArete);
	
	    ImageIcon iconSupprimerArete = new ImageIcon("image/icon/suppArete.jpeg");
        JButton suppArete = new JButton(iconSupprimerArete);
        suppArete.setToolTipText(Parametres.supprimerArete);
        suppArete.setActionCommand(Parametres.supprimerArete);
        suppArete.addActionListener(controleur.getControleurMenuEdition());
	    this.add(suppArete);
	    
	    this.setFloatable(false);
 
    }
	
}
		
		
		
		

	
	

