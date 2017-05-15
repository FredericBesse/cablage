package fr.enac.iessa16.cablage.view;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.EtatVue;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.Cablage;
import fr.enac.iessa16.cablage.model.core.Sommet;
import fr.enac.iessa16.cablage.view.dialog.FenetreImprimer;


/**
 * Classe permettant de dessiner un graphe
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class PanneauDessinGraphe extends JPanel implements Printable {

	
	/*
	 * Attributs de la classe
	 */
	
	// Le logger
	private Logger LOGGER = LogManager.getLogger(Modele.class);
		
	public Modele modele;
	

	/**
	 * Constructeur de la classe PanneauDessinGraphe
	 * 
	 * @param model le modèle
	 * @param controleur le controleur
	 */
	public PanneauDessinGraphe(Modele model, Controleur controleur) {

		super();

		this.modele = model;
		
		// Ajout des listeners
		this.addMouseListener(controleur.getControleurSouris());
		this.addMouseMotionListener(controleur.getControleurSouris());
		this.addMouseWheelListener(controleur.getControleurSouris());
	}


	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
        
		if (modele != null) {
			
			// Si le graphe n'est pas vide
			if (modele.getGraphe() != null) {
				
				if (Parametres.afficherSommet)
					dessinerSommets(g);
				
				if (Parametres.afficherNomSommet)
					dessinerNomSommets(g);
				
				if (Parametres.afficherArete)
					dessinerAretes(g);

				if (Parametres.afficherDijkstra)
					dessinerCheminDijkstra(g);
				
				if (Parametres.afficherKruskal)
					dessinerCheminKruskal(g);
				
				if (Parametres.afficherArete)
					dessinerAreteSelectionne(g);
				
				if (Parametres.afficherSommet) {
					dessinerSommetsSelectionnes(g);
					dessinerDernierSommetSelectionne(g);
				}	
			}
		}
	}

	
	
	/**
	 * Méthode permettant de dessiner les sommets
	 * @param g
	 */
	private void dessinerSommets(Graphics g) {

		long start = System.currentTimeMillis();
		
		// On récupère l'ensemble des sommets
		ArrayList<Sommet> sommets = modele.getGraphe().getListeSommets();
		
		// On choisit la couleur des sommets
		g.setColor(Parametres.couleurSommet);
		
		// On parcourt l'ensemble des sommets
		for (Sommet sommet : sommets) {
			
			// on dessine chaque sommet
			dessinerSommet(sommet, g);

		}
		
		long duree = System.currentTimeMillis() - start;
		LOGGER.debug("affichage des "+sommets.size()+" sommets en " + duree + " ms");
	}
	
	
	/**
	 * Méthode permettant de dessiner un sommet 
	 * @param sommet
	 * @param g
	 */
	void dessinerSommet(Sommet sommet, Graphics g) {
		
		int x, y;
		double longitude;
		double latitude;
		
		// On récupère ses coordonnees
		longitude = sommet.getAbscisse();
		latitude = sommet.getOrdonnee();
		
		// On convertit les coordonnees pour l'affichage
		x = conversionLongitudeEnX(longitude);
		y = conversionLatitudeEnY(latitude);
		
		// On dessine le sommet
		g.fillOval(x - Parametres.rayonSommetAffichage, y - Parametres.rayonSommetAffichage, 
				2 * Parametres.rayonSommetAffichage,	2 * Parametres.rayonSommetAffichage);
		
	}
	
	
	/**
	 * Méthode permettant de dessiner le nom des sommets
	 * @param g
	 */
	private void dessinerNomSommets(Graphics g) {

		int x, y;
		double longitude;
		double latitude;
		
		// On récupère l'ensemble des sommets
		ArrayList<Sommet> sommets = modele.getGraphe().getListeSommets();
		
		// On choisit la couleur des sommets
		g.setColor(Parametres.couleurSommet);
		
		// On parcourt l'ensemble des sommets
		for (Sommet sommet : sommets) {
			
			// On récupère ses coordonnees
			longitude = sommet.getAbscisse();
			latitude = sommet.getOrdonnee();
			
			// On convertit les coordonnees pour l'affichage
			x = conversionLongitudeEnX(longitude);
			y = conversionLatitudeEnY(latitude);
			
			// On affiche les noms de chaque sommet
			g.drawString(sommet.getNom(), x + 2*Parametres.rayonSommetAffichage, y + Parametres.rayonSommetAffichage);

		}
		
	}
	
	

	
	
	
	/**
	 * Méthode permettant de dessiner les aretes
	 * 
	 * @param g
	 */
	private void dessinerAretes(Graphics g) {

		int nombreAretes;		
		Arete arete;
		Graphics2D g2D = (Graphics2D) g;
		
		long start = System.currentTimeMillis();
		
		// on récupère la liste des aretes
		ArrayList<Arete> listeAretes = modele.getGraphe().getListeAretes();
		nombreAretes = listeAretes.size();
		
		// on choisit la couleur d'affichage des aretes
		g2D.setColor(Parametres.couleurArete);
		
		// on parcourt toutes les aretes
		for (int i = 0; i < nombreAretes; i++) {

			// on récupère l'arete i
			arete = listeAretes.get(i);
			
			dessinerArete(arete, g2D);
		}
		
		long duree = System.currentTimeMillis() - start;
		LOGGER.debug("affichage 2 des "+modele.getGraphe().getListeAretes().size()+" aretes en " + duree + " ms");
	
	}

	
	private void dessinerArete(Arete arete, Graphics2D g) {
		
		int x1, y1, x2, y2;
		double long1, long2;
		double lat1, lat2;
		Sommet origine, extremite;
		
		// on récupère les sommets origine et destination
		origine = arete.getSommetOrigine();
		extremite = arete.getSommetExtremité();
			
		// on récupère leurs coordonnées
		long1 = origine.getAbscisse();
		long2 = extremite.getAbscisse();
		lat1 = origine.getOrdonnee();
		lat2 = extremite.getOrdonnee();

		// on les convertit pour l'affichage
		x1 = conversionLongitudeEnX(long1);
		x2 = conversionLongitudeEnX(long2);
		y1 = conversionLatitudeEnY(lat1);
		y2 = conversionLatitudeEnY(lat2);
		
		// on dessine l'arete
		g.drawLine(x1, y1, x2, y2);	
	}
	
	
	private void dessinerAreteSelectionne(Graphics g) {

		Graphics2D g2D = (Graphics2D) g;
		
		Arete a = modele.getDerniereAreteSelectionne();
		
		if (a!=null)
			dessinerArete(a, g2D);
	}


	/**
	 * Méthode permettant de dessiner les sommets
	 * @param g
	 */
	private void dessinerSommetsSelectionnes(Graphics g) {

		int x, y;
		double longitude;
		double latitude;
					
		// On récupère l'ensemble des sommets selectionnes
		ArrayList<Sommet> sommets = modele.getListeSommetsSelectionnes();

		// On choisit la couleur des sommets sélectionnés
		g.setColor(Parametres.couleurSommetSelectionne);
		
		// On parcourt l'ensemble des sommets
		for (Sommet sommet : sommets) {
			
			// On récupère ses coordonnees
			longitude = sommet.getAbscisse();
			latitude = sommet.getOrdonnee();
			
			// On convertit les coordonnees pour l'affichage
			x = conversionLongitudeEnX(longitude);
			y = conversionLatitudeEnY(latitude);
			
			// On dessine le sommets
			g.fillOval(x - Parametres.rayonSommetSelectionneAffichage, y - Parametres.rayonSommetSelectionneAffichage, 
					2 * Parametres.rayonSommetSelectionneAffichage,	2 * Parametres.rayonSommetSelectionneAffichage);
		}
	}
	
	
	/**
	 * Méthode permettant de dessiner les sommets sélectionnes
	 *  
	 * @param g
	 */
	private void dessinerDernierSommetSelectionne(Graphics g) {

		int x, y;
		double lon;
		double lat;
		Sommet sommet;
		
		// On récupère le dernier noeud sélectionné
		sommet = modele.getdernierSommetSelectionne();
		
		// s'il existe
		if(sommet!=null){
			
			// on recupère ses coordonnées 
			lon = sommet.getAbscisse();
			lat = sommet.getOrdonnee();
			
			// on les convertit pour l'affichage
			x =	conversionLongitudeEnX(lon);
			y = conversionLatitudeEnY(lat);
			
			// on l'affiche
			if (sommet.getSelected())
				g.setColor(Parametres.couleurSommetSelectionne);
			else 
				g.setColor(Parametres.couleurSommet);
			g.fillOval(x - Parametres.rayonSommetSelectionneAffichage, y - Parametres.rayonSommetSelectionneAffichage,
					2*Parametres.rayonSommetSelectionneAffichage, 2*Parametres.rayonSommetSelectionneAffichage);
			g.setColor(Parametres.couleurDernierSommetSelectionne);
			g.fillOval(x - Parametres.rayonSommetSelectionneAffichage/2, y - Parametres.rayonSommetSelectionneAffichage/2,
					Parametres.rayonSommetSelectionneAffichage, Parametres.rayonSommetSelectionneAffichage);
	
		}
	}
	
	
	
	/**
	 * Méthode permettant de dessiner le chemin le plus courts dijkstra
	 *  
	 * @param g
	 */
	private void dessinerCheminDijkstra(Graphics g) {

		int nombreAretes;
		Arete arete;
		ArrayList<Arete> listeAretesDijkstra;
		
		// on récupère le chemin le plus court dijkstra
		listeAretesDijkstra = modele.getListeAretesDijkstra();
		
		Graphics2D g2D = (Graphics2D) g;
		Stroke s = g2D.getStroke();
		
		// trait épais
		g2D.setStroke(new BasicStroke(5));
		g2D.setColor(Parametres.couleurAreteDijkstra);
		
		// s'il existe
		if (listeAretesDijkstra != null) {
			
			// on parcourt toutes les aretes du chemin
			nombreAretes = listeAretesDijkstra.size();
			for (int i = 0; i < nombreAretes; i++) {

				// on récupère l'arete i
				arete = listeAretesDijkstra.get(i);
				
				// on la dessine
				dessinerArete(arete, g2D);
			}
		}
		
		// retour au trait "normal"
		g2D.setStroke(s);
	}

	
	
	/**
	 * Méthode permettant de dessiner le chemin le plus court Kruskal  
	 * 
	 * @param g
	 */
	private void dessinerCheminKruskal(Graphics g) {
		
		int nombreAretes;
		Arete arete;
		Cablage cablage;
		ArrayList<Arete> listeAretesKruskal;
		
		// on récupère le chemin le plus court kruskal
		cablage = modele.getCablage();
		
		if (cablage != null) {
			listeAretesKruskal = cablage.getAretes();
		
			// s'il existe
			if (listeAretesKruskal != null) {
				
				Graphics2D g2D = (Graphics2D) g;
				Stroke s = g2D.getStroke();
				// trait épais
				g2D.setStroke(new BasicStroke(5));
				g2D.setColor(Parametres.couleurAreteKruskal);
				
				// on parcourt toutes les aretes du chemin
				nombreAretes = listeAretesKruskal.size();
				for (int i = 0; i < nombreAretes; i++) {
	
					// on récupère l'arete i
					arete = listeAretesKruskal.get(i);
					
					dessinerArete(arete, g2D);
				}
				
				// trait normal
				g2D.setStroke(s);
			}	
		}
	}
	
	
	
	
	public void centrerVue() {

		double echelleX = 0;
		double echelleY = 0;
		double latMin, latMax, lonMin, lonMax;
		
		latMin = modele.getGraphe().getOrdMin();
		latMax = modele.getGraphe().getOrdMax();
		lonMin = modele.getGraphe().getAbsMin();
		lonMax = modele.getGraphe().getAbsMax();

		echelleX = (this.getWidth() - 100) * Parametres.ECHELLE_BASE
					/ (lonMax - lonMin);
			echelleY = (this.getHeight() - 100) * Parametres.ECHELLE_BASE
					/ (latMax - latMin);

		Parametres.echelle = Math.min(echelleX, echelleY);

		Parametres.offsetX = -(PanneauDessinGraphe.conversionLongitudeEnX((lonMax + lonMin) / 2)
				- PanneauDessinGraphe.conversionLongitudeEnX(0));
		
		Parametres.offsetY = -(PanneauDessinGraphe.conversionLatitudeEnY((latMax + latMin) / 2)
				- PanneauDessinGraphe.conversionLatitudeEnY(0));
		
		this.repaint();
		
	}
	
		
	
	
	
	/**
	 * Méthode permettant de convertir la longitude des données en coordonnées écran
	 *   
	 * @param longitude la longitude
	 * @return x en pixel
	 */
	public static int conversionLongitudeEnX(double longitude) {
		
		return (int) Math.round(Parametres.panneauDessinWidth / 2 + Parametres.offsetX + longitude
				* Parametres.echelle / Parametres.ECHELLE_BASE);

	}

	
	/**
	 * Méthode permettant de convertir la latitude des données en coordonnées écran
	 * 
	 * @param latitude la latitude
	 * @return y en pixel
	 */
	public static int conversionLatitudeEnY(double latitude) {
		
		return (int) Math.round(Parametres.panneauDessinHeight / 2 + Parametres.offsetY - latitude
				* Parametres.echelle / Parametres.ECHELLE_BASE);

	}

	
	
	/**
	 * Méthode permettant de convertir une abscisse locale (repère JPanel) en
	 * longitude réelle
	 * 
	 * @param x l'abscisse locale en pixel
	 * @return la longitude réelle
	 */
	public static double conversionXenLongitude(int x) {
		
		return (x - (Parametres.panneauDessinWidth / 2 + Parametres.offsetX))
				* Parametres.ECHELLE_BASE / Parametres.echelle;
	}

	/**
	 * Méthode permettant de convertir une ordonnée locale (repère JPanel) en
	 * latitude réelle
	 * 
	 * @param y l'ordonnée locale en pixel
	 * @return la latitude réelle
	 */
	public static double conversionYenLatitude(int y) {
			
		return (y - (Parametres.panneauDessinHeight / 2 + Parametres.offsetY))
				* -Parametres.ECHELLE_BASE / Parametres.echelle;
	}

	

	/**
	 * Méthode permettant de redessiner le panneau 
	 */
	public void update() {
		
		// si on a demandé à centrer la vue
		if (EtatVue.centreVueDemande) {
			
			this.centrerVue();			
			EtatVue.centreVueDemande = false;
		}
		
		// Si on est en mode ajout (noeud ou arete)
		if(EtatVue.modeAjouterSommet || EtatVue.modeAjouterArete) {
			
			//On modifie la forme du curseur
		    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR) );		
		}
		else {
			
			// sinon on remet le curseur par defaut
			setCursor(Cursor.getDefaultCursor());
		}
		
		
		// si on a demandé à imprimer		
		if (EtatVue.imprimerDemande) {
			new FenetreImprimer(this);
			EtatVue.imprimerDemande = false;
		}
		
		
		repaint();
	}

	
	
	/* (non-Javadoc)
	 * @see java.awt.print.Printable#print(java.awt.Graphics, java.awt.print.PageFormat, int)
	 */
	@Override
	public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {

		int retValue = Printable.NO_SUCH_PAGE;
		
		if (pageIndex == 0) {
			
			retValue = Printable.PAGE_EXISTS;
		
			if (modele != null) {
				
				// Si le graphe n'est pas vide
				if (modele.getGraphe() != null) {
					
					//dessinerFondCarte(g);
					
					dessinerSommets(g);
	
					dessinerAretes(g);
	
					dessinerDernierSommetSelectionne(g);
	
					dessinerCheminDijkstra(g);
	
					dessinerCheminKruskal(g);
				}
			}	     
		}	    
		return retValue;
	}


	public void updateNouveauGraphe() {
		this.setBackground(Parametres.couleurFondPanneauDessin);
	}


	public void updateFermerGraphe() {
		this.setBackground(Parametres.couleurFondPanneauDessinLancement);		
	}	
}
