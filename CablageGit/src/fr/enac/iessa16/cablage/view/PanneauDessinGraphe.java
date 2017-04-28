package fr.enac.iessa16.cablage.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
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

import com.sun.javafx.tk.FontMetrics;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;
import javafx.scene.text.Font;


/**
 * Classe permmettant de dessiner un graphe
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class PanneauDessinGraphe extends JPanel implements Printable {

	
	// Attributs de la classe
	private static Modele donneesaafficher;
	private Controleur controleur;
	
	private BufferedImage imageFond;

	// public DessinDuGrapheParDefaut(ConstructionGrapheParDefaut toto) {
	public PanneauDessinGraphe(Modele model, Controleur controleur) {

		super();

		this.donneesaafficher = model;
		this.controleur = controleur;

		this.addMouseListener(controleur.getControleurSouris());
		this.addMouseMotionListener(controleur.getControleurSouris());
		this.addMouseWheelListener(controleur.getControleurSouris());
		
		
		
		this.setPreferredSize(ParametresFenetre.dimensionJPanelDessin);
		this.setBorder(BorderFactory.createTitledBorder("Vue 2D"));
		this.imageFond = null;
		
	
		System.out.println("taille du panneau "+ParametresFenetre.dimensionJPanelDessin);
		
		//String center="Brooklyn+Bridge,New+York,NY";
		String center="Paris-Charles+De+Gaulle+(CDG)";
		String zoom = "12";//"13";
		String size = "2000x2000";
		String key = "AIzaSyABrzv3EoXRNgraD15R12UduLzOBpwg14A";
		
		/*String adresseImage = "https://maps.googleapis.com/maps/api/staticmap?center=" + center
				+"&zoom="+zoom
				+ "&size="+size
				+ "&maptype=roadmap&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318&markers=color:red%7Clabel:C%7C40.718217,-73.998284&key=AIzaSyABrzv3EoXRNgraD15R12UduLzOBpwg14A";
		*/
		String adresseImage = "https://maps.googleapis.com/maps/api/staticmap?center=" + center
				+"&zoom="+zoom
				+ "&size="+size
				+ "&maptype=roadmap&key="+key;
		try {
			this.imageFond = ImageIO.read(new URL(adresseImage));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
        
		if (donneesaafficher != null) {
			
			// Si le graphe n'est pas vide
			if (donneesaafficher.getGraphe() != null) {

				//Graphics2D g2D = (Graphics2D) g;
				
				//AffineTransform at = new AffineTransform();
		        //at.scale(scale, scale);
		        //g2D.setTransform(at);
				
				
				dessinerFondCarte(g);
				
				dessinerSommets(g);

				dessinerAretes(g);

				dessinerDernierSommetSelectionne(g);

				dessinerCheminDijkstra(g);

				dessinerCheminKruskal(g);
				
				//dessinerCheminAstar(g);
			}
		}
	}

	

	private void dessinerFondCarte(Graphics g) {
		// TODO Auto-generated method stub
		if (this.imageFond != null) {
			g.drawImage(imageFond, 16, 20, this);
			
		}
	}

	private void dessinerSommets(Graphics g) {

		

		int x, y;
		double longitude;
		double latitude;
		Sommet sommet;

		// On parcourt l'ensemble des sommets
		long start = System.currentTimeMillis();
		ArrayList<Sommet > sommetsRacha = donneesaafficher.getGraphe().getListeSommets();
		int size = sommetsRacha.size();
		for (int i = 0; i < size; i++) {

			// super.paintComponent(g);
			// Graphics2D gr = (Graphics2D) g;
			// On recupere la longitude et la latitude de chaque centre du
			// sommet
			sommet = sommetsRacha.get(i);
			longitude = sommet.getLongitude();
			latitude = sommet.getLatitude();
			x = conversionLongitudeEnX(longitude);
			y = conversionLatitudeEnY(latitude);
			// System.out.println("latitude = " + latitude +" longitude = " +
			// longitude + " x = " + x + " y = "+y);
			// On colorie les sommets en bleu
			if (sommet.getSelected() == false)
				g.setColor(java.awt.Color.BLUE);
			else
				g.setColor(Color.pink);
			// On rend visible les sommets
			g.fillOval(x - ParametresFenetre.rayonSommetAffichage, y - ParametresFenetre.rayonSommetAffichage, 2 * ParametresFenetre.rayonSommetAffichage,
					2 * ParametresFenetre.rayonSommetAffichage);
			g.setColor(java.awt.Color.BLACK);
			// On affiche les noms de chaque sommet
			g.drawString(sommet.getNom(), x, y);

		}
		long duree = System.currentTimeMillis() - start;
		System.out.println("affichage 1 des sommet en " + duree + " ms");
		
		
		
		long start3 = System.currentTimeMillis();
		ArrayList<Sommet> sommetsFred = donneesaafficher.getGraphe().getListeSommets();
		for (Sommet s : sommetsFred) {

			// super.paintComponent(g);
			// Graphics2D gr = (Graphics2D) g;
			// On recupere la longitude et la latitude de chaque centre du
			// sommet
	
			longitude = s.getLongitude();
			latitude = s.getLatitude();
			x = conversionLongitudeEnX(longitude);
			y = conversionLatitudeEnY(latitude);
			// System.out.println("latitude = " + latitude +" longitude = " +
			// longitude + " x = " + x + " y = "+y);
			// On colorie les sommets en bleu
			if (s.getSelected() == false)
				g.setColor(java.awt.Color.BLUE);
			else
				g.setColor(Color.pink);
			// On rend visible les sommets
			g.fillOval(x - ParametresFenetre.rayonSommetAffichage, y - ParametresFenetre.rayonSommetAffichage, 2 * ParametresFenetre.rayonSommetAffichage,
					2 * ParametresFenetre.rayonSommetAffichage);
			g.setColor(java.awt.Color.BLACK);
			// On affiche les noms de chaque sommet
			g.drawString(s.getNom(), x, y);

		}
		long duree3 = System.currentTimeMillis() - start3;
		System.out.println("affichage Fred des sommet en " + duree3 + " ms");
		
		
		

		// for (int i = 0; i <
		// donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size();
		// i++) {
		// for (int i = 0; i <
		// donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size();
		// i++) {
		long start2 = System.currentTimeMillis();
		// int size1 =
		// donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size();
		for (int i = 0; i < size; i++) {

			// super.paintComponent(g);
			// Graphics2D gr = (Graphics2D) g;
			// On recupere la longitude et la latitude de chaque centre du
			// sommet
			longitude = donneesaafficher.getGraphe().getListeSommets().get(i).getLongitude();
			latitude = donneesaafficher.getGraphe().getListeSommets().get(i).getLatitude();
			x = conversionLongitudeEnX(longitude);
			y = conversionLatitudeEnY(latitude);
			// System.out.println("latitude = " + latitude +" longitude = " +
			// longitude + " x = " + x + " y = "+y);
			// On colorie les sommets en bleu
			if (donneesaafficher.getGraphe().getListeSommets().get(i).getSelected() == false)
				g.setColor(java.awt.Color.BLUE);
			else
				g.setColor(Color.pink);
			// On rend visible les sommets
			g.fillOval(x - ParametresFenetre.rayonSommetAffichage, y - ParametresFenetre.rayonSommetAffichage, 2 * ParametresFenetre.rayonSommetAffichage,
					2 * ParametresFenetre.rayonSommetAffichage);
			g.setColor(java.awt.Color.BLACK);
			// On affiche les noms de chaque sommet
			g.drawString(donneesaafficher.getGraphe().getListeSommets().get(i).getNom(), x, y);

		}
		long duree2 = System.currentTimeMillis() - start2;
		System.out.println("affichage 2 des sommet en " + duree2 + " ms");

	}

	private void dessinerAretes(Graphics g) {
		// Pareil pour les aretes...
		int x, y;
		int x1, y1, x2, y2;
		double longitude;
		double latitude;
		double long1, long2;
		double lat1, lat2;
		Sommet sommet;

		for (int i = 0; i < donneesaafficher.getGraphe().getListeAretes().size(); i++) {

			long1 = donneesaafficher.getGraphe().getListeAretes().get(i).getSommetOrigine().getLongitude();
			long2 = donneesaafficher.getGraphe().getListeAretes().get(i).getSommetExtremité()
					.getLongitude();
			lat1 = donneesaafficher.getGraphe().getListeAretes().get(i).getSommetOrigine().getLatitude();
			lat2 = donneesaafficher.getGraphe().getListeAretes().get(i).getSommetExtremité().getLatitude();

			x1 = conversionLongitudeEnX(long1);
			x2 = conversionLongitudeEnX(long2);
			y1 = conversionLatitudeEnY(lat1);
			y2 = conversionLatitudeEnY(lat2);

			g.setColor(java.awt.Color.RED);
			g.drawLine(x1, y1, x2, y2);
		}

	}

	private void dessinerDernierSommetSelectionne(Graphics g) {

		
		int x, y;
		int x1, y1, x2, y2;
		double longitude;
		double latitude;
		double long1, long2;
		double lat1, lat2;
		Sommet sommet;
		// Affichage en vert du dernier noeud sélectionné
		sommet=donneesaafficher.getdernierSommetSelectionne();
		if(sommet!=null){
			int x3, y3;
			double long3;
			double lat3;
			String nom;
		
			long3=(int)sommet.getLongitude();
			lat3=(int)sommet.getLatitude();
			nom=sommet.getNom();
			x3=	conversionLongitudeEnX(long3);
			y3 = conversionLatitudeEnY(lat3);
			g.drawString(nom, x3, y3);
			g.setColor(java.awt.Color.GREEN);
			g.fillOval((int)(x3 - ParametresFenetre.rayonSommetAffichage*0.5), (int)(y3 - ParametresFenetre.rayonSommetAffichage*0.5),ParametresFenetre.rayonSommetAffichage,ParametresFenetre.rayonSommetAffichage);
		
			//System.out.println("Affichage du dernier noeud sélectionnée");
		}
	}
	
	
	
	private void dessinerCheminDijkstra(Graphics g) {
		// TODO Auto-generated method stub
		int x, y;
		int x1, y1, x2, y2;
		double longitude;
		double latitude;
		double long1, long2;
		double lat1, lat2;
		Sommet sommet;

		if (donneesaafficher.getListeAretesDijkstra() != null) {
			for (int i = 0; i < donneesaafficher.getListeAretesDijkstra().size(); i++) {

				long1 = donneesaafficher.getListeAretesDijkstra().get(i)
						.getSommetOrigine().getLongitude();
				long2 = donneesaafficher.getListeAretesDijkstra().get(i)
						.getSommetExtremité().getLongitude();
				lat1 = donneesaafficher.getListeAretesDijkstra().get(i)
						.getSommetOrigine().getLatitude();
				lat2 = donneesaafficher.getListeAretesDijkstra().get(i)
						.getSommetExtremité().getLatitude();

				x1 = conversionLongitudeEnX(long1);
				x2 = conversionLongitudeEnX(long2);
				y1 = conversionLatitudeEnY(lat1);
				y2 = conversionLatitudeEnY(lat2);

				g.setColor(java.awt.Color.BLACK);
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

	private void dessinerCheminKruskal(Graphics g) {
		// TODO Auto-generated method stub
		int x, y;
		int x1, y1, x2, y2;
		double longitude;
		double latitude;
		double long1, long2;
		double lat1, lat2;
		Sommet sommet;
	

		if (donneesaafficher.getGraphe() != null) {
			if (donneesaafficher.getListeAretesKruskal() != null) {
				for (int i = 0; i < donneesaafficher.getListeAretesKruskal()
						.size(); i++) {

					long1 = donneesaafficher.getListeAretesKruskal().get(i)
							.getSommetOrigine().getLongitude();
					long2 = donneesaafficher.getListeAretesKruskal().get(i)
							.getSommetExtremité().getLongitude();
					lat1 = donneesaafficher.getListeAretesKruskal().get(i)
							.getSommetOrigine().getLatitude();
					lat2 = donneesaafficher.getListeAretesKruskal().get(i)
							.getSommetExtremité().getLatitude();

					x1 = conversionLongitudeEnX(long1);
					x2 = conversionLongitudeEnX(long2);
					y1 = conversionLatitudeEnY(lat1);
					y2 = conversionLatitudeEnY(lat2);

					g.setColor(java.awt.Color.YELLOW);
					g.drawLine(x1, y1, x2, y2);
				}
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
	 * Méthode permettant de calculer les valeurs Xmin/max et Ymin/max de la
	 * boite englobante des secteurs du dataset, utilisés pour centrer la vue
	 * 
	 * @return un tableau de 4 double: Xmin, Xmax, Ymin, Ymax
	 */
	/*private double[] getBoundingBox() {

		double data[] = new double[4];
		double[] bb = new double[4];

		/*data[0] = Double.POSITIVE_INFINITY; // x min
		data[1] = Double.NEGATIVE_INFINITY; // x max
		data[2] = Double.POSITIVE_INFINITY; // y min
		data[3] = Double.NEGATIVE_INFINITY; // y max

		
		//for (Airspace atsu : atsuList.list()) {
			for (Sector sector : model.getAllSectorVector()) {
				for (Volume volume : sector.getVolumesList()) {
					Leg leg = volume.getBoundingBox();
					bb[0] = leg.start.lonW();
					bb[1] = leg.stop.lonW();
					bb[2] = leg.stop.latW();
					bb[3] = leg.start.latW();

					if (bb[0] < data[0]) {
						data[0] = bb[0];
					}
					if (bb[1] > data[1]) {
						data[1] = bb[1];
					}
					if (bb[2] < data[2]) {
						data[2] = bb[2];
					}
					if (bb[3] > data[3]) {
						data[3] = bb[3];
					}
				}
			}
		}*/
		
	/*	data[0] = model.getCoflightParser().getMinLon(); // x min
		data[1] = model.getCoflightParser().getMaxLon(); // x max
		data[2] = model.getCoflightParser().getMinLat(); // y min
		data[3] = model.getCoflightParser().getMaxLat(); // y max
		
		return data;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int conversionLongitudeEnX(double longitude) {
		return (int) Math.round(ParametresFenetre.dimensionJPanelDessin.getWidth() / 2 + ParametresFenetre.offsetX + longitude
				* ParametresFenetre.echelle / ParametresFenetre.ECHELLE_BASE);

	}

	public static int conversionLatitudeEnY(double latitude) {
		return (int) Math.round(ParametresFenetre.dimensionJPanelDessin.getHeight() / 2 + ParametresFenetre.offsetY - latitude
				* ParametresFenetre.echelle / ParametresFenetre.ECHELLE_BASE);

	}

	
	
	/**
	 * Méthode permettant de convertir une abscisse locale (repère JPanel) en
	 * abscisse réelle
	 * 
	 * @param x
	 *            l'abscisse locale
	 * @return l'abscisse réelle
	 */
	// TODO fonction a mettre dans le model ???
	public static double getRealCoordX(double x) {
		return (x - (ParametresFenetre.dimensionJPanelDessin.getWidth() / 2 + ParametresFenetre.offsetX))
				* ParametresFenetre.ECHELLE_BASE / ParametresFenetre.echelle;
	}

	/**
	 * Méthode permettant de convertir une ordonnée locale (repère JPanel) en
	 * ordonnée réelle
	 * 
	 * @param y
	 *            l'ordonnée locale
	 * @return l'ordonnée réelle
	 */
	// TODO fonction a mettre dans le model ???
	public static double getRealCoordY(double y) {
		return (y - (ParametresFenetre.dimensionJPanelDessin.getHeight() / 2 + ParametresFenetre.offsetY))
				* -ParametresFenetre.ECHELLE_BASE / ParametresFenetre.echelle;
	}

	/*
	 * Sauvegarde FBE
	 * 
	 * public static int ConversionLongitudeEnx(double longitude) {
		int x;
		x = ((int) (((longitude - donneesaafficher.getGrapheàafficher().getLongitudemin())
				* (ParametresFenetre.dimensionJPanelGraphe.width * 0.9 - ParametresFenetre.dimensionJPanelGraphe.width * 0.1)
				/ (donneesaafficher.getGrapheàafficher().getLongitudemax()
						- donneesaafficher.getGrapheàafficher().getLongitudemin()))
				+ ParametresFenetre.dimensionJPanelGraphe.width * 0.1));
		return x;

	}

	public static int ConversionLatitudeEny(double latitude) {
		int y;
		y = ((int) (((latitude - donneesaafficher.getGrapheàafficher().getLatitudemin())
				* (ParametresFenetre.dimensionEcran.height * 0.9 - ParametresFenetre.dimensionEcran.height * 0.1)
				/ (donneesaafficher.getGrapheàafficher().getLatitudemax()
						- donneesaafficher.getGrapheàafficher().getLatitudemin()))
				+ ParametresFenetre.dimensionEcran.height * 0.1));
		return y;

	}*/

	/*public static double ConversionxEnLongitude(int x) {

		double longitude;
		longitude = (donneesaafficher.getGrapheàafficher().getLongitudemax()
				- donneesaafficher.getGrapheàafficher().getLongitudemin())
				/ (ParametresFenetre.dimensionEcran.width * 0.9 - ParametresFenetre.dimensionEcran.width * 0.1)
				* (x - ParametresFenetre.dimensionEcran.width * 0.1)
				+ donneesaafficher.getGrapheàafficher().getLongitudemin();
		return longitude;
	}*/

	/*public static double ConversionyEnLatitude(int y) {
		double latitude;
		latitude = (donneesaafficher.getGrapheàafficher().getLatitudemax()
				- donneesaafficher.getGrapheàafficher().getLatitudemin())
				/ (ParametresFenetre.dimensionEcran.height * 0.9 - ParametresFenetre.dimensionEcran.height * 0.1)
				* (y - ParametresFenetre.dimensionEcran.height * 0.1)
				+ donneesaafficher.getGrapheàafficher().getLatitudemin();
		return latitude;

	}*/

	public void update() {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		if (donneesaafficher != null) {
			
			// Si le graphe n'est pas vide
			if (donneesaafficher.getGraphe() != null) {
				
				dessinerFondCarte(g);
				
				dessinerSommets(g);

				dessinerAretes(g);

				dessinerDernierSommetSelectionne(g);

				dessinerCheminDijkstra(g);

				dessinerCheminKruskal(g);
				
				//dessinerCheminAstar(g);
			}
		}
	      if(pageIndex>=1) return NO_SUCH_PAGE;
	    //  g.setFont(new Font("arial", Font.BOLD, 30));
	     /// FontMetrics fm = g.getFontMetrics();
	      int X = (int) pageFormat.getImageableX(), Y = (int) pageFormat.getImageableY();
	      int W = (int) pageFormat.getImageableWidth(), H = (int) pageFormat.getImageableHeight();
	     // java.awt.geom.Rectangle2D r = fm.getStringBounds("" + pi, g);
		
		return 0;
	}
	
	
	
}