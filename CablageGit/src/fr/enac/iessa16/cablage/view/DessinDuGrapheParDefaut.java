package fr.enac.iessa16.cablage.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.model.GrapheTheorique;
import fr.enac.iessa16.cablage.model.Sommet;

/**
 * Classe permmettant de dessiner un graphe
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class DessinDuGrapheParDefaut extends JPanel {

	
	//private float scale = 1;
	
	// Attributs de la classe
	private static DonneesAAfficher donneesaafficher;
	private Controleur controleur;
	
	private BufferedImage imageFond;

	// public DessinDuGrapheParDefaut(ConstructionGrapheParDefaut toto) {
	public DessinDuGrapheParDefaut(DonneesAAfficher model, Controleur controleur) {

		super();

		this.donneesaafficher = model;
		this.controleur = controleur;

		this.addMouseListener(controleur.getControleurClique());
		this.addMouseMotionListener(controleur.getControleurClique());

		
		
		
		this.setPreferredSize(ParametresFenetre.dimensionJPanelGraphe);
		this.setBorder(BorderFactory.createTitledBorder("Vue 2D"));
		this.imageFond = null;
		
	
		System.out.println("taille du panneau "+ParametresFenetre.dimensionJPanelGraphe);
		
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
			if (donneesaafficher.getGrapheàafficher() != null) {

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
				
				dessinerCheminAstar(g);
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

		long start = System.currentTimeMillis();

		int x, y;
		double longitude;
		double latitude;
		Sommet sommet;

		// On parcourt l'ensemble des sommets
		int size = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size();
		for (int i = 0; i < size; i++) {

			// super.paintComponent(g);
			// Graphics2D gr = (Graphics2D) g;
			// On recupere la longitude et la latitude de chaque centre du
			// sommet
			sommet = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i);
			longitude = sommet.getLongitude();
			latitude = sommet.getLatitude();
			x = ConversionLongitudeEnx(longitude);
			y = ConversionLatitudeEny(latitude);
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
			longitude = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getLongitude();
			latitude = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude();
			x = ConversionLongitudeEnx(longitude);
			y = ConversionLatitudeEny(latitude);
			// System.out.println("latitude = " + latitude +" longitude = " +
			// longitude + " x = " + x + " y = "+y);
			// On colorie les sommets en bleu
			if (donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getSelected() == false)
				g.setColor(java.awt.Color.BLUE);
			else
				g.setColor(Color.pink);
			// On rend visible les sommets
			g.fillOval(x - ParametresFenetre.rayonSommetAffichage, y - ParametresFenetre.rayonSommetAffichage, 2 * ParametresFenetre.rayonSommetAffichage,
					2 * ParametresFenetre.rayonSommetAffichage);
			g.setColor(java.awt.Color.BLACK);
			// On affiche les noms de chaque sommet
			g.drawString(donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getNom(), x, y);

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

		for (int i = 0; i < donneesaafficher.getGrapheàafficher().getEnsembleAretes().size(); i++) {

			long1 = donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetOrigine().getLongitude();
			long2 = donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetExtremité()
					.getLongitude();
			lat1 = donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetOrigine().getLatitude();
			lat2 = donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetExtremité().getLatitude();

			x1 = ConversionLongitudeEnx(long1);
			x2 = ConversionLongitudeEnx(long2);
			y1 = ConversionLatitudeEny(lat1);
			y2 = ConversionLatitudeEny(lat2);

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
			x3=	ConversionLongitudeEnx(long3);
			y3 = ConversionLatitudeEny(lat3);
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

		if (donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra() != null) {
			for (int i = 0; i < donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().size(); i++) {

				long1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().get(i)
						.getSommetOrigine().getLongitude();
				long2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().get(i)
						.getSommetExtremité().getLongitude();
				lat1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().get(i)
						.getSommetOrigine().getLatitude();
				lat2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().get(i)
						.getSommetExtremité().getLatitude();

				x1 = ConversionLongitudeEnx(long1);
				x2 = ConversionLongitudeEnx(long2);
				y1 = ConversionLatitudeEny(lat1);
				y2 = ConversionLatitudeEny(lat2);

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
	

		if (donneesaafficher.getGrapheàafficher() != null) {
			if (donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal() != null) {
				for (int i = 0; i < donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal()
						.size(); i++) {

					long1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().get(i)
							.getSommetOrigine().getLongitude();
					long2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().get(i)
							.getSommetExtremité().getLongitude();
					lat1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().get(i)
							.getSommetOrigine().getLatitude();
					lat2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().get(i)
							.getSommetExtremité().getLatitude();

					x1 = ConversionLongitudeEnx(long1);
					x2 = ConversionLongitudeEnx(long2);
					y1 = ConversionLatitudeEny(lat1);
					y2 = ConversionLatitudeEny(lat2);

					g.setColor(java.awt.Color.YELLOW);
					g.drawLine(x1, y1, x2, y2);
				}
			}

		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void dessinerCheminAstar(Graphics g) {
		// TODO Auto-generated method stub
		int x, y;
		int x1, y1, x2, y2;
		double longitude;
		double latitude;
		double long1, long2;
		double lat1, lat2;
		Sommet sommet;
		if (donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtAStar() != null) {
			if (donneesaafficher.getGrapheàafficher() != null) {
				for (int i = 0; i < donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtAStar()
						.size(); i++) {

					long1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtAStar.get(i)
							.getSommetOrigine().getLongitude();

					long2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtAStar().get(i)
							.getSommetExtremité().getLongitude();
					lat1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtAStar().get(i)
							.getSommetOrigine().getLatitude();
					lat2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtAStar().get(i)
							.getSommetExtremité().getLatitude();

					x1 = ConversionLongitudeEnx(long1);
					x2 = ConversionLongitudeEnx(long2);
					y1 = ConversionLatitudeEny(lat1);
					y2 = ConversionLatitudeEny(lat2);

					g.setColor(java.awt.Color.BLACK);
					g.drawLine(x1, y1, x2, y2);
				}
			}

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static int ConversionLongitudeEnx(double longitude) {
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

	}

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
	
	
	
}
