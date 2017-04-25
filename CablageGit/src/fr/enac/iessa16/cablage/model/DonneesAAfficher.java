package fr.enac.iessa16.cablage.model;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import javax.swing.JOptionPane;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleWeightedGraph;

import fr.enac.iessa16.cablage.fichierTexte.LectureFichier;
import fr.enac.iessa16.cablage.model.algo.DijkstraJGrapht;
import fr.enac.iessa16.cablage.model.algo.KruskalJGrapht;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;
import fr.enac.iessa16.cablage.view.DessinDuGrapheParDefaut;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe DonneesAAfficher contenant les données utiles à afficher
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class DonneesAAfficher extends Observable {

	//Attributs de la classe DonneesAAfficher 
	private GrapheTheorique grapheAafficher;	

	private Sommet dernierSommetSelectionne;
	private ArrayList<Sommet> listeDeSommetsSelectionnés;

	private List<Arete> listearetesCoresspondantauCheminLeplusCourtDjikstra;
	private double coutCheminLeplusCourtDjikstra;
	private Sommet sommetOrigine;
	private Sommet sommetDestination;

	private ArrayList<Arete> listearetesCoresspondantauCheminLeplusCourtKruskal;
	
	private DijkstraJGrapht djikstra;
	private KruskalJGrapht kruskal;

	private List<Set<Sommet>> connectedSet;

	private Component parent;

	
	/**
	 * Constructeur de la classe DonneesAAfficher.java
	 */
	public DonneesAAfficher(){

		// Initialisation des attributs
		this.grapheAafficher = null;
		this.dernierSommetSelectionne = null;
		this.listeDeSommetsSelectionnés = new ArrayList<Sommet>();
		
		this.listearetesCoresspondantauCheminLeplusCourtDjikstra = new ArrayList<Arete>();
		this.coutCheminLeplusCourtDjikstra = 0;
		this.sommetOrigine = null;
		this.sommetDestination = null;

		this.listearetesCoresspondantauCheminLeplusCourtKruskal = new ArrayList<Arete>();
		
		this.djikstra = null;
		this.kruskal = null;

		this.connectedSet = null;
		
		this.parent = null;
	}


	/**
	 * Methode permettant d'afficher le graphe par defaut , apres avoir cliqué sur l'option adequate du menu
	 */
	public void chargerLeGrapheParDefaut() {

		//Construction de l'instance de classe "constructeur graphe defaut"
		ConstructionGrapheParDefaut constructeurgraphedefaut = new ConstructionGrapheParDefaut();
		grapheAafficher = constructeurgraphedefaut.getGraphe();

		testConnectivite();

		changement();
	}






	public void changement() {
		// TODO Auto-generated method stub
		this.setChanged();//Le modele change mais personne ne le sait
		this.notifyObservers(); //on informe les autres que le modele change

	}



	/**
	 * Méthode permettant de trouver le noeud le plus proche du clic souris
	 * @param xClic
	 * @param yClic
	 */
	public void nouveauClicSouris(int xClic, int yClic) {
	

		double distance, distanceMin = Double.MAX_VALUE;
		double latitudeSommet, longitudeSommet;
		double xSommet,ySommet;
		Sommet sommetLePlusProcheDuClic = null;

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
			if(distance<ParametresFenetre.rayonSommetClic)	{
				
				if (distance<distanceMin) {
					
					distanceMin = distance;
					sommetLePlusProcheDuClic = grapheAafficher.getEnsembleDeSommet().get(i);
					
				}
			}
		}
		
		if (sommetLePlusProcheDuClic != null) {
				
			// on  stocke le noeud cliqué dans l'attribut dernierSommetSelectionne
			this.dernierSommetSelectionne = sommetLePlusProcheDuClic;

			//System.out.println(sommet.getNom());
			//this.dessin1.paint(model.getGrapheàafficher());
			//this.selectionner = true ;
			
			//Si le sommmet était déjà selectionné
			if (dernierSommetSelectionne.getSelected() == true) {
				//on le deselectionne quand on clique dessus
				dernierSommetSelectionne.setSelected(false); 
				
				// on le supprime de la liste des noeuds sélectionnés
				this.listeDeSommetsSelectionnés.remove(dernierSommetSelectionne);
			}
			else { 
				//on le selectionne 
				dernierSommetSelectionne.setSelected(true);
				
				// on l'ajoute dans la liste des noeuds sélectionnés
				this.listeDeSommetsSelectionnés.add(dernierSommetSelectionne);
			}


			//On notifie la vue que le modèle a changé
			this.changement();
			

			
		}


		




		//	System.out.println("MODELE : CLIC x="+x1+" y="+y);

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

		LectureFichier constructeurgrapheText = new LectureFichier(parent);
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
		ArrayList<Sommet> sousSommets = new ArrayList<Sommet>();
		GrapheTheorique sousGraphe;
		ArrayList<Arete> chemin;
		
		int nbNoeudInutile = 0;
		
		// Le sous graphe pour Kruskal contient tous les noeuds sélectionnés
		sousSommets.addAll(listeDeSommetsSelectionnés);
		
		// Création de l'objet permettant le calcul de Dijkstra
		this.djikstra = new DijkstraJGrapht(grapheAafficher);
		
		//On calcule Djikstra entre un noeud de depart (indice i ) et toutes les combinaisons possibles (autre noeud commencant à l'indice i+1);
		for(int i = 0 ; i<listeDeSommetsSelectionnés.size(); i++)
		{
			for(int j = i+1 ; j<listeDeSommetsSelectionnés.size(); j++) 
			{

				chemin = this.djikstra.getDijkstraShortestPath(listeDeSommetsSelectionnés.get(i),listeDeSommetsSelectionnés.get(j));
				
				// on ajoute toutes les aretes du chemin le plus court aux sous Aretes
				sousAretes.addAll(chemin);
				
				// on parcourt toutes les aretes pour ajouter les sommets supplémentaires (non sélectionné) à la liste des sous sommets
				for (Arete arete : chemin) {
					
					if (!sousSommets.contains(arete.getSommetOrigine())) {
						nbNoeudInutile++;
						sousSommets.add(arete.getSommetOrigine());
					}
					if (!sousSommets.contains(arete.getSommetExtremité())) {
						nbNoeudInutile++;
						sousSommets.add(arete.getSommetExtremité());
					}					
				}
			}
		}
		
		if (nbNoeudInutile != 0) {
			
			message("Algorithme de Kruskal", nbNoeudInutile+" noeud(s) non sélectionné(s) ont du etre rajouté pour le calcul du chemin à cout minimum");
			
		}
		
		//On cree un sous graphe avec la liste des sommets selectionnés et les sous Aretes.
		sousGraphe = new GrapheTheorique(sousSommets, sousAretes);

		//On appelle Kruskal sur ce sous graphe
		this.kruskal = new KruskalJGrapht(sousGraphe);
		this.listearetesCoresspondantauCheminLeplusCourtKruskal = this.kruskal.getKruskalShortestPath();

		System.out.println("Le chemin le plus court contient " + listearetesCoresspondantauCheminLeplusCourtKruskal.size() +" aretes");
		changement();

	}

	//Getters et Setters 

	private void message(String titre, String message) {
		
		
		JOptionPane.showMessageDialog(parent, message, titre, JOptionPane.INFORMATION_MESSAGE);
		//default title and icon
		/*JOptionPane.showMessageDialog(frame,
		    "Eggs are not supposed to be green.");

		Informational dialog with custom title, warning icon 	

		//custom title, warning icon
		JOptionPane.showMessageDialog(frame,
		    "Eggs are not supposed to be green.",
		    "Inane warning",
		    JOptionPane.WARNING_MESSAGE);

		Informational dialog with custom title, error icon 	

		//custom title, error icon
		JOptionPane.showMessageDialog(frame,
		    "Eggs are not supposed to be green.",
		    "Inane error",
		    JOptionPane.ERROR_MESSAGE);*/

		
	}


	public List<Arete> getListearetesCoresspondantauCheminLeplusCourtDjikstra() {
		return listearetesCoresspondantauCheminLeplusCourtDjikstra;
	}


	public void setListearetesCoresspondantauCheminLeplusCourt(List<Arete> listearetesCoresspondantauCheminLeplusCourt) {
		this.listearetesCoresspondantauCheminLeplusCourtDjikstra = listearetesCoresspondantauCheminLeplusCourt;
	}


	public List<Arete> getListearetesCoresspondantauCheminLeplusCourtKruskal() {
		return listearetesCoresspondantauCheminLeplusCourtKruskal;
	}




	/**Getter Sommet
	 * @return sommet
	 */
	public Sommet getdernierSommetSelectionne() {
		return dernierSommetSelectionne;
	}





	public double getCoutCheminLeplusCourtDjikstra() {
		// TODO Auto-generated method stub
		return coutCheminLeplusCourtDjikstra;
	}
	public Sommet getSommetOrigine() {
		return sommetOrigine;
	}


	public List<Set<Sommet>> getConnectedSet() {
		return connectedSet;
	}


	public Sommet getSommetDestination() {
		return sommetDestination;
	}


	/**
	 * @return le graphe à afficher
	 */
	public GrapheTheorique getGrapheàafficher() {
		return grapheAafficher;
	}
	
	public void setParent(Component parent) {
		this.parent = parent;
	}


	public void nouveauGraphe() {
		// TODO Auto-generated method stub
		
		message("nouveau", "à faire");
		
	}

	public void OuvertureGrapheAleatoire() {
		// TODO Auto-generated method stub
		
		message("graphe aleatoire", "à faire");
		
	}

	public void OuvertureGrapheVide() {
		// TODO Auto-generated method stub
		
		message("Graphe Vide", "à faire");
		
	}
	public void EnregisterSous() {
		// TODO Auto-generated method stub
		
		message("Enregistrer sous", "à faire");
		
	}
	
	public void Imprimer() {
		// TODO Auto-generated method stub
		
		message("Imprimer", "à faire");
		
	}


	public void Fermer() {
		// TODO Auto-generated method stub
		message("Fermer", "à faire");
	}


	public void Ouvrir() {
		// TODO Auto-generated method stub
		message("Ouvrir", "à faire");
	}
	
	



}




