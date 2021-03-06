package fr.enac.iessa16.cablage.model;

import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.alg.ConnectivityInspector;

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
import fr.enac.iessa16.cablage.view.Parametres;

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

	// La dernière arete selectionnee
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


	/**
	 * Constructeur de la classe DonneesAAfficher.java
	 */
	public Modele() {

		LOGGER.debug("Création du modèle");

		// initialisation du modele
		this.initialise();
	}

	/**
	 * Méthode permettant d'initialiser les attributs  
	 */
	private void initialise() {

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
		this.cablageKruskal = null;

		this.listeSousGraphesConnexes = null;

		this.fichierXML = null;
	}

	/*
	 * Fonctions appelées par le menu Fichier (et la toolbar)
	 */
	/**
	 * Méthode permettant de créer un nouveau graphe vide
	 */
	public void nouveauGrapheVide() {

		LOGGER.debug("Création d'un nouveau graphe vide");

		GrapheTheorique graphevide = new GrapheTheorique();
		this.setGraphe(graphevide);

	}

	/**
	 * Methode permettant de charger le graphe par defaut, apres avoir cliqué
	 * sur l'option adequate du menu
	 */
	public void chargerGrapheParDefaut() {

		LOGGER.debug("Chargement du graphe par défaut");

		// Création du constructeur de graphe par defaut
		ConstructeurGrapheParDefaut constructeurGrapheParDefaut = new ConstructeurGrapheParDefaut();

		// Récupération du graphe
		this.setGraphe(constructeurGrapheParDefaut.getGraphe());
	}

	/**
	 * Méthode permettant de charger le graphe depuis un fichier texte.
	 */
	public void chargerGrapheFichierTexte() {

		LOGGER.debug("Chargement du graphe depuis un fichier texte");

		// Création du lecteur de graphe depuis un fichier texte
		ConstructeurGrapheFichierTexte constructeurGrapheFichierTexte = new ConstructeurGrapheFichierTexte(this);

		// Récupération du graphe
		GrapheTheorique graphe = constructeurGrapheFichierTexte.getGraphe();

		// S'il existe, on met a jour le modele
		if (graphe != null)
			setGraphe(graphe);
		else
			LOGGER.trace(
					"chargerGrapheFichierTexte : le graphe retourné est null (suite annulation utilisateur ou erreur de lecture...)");
	}

	/**
	 * Méthode permettant d'ouvrir un fichier XML
	 */
	public void ouvrir() {

		// On crée l'objet permettant de lire un fichier XML
		ConstructeurGrapheFichierXML constructeurGrapheFichierXML = new ConstructeurGrapheFichierXML(this);

		// on récupère le fichier ouvert par l'utilisateur
		this.fichierXML = constructeurGrapheFichierXML.ouvrirFichierXML();

		// on récupère l'objet contenant le contenu du fichier XML
		ContenuFichierXML contenuFichierXML = constructeurGrapheFichierXML.getContenuFichierXML();

		// si la lecture est réussie, on met à jour le modèle
		if (contenuFichierXML != null) {
			this.setGraphe(contenuFichierXML.getGraphe());
			this.setCablage(contenuFichierXML.getCablage());
		} else {
			LOGGER.trace(
					"ouvrir : le graphe retourné est null (suite annulation utilisateur ou erreur de lecture du fichier XML...)");
		}
	}

	/**
	 * Méthode permettant d'enregistrer un graphe dans un fichier XML
	 */
	public void enregister() {
		// FIXME enregistrer : interdire le bouton enregistrer si enregistrer
		// sous n'a pas ete fait

		if (fichierXML != null) {
			ConstructeurGrapheFichierXML constructeurGrapheFichierXML = new ConstructeurGrapheFichierXML(this);
			ContenuFichierXML contenuFichierXML = new ContenuFichierXML(this.graphe, this.cablageKruskal);
			System.out.println("ecriture xml nbsommets=" + this.graphe.getListeSommets().size());
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
		System.out.println("ecriture xml nbsommets=" + this.graphe.getListeSommets().size());
		this.fichierXML = constructeurGrapheFichierXML.enregistrerSousFichierXML(contenuFichierXML);

	}

	/**
	 * Méthode permettant d'imprimer le graphe
	 */
	public void imprimer() {
		EtatVue.imprimerDemande = true;
		this.changement();
	}

	public void fermer() {
		this.initialise();
		EtatVue.fermerGraphe = true;
		this.changement();
	}

	public void quitter() {

		// FIXME : demander si l'utilisateur est sur de vouloir quitter

		LOGGER.info("Fin de l'application ! Au revoir...");
		System.exit(0);
	}

	/*
	 * Fonctions appelées par le menu Edition (et la toolbar)
	 */
	/**
	 * Méthode permettant de passer en mode ajout sommet
	 */
	public void ajouterSommet() {
		EtatVue.modeAjouterSommet = true;
		this.changement();
	}

	

	/**
	 * Méthode permettant de créer un nouveau sommet dans le graphe
	 * 
	 * @param x l'abscisse du sommet
	 * @param y l'ordonnee
	 * @param nom le nom
	 * @return le sommet créé
	 */
	public Sommet ajouterSommetEffectif(double x, double y, String nom) {

		Sommet sommet = new Sommet(x, y, nom);
		graphe.getListeSommets().add(sommet);
		graphe.addVertex(sommet);
		EtatVue.modeAjouterSommet = false;
		changement();

		return sommet;
	}

	/**
	 * Méthode permettant de supprimer un sommet sélectionné
	 */
	public void supprimerSommet() {
		
		if (this.dernierSommetSelectionne == null) {
			erreur("Suppression sommet impossible", "Vous devez selectionner un sommet !");
		} else {
			Set<Arete> aretesTouchantLeSommetSet = graphe.edgesOf(dernierSommetSelectionne);
			ArrayList<Arete> aretesTouchantLeSommet = new ArrayList<Arete>(aretesTouchantLeSommetSet);

			String message;
			if (aretesTouchantLeSommet.size() == 0) {
				message = "Vous avez demande la suppression du sommet :\n" + dernierSommetSelectionne.toString()
						+ "\nVoulez-vous continuer ?";
			} else {
				message = "La suppression du sommet selectionné provoquera\n" + " la suppression des "
						+ aretesTouchantLeSommet.size() + " aretes qui le touche.\n" + "Voulez-vous continuer ?";
			}

			int n = JOptionPane.showConfirmDialog(null, message, "Suppression du sommet", JOptionPane.YES_NO_OPTION);

			if (n == JOptionPane.YES_OPTION) {

				Arete arete;
				for (int i = 0; i < aretesTouchantLeSommet.size(); i++) {
					arete = aretesTouchantLeSommet.get(i);
					graphe.getListeAretes().remove(arete);
					graphe.removeEdge(arete);
				}

				graphe.getListeSommets().remove(dernierSommetSelectionne);
				graphe.removeVertex(dernierSommetSelectionne);
				listeSommetsSelectionnes.remove(dernierSommetSelectionne);
				derniereAreteSelectionne = null;
				dernierSommetSelectionne = null;

				this.changement();

			}
		}
	}

	/**
	 * Méthode permettant de passer en mode ajout arete
	 */
	public void ajouterArete() {
		
		if (this.dernierSommetSelectionne == null) {
			erreur("Ajout arete impossible", "Vous devez d'abord selectionner un sommet origine !");
		} else {
			EtatVue.modeAjouterArete = true;
			this.changement();
		}
	}

	/**
	 * Méthode permettant d'ajouter une arete
	 * 
	 * @param xClic la position x du clic souris (pixel)
	 * @param yClic la position y du clic souris (pixel)
	 */
	public void ajouterAreteEffectif(int xClic, int yClic) {

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
				for (int i = 0; i < nombreSommet; i++) {

					// on récupère le sommet i
					sommet = graphe.getListeSommets().get(i);

					// on recupère la longitude et latitude du sommet i.
					longitude = sommet.getAbscisse();
					latitude = sommet.getOrdonnee();

					// on les convertit en coordonnées écran (pixel)
					x = PanneauDessinGraphe.conversionLongitudeEnX(longitude);
					y = PanneauDessinGraphe.conversionLatitudeEnY(latitude);

					// on recupère la distance entre la position du clic et la
					// position du sommet
					distance = Math.sqrt(Math.pow(xClic - x, 2) + Math.pow(yClic - y, 2));

					// Le sommet est consideré comme "sélectionné" si la
					// distance entre le clique et
					// la position du sommet du graphe est inférieur au rayon de
					// chaque sommet
					if (distance < Parametres.rayonSommetClic) {

						if (distance < distanceMin) {

							distanceMin = distance;
							sommetLePlusProcheDuClic = sommet;

						}
					}
				}

				if (sommetLePlusProcheDuClic != null) {

					// FIXME on demande le poids de l'arete a l'utilisateur
					message("Création arete", "poids=1");
					double poids = 1;

					// Création de l'arete
					Arete a = new Arete(dernierSommetSelectionne, sommetLePlusProcheDuClic, poids);

					// Ajout de l'arete au graphe
					graphe.getListeAretes().add(a);
					graphe.addEdge(dernierSommetSelectionne, sommetLePlusProcheDuClic, a);

					// on sort du mode ajout arete
					EtatVue.modeAjouterArete = false;

					// On notifie la vue que le modèle a changé
					this.changement();
				} else {

					int n = JOptionPane.showConfirmDialog(null,
							"Voulez-vous créer un nouveau sommet pour l'extremite ?", "Ajout d'une arete",
							JOptionPane.YES_NO_OPTION);

					// TODO : demander le nom a l'utilisateur
					String nom = "toto";
					
					if (n == JOptionPane.YES_OPTION) {
						sommet = this.ajouterSommetEffectif(xClic, yClic, nom);

						// si le sommet a bien été créé
						if (sommet != null) {
							// FIXME on demande le poids de l'arete a
							// l'utilisateur
							message("Création arete", "poids=1");
							double poids = 1;

							// Création de l'arete
							Arete a = new Arete(dernierSommetSelectionne, sommet, poids);

							// Ajout de l'arete au graphe
							graphe.getListeAretes().add(a);
							graphe.addEdge(dernierSommetSelectionne, sommet, a);

							// on sort du mode ajout arete
							EtatVue.modeAjouterArete = false;

							// On notifie la vue que le modèle a changé
							this.changement();
						}
					}
				}
			}
		}
	}

	/**
	 * Méthode permettant de supprimer une arete sélectionnée
	 */
	public void supprimerArete() {
		if (this.derniereAreteSelectionne == null) {
			erreur("Suppression arete impossible", "Vous devez selectionner une arete !");
		} else {

			int n = JOptionPane
					.showConfirmDialog(
							null, "Vous avez demande la suppression de l'arete :\n"
									+ derniereAreteSelectionne.toString() + "\nVoulez-vous continuer ?",
							"Suppression de l'arete", JOptionPane.YES_NO_OPTION);

			if (n == JOptionPane.YES_OPTION) {

				graphe.getListeAretes().remove(derniereAreteSelectionne);
				graphe.removeEdge(derniereAreteSelectionne);
				derniereAreteSelectionne = null;

				this.changement();

			}
		}
	}



	/**
	 * Methode permettant de calculer Dijkstra
	 */
	public void calculerDijkstra() {

		this.algoDijkstra = new AlgoDijkstraJGrapht(this, graphe);

		LOGGER.trace("il y a " + listeSommetsSelectionnes.size() + " noeuds selectionnés");

		// Si on a selectionné deux noeuds
		if (listeSommetsSelectionnes.size() == 2) {

			// On appelle Djikstra pour trouver le chemin le plus court pour
			// aller du noued de depart au noeud d'arrivé.
			this.sommetOrigineDijkstra = listeSommetsSelectionnes.get(0);
			this.sommetDestinationDijkstra = listeSommetsSelectionnes.get(1);

			this.listeAretesDijkstra = this.algoDijkstra.getCheminLePlusCourtDijkstra(sommetOrigineDijkstra,
					sommetDestinationDijkstra);

			this.coutCheminDijkstra = 0;
			for (Arete arete : listeAretesDijkstra) {
				coutCheminDijkstra += arete.getCout();
			}

			LOGGER.trace("Le chemin le plus court contient " + listeAretesDijkstra.size() + " aretes");

			// On notifie la vue que le modèle a changé:
			changement();

		} else {
			erreur("Calcul de Dijkstra", "Il faut sélectionner deux noeuds !");
		}

	}

	/**
	 * Methode permettant de calculer Kruskal
	 */
	public void calculerKruskal() {

		this.algoKruskal = new AlgoKruskalJGrapht(this);

		algoKruskal.calculerKruskal(this.listeSommetsSelectionnes);

		this.cablageKruskal = algoKruskal.getCablageLePlusCourtKruskal();

		LOGGER.trace("Le chemin le plus court contient " + cablageKruskal.getAretes().size() + " aretes");

		changement();
	}

	
	
	/*
	 * Fonctions appelées par le controleur de la souris
	 */

	/**
	 * Méthode permettant de trouver le noeud le plus proche du clic souris
	 * @param xClic la position x du clic souris (pixel)
	 * @param yClic la position y du clic souris (pixel)
	 * @return true si un sommet proche du clic souris a été trouvé
	 */
	public boolean touverSommetLePlusProcheDuClicSouris(int xClic, int yClic) {

		LOGGER.debug("Recherche sommet le plus proche du clic souris x=" + xClic + " y=" + yClic);

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
				for (int i = 0; i < nombreSommet; i++) {

					// on récupère le sommet i
					sommet = graphe.getListeSommets().get(i);

					// on recupère la longitude et latitude du sommet i.
					longitude = sommet.getAbscisse();
					latitude = sommet.getOrdonnee();

					// on les convertit en coordonnées écran (pixel)
					x = PanneauDessinGraphe.conversionLongitudeEnX(longitude);
					y = PanneauDessinGraphe.conversionLatitudeEnY(latitude);

					// on recupère la distance entre la position du clic et la
					// position du sommet
					distance = Math.sqrt(Math.pow(xClic - x, 2) + Math.pow(yClic - y, 2));

					// LOGGER.trace("distance = "+distance);
					// Le sommet est consideré comme "sélectionné" si la
					// distance entre le clique et
					// la position du sommet du graphe est inférieur au rayon de
					// chaque sommet
					if (distance < Parametres.rayonSommetClic) {

						if (distance < distanceMin) {

							distanceMin = distance;
							sommetLePlusProcheDuClic = sommet;

						}
					}
				}

				// on stocke le noeud cliqué dans l'attribut
				// dernierSommetSelectionne
				this.dernierSommetSelectionne = sommetLePlusProcheDuClic;

				if (sommetLePlusProcheDuClic != null) {

					// System.out.println(sommet.getNom());
					// this.dessin1.paint(model.getGrapheàafficher());
					// this.selectionner = true ;

					// Si le sommmet était déjà selectionné
					if (dernierSommetSelectionne.getSelected() == true) {
						// on le deselectionne quand on clique dessus
						dernierSommetSelectionne.setSelected(false);

						// on le supprime de la liste des noeuds sélectionnés
						this.listeSommetsSelectionnes.remove(dernierSommetSelectionne);
					} else {
						// on le selectionne
						dernierSommetSelectionne.setSelected(true);

						// on l'ajoute dans la liste des noeuds sélectionnés
						this.listeSommetsSelectionnes.add(dernierSommetSelectionne);
					}

					// On notifie la vue que le modèle a changé
					this.changement();
				}
			}
		}
		
		// On renvoie true si on a trouvé un sommet, false sinon
		return (sommetLePlusProcheDuClic != null);
	}

	/**
	 * Méthode permettant de trouver l'arete le plus proche du clic souris
	 * 
	 * @param xClic la position x du clic souris (pixel)
	 * @param yClic la position y du clic souris (pixel)
	 */
	public void touverAreteLaPlusProcheDuClicSouris(int xClic, int yClic) {

		LOGGER.debug("Recherche arete le plus proche du clic souris x=" + xClic + " y=" + yClic);

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
			for (int i = 0; i < nombreArete; i++) {

				// on récupère l'arete i
				arete = listeAretes.get(i);

				// on recupère la longitude et latitude du sommet origine.
				longitudeOrigine = arete.getSommetOrigine().getAbscisse();
				latitudeOrigine = arete.getSommetOrigine().getOrdonnee();

				// on les convertit en coordonnées écran (pixel)
				xOrigine = PanneauDessinGraphe.conversionLongitudeEnX(longitudeOrigine);
				yOrigine = PanneauDessinGraphe.conversionLatitudeEnY(latitudeOrigine);

				// on recupère la longitude et latitude du sommet extremite.
				longitudeExtremite = arete.getSommetExtremité().getAbscisse();
				latitudeExtremite = arete.getSommetExtremité().getOrdonnee();

				// on les convertit en coordonnées écran (pixel)
				xExtremite = PanneauDessinGraphe.conversionLongitudeEnX(longitudeExtremite);
				yExtremite = PanneauDessinGraphe.conversionLatitudeEnY(latitudeExtremite);

				// on recupère la distance entre la position du clic et la
				// position du sommet
				distance = Line2D.ptSegDist(xOrigine, yOrigine, xExtremite, yExtremite, xClic, yClic);

				// LOGGER.trace("distance = "+distance);
				// Le sommet est consideré comme "sélectionné" si la distance
				// entre le clique et
				// la position du sommet du graphe est inférieur au rayon de
				// chaque sommet
				if (distance < Parametres.rayonSommetClic) {

					if (distance < distanceMin) {

						distanceMin = distance;
						areteLaPlusProcheDuClic = arete;

					}
				}
			}

			this.derniereAreteSelectionne = areteLaPlusProcheDuClic;

			// On notifie la vue que le modèle a changé
			this.changement();

		}

	}

	/**
	 * Méthode permettant de déplacer la vue suite à un drag de la souris
	 * 
	 * @param dx le déplacement en x (pixel)
	 * @param dy le déplacement en y (pixel)
	 */
	public void drag(int dx, int dy) {

		Parametres.offsetX += dx;
		Parametres.offsetY += dy;

		changement();
	}

	/**
	 * Méthode permettant de zoomer
	 * 
	 * @param xs la position x du clic souris (pixel)
	 * @param ys la position y du clic souris (pixel)
	 * @param rotation la rotation de la molette
	 */
	public void zoom(int xs, int ys, int rotation) {

		// on calcule la nouvelle échelle pour la vue
		double newEchelle = Parametres.echelle * Math.pow(1.01, -5 * rotation);

		// on calcule les nouveaux offset en fonction de la position de la
		// souris
		double newOffsetX = Parametres.offsetX + PanneauDessinGraphe.conversionXenLongitude(xs)
				/ Parametres.ECHELLE_BASE * (Parametres.echelle - newEchelle);
		double newOffsetY = Parametres.offsetY - PanneauDessinGraphe.conversionYenLatitude(ys) / Parametres.ECHELLE_BASE
				* (Parametres.echelle - newEchelle);

		// On met à jour les paramètres de la fenetre
		Parametres.echelle = newEchelle;
		Parametres.offsetX = newOffsetX;
		Parametres.offsetY = newOffsetY;

		// On notifie la vue
		this.changement();
	}

	/**
	 * Méthode permettant de tester la connectivité du graphe et d'en extraire
	 * le(s) sous-graphe(s) connexe(s)
	 */
	private void testConnectivite() {

		

		ConnectivityInspector<Sommet, Arete> connectivityInspector = new ConnectivityInspector<>(this.graphe);

		listeSousGraphesConnexes = (ArrayList<Set<Sommet>>) connectivityInspector.connectedSets();

		LOGGER.trace(listeSousGraphesConnexes.size() + " composantes connexes trouvées");

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

		EtatVue.centreVueDemande = true;
		EtatVue.nouveauGraphe = true;

		changement();
	}

	public Cablage getCablage() {
		return cablageKruskal;
	}

	public void setCablage(Cablage cablage) {
		this.cablageKruskal = cablage;
		changement();
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

	public ArrayList<Sommet> getListeSommetsSelectionnes() {
		return listeSommetsSelectionnes;
	}

	public Arete getDerniereAreteSelectionne() {
		return derniereAreteSelectionne;
	}

}
