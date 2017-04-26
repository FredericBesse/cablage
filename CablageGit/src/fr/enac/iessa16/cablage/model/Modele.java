package fr.enac.iessa16.cablage.model;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleWeightedGraph;

import fr.enac.iessa16.cablage.model.algo.AlgoDijkstraJGrapht;
import fr.enac.iessa16.cablage.model.algo.AlgoKruskalJGrapht;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;
import fr.enac.iessa16.cablage.model.file.ConstructeurGrapheFichierTexte;
import fr.enac.iessa16.cablage.view.DessinDuGrapheParDefaut;
import fr.enac.iessa16.cablage.view.Fenetre;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe Modele contenant les données utiles à afficher
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Modele extends Observable {

	/*
	 * Attributs de la classe Modele 
	 */
	// Le logger
	private static final Logger LOGGER = Logger.getLogger("Cablage");
		
	// Le graphe à afficher
	private GrapheTheorique graphe;	

	// Les sommets selectionnes
	private Sommet dernierSommetSelectionne;
	private ArrayList<Sommet> listeSommetsSelectionnes;

	// Dijkstra
	private AlgoDijkstraJGrapht algoDijkstra;
	private Sommet sommetOrigineDijkstra;
	private Sommet sommetDestinationDijkstra;
	private ArrayList<Arete> listeAretesDijkstra;
	private double coutCheminDijkstra;

	// Kruskal
	private AlgoKruskalJGrapht algoKruskal;
	private ArrayList<Arete> listeAretesKruskal;
	
	// Connectivité
	private ArrayList<Set<Sommet>> listeSousGraphesConnexes;

	// La vue (pour affichage des boites de dialogue)
	private JFrame parent;

	
	/**
	 * Constructeur de la classe DonneesAAfficher.java
	 */
	public Modele(){
		
		LOGGER.info("Création du modèle");
		
		// Initialisation des attributs
		this.graphe = null;
		
		this.dernierSommetSelectionne = null;
		this.listeSommetsSelectionnes = new ArrayList<Sommet>();
		
		this.algoDijkstra = null;
		this.sommetOrigineDijkstra = null;
		this.sommetDestinationDijkstra = null;
		this.listeAretesDijkstra = new ArrayList<Arete>();
		this.coutCheminDijkstra = 0;
		
		this.algoKruskal = null;
		this.listeAretesKruskal = new ArrayList<Arete>();
		
		this.listeSousGraphesConnexes = null;
				
		this.parent = null;
	}


	/**
	 * Methode permettant de charger le graphe par defaut, apres avoir cliqué sur l'option adequate du menu
	 */
	public void chargerGrapheParDefaut() {
		
		LOGGER.info("Chargement du graphe par défaut");

		// Création du constructeur de graphe par defaut
		ConstructeurGrapheParDefaut constructeurGrapheParDefaut = new ConstructeurGrapheParDefaut();
		
		// Récupération du graphe
		this.setGraphe(constructeurGrapheParDefaut.getGraphe());
	}


	/**
	 * Méthode permettant de charger le graphe depuis un fichier texte.	 * 
	 */
	public void chargerGrapheFichierTexte() {
		
		LOGGER.info("Chargement du graphe depuis un fichier texte");
		
		// Création du lecteur de graphe depuis un fichier texte		
		ConstructeurGrapheFichierTexte constructeurGrapheFichierTexte = new ConstructeurGrapheFichierTexte(parent);
		
		// Récupération du graphe
		setGraphe(constructeurGrapheFichierTexte.getGraphe());
	}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	


	/**
	 * Méthode permettant de trouver le noeud le plus proche du clic souris
	 * 
	 * @param xClic
	 * @param yClic
	 */
	public void touverSommetLePlusProcheDuClicSouris (int xClic, int yClic) {
	
		LOGGER.info("Recherche sommet le plus proche du clic souris x="+xClic+" y="+yClic);
		
		// Déclaration des variables locales
		double distance;
		double distanceMin = Double.MAX_VALUE;
		double latitude;
		double longitude;
		double x;
		double y;
		Sommet sommetLePlusProcheDuClic = null;
		Sommet sommet;
		int nombreSommet;
		ArrayList<Sommet> listeSommets;
		
		// si le graphe existe
		if (this.graphe != null) {
			
			// on récupère la liste de sommets
			listeSommets = graphe.getEnsembleDeSommet();
			
			// si la liste existe (non null)
			if (listeSommets != null) {
				
				// on récupère le nombre de sommets
				nombreSommet = this.graphe.getEnsembleDeSommet().size();
								
				// on parcourt l'ensemble des sommets du graphe 
				for(int i=0 ; i<nombreSommet;i++) {
					
					// on récupère le sommet i
					sommet = graphe.getEnsembleDeSommet().get(i);
					
					// on recupère la longitude et latitude du sommet i.
					longitude = sommet.getLongitude();
					latitude  = sommet.getLatitude();

					// on les convertit en coordonnées écran (pixel)
					x = DessinDuGrapheParDefaut.conversionLongitudeEnX(longitude);
					y = DessinDuGrapheParDefaut.conversionLatitudeEnY(latitude);

					// on recupère la distance entre la position du clic et la position du sommet
					distance = Math.sqrt(Math.pow(xClic-x,2)+Math.pow(yClic-y,2));
					
					LOGGER.finest("distance = "+distance);
					//Le sommet est consideré comme "sélectionné" si la distance entre le clique et 
					//la position du sommet du graphe est inférieur au rayon de chaque sommet 
					if(distance<ParametresFenetre.rayonSommetClic)	{
						
						if (distance<distanceMin) {
							
							distanceMin = distance;
							sommetLePlusProcheDuClic = graphe.getEnsembleDeSommet().get(i);
							
						}
					}
				}
				
				if (sommetLePlusProcheDuClic != null) 
				{
						
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
						this.listeSommetsSelectionnes.remove(dernierSommetSelectionne);
					}
					else { 
						//on le selectionne 
						dernierSommetSelectionne.setSelected(true);
						
						// on l'ajoute dans la liste des noeuds sélectionnés
						this.listeSommetsSelectionnes.add(dernierSommetSelectionne);
					}


					//On notifie la vue que le modèle a changé
					this.changement();
					

					
				}
				
				
			}
			
		}
		
		
		


		




		//	System.out.println("MODELE : CLIC x="+x1+" y="+y);

	}



















	/**
	 * Méthode permettant de tester la connectivité du graphe
	 * et d'en extraire le(s) sous-graphe(s) connexe(s)
	 */
	private void testConnectivite() {

		SimpleWeightedGraph graphPourJGrapht = new SimpleWeightedGraph<Sommet, Arete>(Arete.class);

		// Ajout de tous les sommets au graphe JGrapht
		for (Sommet sommet: graphe.getEnsembleDeSommet()) {
			graphPourJGrapht.addVertex(sommet);
		}
		/*  for (int i = 0; i< graphe.getEnsembleDeSommet().size();i++) {
	        	Sommet sommet = graphe.getEnsembleDeSommet().get(i);
	            graphPourJGrapht.addVertex(sommet);
	        }*/

		// Ajout de toutes les aretes au graphe JGrapht
		for (Arete arete : graphe.getEnsembleAretes()) {
			graphPourJGrapht.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
			// FIXME graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
		}

		ConnectivityInspector<Sommet, Arete> connectivityInspector = new ConnectivityInspector<>(graphPourJGrapht);

		listeSousGraphesConnexes = (ArrayList<Set<Sommet>>) connectivityInspector.connectedSets();

		System.out.println(listeSousGraphesConnexes.size()+" composantes connexes trouvées");


	}


	/**
	 * 
	 * 
	 * 
	 */

	public void calculerDjikstra() {
		// TODO Auto-generated method stub

		this.algoDijkstra = new AlgoDijkstraJGrapht(graphe);
		listeSommetsSelectionnes.clear();
		int size = graphe.getEnsembleDeSommet().size();
		Sommet sommet;

		for(int i = 0 ; i<size; i++)
		{	
			sommet = graphe.getEnsembleDeSommet().get(i);
			//Si le sommet est selectionné
			if(sommet.getSelected() == true)
			{

				//On rajoute le sommet à la liste de sommets sélectionnés.
				listeSommetsSelectionnes.add(sommet);


			}

		}


		System.out.println("il y a "+ listeSommetsSelectionnes.size()+ " noeuds selectionnés");
		if(listeSommetsSelectionnes.size()==2)//Si on a selectionné deux noeuds 
		{
			//On appelle Djikstra pour trouver le chemin le plus court pour aller du noued de depart au noeud d'arrivé.
			this.sommetOrigineDijkstra = listeSommetsSelectionnes.get(0);
			this.sommetDestinationDijkstra = listeSommetsSelectionnes.get(1);

			this.listeAretesDijkstra = this.algoDijkstra.getDijkstraShortestPath(sommetOrigineDijkstra,sommetDestinationDijkstra);

			this.coutCheminDijkstra = 0;
			for (Arete arete : listeAretesDijkstra) {
				coutCheminDijkstra += arete.getCout();
			}

			System.out.println("Le chemin le plus court contient " + listeAretesDijkstra.size() +" aretes");
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
		
		
		if (this.algoKruskal == null) {
			this.algoKruskal = new AlgoKruskalJGrapht(this);
		}
		
		algoKruskal.calculerKruskal(this.listeSommetsSelectionnes);
		
		listeAretesKruskal = algoKruskal.getKruskalShortestPath();
		
		System.out.println("Le chemin le plus court contient " + listeAretesKruskal.size() +" aretes");
		changement();

	}

	//Getters et Setters 

	public void message(String titre, String message) {
		
		
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
		return listeAretesDijkstra;
	}


	/*public void setListearetesCoresspondantauCheminLeplusCourt(List<Arete> listearetesCoresspondantauCheminLeplusCourt) {
		this.listearetesCoresspondantauCheminLeplusCourtDjikstra = listearetesCoresspondantauCheminLeplusCourt;
	}*/


	public List<Arete> getListearetesCoresspondantauCheminLeplusCourtKruskal() {
		return listeAretesKruskal;
	}




	/**Getter Sommet
	 * @return sommet
	 */
	public Sommet getdernierSommetSelectionne() {
		return dernierSommetSelectionne;
	}





	public double getCoutCheminLeplusCourtDjikstra() {
		// TODO Auto-generated method stub
		return coutCheminDijkstra;
	}
	public Sommet getSommetOrigine() {
		return sommetOrigineDijkstra;
	}


	public List<Set<Sommet>> getConnectedSet() {
		return listeSousGraphesConnexes;
	}


	public Sommet getSommetDestination() {
		return sommetDestinationDijkstra;
	}


	
	
	public void setParent(JFrame parent) {
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
		fr.enac.iessa16.cablage.view.Imprimer monImpression = new fr.enac.iessa16.cablage.view.Imprimer(((Fenetre)parent).getDessin());
		//message("Imprimer", "à faire");
		
	}


	public void Fermer() {
		// TODO Auto-generated method stub
		message("Fermer", "à faire");
	}


	public void ouvrir() {
		// TODO Auto-generated method stub
		message("Ouvrir", "à faire");
	}


	public void drag(int dx, int dy) {
		// TODO Auto-generated method stub
		ParametresFenetre.offsetX += dx;
		ParametresFenetre.offsetY += dy;
		
		//message("drag", "dx="+dx+" dy="+dy);
		
		changement();
	}


	public void aide() {
		// TODO Auto-generated method stub
		message("Aide", "à faire");
	}


	public void javaDoc() {
		// TODO Auto-generated method stub
		message("javadoc", "à faire");
		
	}


	public void aPropos() {
		// TODO Auto-generated method stub
		message("apropos", "à faire");
	}


	public void ajoutSommet(int xClic, int yClic) {
		// TODO Auto-generated method stub
		message("Ajouter Sommet", "à faire");
		
		
	}


	public void suppSommet() {
		// TODO Auto-generated method stub
		message("Supprimer Sommet", "à faire");
	}


	public void ajoutArete() {
		// TODO Auto-generated method stub
		message("Ajouter Arete", "à faire");
	}


	public void supprimerArete() {
		// TODO Auto-generated method stub
		message("SupprimerArete", "à faire");
	}





	
	public GrapheTheorique getGraphe() {
		return graphe;
	}


	public void setGraphe(GrapheTheorique graphe) {
		this.graphe = graphe;
		testConnectivite();

		changement();
	}
	
	
	
	
	public void changement() {
		// TODO Auto-generated method stub
		this.setChanged();//Le modele change mais personne ne le sait
		this.notifyObservers(); //on informe les autres que le modele change

	}



}




