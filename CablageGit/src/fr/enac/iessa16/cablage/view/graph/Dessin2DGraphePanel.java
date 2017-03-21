package fr.enac.iessa16.cablage.view.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Donnees;
import fr.enac.iessa16.cablage.model.graph.Arete;
import fr.enac.iessa16.cablage.model.graph.Graphe;
import fr.enac.iessa16.cablage.model.graph.Sommet;
import fr.enac.iessa16.cablage.view.ParametresAffichage;

/**
 * Classe Dessin2DGraphePanel, dérivant de JPanel, permettant le dessin du
 * graphe du modèle grace à la bibliothèque Graphics2D 
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Dessin2DGraphePanel extends JPanel {
	
	/**
	 * Le modèle de l'application, contenant les données à afficher
	 */
	private Donnees _donnees;
	
	
	/**
	 * Attribut contenant l'image de fond (google maps)
	 */
	//private BufferedImage image = null;
	
		
	/**
	 * Constructeur de la classe DrawGraph2DPanel
	 * 
	 * @param model le modèle de l'application
	 * @param controller le controleur de l'application
	 */
	public Dessin2DGraphePanel(Donnees model,Controleur controller) {
		
		// Appel du constructeur parent (JPanel)
		super();
		
		// Sauvegarde du modele dans les attributs
		this._donnees = model;
		
		// Ajout du listener permettant d'écouter les clics souris (le listener est dans le controleur)
		this.addMouseListener(controller.getCliqueFenetreGraphController());
		
		// Téléchargement de l'image de fond depuis google maps
		/*try {
			image = ImageIO.read(new URL("https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318&markers=color:red%7Clabel:C%7C40.718217,-73.998284&key=AIzaSyABrzv3EoXRNgraD15R12UduLzOBpwg14A"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}

	
	/* (non-Javadoc)
	 * 
	 * Rédéfinition de la méthode paint du JPanel pour dessiner le graphe
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		
		// Affichage de l'image de fond (si la variable correspondante est à true)
	//	if (ParametresAffichage.isDrawBackgroundImage) {
			dessinerImageFond(g);
	//	}

		// Affichage des sommets du graphe du modèle (si la variable correspondante est à true)
	//	if (ParametresAffichage.isDrawVertex) {
			dessinerSommets(g);
	//	}
		
		// Affichage des aretes du graphe du modèle (si la variable correspondante est à true)
	//	if (ParametresAffichage.isDrawEdge) {
			drawEdge(g);
	//	}	
	}
	
	
	/**
	 * Methode permettant de dessiner l'image de fond
	 * 
	 * @param g
	 */
	private void dessinerImageFond(Graphics g) {
		
		// Si l'image a bien été téléchargée,
		/*if (image != null) {
			
			// on la dessine
			g.drawImage(image, 0, 0, null);
		}*/
	}
	

	/**
	 * Methode permettant de dessiner les sommets du graphe du modele
	 * 
	 * @param g
	 */
	private void dessinerSommets(Graphics g) {
		
		// Variables locales
		int i, x, y, size;
		
		// On récupère la taille des sommets dans la classe contenant les paramètres de la vue
		size = ParametresAffichage.vertexSize;
		
		// On récupère le graphe à afficher dans le modele	
		Graphe graphe = _donnees.getGraph();
		
		// S'il y a un graphe existant
		if (graphe != null) {
			
			// On récupère la liste de ses sommets
			ArrayList<Sommet> sommets = graphe.getListeSommets();
			
			/*for (Sommet sommet : sommets) {
				
				g.fillOval(, 50, 50, 50);
				
			}*/
			
			// On fait une boucle sur tous les sommets du graphe
			for (i = 0; i < sommets.size(); i++) {
				
				// Choix de la couleur du cercle
				g.setColor(Color.red);
				
				// Calcul des coordonnées d'affichage du sommets
				x = this.longitudeToX(_donnees.getGraph().getListeSommets().get(i).getLongitude()) - size/2;
				y = this.latitudeToY(sommets.get(i).getLatitude()) - size/2;
				
				// On dessine le cercle plein
				g.fillOval(x, y, size, size);
				
				// Choix de la couleur du texte
				g.setColor(Color.black);
				
				// Affichage du texte (le nom du sommet)
				g.drawString(sommets.get(i).getNom(), x, y);
				
				//System.out.println("Affichage sommet x="+x+" y="+y);
				
			}
		
		}
		
		//g.drawRect(50, 50, 200, 200);
		
		/*g.fillOval(500, 50, 50, 50);
		g.fillOval(900, 50, 50, 50);
		
		g.setColor(Color.pink);
		g.fillOval(275, 400, 50, 50);
		g.setColor(Color.red);
		g.fillOval(750, 400, 50, 50);*/
		
	}
	
	
	/**
	 * Méthode permettant de dessiner les aretes
	 * 
	 * @param g
	 */
	private void drawEdge(Graphics g) {
		
		// Variables locales
		int i, x1, y1, x2 ,y2;
		Arete arete;
		Sommet sommet1, sommet2;
		
		
		// On récupère le graphe à afficher dans le modele		
		Graphe graphe = _donnees.getGraph();
		
		// S'il y a un graphe existant
		if (graphe != null) {
			
			// On récupère la liste des aretes
			ArrayList<Arete> aretes = graphe.getListeAretes();
			
			/*for (Sommet sommet : sommets) {
				
				g.fillOval(, 50, 50, 50);
				
			}*/
			
			// On parcourt toutes les aretes
			for (i = 0; i < aretes.size(); i++) {
				
				// Choix de la couleur des aretes
				g.setColor(Color.red);
				
				// on récupere l'arete i
				arete = aretes.get(i);
				
				// on récupere son sommet origine...
				sommet1 = arete.getSommetOrigine();
				
				// ... et son sommet destination
				sommet2 = arete.getSommetDestination();
				
				// on calcule les coordonnées d'affichage du sommet origine
				x1 = longitudeToX(sommet1.getLongitude());
				y1 = latitudeToY(sommet1.getLatitude());

				// on calcule les coordonnées d'affichage du sommet destination
				x2 = longitudeToX(sommet2.getLongitude());
				y2 = latitudeToY(sommet2.getLatitude());
				
				// On trace une ligne entre les deux
				g.drawLine(x1, y1, x2, y2);

				//System.out.println("Affichage sommet x="+x+" y="+y);
				
			}
		
		}
		
		
		
		
	}

	

	
	/**
	 * Methode permettant de calculer l'abscisse x (en pixel) d'affichage
	 * du sommet en fonction de sa longitude (en degré)
	 * 
	 * @param longitude la longitude du sommet
	 * @return l'abscisse x calculée
 	 */
	private int longitudeToX(double longitude) {
		return (int) longitude;
		//TODO conversion affine
	}
	
	
	/**
	 * Methode permettant de calculer l'ordonnée y (en pixel) d'affichage
	 * du sommet en fonction de sa latitude (en degré)
	 * 
	 * @param latitude la latitude du sommet
	 * @return l'ordonnée y calculée
	 */
	private int latitudeToY(double latitude) {
		return (int)latitude;
		//TODO conversion affine
	}
}
