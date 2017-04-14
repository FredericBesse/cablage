package fr.enac.iessa16.cablage.view;

import java.awt.Color;
import java.awt.Graphics;

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

	// Attributs de la classe
	private static DonneesAAfficher donneesaafficher;
	private Controleur controleur;
	private Sommet sommet;


	// public DessinDuGrapheParDefaut(ConstructionGrapheParDefaut toto) {
	public DessinDuGrapheParDefaut(DonneesAAfficher model, Controleur controleur) {
		super();
		this.donneesaafficher = model;
		this.controleur = controleur;

		this.addMouseListener(controleur.getControleurClique());
		this.addMouseMotionListener(controleur.getControleurClique());

		this.sommet = null;

		// System.out.println("Création du JPanel Dessin graphe à partir d'un
		// graphe contenant
		// "+monGraphePrecedemmentCréé.getEnsembleDeSommet().size()+" sommets");

		// TODO Auto-generated constructor stub

		// GrapheTheorique graphe = new
		// GrapheTheorique(this.graphe.getEnsembleDeSommet(),this.graphe.getEnsembleAretes());
		// this.graphe = null;
	}

	protected void paintComponent(Graphics g) {

		//System.out.println("paint componet");

		int x, y;
		int x1, y1, x2, y2;
		double longitude;
		double latitude;
		double long1 , long2;
		double lat1,lat2;
		Sommet sommet;
		// int x3, y3;
		// Si le graphe n'est pas vide
		if (donneesaafficher.getGrapheàafficher() != null) {
			// On parcourt l'ensemble des sommets
			long start = System.currentTimeMillis();
			int size = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size();
			for (int i = 0; i < size; i++) {
				
				// super.paintComponent(g);
				// Graphics2D gr = (Graphics2D) g;
				// On recupere la longitude et la latitude de chaque centre du
				// sommet
				sommet = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i);
				longitude = sommet.getLongitude();
				latitude =sommet.getLatitude();
				x = ConversionLongitudeEnx(longitude);
				y = ConversionLatitudeEny(latitude);
				//System.out.println("latitude = " + latitude +" longitude = " + longitude + " x = " + x + " y = "+y);
				// On colorie les sommets en bleu
				if (sommet.getSelected() == false)
					g.setColor(java.awt.Color.BLUE);
				else
					g.setColor(Color.pink);
				// On rend visible les sommets
				g.fillOval(x - ParametresFenetre.rayon, y - ParametresFenetre.rayon, 2*ParametresFenetre.rayon, 2*ParametresFenetre.rayon);
				g.setColor(java.awt.Color.BLACK);
				// On affiche les noms de chaque sommet
				g.drawString(sommet.getNom(), x, y);

			}
			long duree = System.currentTimeMillis()-start;
			System.out.println("affichage 1 des sommet en "+ duree+ " ms");



			//for (int i = 0; i < donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size(); i++) {
			//for (int i = 0; i < donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size(); i++) {
			long start2 = System.currentTimeMillis();
			//int size1 = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size();
			for (int i = 0; i < size; i++) {

				// super.paintComponent(g);
				// Graphics2D gr = (Graphics2D) g;
				// On recupere la longitude et la latitude de chaque centre du
				// sommet
				longitude = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getLongitude();
				latitude = donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude();
				x = ConversionLongitudeEnx(longitude);
				y = ConversionLatitudeEny(latitude);
				//System.out.println("latitude = " + latitude +" longitude = " + longitude + " x = " + x + " y = "+y);
				// On colorie les sommets en bleu
				if (donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getSelected() == false)
					g.setColor(java.awt.Color.BLUE);
				else
					g.setColor(Color.pink);
				// On rend visible les sommets
				g.fillOval(x - ParametresFenetre.rayon, y - ParametresFenetre.rayon, 2*ParametresFenetre.rayon, 2*ParametresFenetre.rayon);
				g.setColor(java.awt.Color.BLACK);
				// On affiche les noms de chaque sommet
				g.drawString(donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getNom(), x, y);

			}
			long duree2 = System.currentTimeMillis()-start2;
			System.out.println("affichage 2 des sommet en "+ duree2+ " ms");

		}
		// Pareil pour les aretes...
		if (donneesaafficher.getGrapheàafficher() != null) {

			for (int i = 0; i < donneesaafficher.getGrapheàafficher().getEnsembleAretes().size(); i++) {

				long1 = donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetOrigine()
						.getLongitude();
				long2 = donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetExtremité()
						.getLongitude();
				lat1 = donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetOrigine()
						.getLatitude();
				lat2 = donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetExtremité()
						.getLatitude();

				x1 = ConversionLongitudeEnx(long1);
				x2 = ConversionLongitudeEnx(long2);
				y1 = ConversionLatitudeEny(lat1);
				y2 = ConversionLatitudeEny(lat2);


				g.setColor(java.awt.Color.RED);
				g.drawLine(x1, y1, x2, y2);
			}			
		}

		/*
		 * sommet =donneesaafficher.getSommet(); if (this.sommet != null) { int
		 * x3, y3; if (donneesaafficher.getGrapheàafficher() != null) { for (int
		 * i = 0; i <
		 * donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size();
		 * i++) { if
		 * (donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i)
		 * == sommet) { System.out.println("racha"); y3 = (int)
		 * donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).
		 * getLongitude(); x3 = (int)
		 * donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).
		 * getLatitude(); g.setColor(java.awt.Color.GREEN); g.drawOval(x3 - 25,
		 * y3 - 25, 50, 50);
		 * 
		 * } } } }
		 */

		sommet = donneesaafficher.getSommet();
		if (this.sommet != null) {
			int x3, y3;
			double long3;
			double lat3;
			String nom;

			long3 = (int) sommet.getLongitude();
			lat3 = (int) sommet.getLatitude();
			nom = sommet.getNom();
			x3 = ConversionLongitudeEnx(long3);
			y3 = ConversionLatitudeEny(lat3);
			g.drawString(nom, x3, y3);
			g.setColor(java.awt.Color.GREEN);
			g.fillOval((int)(x3 - ParametresFenetre.rayon*0.5), (int)(y3 - ParametresFenetre.rayon*0.5),ParametresFenetre.rayon,ParametresFenetre.rayon);


		}




		// Pareil pour les aretesDuCheminLeplusCourt...
		if (donneesaafficher.getGrapheàafficher() != null) {
			if (donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra()!= null) {
				for (int i = 0; i < donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().size(); i++) {

					long1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().get(i).getSommetOrigine()
							.getLongitude();
					long2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().get(i).getSommetExtremité()
							.getLongitude();
					lat1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().get(i).getSommetOrigine()
							.getLatitude();
					lat2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtDjikstra().get(i).getSommetExtremité()
							.getLatitude();

					x1 = ConversionLongitudeEnx(long1);
					x2 = ConversionLongitudeEnx(long2);
					y1 = ConversionLatitudeEny(lat1);
					y2 = ConversionLatitudeEny(lat2);


					g.setColor(java.awt.Color.BLACK);
					g.drawLine(x1, y1, x2, y2);
				}		}

		}
		if (donneesaafficher.getGrapheàafficher() != null) {
			if (donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal()!= null) {
				for (int i = 0; i < donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().size(); i++) {

					long1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().get(i).getSommetOrigine()
							.getLongitude();
					long2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().get(i).getSommetExtremité()
							.getLongitude();
					lat1 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().get(i).getSommetOrigine()
							.getLatitude();
					lat2 = donneesaafficher.getListearetesCoresspondantauCheminLeplusCourtKruskal().get(i).getSommetExtremité()
							.getLatitude();

					x1 = ConversionLongitudeEnx(long1);
					x2 = ConversionLongitudeEnx(long2);
					y1 = ConversionLatitudeEny(lat1);
					y2 = ConversionLatitudeEny(lat2);


					g.setColor(java.awt.Color.YELLOW);
					g.drawLine(x1, y1, x2, y2);
				}		}

		}

	}

	public static int ConversionLongitudeEnx(double longitude)
	{
		int x;
		x =  ((int) (((longitude - donneesaafficher.getGrapheàafficher().getLongitudemin())*(ParametresFenetre.dimensionEcran.width*0.9-ParametresFenetre.dimensionEcran.width*0.1)/(donneesaafficher.getGrapheàafficher().getLongitudemax()-donneesaafficher.getGrapheàafficher().getLongitudemin()))+ParametresFenetre.dimensionEcran.width*0.1));
		return x;


	}


	public static int ConversionLatitudeEny(double latitude)
	{
		int y;
		y =  ((int) (((latitude - donneesaafficher.getGrapheàafficher().getLatitudemin())*(ParametresFenetre.dimensionEcran.height*0.9-ParametresFenetre.dimensionEcran.height*0.1)/(donneesaafficher.getGrapheàafficher().getLatitudemax()-donneesaafficher.getGrapheàafficher().getLatitudemin()))+ParametresFenetre.dimensionEcran.height*0.1));
		return y;


	}

	public static double ConversionxEnLongitude(int x)
	{

		double longitude;
		longitude =(donneesaafficher.getGrapheàafficher().getLongitudemax()-donneesaafficher.getGrapheàafficher().getLongitudemin())/(ParametresFenetre.dimensionEcran.width*0.9-ParametresFenetre.dimensionEcran.width*0.1)*(x-ParametresFenetre.dimensionEcran.width*0.1)+donneesaafficher.getGrapheàafficher().getLongitudemin();		
		return longitude;
	}



	public static double ConversionyEnLatitude(int y)
	{
		double latitude;
		latitude =(donneesaafficher.getGrapheàafficher().getLatitudemax()-donneesaafficher.getGrapheàafficher().getLatitudemin())/(ParametresFenetre.dimensionEcran.height*0.9-ParametresFenetre.dimensionEcran.height*0.1)*(y-ParametresFenetre.dimensionEcran.height*0.1)+donneesaafficher.getGrapheàafficher().getLatitudemin();		
		return latitude;



	}


}
