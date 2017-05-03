package fr.enac.iessa16.cablage.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.Parametres;

public class FenetrePreferences extends JDialog implements ActionListener {

	private Modele modele;

	private JButton boutonCouleurSommet;
	private JButton boutonCouleurSommetSelectionne;

	private JButton boutonCouleurDernierSommetSelectionne;

	private JButton boutoncouleurAreteSelectionne;

	private JButton boutoncouleurArete;

	//private JButton boutoncouleurDerniereAreteSelectionne;

	private JButton boutoncouleurAreteDijkstra;

	private JButton boutoncouleurAreteKruskal;
	
	private JButton boutoncouleurFond;

	public FenetrePreferences(Modele monModele) {

		super();
		this.modele = monModele;

		this.setTitle("Preferences");

		// Color initialColor = Color.red;
		// Color newColor = JColorChooser.showDialog(null, "Dialog Title",
		// initialColor);
		// System.out.println(initialColor+" "+newColor);

		// this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		// this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		this.pack();
		this.setVisible(true);

	}

	private void initComponent() {

		// Les couleurs nom
		JPanel panCouleurs = new JPanel();
		panCouleurs.setBorder(BorderFactory.createTitledBorder("Choix des couleurs"));
		panCouleurs.setLayout(new GridLayout(8, 2));

		// sommet
		JLabel couleurSommet = new JLabel("Sommet");
		this.boutonCouleurSommet = new JButton();
		boutonCouleurSommet.setBackground(Parametres.couleurSommet);
		// boutonCouleurSommet.setActionCommand("couleurDuSommet");
		boutonCouleurSommet.setToolTipText("Couleur du sommet");
		boutonCouleurSommet.addActionListener(this);
		panCouleurs.add(couleurSommet);
		panCouleurs.add(boutonCouleurSommet);

		// sommet selectionné
		JLabel couleurSommetSelectionne = new JLabel("Sommet sélectionné");
		this.boutonCouleurSommetSelectionne = new JButton();
		boutonCouleurSommetSelectionne.setBackground(Parametres.couleurSommetSelectionne);
		// boutonCouleurSommetSelectionne.setActionCommand("couleurSelectionne");
		boutonCouleurSommetSelectionne.setToolTipText("Couleur du sommet sélectionné");
		boutonCouleurSommetSelectionne.addActionListener(this);
		panCouleurs.add(couleurSommetSelectionne);
		panCouleurs.add(boutonCouleurSommetSelectionne);

		// Dernier Sommet selectionné
		JLabel couleurDernierSommetSelectionne = new JLabel("Dernier sommet sélectionné");
		this.boutonCouleurDernierSommetSelectionne = new JButton();
		boutonCouleurDernierSommetSelectionne.setBackground(Parametres.couleurDernierSommetSelectionne);
		// boutonCouleurDernierSommetSelectionne.setActionCommand("couleurDernierSelectionne");
		boutonCouleurDernierSommetSelectionne.setToolTipText("Couleur du sommet sélectionné");
		boutonCouleurDernierSommetSelectionne.addActionListener(this);
		panCouleurs.add(couleurDernierSommetSelectionne);
		panCouleurs.add(boutonCouleurDernierSommetSelectionne);
		
		
		//Couleur Arete 
		
		JLabel couleurArete = new JLabel("Arete");
		this.boutoncouleurArete = new JButton();
		boutoncouleurArete.setBackground(Parametres.couleurArete);
		// boutonCouleurDernierSommetSelectionne.setActionCommand("couleurDernierSelectionne");
		boutoncouleurArete.setToolTipText("Couleur de l'arete");
		boutoncouleurArete.addActionListener(this);
		panCouleurs.add(couleurArete);
		panCouleurs.add(boutoncouleurArete);
		
		
		
		//Arete Selectionné
		JLabel couleurAreteSelectionne = new JLabel("Arete sélectionnée");
		this.boutoncouleurAreteSelectionne = new JButton();
		boutoncouleurAreteSelectionne.setBackground(Parametres.couleurAreteSelectionne);
		// boutonCouleurDernierSommetSelectionne.setActionCommand("couleurDernierSelectionne");
		boutoncouleurAreteSelectionne.setToolTipText("Couleur de l'arete sélectionné");
		boutoncouleurAreteSelectionne.addActionListener(this);
		panCouleurs.add(couleurAreteSelectionne);
		panCouleurs.add(boutoncouleurAreteSelectionne);
		
		
		
		
		//Arete Djikstra
		
		JLabel couleurAreteDijkstra = new JLabel("Arete Dijkstra");
		this.boutoncouleurAreteDijkstra = new JButton();
		boutoncouleurAreteDijkstra.setBackground(Parametres.couleurAreteDijkstra);
		// boutonCouleurDernierSommetSelectionne.setActionCommand("couleurDernierSelectionne");
		boutoncouleurAreteDijkstra.setToolTipText("Couleur de l'arete sélectionné");
		boutoncouleurAreteDijkstra.addActionListener(this);
		panCouleurs.add(couleurAreteDijkstra);
		panCouleurs.add(boutoncouleurAreteDijkstra);
		
		
		//Arete Kruskal
		
		JLabel couleurAreteKruskal = new JLabel("Arete Kruskal");
		this.boutoncouleurAreteKruskal = new JButton();
		boutoncouleurAreteKruskal.setBackground(Parametres.couleurAreteKruskal);
		// boutonCouleurDernierSommetSelectionne.setActionCommand("couleurDernierSelectionne");
		boutoncouleurAreteKruskal.setToolTipText("Couleur de l'arete Kruskal");
		boutoncouleurAreteKruskal.addActionListener(this);
		panCouleurs.add(couleurAreteKruskal);
		panCouleurs.add(boutoncouleurAreteKruskal);
		
		
		// Fond
		JLabel couleurFond = new JLabel("Fond");
		this.boutoncouleurFond = new JButton();
		boutoncouleurFond.setBackground(Parametres.couleurFondPanneauDessin);
		// boutonCouleurDernierSommetSelectionne.setActionCommand("couleurDernierSelectionne");
		boutoncouleurFond.setToolTipText("Couleur de fond du graphe");
		boutoncouleurFond.addActionListener(this);
		panCouleurs.add(couleurFond);
		panCouleurs.add(boutoncouleurFond);
				
		
		
		
		
		
		
		

		JPanel panOkAnnuler = new JPanel();

		JButton boutonOk = new JButton("OK");
		// FIXME boutonOk.setFocusPainted(true);
		boutonOk.addActionListener(this);
		boutonOk.setActionCommand("OK");

		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.addActionListener(this);
		boutonAnnuler.setActionCommand("Annuler");

		panOkAnnuler.add(boutonOk);
		panOkAnnuler.add(boutonAnnuler);
		this.getContentPane().add(panCouleurs, BorderLayout.CENTER);
		this.getContentPane().add(panOkAnnuler, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String s = e.getActionCommand();
		if (s.equals("OK")) {

			Parametres.couleurSommet = boutonCouleurSommet.getBackground();
			Parametres.couleurSommetSelectionne = boutonCouleurSommetSelectionne.getBackground();
			Parametres.couleurDernierSommetSelectionne = boutonCouleurDernierSommetSelectionne.getBackground();
			Parametres.couleurArete = boutoncouleurArete.getBackground();
			Parametres.couleurAreteSelectionne = boutoncouleurAreteSelectionne.getBackground();
			Parametres.couleurAreteDijkstra = boutoncouleurAreteDijkstra.getBackground();
			Parametres.couleurAreteKruskal = boutoncouleurAreteKruskal.getBackground();
			Parametres.couleurFondPanneauDessin = boutoncouleurFond.getBackground();
			

			this.dispose();
			this.modele.changement();

		} else if (s.equals("Annuler")) {

			this.dispose();

		} else {

			/*
			 * if(s.equals("couleurDuSommet")) { Color initialColor =
			 * boutonCouleurSommet.getBackground(); Color newColor =
			 * JColorChooser.showDialog(null, "Couleur", initialColor); if
			 * (newColor != null) { boutonCouleurSommet.setBackground(newColor);
			 * } }
			 */
				//Donne l'objet qui a declenché l'evenement 
			JButton boutonSource = (JButton) e.getSource();

			Color initialColor = boutonSource.getBackground();
			Color newColor = JColorChooser.showDialog(null, "Couleur", initialColor);
			if (newColor != null) {
				boutonSource.setBackground(newColor);
			}
			

		}

	}
}
