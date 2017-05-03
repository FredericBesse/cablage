package fr.enac.iessa16.cablage.view;

import java.awt.Color;
import java.awt.Component;

public class Parametres {
	
	
	// Version
	public static final String version = "1.0";
	
	// Mode demo
	public static final boolean modeDemo = true;
		
	
	// Titre de la fenetre
	public static final String titreFenetre = "Cablage à cout minimum - Version "+version;
		
	
	// Items du menu Fichier 
	public static final String menuFichier = "Fichier";
	
	public static final String nouveau = "Nouveau";
	public static final String grapheVide = "Graphe vide";
	public static final String grapheDefaut = "Graphe par défaut";
	
	public static final String importer = "Importer...";
	public static final String importerTooltip = "Importer un fichier texte...";
	public static final String ouvrir = "Ouvrir...";
	public static final String ouvrirTooltip = "Ouvrir un fichier XML...";

	public static final String enregistrer = "Enregistrer";
	public static final String enregistrerSous = "Enregistrer sous...";
	public static final String imprimer = "Imprimer...";
	
	public static final String fermer = "Fermer";
	public static final String quitter = "Quitter";
	
	
	// Items du menu Edition
	public static final String menuEdition = "\u00C9dition";
	
	public static final String ajouterSommet = "Ajouter un sommet...";
	public static final String supprimerSommet = "Supprimer un sommet...";
	
	public static final String ajouterArete = "Ajouter une arete...";
	public static final String supprimerArete = "Supprimer une arete...";
	
	public static final String preferences = "Préférences...";
	
	
	// Item du menu Affichage
	public static final String menuAffichage = "Affichage";
	
	public static final String centrage = "Centrer vue";
	
	public static final String zoomPlus = "Zoomer";
	public static final String zoomMoins = "Dezoomer";
	
	public static final String affichageSommet = "Sommet";
	public static final String affichageNomSommet = "Nom sommet";
	public static final String affichageArete = "Arete";
	public static final String affichageDijkstra = "Dijkstra";
	public static final String affichageKruskal = "Kruskal";
	
	
	
		
	
	// Items du menu Calcul
	public static final String menuCalcul = "Calcul";
	public static final String dijkstra = "Calculer Dijkstra";
	public static final String kruskal = "Calculer Kruskal";
	
	
	// Items du menu ?
	public static final String menuAide = "Aide";
	public static final String apropos = "\u00C0 propos...";
	public static final String javadoc = "Javadoc...";

		
	// Titres des panneaux
	public static final String titrePanneauVue2D = "Vue 2D";
	public static final String titrePanneauParametres = "Param\u00e8tres";
	public static final String titrePanneauParametresAffichage = "Affichage";
	public static final String titrePanneauParametresGraphe = "Graphe";
	public static final String titrePanneauParametresSommet = "Sommet";
	public static final String titrePanneauParametresArete = "Arete";
	public static final String titrePanneauParametresDijkstra = "Dijkstra";
	public static final String titrePanneauParametresKruskal = "Kruskal";
	public static final String titrePanneauParametresInformation = "Informations";
	
	
	// Parametre du zoom
	public static double offsetX=0;
	public static double echelle=1;
	public static double ECHELLE_BASE=1;
	public static double offsetY=0;
	
	
	// Parametre d'affichage des sommets
	public static int rayonSommetAffichage = 5;
	public static int rayonSommetSelectionneAffichage = 8;
	public static int rayonSommetClic = 25; // zone cliquable autour du sommet permettant sa sélection
	public static Color couleurSommet = Color.BLUE;
	public static Color couleurSommetSelectionne = Color.RED;	
	public static Color couleurDernierSommetSelectionne = Color.GREEN;	
	
	// Parametre d'affichage des aretes
	public static Color couleurArete = Color.PINK;
	public static Color couleurAreteSelectionne = Color.MAGENTA;
	public static Color couleurAreteDijkstra = Color.MAGENTA;
	public static Color couleurAreteKruskal = Color.ORANGE;
	
	
	// Taille du panneau dessin (pour les fonctions de conversion)
	public static int panneauDessinWidth;
	public static int panneauDessinHeight;
	public static Color couleurFondPanneauDessin = new Color(225, 225, 225);
	public static Color couleurFondPanneauDessinLancement;

	
	
	// Parametres permettant de savoir si on affiche ou non les elements
	public static boolean afficherSommet = true;
	public static boolean afficherNomSommet = false;
	public static boolean afficherArete = true;
	public static boolean afficherDijkstra = true;
	public static boolean afficherKruskal = true;

	
	
	
}
