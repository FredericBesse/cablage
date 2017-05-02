package fr.enac.iessa16.cablage.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;


/**
 * Classe définissant le panneau paramètres (à gauche)
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class PanneauParametres extends JPanel {
	
	private Modele modele;
	
	private JTextField textFieldNom;
	private JTextField textFieldLat;
	private JTextField textFieldLon;
	private JTextField textFieldNombreSommets;
	private JTextField textFieldNombreAretes;
	private JTextField textFieldCompConnexe;
	private JTextField textFieldOrigine;
	private JTextField textFieldDest;
	private JTextField textFieldCout;
	private JComboBox<Arete> comboBoxAretesDijkstra;
	private JComboBox<Sommet> comboBoxSommetSelectionne;
	private JComboBox<Arete> comboBoxAretesKruskal;
	private JTextField textFieldCoutKruskal;
	private JTextArea infoArea;

	private JTextField textFieldAreteOrigine;

	private JTextField textFieldAreteExtremite;

	private JTextField textFieldAretePoids;

	private JTextField textFieldAreteCout;

	private JLabel labelNbSommet;
	
	
	/**
	 * Constructeur de la classe JPanelParametres.java
	 * 
	 * @param modele le modele
	 */
	public PanneauParametres(Modele modele) {
		
		super();
		
		// On récupère le modèle
		this.modele = modele;
		
		// Création de la bordure
		this.setBorder(BorderFactory.createTitledBorder(Parametres.titrePanneauParametres));
		
		// Layout
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		// Création du sous-panneau graphe
		creerPanneauGraphe();
		
		// Création du sous-panneau sommet
		creerPanneauSommet();
		
		// Création du sous-panneau arete
		creerPanneauArete();
		
		// Création du sous-panneau dijkstra
		creerPanneauDijkstra();		
		
		// Création du sous-panneau Kruskal
		creerPanneauKruskal();	
		
		// Création du sous-panneau Info
		creerPanneauInfo();
	}
	
	
	/**
	 * Méthode permettant de créer le sous-panneau graphe
	 */
	private void creerPanneauGraphe() {
		
		JPanel graphe = new JPanel();
		graphe.setBorder(BorderFactory.createTitledBorder(Parametres.titrePanneauParametresGraphe));
		graphe.setLayout(new GridLayout(3,2));
		
		labelNbSommet = new JLabel("Nombre sommet");
		graphe.add(labelNbSommet);
		textFieldNombreSommets = new JTextField(10);
		textFieldNombreSommets.setEditable(false);
		labelNbSommet.setLabelFor(textFieldNombreSommets);
		graphe.add(textFieldNombreSommets);
		
		JLabel labelNbAretes = new JLabel("Nombre arete");
		graphe.add(labelNbAretes);
		textFieldNombreAretes = new JTextField(10);
		textFieldNombreAretes.setEditable(false);
		labelNbAretes.setLabelFor(textFieldNombreAretes);
		graphe.add(textFieldNombreAretes);
		
		JLabel labelNbCompConnexe = new JLabel("Comp. connexe");
		graphe.add(labelNbCompConnexe);
		textFieldCompConnexe = new JTextField(10);
		textFieldCompConnexe.setEditable(false);
		labelNbCompConnexe.setLabelFor(textFieldCompConnexe);
		graphe.add(textFieldCompConnexe);
		
		this.add(graphe);
	}
	
	
	/**
	 * Méthode permettant de créer le sous-panneau sommet
	 */
	private void creerPanneauSommet() {
		
		JPanel sommet = new JPanel();
		sommet.setBorder(BorderFactory.createTitledBorder(Parametres.titrePanneauParametresSommet));
		sommet.setLayout(new GridLayout(3,2));
		
		JLabel labelNom = new JLabel("Nom : ");
		sommet.add(labelNom);
		textFieldNom = new JTextField(10);
		labelNom.setLabelFor(textFieldNom);
		sommet.add(textFieldNom);
		
		JLabel labelLat = new JLabel("Lat : ");
		sommet.add(labelLat);
		textFieldLat = new JTextField(10);
		labelLat.setLabelFor(textFieldLat);
		sommet.add(textFieldLat);
		
		JLabel labelLon = new JLabel("Long : ");
		sommet.add(labelLon);
		textFieldLon = new JTextField(10);
		labelLon.setLabelFor(textFieldLon);
		sommet.add(textFieldLon);
		
		this.add(sommet);
	}
	
	
	/**
	 * Méthode permettant de créer le sous-panneau arete
	 */
	private void creerPanneauArete() {
		
		JPanel arete = new JPanel();
		arete.setBorder(BorderFactory.createTitledBorder(Parametres.titrePanneauParametresArete));
		arete.setLayout(new GridLayout(4,2));
		
		JLabel labelOrigine = new JLabel("Origine : ");
		arete.add(labelOrigine);
		textFieldAreteOrigine = new JTextField(10);
		labelOrigine.setLabelFor(textFieldAreteOrigine);
		arete.add(textFieldAreteOrigine);
		
		JLabel labelExtremite = new JLabel("Extremite : ");
		arete.add(labelExtremite);
		textFieldAreteExtremite = new JTextField(10);
		labelExtremite.setLabelFor(textFieldAreteExtremite);
		arete.add(textFieldAreteExtremite);
		
		JLabel labelPoids= new JLabel("Poids : ");
		arete.add(labelPoids);
		textFieldAretePoids = new JTextField(10);
		labelPoids.setLabelFor(textFieldAretePoids);
		arete.add(textFieldAretePoids);
		
		JLabel labelCout= new JLabel("Cout : ");
		arete.add(labelCout);
		textFieldAreteCout = new JTextField(10);
		labelCout.setLabelFor(textFieldAreteCout);
		arete.add(textFieldAreteCout);
		
		this.add(arete);
	}
	
	
	/**
	 * Méthode permettant de créer le sous-panneau Dijkstra
	 */
	private void creerPanneauDijkstra() {
		
		JPanel dijkstra = new JPanel();
		dijkstra.setBorder(BorderFactory.createTitledBorder(Parametres.titrePanneauParametresDijkstra));
		dijkstra.setLayout(new GridLayout(4,2));
		
		JLabel labelOrigine = new JLabel("Origine : ");
		dijkstra.add(labelOrigine);
		textFieldOrigine = new JTextField(10);
		labelOrigine.setLabelFor(textFieldOrigine);
		dijkstra.add(textFieldOrigine);
		
		JLabel labelDest = new JLabel("Destination : ");
		dijkstra.add(labelDest);
		textFieldDest= new JTextField(10);
		labelDest.setLabelFor(textFieldDest);
		dijkstra.add(textFieldDest);
		
		JLabel labelListeArete = new JLabel("Aretes : ");
		dijkstra.add(labelListeArete);
		comboBoxAretesDijkstra= new JComboBox<Arete>();
		//labelDest.setLabelFor(textFieldDest);
		dijkstra.add(comboBoxAretesDijkstra);	
		
		JLabel labelCout = new JLabel("Cout total : ");
		dijkstra.add(labelCout);
		textFieldCout= new JTextField(10);
		labelCout.setLabelFor(textFieldCout);
		dijkstra.add(textFieldCout);
		
		this.add(dijkstra);
	}
	
	
	/**
	 * Méthode permettant de créer le sous-panneau Kruskal
	 */
	private void creerPanneauKruskal() {
	
		JPanel kruskal = new JPanel();
		kruskal.setBorder(BorderFactory.createTitledBorder(Parametres.titrePanneauParametresKruskal));
		kruskal.setLayout(new GridLayout(3,2));
		
		JLabel labelListeSommetSelectionne = new JLabel("Sommet Selec : ");
		kruskal.add(labelListeSommetSelectionne);
		comboBoxSommetSelectionne= new JComboBox<Sommet>();
		kruskal.add(comboBoxSommetSelectionne);
		
		JLabel labelListeArete = new JLabel("Aretes : ");
		kruskal.add(labelListeArete);
		comboBoxAretesKruskal= new JComboBox<Arete>();
		kruskal.add(comboBoxAretesKruskal);
		
		
		JLabel labelCout = new JLabel("Cout total : ");
		kruskal.add(labelCout);
		textFieldCoutKruskal= new JTextField(10);
		labelCout.setLabelFor(textFieldCoutKruskal);
		kruskal.add(textFieldCoutKruskal);
		
		this.add(kruskal);
	}
	
	
	/**
	 * Méthode permettant de créer le sous-panneau Info
	 */
	private void creerPanneauInfo() {
		
		infoArea = new JTextArea(10, 10);
		JScrollPane info = new JScrollPane(infoArea); 
		infoArea.setEditable(false);
		info.setBorder(BorderFactory.createTitledBorder(Parametres.titrePanneauParametresInformation));			
		this.add(info);
	}
	
	
	
	
	/**
	 * Méthode permettant de mettre à jour le panneau 
	 */
	public void update() {
		// FIXME a enveler ...
		if (modele != null) {
						
			// Mise à jour panneau graphe
			GrapheTheorique graphe = modele.getGraphe();
			if (graphe != null) {
				
				//FIXME mettre à jour le nom du lable pour gérer le singulier pluriel
				textFieldNombreSommets.setText(Integer.toString(graphe.getListeSommets().size()));
				textFieldNombreAretes.setText(Integer.toString(graphe.getListeAretes().size()));
				if (modele.getListeSousGraphesConnexes() != null)
					textFieldCompConnexe.setText(Integer.toString(modele.getListeSousGraphesConnexes().size()));
			} else {
				textFieldNombreSommets.setText("");
				textFieldNombreAretes.setText("");
				textFieldCompConnexe.setText("");
			}
			
			// Mise à jour panneau sommet
			Sommet sommetSelectionne = modele.getdernierSommetSelectionne();
		
			if (sommetSelectionne != null) {				
				textFieldNom.setText(sommetSelectionne.getNom());
				textFieldLat.setText(Double.toString(sommetSelectionne.getLatitude()));
				textFieldLon.setText(Double.toString(sommetSelectionne.getLongitude()));
			} else {
				textFieldNom.setText("");
				textFieldLat.setText("");
				textFieldLon.setText("");
			}
			
			// Mise à jour panneau arete
			Arete a = modele.getDerniereAreteSelectionne();
			if (a != null) {
				textFieldAreteOrigine.setText(a.getSommetOrigine().toString());
				textFieldAreteExtremite.setText(a.getSommetExtremité().toString());
				textFieldAreteCout.setText(Double.toString(a.getCout()));
				textFieldAretePoids.setText(Double.toString(a.getPoids()));
			} else {
				textFieldAreteOrigine.setText("");
				textFieldAreteExtremite.setText("");
				textFieldAreteCout.setText("");
				textFieldAretePoids.setText("");
			}
			
			// Mise à jour panneau dijkstra
			// TODO rajouter une variable dijkstra changed dans le modele
			
			double cout = modele.getCoutCheminDijkstra();
			if (cout > 0) {
				textFieldCout.setText(Double.toString(cout));
			}
			
			Sommet origine = modele.getSommetOrigineDijkstra();
			if (origine != null) {
				textFieldOrigine.setText(origine.getNom());
			}
			
			Sommet destination = modele.getSommetDestination();
			if (destination != null) {
				textFieldDest.setText(destination.getNom());
			}
			
			
			
			List<Arete> chemin = modele.getListeAretesDijkstra();
			if (chemin != null) {
				comboBoxAretesDijkstra.removeAllItems();
				for (int i = 0; i < chemin.size(); i++) {
					comboBoxAretesDijkstra.addItem(chemin.get(i));
				}				
			}
		}		
	}
}
