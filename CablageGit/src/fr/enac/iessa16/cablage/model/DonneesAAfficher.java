package fr.enac.iessa16.cablage.model;

import java.util.Observable;

import fr.enac.iessa16.cablage.controller.Controleur;

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


	public void nouveauClicSouris(int x, int y) {
		// TODO Auto-generated method stub
		//for(int i =0 ;grapheAafficher.getEnsembleDeSommet().size())
	
		
		
		//System.out.println("Clic souris x="+e.getX());
		
		for(int i=0 ; i<this.grapheAafficher.getEnsembleDeSommet().size();i++)
		{
			
			//System.out.println("Latitude sommet "+i+" : "+model.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude());
			
			//latitude = 
			
			
			if(Math.sqrt(Math.pow((grapheAafficher.getEnsembleDeSommet().get(i).getLatitude()-x),2)+Math.pow((this.getGrapheàafficher().getEnsembleDeSommet().get(i).getLongitude()-y),2))<25)
			  
			
			{
				
				//System.out.println("ca coincide avec le sommet "+model.getGrapheàafficher().getEnsembleDeSommet().get(i).getNom());
				
				
				this.sommet = grapheAafficher.getEnsembleDeSommet().get(i);
				
				System.out.println(sommet.getNom());
				//this.dessin1.paint(model.getGrapheàafficher());
				//this.selectionner = true ;
				if (grapheAafficher.getEnsembleDeSommet().get(i).getSelected() == true)
					grapheAafficher.getEnsembleDeSommet().get(i).setSelected(false); 
				else 
					grapheAafficher.getEnsembleDeSommet().get(i).setSelected(true);
			
			} else {
				System.out.println(" TEST NOK");
			}
		}
		
		
		
		
		
		
		//if (sommet != null) {
			this.changement();
		//}
		
		
		
		
		
	//	System.out.println("MODELE : CLIC x="+x1+" y="+y);
		
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

}
