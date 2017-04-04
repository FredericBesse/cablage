package fr.enac.iessa16.cablage.model;

import java.util.Observable;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.fichierTexte.Main;
import fr.enac.iessa16.cablage.view.DessinDuGrapheParDefaut;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe Donnees contenant les données utiles à afficher
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */

public class DonneesAAfficher extends Observable
{

	//attribut de la classe DonneesAAfficher : le grapheaaficher;
   private 	GrapheTheorique grapheAafficher;	
	
   private Controleur controleur;

private Sommet sommet;
   
private boolean selectionner = false; 
private final double R = 6371;

   
	/**
	 * Constructeur de la classe DonneesAAfficher.java
	 */
	public DonneesAAfficher(){
		
		this.grapheAafficher = null;
		
	
	}
	
	
	/**
	 * Methode permettant d'afficher le graphe par defaut , apres avoir cliqué sur l'option adequate du menu
	 */
	public void ChargerLeGraphe()   
	
	{
		
	 //Construire le graphe
		
		ConstructionGrapheParDefaut constructeurgraphedefaut = new ConstructionGrapheParDefaut();
	    grapheAafficher = constructeurgraphedefaut.getGraphe();
		
	   // fenetre.repaint
	    this.setChanged();//Le modele change mais personne ne le sait
	    this.notifyObservers(); //on informe les autres que le modele change
	}
	
	

	
   
	/**
	 * @return le graphe à afficher
	 */
	public GrapheTheorique getGrapheàafficher() {
	return grapheAafficher;
     }


	public void changement() {
		// TODO Auto-generated method stub
		this.setChanged();//Le modele change mais personne ne le sait
	    this.notifyObservers(); //on informe les autres que le modele change

	}


	public void nouveauClicSouris(int xClic, int yClic) {
		// TODO Auto-generated method stub
		//for(int i =0 ;grapheAafficher.getEnsembleDeSommet().size())
		double distance;
		double latitudeSommet, longitudeSommet;
		double xSommet,ySommet;
		
		//System.out.println("Clic souris lon="+longitude+" lat="+latitude);
		
		for(int i=0 ; i<this.grapheAafficher.getEnsembleDeSommet().size();i++)
		{
			
			//System.out.println("Latitude sommet "+i+" : "+model.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude());
			
			longitudeSommet = grapheAafficher.getEnsembleDeSommet().get(i).getLongitude();
			latitudeSommet = grapheAafficher.getEnsembleDeSommet().get(i).getLatitude();
			
			xSommet = DessinDuGrapheParDefaut.ConversionLongitudeEnx(longitudeSommet);
			ySommet = DessinDuGrapheParDefaut.ConversionLatitudeEny(latitudeSommet);
			
			distance = Math.sqrt(Math.pow(xClic-xSommet,2)+Math.pow(yClic-ySommet,2));
			//distance = calculerDistance(longitude, latitude, grapheAafficher.getEnsembleDeSommet().get(i).getLongitude(), this.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude());
			
			System.out.println("distance = "+distance);
			if(distance<ParametresFenetre.rayon)
			  
			
			{
				
				System.out.println("ca coincide avec le sommet "+grapheAafficher.getEnsembleDeSommet().get(i).getNom());
				
				
				this.sommet = grapheAafficher.getEnsembleDeSommet().get(i);
				
				System.out.println(sommet.getNom());
				//this.dessin1.paint(model.getGrapheàafficher());
				//this.selectionner = true ;
				if (grapheAafficher.getEnsembleDeSommet().get(i).getSelected() == true)
					grapheAafficher.getEnsembleDeSommet().get(i).setSelected(false); 
				else 
					grapheAafficher.getEnsembleDeSommet().get(i).setSelected(true);
			
			} else {
				System.out.println(" TEST NOK avec le sommet "+grapheAafficher.getEnsembleDeSommet().get(i).getNom());
			}
		}
		
		
		
		
		
		
		//if (sommet != null) {
			this.changement();
		//}
		
		
		
		
		
	//	System.out.println("MODELE : CLIC x="+x1+" y="+y);
		
	}
	
	
	
	
	
	private double calculerDistance(double long1,double lat1 ,double long2,double lat2)
	
	{
		double distance;
		distance = 2*R*Math.asin(Math.sqrt(Math.pow(Math.sin(Math.toRadians((lat1-lat2)/2)), 2)+Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin(Math.toRadians((long2-long1)/2)), 2)));
		return distance;
	}
	
	
	
	
	/**
	 * Getters et Setters
	 */
	public Sommet getSommet() {
		return sommet;
	}


		






	public void setSommet(Sommet sommet) {
		this.sommet = sommet;
	}


	public void ChargerLeGrapheDuFicherTexte() {
		// TODO Auto-generated method stub
 //Construire le graphe
		
		Main constructeurgrapheText = new Main();
	    grapheAafficher = constructeurgrapheText.getGraphe();
		
	   // fenetre.repaint
	    this.setChanged();//Le modele change mais personne ne le sait
	    this.notifyObservers(); //on informe les autres que le modele change
	}




}
