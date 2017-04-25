package fr.enac.iessa16.cablage.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;

/**
 * Classe définissant le panneau paramètres (à gauche)
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class PanneauParametres extends JPanel {
	
	private DonneesAAfficher modele;
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
	
	
	/**
	 * Constructeur de la classe JPanelParametres.java
	 */
	public PanneauParametres(DonneesAAfficher modele) {
		
		super();
		
		// On récupère le modèle
		this.modele = modele;
		
		// Création de la bordure
		this.setBorder(BorderFactory.createTitledBorder("Paramètres"));
		
		// Taille du panneau
		this.setPreferredSize(ParametresFenetre.dimensionJPanelParametres);
		
		// Layout
		this.setLayout(new FlowLayout());
		
		// Création du sous-panneau graphe
		creerPanneauGraphe();
		
		// Création du sous-panneau sommet
		creerPanneauSommet();
		
		// Création du sous-panneau dijkstra
		creerPanneauDijkstra();		
		
		// Création du sous-panneau Kruskal
		creerPanneauKruskal();		
	}
	
	
	/**
	 * Méthode permettant de créer le sous-panneau sommet
	 */
	private void creerPanneauGraphe() {
		
		JPanel graphe = new JPanel();
		graphe.setBorder(BorderFactory.createTitledBorder("Graphe"));
		graphe.setLayout(new GridLayout(3,2));
		
		JLabel labelNbSommet = new JLabel("Nb sommet(s) : ");
		graphe.add(labelNbSommet);
		textFieldNombreSommets = new JTextField(10);
		labelNbSommet.setLabelFor(textFieldNombreSommets);
		graphe.add(textFieldNombreSommets);
		
		JLabel labelNbAretes = new JLabel("Nb arete(s) : ");
		graphe.add(labelNbAretes);
		textFieldNombreAretes = new JTextField(10);
		labelNbAretes.setLabelFor(textFieldNombreAretes);
		graphe.add(textFieldNombreAretes);
		
		JLabel labelNbCompConnexe = new JLabel("Comp. connexe(s) : ");
		graphe.add(labelNbCompConnexe);
		textFieldCompConnexe = new JTextField(10);
		labelNbCompConnexe.setLabelFor(textFieldCompConnexe);
		graphe.add(textFieldCompConnexe);
		
		this.add(graphe);
	}
	
	/**
	 * Méthode permettant de créer le sous-panneau sommet
	 */
	private void creerPanneauSommet() {
		
		JPanel sommet = new JPanel();
		sommet.setBorder(BorderFactory.createTitledBorder("Sommet"));
		sommet.setLayout(new GridLayout(3,2));
		
		JLabel labelNom = new JLabel("Nom : ");
		sommet.add(labelNom);
		textFieldNom = new JTextField(10);
		labelNom.setLabelFor(textFieldNom);
		sommet.add(textFieldNom);
		
		JLabel labelLat = new JLabel("Lat : ");
		sommet.add(labelLat);
		textFieldLat = new JTextField(10);
		labelNom.setLabelFor(textFieldLat);
		sommet.add(textFieldLat);
		
		JLabel labelLon = new JLabel("Long : ");
		sommet.add(labelLon);
		textFieldLon = new JTextField(10);
		labelNom.setLabelFor(textFieldLon);
		sommet.add(textFieldLon);
		
		this.add(sommet);
	}
	
	
	/**
	 * Méthode permettant de créer le sous-panneau Dijkstra
	 */
	private void creerPanneauDijkstra() {
		
		JPanel dijkstra = new JPanel();
		dijkstra.setBorder(BorderFactory.createTitledBorder("Dijkstra"));
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
		
		
		JLabel labelCout = new JLabel("Cout : ");
		dijkstra.add(labelCout);
		textFieldCout= new JTextField(10);
		labelCout.setLabelFor(textFieldCout);
		dijkstra.add(textFieldCout);
		
		this.add(dijkstra);
	}
	
	private void creerPanneauKruskal() {
	
		JPanel dijkstra = new JPanel();
		dijkstra.setBorder(BorderFactory.createTitledBorder("Kruskal"));
		dijkstra.setLayout(new GridLayout(3,2));
		
		
		JLabel labelListeSommetSelectionne = new JLabel("Sommet Selec : ");
		dijkstra.add(labelListeSommetSelectionne);
		comboBoxSommetSelectionne= new JComboBox<Sommet>();
		//labelDest.setLabelFor(textFieldDest);
		dijkstra.add(comboBoxSommetSelectionne);
		
		JLabel labelListeArete = new JLabel("Aretes : ");
		dijkstra.add(labelListeArete);
		comboBoxAretesKruskal= new JComboBox<Arete>();
		//labelDest.setLabelFor(textFieldDest);
		dijkstra.add(comboBoxAretesKruskal);
		
		
		JLabel labelCout = new JLabel("Cout : ");
		dijkstra.add(labelCout);
		textFieldCoutKruskal= new JTextField(10);
		labelCout.setLabelFor(textFieldCoutKruskal);
		dijkstra.add(textFieldCoutKruskal);
		
		this.add(dijkstra);
		
	}
	
	private void creerPanneauArete() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public void update() {
		
		if (modele != null) {
			
			
			// Mise à jour panneau graphe
			GrapheTheorique graphe = modele.getGrapheàafficher();
			
			if (graphe != null) {
				
				textFieldNombreSommets.setText(Integer.toString(graphe.getEnsembleDeSommet().size()));
				textFieldNombreAretes.setText(Integer.toString(graphe.getEnsembleAretes().size()));
				if (modele.getConnectedSet() != null)
					textFieldCompConnexe.setText(Integer.toString(modele.getConnectedSet().size()));
				
			}
			
			
			// Mise à jour sommet
			Sommet sommetSelectionne = modele.getdernierSommetSelectionne();
		
			if (sommetSelectionne != null) {
			
				
				textFieldNom.setText(sommetSelectionne.getNom());
				textFieldLat.setText(Double.toString(sommetSelectionne.getLatitude()));
				textFieldLon.setText(Double.toString(sommetSelectionne.getLongitude()));
			}
			
			
			// Mise à jour ^panneau dijkstra
			double cout = modele.getCoutCheminLeplusCourtDjikstra();
			if (cout >= 0) {
				textFieldCout.setText(Double.toString(cout));
			}
			
			Sommet origine = modele.getSommetOrigine();
			if (origine != null) {
				textFieldOrigine.setText(origine.getNom());
			}
			
			Sommet destination = modele.getSommetDestination();
			if (destination != null) {
				textFieldDest.setText(destination.getNom());
			}
			
			List<Arete> chemin = modele.getListearetesCoresspondantauCheminLeplusCourtDjikstra();
			if (chemin != null) {
				comboBoxAretesDijkstra.removeAllItems();
				for (int i = 0; i < chemin.size(); i++) {
					comboBoxAretesDijkstra.addItem(chemin.get(i));
				}
				
				
			}
			
			
		}
		
	}
	

}
