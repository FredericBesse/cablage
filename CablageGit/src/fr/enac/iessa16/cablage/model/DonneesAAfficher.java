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
		System.out.println("MODELE : CLIC x="+x+" y="+y);
		
	}


}
