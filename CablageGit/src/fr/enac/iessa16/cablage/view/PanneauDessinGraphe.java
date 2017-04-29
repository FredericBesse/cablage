package fr.enac.iessa16.cablage.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.Sommet;


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
	
	private BufferedImage imageFond;

	/**
	 * Constructeur de la classe PanneauDessinGraphe
	 * 
	 * @param model
	 * @param controleur
	 */
	public PanneauDessinGraphe(Modele model, Controleur controleur) {

		super();
		
		this.modele = model;
		this.imageFond = null;
		
		// Ajout des listeners
		this.addMouseListener(controleur.getControleurSouris());
		this.addMouseMotionListener(controleur.getControleurSouris());
		this.addMouseWheelListener(controleur.getControleurSouris());
		
		// Bordure 	
		this.setBorder(BorderFactory.createTitledBorder(ParametresFenetre.titrePanneauVue2D));
		
		// FIXME Chargement image de fond uniquement si besoin apres chargement du graphe
		this.chargerImageFond();
	
	}

	
	/**
	 * Méthode permettant de charger une image de fond google maps  
	 */
	private void chargerImageFond() {		
				
		String center="Paris-Charles+De+Gaulle+(CDG)";
		String zoom = "12";
		String size = "2000x2000";
		String key = "AIzaSyABrzv3EoXRNgraD15R12UduLzOBpwg14A";
		
		String adresseImage = "https://maps.googleapis.com/maps/api/staticmap?center=" + center
				+"&zoom="+zoom
				+ "&size="+size
				+ "&maptype=roadmap&key="+key;
		try {
			this.imageFond = ImageIO.read(new URL(adresseImage));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}


	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
        
		if (modele != null) {
			
			// Si le graphe n'est pas vide
			if (modele.getGraphe() != null) {

				dessinerFondCarte(g);
				
				dessinerSommets(g);
				dessinerSommets1(g);
				dessinerSommets2(g);

				dessinerAretes(g);
				dessinerAretes2(g);
				
				dessinerDernierSommetSelectionne(g);

				dessinerCheminDijkstra(g);

				dessinerCheminKruskal(g);
				
			}
		}
	}

	

	/**
	 * Méthode permettant de dessiner l'image de fond
	 *  
	 * @param g
	 */
	private void dessinerFondCarte(Graphics g) {
		
		if (this.imageFond != null) {
			g.drawImage(imageFond, 16, 20, this);
		}
	}

	
	
	/**
	 * Méthode permettant de dessiner les sommets
	 * @param g
	 */
	private void dessinerSommets(Graphics g) {

		int x, y;
		double longitude;
		double latitude;
		Sommet sommet;

		long start = System.currentTimeMillis();
		
		// On récupère l'ensemble des sommets
		ArrayList<Sommet> sommets = modele.getGraphe().getListeSommets();
		
		// On parcourt l'ensemble des sommets
		for (int i = 0; i < sommets.size(); i++) {
			
			// On récupère le sommet i
			sommet = sommets.get(i);
			
			// On récupère ses coordonnees
			longitude = sommet.getLongitude();
			latitude = sommet.getLatitude();
			
			// On convertit les coordonnees pour l'affichage
			x = conversionLongitudeEnX(longitude);
			y = conversionLatitudeEnY(latitude);
			
			// On choisit la couleur selon que le sommet est sélectionné ou non
			if (sommet.getSelected() == false)
				g.setColor(ParametresFenetre.couleurSommetNonSelectionne);
			else
				g.setColor(ParametresFenetre.couleurSommetSelectionne);
			
			// On dessine les sommets
			g.fillOval(x - ParametresFenetre.rayonSommetAffichage, y - ParametresFenetre.rayonSommetAffichage, 
					2 * ParametresFenetre.rayonSommetAffichage,	2 * ParametresFenetre.rayonSommetAffichage);
			
			// On choisit la couleur d'affichage du nom des sommets
			g.setColor(ParametresFenetre.couleurNomSommet);
			
			// On affiche les noms de chaque sommet
			g.drawString(sommet.getNom(), x, y);

		}
		
		long duree = System.currentTimeMillis() - start;
		LOGGER.debug("affichage 1 (for sommets.size()) des "+sommets.size()+" sommets en " + duree + " ms");
	}
	
	/**
	 * Méthode permettant de dessiner les sommets
	 * @param g
	 */
	private void dessinerSommets1(Graphics g) {

		int x, y, nombreSommets;
		double longitude;
		double latitude;
		Sommet sommet;

		long start = System.currentTimeMillis();
		
		// On récupère l'ensemble des sommets
		ArrayList<Sommet> sommets = modele.getGraphe().getListeSommets();
		
		// On parcourt l'ensemble des sommets
		nombreSommets = sommets.size();
		for (int i = 0; i < nombreSommets; i++) {
			
			// On récupère le sommet i
			sommet = sommets.get(i);
			
			// On récupère ses coordonnees
			longitude = sommet.getLongitude();
			latitude = sommet.getLatitude();
			
			// On convertit les coordonnees pour l'affichage
			x = conversionLongitudeEnX(longitude);
			y = conversionLatitudeEnY(latitude);
			
			// On choisit la couleur selon que le sommet est sélectionné ou non
			if (sommet.getSelected() == false)
				g.setColor(ParametresFenetre.couleurSommetNonSelectionne);
			else
				g.setColor(ParametresFenetre.couleurSommetSelectionne);
			
			// On dessine les sommets
			g.fillOval(x - ParametresFenetre.rayonSommetAffichage, y - ParametresFenetre.rayonSommetAffichage, 
					2 * ParametresFenetre.rayonSommetAffichage,	2 * ParametresFenetre.rayonSommetAffichage);
			
			// On choisit la couleur d'affichage du nom des sommets
			g.setColor(ParametresFenetre.couleurNomSommet);
			
			// On affiche les noms de chaque sommet
			g.drawString(sommet.getNom(), x, y);

		}
		
		long duree = System.currentTimeMillis() - start;
		LOGGER.debug("affichage 2 (for size)           des "+sommets.size()+" sommets en " + duree + " ms");
	}
	
	
	/**
	 * Méthode permettant de dessiner les sommets
	 * @param g
	 */
	private void dessinerSommets2(Graphics g) {

		int x, y;
		double longitude;
		double latitude;
				
		long start = System.currentTimeMillis();
		
		// On récupère l'ensemble des sommets
		ArrayList<Sommet> sommets = modele.getGraphe().getListeSommets();
		
		// On parcourt l'ensemble des sommets
		for (Sommet sommet : sommets) {
			
			// On récupère ses coordonnees
			longitude = sommet.getLongitude();
			latitude = sommet.getLatitude();
			
			// On convertit les coordonnees pour l'affichage
			x = conversionLongitudeEnX(longitude);
			y = conversionLatitudeEnY(latitude);
			
			// On choisit la couleur selon que le sommet est sélectionné ou non
			if (sommet.getSelected() == false)
				g.setColor(ParametresFenetre.couleurSommetNonSelectionne);
			else
				g.setColor(ParametresFenetre.couleurSommetSelectionne);
			
			// On dessine les sommets
			g.fillOval(x - ParametresFenetre.rayonSommetAffichage, y - ParametresFenetre.rayonSommetAffichage, 
					2 * ParametresFenetre.rayonSommetAffichage,	2 * ParametresFenetre.rayonSommetAffichage);
			
			// On choisit la couleur d'affichage du nom des sommets
			g.setColor(ParametresFenetre.couleurNomSommet);
			
			// On affiche les noms de chaque sommet
			g.drawString(sommet.getNom(), x, y);

		}
		
		long duree = System.currentTimeMillis() - start;
		LOGGER.debug("affichage 3 (for each sommet)    des "+sommets.size()+" sommets en " + duree + " ms");
	}
	
	
	

	/**
	 * Méthode permettant de dessiner les aretes
	 * 
	 * @param g
	 */
	private void dessinerAretes(Graphics g) {

		
		int x1, y1, x2, y2;
		double long1, long2;
		double lat1, lat2;
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < modele.getGraphe().getListeAretes().size(); i++) {

			long1 = modele.getGraphe().getListeAretes().get(i).getSommetOrigine().getLongitude();
			long2 = modele.getGraphe().getListeAretes().get(i).getSommetExtremité()
					.getLongitude();
			lat1 = modele.getGraphe().getListeAretes().get(i).getSommetOrigine().getLatitude();
			lat2 = modele.getGraphe().getListeAretes().get(i).getSommetExtremité().getLatitude();

			x1 = conversionLongitudeEnX(long1);
			x2 = conversionLongitudeEnX(long2);
			y1 = conversionLatitudeEnY(lat1);
			y2 = conversionLatitudeEnY(lat2);

			g.setColor(ParametresFenetre.couleurArete);
			g.drawLine(x1, y1, x2, y2);
		}
		
		long duree = System.currentTimeMillis() - start;
		LOGGER.debug("affichage 1 des "+modele.getGraphe().getListeAretes().size()+" aretes en " + duree + " ms");
	}
	
	/**
	 * Méthode permettant de dessiner les aretes
	 * 
	 * @param g
	 */
	private void dessinerAretes2(Graphics g) {

		
		int x1, y1, x2, y2, nombreAretes;
		double long1, long2;
		double lat1, lat2;
		Arete arete;
		Sommet origine, extremite;
		
		long start = System.currentTimeMillis();
		
		// on récupère la liste des aretes
		ArrayList<Arete> listeAretes = modele.getGraphe().getListeAretes();
		nombreAretes = listeAretes.size();
		
		// on parcourt toutes les aretes
		for (int i = 0; i < nombreAretes; i++) {

			// on récupère l'arete i
			arete = listeAretes.get(i);
			
			// on récupère les sommets origine et destination
			origine = arete.getSommetOrigine();
			extremite = arete.getSommetExtremité();
			
			// on récupère leurs coordonnées
			long1 = origine.getLongitude();
			long2 = extremite.getLongitude();
			lat1 = origine.getLatitude();
			lat2 = extremite.getLatitude();

			// on les convertit pour l'affichage
			x1 = conversionLongitudeEnX(long1);
			x2 = conversionLongitudeEnX(long2);
			y1 = conversionLatitudeEnY(lat1);
			y2 = conversionLatitudeEnY(lat2);

			// on choisit la couleur d'affichage des aretes
			g.setColor(ParametresFenetre.couleurArete);
			
			// on dessine l'arete
			g.drawLine(x1, y1, x2, y2);
		}
		
		long duree = System.currentTimeMillis() - start;
		LOGGER.debug("affichage 2 des "+modele.getGraphe().getListeAretes().size()+" aretes en " + duree + " ms");
	
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
		String nom;		
		Sommet sommet;
		
		// On récupère le dernier noeud sélectionné
		sommet = modele.getdernierSommetSelectionne();
		
		// s'il existe
		if(sommet!=null){
			
			// on recupère ses coordonnées 
			lon = sommet.getLongitude();
			lat = sommet.getLatitude();
			
			// on les convertit pour l'affichage
			x =	conversionLongitudeEnX(lon);
			y = conversionLatitudeEnY(lat);
			
			// on l'affiche
			g.setColor(ParametresFenetre.couleurDernierSommetSelectionne);
			g.fillOval(x - ParametresFenetre.rayonSommetAffichage/2, y - ParametresFenetre.rayonSommetAffichage/2,
					ParametresFenetre.rayonSommetAffichage, ParametresFenetre.rayonSommetAffichage);
		
			// on affiche le nom
			nom = sommet.getNom();
			g.drawString(nom, x, y);

		}
	}
	
	
	
	/**
	 * Méthode permettant de dessiner le chemin le plus courts dijkstra
	 *  
	 * @param g
	 */
	private void dessinerCheminDijkstra(Graphics g) {

		int x1, y1, x2, y2, nombreAretes;
		double long1, long2;
		double lat1, lat2;
		Arete arete;
		Sommet origine, extremite;
		ArrayList<Arete> listeAretesDijkstra;
		
		// on récupère le chemin le plus court dijkstra
		listeAretesDijkstra = modele.getListeAretesDijkstra();
		
		// s'il existe
		if (listeAretesDijkstra != null) {
			
			// on parcourt toutes les aretes du chemin
			nombreAretes = listeAretesDijkstra.size();
			for (int i = 0; i < nombreAretes; i++) {

				// on récupère l'arete i
				arete = listeAretesDijkstra.get(i);
				
				// on récupère les sommets origine et extremite
				origine = arete.getSommetOrigine();
				extremite = arete.getSommetExtremité();
				
				// on récupère leurs coordonnées
				long1 = origine.getLongitude();
				long2 = extremite.getLongitude();
				lat1 = origine.getLatitude();
				lat2 = extremite.getLatitude();

				// on les convertit pour l'affichage
				x1 = conversionLongitudeEnX(long1);
				x2 = conversionLongitudeEnX(long2);
				y1 = conversionLatitudeEnY(lat1);
				y2 = conversionLatitudeEnY(lat2);

				// on l'affiche
				g.setColor(ParametresFenetre.couleurAreteDijkstra);
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

	
	
	/**
	 * Méthode permettant de dessiner le chemin le plus court Kruskal  
	 * 
	 * @param g
	 */
	private void dessinerCheminKruskal(Graphics g) {
		
		int x1, y1, x2, y2, nombreAretes;
		double long1, long2;
		double lat1, lat2;
		Arete arete;
		Sommet origine, extremite;
		ArrayList<Arete> listeAretesKruskal;
		
		// on récupère le chemin le plus court kruskal
		listeAretesKruskal = modele.getListeAretesKruskal();
		
		// s'il existe
		if (listeAretesKruskal != null) {
			
			// on parcourt toutes les aretes du chemin
			nombreAretes = listeAretesKruskal.size();
			for (int i = 0; i < nombreAretes; i++) {

				// on récupère l'arete i
				arete = listeAretesKruskal.get(i);
				
				// on récupère les sommets origine et extremite
				origine = arete.getSommetOrigine();
				extremite = arete.getSommetExtremité();
				
				// on récupère leurs coordonnées
				long1 = origine.getLongitude();
				long2 = extremite.getLongitude();
				lat1 = origine.getLatitude();
				lat2 = extremite.getLatitude();

				// on les convertit pour l'affichage
				x1 = conversionLongitudeEnX(long1);
				x2 = conversionLongitudeEnX(long2);
				y1 = conversionLatitudeEnY(lat1);
				y2 = conversionLatitudeEnY(lat2);

				// on l'affiche
				g.setColor(ParametresFenetre.couleurAreteKruskal);
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/*private void centerView() {

		double echelleX = 0;
		double echelleY = 0;

		double[] boundingBox = this.getBoundingBox();

		if ((boundingBox[1] - boundingBox[0] != Double.NEGATIVE_INFINITY)
				&& (boundingBox[3] - boundingBox[2] != Double.NEGATIVE_INFINITY)) {
			echelleX = (this.getWidth() - 100) * ViewParameters.ECHELLE_BASE
					/ (boundingBox[1] - boundingBox[0]);
			echelleY = (this.getHeight() - 100) * ViewParameters.ECHELLE_BASE
					/ (boundingBox[3] - boundingBox[2]);

			this.echelle = Math.min(echelleX, echelleY);

			this.offsetX = -(this
					.getLocalCoordX((boundingBox[1] + boundingBox[0]) / 2) - this
					.getLocalCoordX(0));
			this.offsetY = -(this
					.getLocalCoordY((boundingBox[3] + boundingBox[2]) / 2) - this
					.getLocalCoordY(0));

		} else { // pas de contenu
			Model.logger
					.warning("Pas de contenu trouvé lors du centrage de la vue 2D...");
		}
	}*/
	
		
	
	
	
	/**
	 * Méthode permettant de convertir la longitude des données en coordonnées écran
	 *   
	 * @param longitude
	 * @return x en pixel
	 */
	public int conversionLongitudeEnX(double longitude) {
		
		return (int) Math.round(this.getWidth() / 2 + ParametresFenetre.offsetX + longitude
				* ParametresFenetre.echelle / ParametresFenetre.ECHELLE_BASE);

	}

	
	/**
	 * Méthode permettant de convertir la latitude des données en coordonnées écran
	 * 
	 * @param latitude
	 * @return y en pixel
	 */
	public int conversionLatitudeEnY(double latitude) {
		
		return (int) Math.round(this.getHeight() / 2 + ParametresFenetre.offsetY - latitude
				* ParametresFenetre.echelle / ParametresFenetre.ECHELLE_BASE);

	}

	
	
	/**
	 * Méthode permettant de convertir une abscisse locale (repère JPanel) en
	 * longitude réelle
	 * 
	 * @param x l'abscisse locale en pixel
	 * @return la longitude réelle
	 */
	public double conversionXenLongitude(double x) {
		
		return (x - (this.getWidth() / 2 + ParametresFenetre.offsetX))
				* ParametresFenetre.ECHELLE_BASE / ParametresFenetre.echelle;
	}

	/**
	 * Méthode permettant de convertir une ordonnée locale (repère JPanel) en
	 * latitude réelle
	 * 
	 * @param y
	 *            l'ordonnée locale
	 * @return l'ordonnée réelle
	 */
	public double getRealCoordY(double y) {
			
		return (y - (this.getHeight() / 2 + ParametresFenetre.offsetY))
				* -ParametresFenetre.ECHELLE_BASE / ParametresFenetre.echelle;
	}

	

	/**
	 * Méthode permettant de redessiner le panneau 
	 */
	public void update() {
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
					
					dessinerFondCarte(g);
					
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
}
