package fr.enac.iessa16.cablage.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.model.Sommet;

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
		
		// Création du sous-panneau sommet
		creerPanneauSommet();
		
		// Création du sous-panneau arete
		creerPanneauArete();		
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
		
		JLabel labelLon = new JLabel("Lon : ");
		sommet.add(labelLon);
		textFieldLon = new JTextField(10);
		labelNom.setLabelFor(textFieldLon);
		sommet.add(textFieldLon);
		
		this.add(sommet);
	}
	
	
	private void creerPanneauArete() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public void update() {
		
		if (modele != null) {
			Sommet sommetSelectionne = modele.getdernierSommetSelectionne();
			
			if (sommetSelectionne != null) {
			
				textFieldNom.setText(sommetSelectionne.getNom());
				textFieldLat.setText(Double.toString(sommetSelectionne.getLatitude()));
				textFieldLon.setText(Double.toString(sommetSelectionne.getLongitude()));
			}
		}
		
	}
	

}
