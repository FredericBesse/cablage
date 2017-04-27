package fr.enac.iessa16.cablage.view;

import java.awt.Dimension;

public class ParametresFenetre {

	
	
	
	public static int rayonSommetAffichage = 5;
	public static int rayonSommetClic = 25;
	
	public static Dimension dimensionEcran;
	
	//public static Dimension dimensionContentPane;
	
	public static Dimension dimensionJPanelParametres;
	
	public static Dimension dimensionJPanelParametresSommet;
	public static Dimension dimensionJPanelParametresArete;
	
	public static Dimension dimensionJPanelGraphe;
	public static double offsetX=0;
	public static double echelle=1;
	public static double ECHELLE_BASE=1;
	public static double offsetY=0;
	
	
	// Items du menu Fichier 
	public static final String menuFichier = "Fichier";
	
	public static final String nouveau = "Nouveau";
	public static final String grapheVide = "Graphe vide";
	public static final String grapheDefaut = "Graphe par défaut";
	public static final String grapheAleatoire = "Graphe aléatoire...";
	
	public static final String importer = "Importer...";
	public static final String ouvrir = "Ouvrir...";

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
		
	// Items du menu Calcul
	public static final String menuCalcul = "Calcul";
	public static final String dijkstra = "Calculer Djikstra";
	public static final String kruskal = "Calculer Kruskal";
	
	// Items du menu ?
	public static final String menuAide = "?";
	public static final String apropos = "\u00C0 propos...";
	public static final String javadoc = "Javadoc...";

}
