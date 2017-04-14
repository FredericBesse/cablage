package fr.enac.iessa16.cablage.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.logging.Logger;

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

	//Attributs de la classe DonneesAAfficher 

	private 	GrapheTheorique grapheAafficher;	
	private Controleur controleur;
	private Sommet sommet;
	private boolean selectionner = false; 
	private final double R = 6371;
	private DijkstraJGrapht djikstra;
	private ArrayList<Sommet> listeDeSommetsSelectionnés;
	private List<Arete> listearetesCoresspondantauCheminLeplusCourtDjikstra;
	private KruskalJGrapht kruskal;
	private ArrayList<Arete> listearetesCoresspondantauCheminLeplusCourtKruskal;
	private GrapheTheorique sousGraphe;


	/**
	 * Constructeur de la classe DonneesAAfficher.java
	 * On initialise les graphes et sommets à null
	 */
	public DonneesAAfficher(){

		this.grapheAafficher = null;
		this.sousGraphe = null;
		this.listeDeSommetsSelectionnés = new ArrayList<Sommet>();

	}


	/**
	 * Methode permettant d'afficher le graphe par defaut , apres avoir cliqué sur l'option adequate du menu
	 */
	public void ChargerLeGraphe()   

	{

		//Construction de l'instance de classe "constructeur graphe defaut"

		ConstructionGrapheParDefaut constructeurgraphedefaut = new ConstructionGrapheParDefaut();
		grapheAafficher = constructeurgraphedefaut.getGraphe();

		// fenetre.repaint
		changement();
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



	/**Méthode qui permet de changer la couleur d'un sommet sélectionné
	 * @param xClic
	 * @param yClic
	 */
	public void nouveauClicSouris(int xClic, int yClic) {
		// TODO Auto-generated method stub
		//for(int i =0 ;grapheAafficher.getEnsembleDeSommet().size())

		double distance;
		double latitudeSommet, longitudeSommet;
		double xSommet,ySommet;

		//System.out.println("Clic souris lon="+longitude+" lat="+latitude);
		//On parcourt l'ensemble des sommets du graphe théorique qui apparait sur la fenetre
		for(int i=0 ; i<this.grapheAafficher.getEnsembleDeSommet().size();i++)
		{

			//System.out.println("Latitude sommet "+i+" : "+model.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude());

			//On recupère la longitude et latitude du noeud selectionné à l'instant t.
			longitudeSommet = grapheAafficher.getEnsembleDeSommet().get(i).getLongitude();
			latitudeSommet = grapheAafficher.getEnsembleDeSommet().get(i).getLatitude();

			//On les converti en coordonnées cartesiennes
			xSommet = DessinDuGrapheParDefaut.ConversionLongitudeEnx(longitudeSommet);
			ySommet = DessinDuGrapheParDefaut.ConversionLatitudeEny(latitudeSommet);

			//On recupère la distance entre la position du clique et la position du sommet
			distance = Math.sqrt(Math.pow(xClic-xSommet,2)+Math.pow(yClic-ySommet,2));
			//distance = calculerDistance(longitude, latitude, grapheAafficher.getEnsembleDeSommet().get(i).getLongitude(), this.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude());

			//System.out.println("distance = "+distance);
			//Le sommet est consideré comme "sélectionné" si la distance entre le clique et 
			//la position du sommet du graphe est inférieur au rayon de chaque sommet 
			if(distance<ParametresFenetre.rayon)


			{

				//System.out.println("ça coincide avec le sommet "+grapheAafficher.getEnsembleDeSommet().get(i).getNom());

				// on  stocke le noeud cliqué dans l'attribut sommet

				this.sommet = grapheAafficher.getEnsembleDeSommet().get(i);

				//System.out.println(sommet.getNom());
				//this.dessin1.paint(model.getGrapheàafficher());
				//this.selectionner = true ;
				//Si le sommmet était déjà selectionné , on le deselectionne quand on clique dessus
				if (grapheAafficher.getEnsembleDeSommet().get(i).getSelected() == true)
					grapheAafficher.getEnsembleDeSommet().get(i).setSelected(false); 
				else 
					grapheAafficher.getEnsembleDeSommet().get(i).setSelected(true);

			} else {
				///System.out.println(" TEST NOK avec le sommet "+grapheAafficher.getEnsembleDeSommet().get(i).getNom());
			}
		}


		//On notifie la vue que le modèle a changé

		//if (sommet != null) {
		this.changement();
		//}





		//	System.out.println("MODELE : CLIC x="+x1+" y="+y);

	}











	/**Getter Sommet
	 * @return sommet
	 */
	public Sommet getSommet() {
		return sommet;
	}









	public void setSommet(Sommet sommet) {
		this.sommet = sommet;

	}


	/**Implementation de la methode qui permet de charger le graphe du fichier texte.
	 * On lit le fichier puis on recupère le graphe obtenu suite à la lecture du fichier  
	 * 
	 */
	public void ChargerLeGrapheDuFicherTexte() {
		// TODO Auto-generated method stub
		//Construire le graphe

		Main constructeurgrapheText = new Main();
		grapheAafficher = constructeurgrapheText.getGraphe();

		// fenetre.repaint
		changement();
	}


	/**
	 * 
	 * 
	 * 
	 */

	public void calculerDjikstra() {
		// TODO Auto-generated method stub

		this.djikstra = new DijkstraJGrapht(grapheAafficher);
		listeDeSommetsSelectionnés.clear();
		int size = grapheAafficher.getEnsembleDeSommet().size();
		Sommet sommet;

		for(int i = 0 ; i<size; i++)
		{	
			sommet = grapheAafficher.getEnsembleDeSommet().get(i);
			//Si le sommet est selectionné
			if(sommet.getSelected() == true)
			{

				//On rajoute le sommet à la liste de sommets sélectionnés.
				listeDeSommetsSelectionnés.add(sommet);


			}

		}


		System.out.println("il y a "+ listeDeSommetsSelectionnés.size()+ " noeuds selectionnés");
		if(listeDeSommetsSelectionnés.size()==2)//Si on a selectionné deux noeuds 
		{
			//On appelle Djikstra pour trouver le chemin le plus court pour aller du noued de depart au noeud d'arrivé.
			this.listearetesCoresspondantauCheminLeplusCourtDjikstra = this.djikstra.getDijkstraShortestPath(listeDeSommetsSelectionnés.get(0),listeDeSommetsSelectionnés.get(1));

			System.out.println("Le chemin le plus court contient " + listearetesCoresspondantauCheminLeplusCourtDjikstra.size() +" aretes");
			//On notifie la vue que le modèle a changé:
			changement();

		}
		/*if(listeDeSommetsSelectionnés.size()==3)
		{

			this.listearetesCoresspondantauCheminLeplusCourt = this.djikstra.getDijkstraShortestPath(listeDeSommetsSelectionnés.get(0),listeDeSommetsSelectionnés.get(1));



		}*/
	}


	/**
	 * Methode permettant de calculer Kruskal
	 */
	public void calculerKruskal() {
		// TODO Auto-generated method stub
		ArrayList<Arete> sousAretes = new ArrayList<Arete>();
		this.djikstra = new DijkstraJGrapht(grapheAafficher);
		listeDeSommetsSelectionnés.clear();
		Sommet sommet;
		//On recupere la liste de sommets selectionnés
		int size = grapheAafficher.getEnsembleDeSommet().size(); //Optimisation du code ,utilisation de variable locale...
		for(int i = 0 ;i<size ;i++)
		{
			if(grapheAafficher.getEnsembleDeSommet().get(i).getSelected() == true)
			{
				sommet = grapheAafficher.getEnsembleDeSommet().get(i);

				listeDeSommetsSelectionnés.add(sommet);


			}



		}

		System.out.println("il y a "+ listeDeSommetsSelectionnés.size()+ " noeuds selectionnés");
		//Logger.getLogger()


		for(int i = 0 ; i<listeDeSommetsSelectionnés.size(); i++)
		{
			for(int j = i+1 ; j<listeDeSommetsSelectionnés.size(); j++) 
			{

				//On calcule Djikstra entre un noeud de depart (indice i ) et toutes les combinaisons possibles (autre noeud commencant à l'indice i+1);
				sousAretes.addAll(this.djikstra.getDijkstraShortestPath(listeDeSommetsSelectionnés.get(i),listeDeSommetsSelectionnés.get(j)));

			}




		}
		//On cree un sous graphe avec la liste des sommets selectionnés et les sous Aretes.
		this.sousGraphe = new GrapheTheorique(listeDeSommetsSelectionnés, sousAretes);

		//On appelle Kruskal sur ce sous graphe
		this.kruskal = new KruskalJGrapht(sousGraphe);
		this.listearetesCoresspondantauCheminLeplusCourtKruskal = this.kruskal.getKruskalShortestPath();

		System.out.println("Le chemin le plus court contient " + listearetesCoresspondantauCheminLeplusCourtKruskal.size() +" aretes");
		changement();

	}

	//Getters et Setters 

	public List<Arete> getListearetesCoresspondantauCheminLeplusCourtDjikstra() {
		return listearetesCoresspondantauCheminLeplusCourtDjikstra;
	}


	public void setListearetesCoresspondantauCheminLeplusCourt(List<Arete> listearetesCoresspondantauCheminLeplusCourt) {
		this.listearetesCoresspondantauCheminLeplusCourtDjikstra = listearetesCoresspondantauCheminLeplusCourt;
	}


	public List<Arete> getListearetesCoresspondantauCheminLeplusCourtKruskal() {
		return listearetesCoresspondantauCheminLeplusCourtKruskal;
	}







}
