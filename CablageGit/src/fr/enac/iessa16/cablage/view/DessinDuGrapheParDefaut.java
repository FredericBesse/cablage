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
		// int x3, y3;
		// Si le graphe n'est pas vide
		if (donneesaafficher.getGrapheàafficher() != null) {
			// On parcourt l'ensemble des sommets
			for (int i = 0; i < donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().size(); i++) {

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
				g.fillOval(x - 25, y - 25, 50, 50);
				g.setColor(java.awt.Color.BLACK);
				// On affiche les noms de chaque sommet
				g.drawString(donneesaafficher.getGrapheàafficher().getEnsembleDeSommet().get(i).getNom(), x, y);

			}
		}
		// Pareil pour les aretes...
		/*if (donneesaafficher.getGrapheàafficher() != null) {

			for (int i = 0; i < donneesaafficher.getGrapheàafficher().getEnsembleAretes().size(); i++) {

				y1 = (int) donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetOrigine()
						.getLongitude();
				y2 = (int) donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetExtremité()
						.getLongitude();
				x1 = (int) donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetOrigine()
						.getLatitude();
				x2 = (int) donneesaafficher.getGrapheàafficher().getEnsembleAretes().get(i).getSommetExtremité()
						.getLatitude();
				g.setColor(java.awt.Color.RED);
				g.drawLine(x1, y1, x2, y2);
			}

		}*/

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
			
			long3 = (int) sommet.getLongitude();
			lat3 = (int) sommet.getLatitude();
			x3 = ConversionLongitudeEnx(long3);
			y3 = ConversionLatitudeEny(lat3);
			
			
			g.setColor(java.awt.Color.GREEN);
			g.fillOval(x3 - 20, y3 - 20, 40, 40);

		}

	}
  
	public static int ConversionLongitudeEnx(double longitude)
	{
		int x;
		x =  ((int) (((longitude - donneesaafficher.getGrapheàafficher().getLongitudemin())*(1100*0.9-1100*0.1)/(donneesaafficher.getGrapheàafficher().getLongitudemax()-donneesaafficher.getGrapheàafficher().getLongitudemin()))+1100*0.1));
		return x;
		
		
	}
	
	
	public static int ConversionLatitudeEny(double latitude)
	{
		int y;
		y =  ((int) (((latitude - donneesaafficher.getGrapheàafficher().getLatitudemin())*(600*0.9-600*0.1)/(donneesaafficher.getGrapheàafficher().getLatitudemax()-donneesaafficher.getGrapheàafficher().getLatitudemin()))+600*0.1));
		return y;
		
		
	}
	
	public static double ConversionxEnLongitude(int x)
	{
		
		double longitude;
		longitude =(donneesaafficher.getGrapheàafficher().getLongitudemax()-donneesaafficher.getGrapheàafficher().getLongitudemin())/(1100*0.9-1100*0.1)*(x-1100*0.1)+donneesaafficher.getGrapheàafficher().getLongitudemin();		
		return longitude;
	}
	
	
	
	public static double ConversionyEnLatitude(int y)
	{
		double latitude;
		latitude =(donneesaafficher.getGrapheàafficher().getLatitudemax()-donneesaafficher.getGrapheàafficher().getLatitudemin())/(600*0.9-600*0.1)*(y-600*0.1)+donneesaafficher.getGrapheàafficher().getLatitudemin();		
		return latitude;
		
		
		
	}
	
	
}
