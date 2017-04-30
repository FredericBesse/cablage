package fr.enac.iessa16.cablage.model;

import java.awt.Desktop;
import java.awt.geom.Line2D;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleWeightedGraph;

import fr.enac.iessa16.cablage.model.algorithm.AlgoDijkstraJGrapht;
import fr.enac.iessa16.cablage.model.algorithm.AlgoKruskalJGrapht;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.Cablage;
import fr.enac.iessa16.cablage.model.core.ContenuFichierXML;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;
import fr.enac.iessa16.cablage.model.file.ConstructeurGrapheFichierTexte;
import fr.enac.iessa16.cablage.model.file.ConstructeurGrapheFichierXML;
import fr.enac.iessa16.cablage.view.PanneauDessinGraphe;
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
	private Logger LOGGER = LogManager.getLogger(Modele.class);
				
	// Le graphe à afficher
	private GrapheTheorique graphe;	

	// Les sommets selectionnes
	private Sommet dernierSommetSelectionne;
	private ArrayList<Sommet> listeSommetsSelectionnes;

	
	private Arete derniereAreteSelectionne;
	



	// Dijkstra
	private AlgoDijkstraJGrapht algoDijkstra;
	private Sommet sommetOrigineDijkstra;
	private Sommet sommetDestinationDijkstra;
	private ArrayList<Arete> listeAretesDijkstra;
	private double coutCheminDijkstra;

	// Kruskal
	private AlgoKruskalJGrapht algoKruskal;
	private Cablage cablageKruskal;
	
	


	// Connectivité
	private ArrayList<Set<Sommet>> listeSousGraphesConnexes;
	
	// Fichier XML
	private File fichierXML;
	
	
	private boolean imprimerDemande;
	private boolean centreVueDemande;

	private boolean modeAjouterSommet;

	
	
	/**
	 * Constructeur de la classe DonneesAAfficher.java
	 */
	public Modele(){
		
		LOGGER.debug("Création du modèle");
							
		// Initialisation des attributs
		this.graphe = null;
		
		this.dernierSommetSelectionne = null;
		this.listeSommetsSelectionnes = new ArrayList<Sommet>();
		
		this.derniereAreteSelectionne = null;
		
		this.algoDijkstra = null;
		this.sommetOrigineDijkstra = null;
		this.sommetDestinationDijkstra = null;
		this.listeAretesDijkstra = new ArrayList<Arete>();
		this.coutCheminDijkstra = 0;
		
		this.algoKruskal = null;
		//this.listeAretesKruskal = new ArrayList<Arete>();
		this.cablageKruskal = null;
		
		this.listeSousGraphesConnexes = null;
		this.modeAjouterSommet = false;
				
		this.imprimerDemande = false;
		this.centreVueDemande = false;
		
		this.fichierXML = null;
		
		//this.fenetre = null;
	}


	/* 
	 * Fonctions appelées par le menu Fichier (et la toolbar)
	 */
	/**
	 * Méthode permettant de créer un nouveau graphe vide
	 */
	public void nouveauGrapheVide() {
		
		// TODO nouveauGrapheVide
		message("Graphe Vide", "à faire");
	}
	
	/**
	 * Methode permettant de charger le graphe par defaut, apres avoir cliqué sur l'option adequate du menu
	 */
	public void chargerGrapheParDefaut() {
		
		LOGGER.debug("Chargement du graphe par défaut");

		// Création du constructeur de graphe par defaut
		ConstructeurGrapheParDefaut constructeurGrapheParDefaut = new ConstructeurGrapheParDefaut();
		
		// Récupération du graphe
		this.setGraphe(constructeurGrapheParDefaut.getGraphe());
	}

	/**
	 * Méthode permettant de créer un nouveau graphe aléatoire 
	 */
	public void nouveauGrapheAleatoire() {
		// TODO nouveauGrapheAleatoire
		
		message("graphe aleatoire", "à faire");
		
	}

	/**
	 * Méthode permettant de charger le graphe depuis un fichier texte. 
	 */
	public void chargerGrapheFichierTexte() {
		
		LOGGER.debug("Chargement du graphe depuis un fichier texte");
		
		// Création du lecteur de graphe depuis un fichier texte		
		ConstructeurGrapheFichierTexte constructeurGrapheFichierTexte = new ConstructeurGrapheFichierTexte(this);
		
		// Récupération du graphe
		setGraphe(constructeurGrapheFichierTexte.getGraphe());
	}
	
	/**
	 * Méthode permettant d'ouvrir un fichier XML  
	 */
	public void ouvrir() {
		
		
		
		ConstructeurGrapheFichierXML constructeurGrapheFichierXML = new ConstructeurGrapheFichierXML(this);
		//ContenuFichierXML contenuFichierXML = new ContenuFichierXML(this.graphe);
		//System.out.println("ecriture xml nbsommets="+this.graphe.getListeSommets().size());
		this.fichierXML = constructeurGrapheFichierXML.ouvrirFichierXML();
		
		ContenuFichierXML contenuFichierXML = constructeurGrapheFichierXML.getContenuFichierXML();
		this.setGraphe(contenuFichierXML.getGraphe());
		this.setCablage(contenuFichierXML.getCablage());

	}
	
	/**
	 * Méthode permettant d'enregistrer un graphe dans un fichier XML  
	 */
	public void enregister() {
		// FIXME enregistrer : interdire le bouton enregistrer si enregistrer sous n'a pas ete fait
		
		if (fichierXML != null) {
			ConstructeurGrapheFichierXML constructeurGrapheFichierXML = new ConstructeurGrapheFichierXML(this);
			ContenuFichierXML contenuFichierXML = new ContenuFichierXML(this.graphe, this.cablageKruskal);
			System.out.println("ecriture xml nbsommets="+this.graphe.getListeSommets().size());
			constructeurGrapheFichierXML.enregistrerFichierXML(fichierXML, contenuFichierXML);
		} else {
			erreur("enregistrement impossible", "le fichier xml n'existe pas");
		}
		
		
	}
	
	/**
	 * Méthode permettant d'enregistrer un graphe dans un autre fichier XML  
	 */
	public void enregisterSous() {
		
		ConstructeurGrapheFichierXML constructeurGrapheFichierXML = new ConstructeurGrapheFichierXML(this);
		ContenuFichierXML contenuFichierXML = new ContenuFichierXML(this.graphe, this.cablageKruskal);
		System.out.println("ecriture xml nbsommets="+this.graphe.getListeSommets().size());
		this.fichierXML = constructeurGrapheFichierXML.enregistrerSousFichierXML(contenuFichierXML);
		
		
	}
	
	/**
	 * Méthode permettant d'imprimer le graphe  
	 */
	public void imprimer() {
		
		this.imprimerDemande = true;
		//new Imprimer(fenetre.getDessin());
		
	}


	public void fermer() {
		// TODO fermer
		message("Fermer", "à faire");
	}

	public void quitter() {
		
		// FIXME : demande si l'utilisateur est sur de vouloir quitter
		
		LOGGER.info("Fin de l'application ! Au revoir...");
		System.exit(0);
	}
	
	
	
	
	
	/* 
	 * Fonctions appelées par le menu Edition (et la toolbar)
	 */
	
	public void ajouterSommet() {
		// TODO ajouter sommet
		//message("Ajouter Sommet", "à faire");	
		//return true;
		modeAjouterSommet = true;
		
		this.changement();
	}

	public void supprimerSommet() {
		// TODO supprimer sommet
		message("Supprimer Sommet", "à faire");
	}

	public void ajouterArete() {
		// TODO ajouter arete
		message("Ajouter Arete", "à faire");
	}

	public void supprimerArete() {
		// TODO supprimer arete
		message("SupprimerArete", "à faire");
	}
	
	public void preferences() {
		// TODO preferences
		message("preferences", "à faire");
	}
	

	
	
	/* 
	 * Fonctions appelées par le menu Calcul
	 */
	
	/**
	 * Methode permettant de calculer Dijkstra
	 */
	public void calculerDijkstra() {

		this.algoDijkstra = new AlgoDijkstraJGrapht(this, graphe);
		

		LOGGER.trace("il y a "+ listeSommetsSelectionnes.size()+ " noeuds selectionnés");
		
		// Si on a selectionné deux noeuds 
		if(listeSommetsSelectionnes.size()==2) {
			
			//On appelle Djikstra pour trouver le chemin le plus court pour aller du noued de depart au noeud d'arrivé.
			this.sommetOrigineDijkstra = listeSommetsSelectionnes.get(0);
			this.sommetDestinationDijkstra = listeSommetsSelectionnes.get(1);

			this.listeAretesDijkstra = this.algoDijkstra.getCheminLePlusCourtDijkstra(sommetOrigineDijkstra,sommetDestinationDijkstra);

			this.coutCheminDijkstra = 0;
			for (Arete arete : listeAretesDijkstra) {
				coutCheminDijkstra += arete.getCout();
			}

			LOGGER.trace("Le chemin le plus court contient " + listeAretesDijkstra.size() +" aretes");
			
			//On notifie la vue que le modèle a changé:
			changement();

		} else {
			erreur("Calcul de Dijkstra", "Il faut sélectionner deux noeuds !");
		}
		
	}


	/**
	 * Methode permettant de calculer Kruskal
	 */
	public void calculerKruskal() {
		
		// FIXME a faire plutot dans le setGraphe, ou en cas de modification du graphe (ajout sommet, arete)
		this.algoKruskal = new AlgoKruskalJGrapht(this);
				
		algoKruskal.calculerKruskal(this.listeSommetsSelectionnes);
		
		
		this.cablageKruskal = algoKruskal.getCablageLePlusCourtKruskal();
		
		// FIXME gerer null ?
		LOGGER.trace("Le chemin le plus court contient " + cablageKruskal.getAretes().size() +" aretes");
		
		changement();

	}
	
	
	
	/* 
	 * Fonctions appelées par le menu Aide
	 */
	public void javaDoc() {
	
		
		if(Desktop.isDesktopSupported()) {  
			if(Desktop.getDesktop().isSupported(java.awt.Desktop.Action.BROWSE)) {  
				try { 
					// FIXME copier les fichiers javadoc chez l'utilisateur pour que ca marche en local
					Desktop.getDesktop().browse(new URI("file:///home/eleve/IESSA/bessefc/git/cablageGit/CablageGit/doc/index.html"));
				} catch (Exception ex) {  
					erreur("Javadoc", "Impossible de trouver la Javadoc...");
				} 
			} else {  
				erreur("Javadoc", "La fonction n'est pas supportée par votre système d'exploitation...");  
			}  
		} else {  
			erreur("Javadoc", "Desktop pas supporté par votre système d'exploitation...");  
		}		
	}


	public void aPropos() {
		// TODO apropos
		message("apropos", "à faire");
	}


	
	
	
	/* 
	 * Fonctions appelées par le controleur de la souris
	 */

	/**
	 * Méthode permettant de trouver le noeud le plus proche du clic souris
	 * 
	 * @param xClic
	 * @param yClic
	 */
	public void touverSommetLePlusProcheDuClicSouris (int xClic, int yClic) {
	
		LOGGER.debug("Recherche sommet le plus proche du clic souris x="+xClic+" y="+yClic);
		
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
			listeSommets = graphe.getListeSommets();
			
			// si la liste existe (non null)
			if (listeSommets != null) {
				
				// on récupère le nombre de sommets
				nombreSommet = this.graphe.getListeSommets().size();
								
				// on parcourt l'ensemble des sommets du graphe 
				for(int i=0 ; i<nombreSommet;i++) {
					
					// on récupère le sommet i
					sommet = graphe.getListeSommets().get(i);
					
					// on recupère la longitude et latitude du sommet i.
					longitude = sommet.getLongitude();
					latitude  = sommet.getLatitude();

					// on les convertit en coordonnées écran (pixel)
					x = PanneauDessinGraphe.conversionLongitudeEnX(longitude);
					y = PanneauDessinGraphe.conversionLatitudeEnY(latitude);

					// on recupère la distance entre la position du clic et la position du sommet
					distance = Math.sqrt(Math.pow(xClic-x,2)+Math.pow(yClic-y,2));
					
					//LOGGER.trace("distance = "+distance);
					//Le sommet est consideré comme "sélectionné" si la distance entre le clique et 
					//la position du sommet du graphe est inférieur au rayon de chaque sommet 
					if(distance<ParametresFenetre.rayonSommetClic)	{
						
						if (distance<distanceMin) {
							
							distanceMin = distance;
							sommetLePlusProcheDuClic = sommet;
							
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
	}
	
	
	
	/**
	 * Méthode permettant de trouver l'arete le plus proche du clic souris
	 * 
	 * @param xClic
	 * @param yClic
	 */
	public void touverAreteLPlusProcheDuClicSouris (int xClic, int yClic) {
	
		LOGGER.debug("Recherche arete le plus proche du clic souris x="+xClic+" y="+yClic);
		
		// Déclaration des variables locales
		double distance;
		double distanceMin = Double.MAX_VALUE;
		double latitudeOrigine;
		double latitudeExtremite;
		double longitudeOrigine;
		double longitudeExtremite;
		double xOrigine, xExtremite;
		double yOrigine, yExtremite;
		Arete areteLaPlusProcheDuClic = null;
		Arete arete;
		int nombreArete;
		ArrayList<Arete> listeAretes;
		
		// si le graphe existe
		if (this.graphe != null) {
			
			// on récupère la liste des aretes
			listeAretes = graphe.getListeAretes();
			
				
				// on récupère le nombre d'arete
				nombreArete = this.graphe.getListeAretes().size();
								
				// on parcourt l'ensemble des aretes du graphe 
				for(int i=0 ; i<nombreArete;i++) {
					
					// on récupère l'arete i
					arete = listeAretes.get(i);
					
					// on recupère la longitude et latitude du sommet origine.
					longitudeOrigine = arete.getSommetOrigine().getLongitude();
					latitudeOrigine  = arete.getSommetOrigine().getLatitude();

					// on les convertit en coordonnées écran (pixel)
					xOrigine = PanneauDessinGraphe.conversionLongitudeEnX(longitudeOrigine);
					yOrigine = PanneauDessinGraphe.conversionLatitudeEnY(latitudeOrigine);
					
					// on recupère la longitude et latitude du sommet extremite.
					longitudeExtremite = arete.getSommetExtremité().getLongitude();
					latitudeExtremite  = arete.getSommetExtremité().getLatitude();

					// on les convertit en coordonnées écran (pixel)
					xExtremite = PanneauDessinGraphe.conversionLongitudeEnX(longitudeExtremite);
					yExtremite = PanneauDessinGraphe.conversionLatitudeEnY(latitudeExtremite);

					// on recupère la distance entre la position du clic et la position du sommet
					distance = Line2D.ptSegDist(xOrigine, yOrigine, xExtremite, yExtremite, xClic, yClic);
					
					//LOGGER.trace("distance = "+distance);
					//Le sommet est consideré comme "sélectionné" si la distance entre le clique et 
					//la position du sommet du graphe est inférieur au rayon de chaque sommet 
					if(distance<ParametresFenetre.rayonSommetClic)	{
						
						if (distance<distanceMin) {
							
							distanceMin = distance;
							areteLaPlusProcheDuClic = arete;
							
						}
					}
				}
				
				this.derniereAreteSelectionne = areteLaPlusProcheDuClic;
				
				//On notifie la vue que le modèle a changé
				this.changement();
								
			}		
		
	}

	
	/**
	 * Méthode permettant de déplacer la vue suite à un drag de la souris
	 *   
	 * @param dx
	 * @param dy
	 */
	public void drag(int dx, int dy) {

		ParametresFenetre.offsetX += dx;
		ParametresFenetre.offsetY += dy;
		
		changement();
	}
	
	
	public void zoom(int xs, int ys, int rotation) {

			
		// on calcule la nouvelle échelle pour la vue
		double newEchelle = ParametresFenetre.echelle * 
				 Math.pow(1.01, -5 * rotation);
		
		// on calcule les nouveaux offset en fonction de la position de la souris
		double newOffsetX = ParametresFenetre.offsetX
				+ PanneauDessinGraphe.conversionXenLongitude(xs) / ParametresFenetre.ECHELLE_BASE
				* (ParametresFenetre.echelle - newEchelle);
		double newOffsetY = ParametresFenetre.offsetY
				- PanneauDessinGraphe.conversionYenLatitude(ys) / ParametresFenetre.ECHELLE_BASE
				* (ParametresFenetre.echelle - newEchelle);
		
		// On met à jour les paramètres de la fenetre
		ParametresFenetre.echelle = newEchelle;
		ParametresFenetre.offsetX = newOffsetX;
		ParametresFenetre.offsetY = newOffsetY;
				
		// On notifie la vue
		this.changement();
	}





	/**
	 * Méthode permettant de tester la connectivité du graphe
	 * et d'en extraire le(s) sous-graphe(s) connexe(s)
	 */
	private void testConnectivite() {

		SimpleWeightedGraph<Sommet, Arete> graphPourJGrapht = new SimpleWeightedGraph<Sommet, Arete>(Arete.class);

		// Ajout de tous les sommets au graphe JGrapht
		for (Sommet sommet: graphe.getListeSommets()) {
			graphPourJGrapht.addVertex(sommet);
		}
		
		// Ajout de toutes les aretes au graphe JGrapht
		for (Arete arete : graphe.getListeAretes()) {
			graphPourJGrapht.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
			// FIXME graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
		}

		ConnectivityInspector<Sommet, Arete> connectivityInspector = new ConnectivityInspector<>(graphPourJGrapht);

		listeSousGraphesConnexes = (ArrayList<Set<Sommet>>) connectivityInspector.connectedSets();

		LOGGER.trace(listeSousGraphesConnexes.size()+" composantes connexes trouvées");


	}


	

	

	/*
	 * Affichage des messages d'infos, erreur, warning à l'utilisateur
	 */
	public void message(String titre, String message) {
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE);	
	}
	public void warning(String titre, String message) {		
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.WARNING_MESSAGE);
	}
	public void erreur(String titre, String message) {		
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.ERROR_MESSAGE);
	}

	
	
	
	/*
	 * Getters
	 */
	
	public ArrayList<Arete> getListeAretesDijkstra() {
		return listeAretesDijkstra;
	}

	
	public Cablage getListeAretesKruskal() {
		return cablageKruskal;
	}

	public Sommet getdernierSommetSelectionne() {
		return dernierSommetSelectionne;
	}

	public double getCoutCheminDijkstra() {
		return coutCheminDijkstra;
	}
	
	public Sommet getSommetOrigineDijkstra() {
		return sommetOrigineDijkstra;
	}

	public ArrayList<Set<Sommet>> getListeSousGraphesConnexes() {
		return listeSousGraphesConnexes;
	}

	public Sommet getSommetDestination() {
		return sommetDestinationDijkstra;
	}
	
	public GrapheTheorique getGraphe() {
		return graphe;
	}

	
	
	/*
	 * Setters
	 */
	
	
	public void setGraphe(GrapheTheorique graphe) {
		
		this.graphe = graphe;
		this.graphe.calculExtremumDonnees();
		
		testConnectivite();
		
		this.centreVueDemande = true;
		
		changement();
	}
	
	
	public Cablage getCablage() {
		return cablageKruskal;
	}


	public void setCablage(Cablage cablage) {
		this.cablageKruskal = cablage;
		changement();
	}
	
	
	
	public boolean isModeAjouterSommet() {
		return modeAjouterSommet;
	}


	public void setModeAjouterSommet(boolean modeAjouterSommet) {
		this.modeAjouterSommet = modeAjouterSommet;
	}


	/**
	 * Méthode permettant de notifier la vue d'un changement du modèle  
	 */
	public void changement() {
		
		// Le modele change (mais personne ne le sait)
		this.setChanged();
		
		// On informe les obverveurs que le modele a changé
		this.notifyObservers(); 
	}


	public void centrerVue() {
		this.centreVueDemande = true;
		
		this.changement();
	}


	public void zoomer() {
		// TODO Auto-generated method stub
		
	}


	public void dezoomer() {
		// TODO Auto-generated method stub
		
	}


	public boolean isImprimerDemande() {
		return imprimerDemande;
	}
	
	


	public boolean isCentreVueDemande() {
		return centreVueDemande;
	}


	public void setImprimerDemande(boolean imprimerDemande) {
		this.imprimerDemande = imprimerDemande;
	}


	public void setCentreVueDemande(boolean centreVueDemande) {
		this.centreVueDemande = centreVueDemande;
	}
	
	public ArrayList<Sommet> getListeSommetsSelectionnes() {
		return listeSommetsSelectionnes;
	}


	public Arete getDerniereAreteSelectionne() {
		return derniereAreteSelectionne;
	}


	public void ajouterSommetEffectif(int x, int y) {
		// TODO Auto-generated method stub
		double longitude = PanneauDessinGraphe.conversionXenLongitude(x);
		double latitude = PanneauDessinGraphe.conversionYenLatitude(y);
        Sommet sommet = new Sommet(longitude,latitude, "toto");
        graphe.getListeSommets().add(sommet);
        this.modeAjouterSommet = false;
        changement();
		
		
	}
	
}
