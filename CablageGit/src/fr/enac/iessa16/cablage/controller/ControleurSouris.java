package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.enac.iessa16.cablage.model.EtatVue;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.PanneauDessinGraphe;
import fr.enac.iessa16.cablage.view.dialog.FenetreAjoutSommet;


/**
 * Classe Controleur Souris
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurSouris implements MouseListener, MouseMotionListener, MouseWheelListener {

	// Le modele
	private Modele modele;
	
	// La position précédente de la souris	
	private int x_old, y_old;
		
	
	/**
	 * Constructeur de la classe ControleurSouris
	 * 
	 * @param monModele le modele
	 */
	public ControleurSouris(Modele monModele) {
		super();
		this.modele = monModele;		
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			
			if(EtatVue.modeAjouterSommet == true) {
				double longitude;
				double latitude;
				longitude = PanneauDessinGraphe.conversionXenLongitude(e.getX());
				latitude = PanneauDessinGraphe.conversionYenLatitude(e.getY());
				new FenetreAjoutSommet(this.modele,longitude,latitude);
			}
			else if (EtatVue.modeAjouterArete == true) {
				modele.ajouterAreteEffectif(e.getX(),e.getY());
			} else {
				
				boolean sommetTrouve = modele.touverSommetLePlusProcheDuClicSouris(e.getX(), e.getY());
				
				// si on n'a pas trouve de sommet proche du clic souris, on cherche une arete
				if (sommetTrouve == false)
					modele.touverAreteLaPlusProcheDuClicSouris(e.getX(), e.getY());
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			
			EtatVue.modeAjouterSommet = false;
			EtatVue.modeAjouterArete = false;
			
			modele.changement();
		}
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// on enregistre la position de la souris pour le drag
		this.x_old = e.getX();
		this.y_old = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {

		// on calcule le déplacement depuis l'appui sur le bouton
		int dx = e.getX()-x_old;
		int dy = e.getY()-y_old;
		
		modele.drag(dx, dy);
		
		this.x_old = e.getX();
		this.y_old = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}




	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		// on récupére la position de la souris pour zoomer sur la souris
		int xs = e.getX();
		int ys = e.getY();
		
		// on récupére la rotation de la molette
		int rotation = e.getWheelRotation();
		
		// on appelle la fonction zoom du modele
		modele.zoom(xs, ys, rotation);
	}
}
