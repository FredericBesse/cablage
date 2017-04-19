package fr.enac.iessa16.cablage.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.logging.Logger;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.graph.SimpleWeightedGraph;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.fichierTexte.LectureFichier;
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

	public Sommet getSommetOrigine() {
		return sommetOrigine;
	}


	public List<Set<Sommet>> getConnectedSet() {
		return connectedSet;
	}


	public Sommet getSommetDestination() {
		return sommetDestination;
	}


	private 	GrapheTheorique grapheAafficher;	
	private Controleur controleur;
	private Sommet dernierSommetSelectionne;
	private boolean selectionner = false; 
	private final double R = 6371;
	private DijkstraJGrapht djikstra;
	private ArrayList<Sommet> listeDeSommetsSelectionnés;
	private List<Arete> listearetesCoresspondantauCheminLeplusCourtDjikstra;
	private KruskalJGrapht kruskal;
	private ArrayList<Arete> listearetesCoresspondantauCheminLeplusCourtKruskal;
	private GrapheTheorique sousGraphe;
private Plus AStar;
private ArrayList<Arete> listearetesCoresspondantauCheminLeplusCourtAStar;
private AStarAdmissibleHeuristic<Sommet> lol;
private MaClasse param;
public  ArrayList<Arete> getListearetesCoresspondantauCheminLeplusCourtAStar;
private double coutCheminLeplusCourtDjikstra;
private Sommet sommetOrigine = null;
private Sommet sommetDestination = null;
private List<Set<Sommet>> connectedSet;
	/**
	 * Constructeur de la classe DonneesAAfficher.java
	 * On initialise les graphes et sommets à null
	 */
	public DonneesAAfficher(){

		this.grapheAafficher = null;
		this.sousGraphe = null;
		this.listeDeSommetsSelectionnés = new ArrayList<Sommet>();
		this.param = new MaClasse();

	}


	/**
	 * Methode permettant d'afficher le graphe par defaut , apres avoir cliqué sur l'option adequate du menu
	 */
	public void chargerLeGrapheParDefaut()   

	{

		//Construction de l'instance de classe "constructeur graphe defaut"

		ConstructionGrapheParDefaut constructeurgraphedefaut = new ConstructionGrapheParDefaut();
		grapheAafficher = constructeurgraphedefaut.getGraphe();
		
		
		testConnectivite();

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
			if(distance<ParametresFenetre.rayonSommetClic)


			{

				//System.out.println("ça coincide avec le sommet "+grapheAafficher.getEnsembleDeSommet().get(i).getNom());

				// on  stocke le noeud cliqué dans l'attribut sommet

				this.dernierSommetSelectionne = grapheAafficher.getEnsembleDeSommet().get(i);

				//System.out.println(sommet.getNom());
				//this.dessin1.paint(model.getGrapheàafficher());
				//this.selectionner = true ;
				//Si le sommmet était déjà selectionné , on le deselectionne quand on clique dessus
				if (grapheAafficher.getEnsembleDeSommet().get(i).getSelected() == true)
					grapheAafficher.getEnsembleDeSommet().get(i).setSelected(false); 
				else 
					grapheAafficher.getEnsembleDeSommet().get(i).setSelected(true);
				
				
				
				break;

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
	public Sommet getdernierSommetSelectionne() {
		return dernierSommetSelectionne;
	}









	/*public void setdernierSommetSelectionne(Sommet sommet) {
		this.dernierSommetSelectionne = sommet;

	}/*


	/**Implementation de la methode qui permet de charger le graphe du fichier texte.
	 * On lit le fichier puis on recupère le graphe obtenu suite à la lecture du fichier  
	 * 
	 */
	public void ChargerLeGrapheDuFicherTexte() {
		// TODO Auto-generated method stub
		//Construire le graphe

		LectureFichier constructeurgrapheText = new LectureFichier();
		grapheAafficher = constructeurgrapheText.getGraphe();

		testConnectivite();
		
		// fenetre.repaint
		changement();
	}


	private void testConnectivite() {
		// TODO Auto-generated method stub
		
		SimpleWeightedGraph graphPourJGrapht = new SimpleWeightedGraph<Sommet, Arete>(Arete.class);

	        // Ajout de tous les sommets au graphe JGrapht
	        for (Sommet sommet: grapheAafficher.getEnsembleDeSommet()) {
	            graphPourJGrapht.addVertex(sommet);
	        }
	      /*  for (int i = 0; i< graphe.getEnsembleDeSommet().size();i++) {
	        	Sommet sommet = graphe.getEnsembleDeSommet().get(i);
	            graphPourJGrapht.addVertex(sommet);
	        }*/
	        
	        // Ajout de toutes les aretes au graphe JGrapht
	        for (Arete arete : grapheAafficher.getEnsembleAretes()) {
	            graphPourJGrapht.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
	            // FIXME graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
	        }
		
		ConnectivityInspector<Sommet, Arete> connectivityInspector = new ConnectivityInspector<>(graphPourJGrapht);
		
		connectedSet = connectivityInspector.connectedSets();
		
		System.out.println(connectedSet.size()+" composantes connexes trouvées");
		
		
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
			this.sommetOrigine = listeDeSommetsSelectionnés.get(0);
			this.sommetDestination = listeDeSommetsSelectionnés.get(1);
			
			this.listearetesCoresspondantauCheminLeplusCourtDjikstra = this.djikstra.getDijkstraShortestPath(sommetOrigine,sommetDestination);

			this.coutCheminLeplusCourtDjikstra = 0;
			for (Arete arete : listearetesCoresspondantauCheminLeplusCourtDjikstra) {
				coutCheminLeplusCourtDjikstra += arete.getCout();
			}
			
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


	public void calculerAStar() {
		// TODO Auto-generated method stub
		

		this.AStar = new Plus(grapheAafficher);
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


			} }


		System.out.println("il y a "+ listeDeSommetsSelectionnés.size()+ " noeuds selectionnés");
		if(listeDeSommetsSelectionnés.size()==2)//Si on a selectionné deux noeuds 
		{
			//On appelle Djikstra pour trouver le chemin le plus court pour aller du noued de depart au noeud d'arrivé.
			this.listearetesCoresspondantauCheminLeplusCourtAStar = this.AStar.getShortestPath(listeDeSommetsSelectionnés.get(0), listeDeSommetsSelectionnés.get(1),param);

			//System.out.println("Le chemin le plus court contient " + listearetesCoresspondantauCheminLeplusCourtDjikstra.size() +" aretes");
			//On notifie la vue que le modèle a changé:
			changement();
		
		
		
		
		
	}







}


	public ArrayList<Arete> getListearetesCoresspondantauCheminLeplusCourtAStar() {
		return listearetesCoresspondantauCheminLeplusCourtAStar;
	}


	public void setListearetesCoresspondantauCheminLeplusCourtAStar(
			ArrayList<Arete> listearetesCoresspondantauCheminLeplusCourtAStar) {
		this.listearetesCoresspondantauCheminLeplusCourtAStar = listearetesCoresspondantauCheminLeplusCourtAStar;
	}


	public double getCoutCheminLeplusCourtDjikstra() {
		// TODO Auto-generated method stub
		return coutCheminLeplusCourtDjikstra;
	}
}
