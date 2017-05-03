package fr.enac.iessa16.cablage.view.menu;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.Fenetre;
import fr.enac.iessa16.cablage.view.Parametres;

@SuppressWarnings("serial")
public class MenuAffichage extends JMenu {

	private Fenetre fenetre;
	private Controleur controleur;

	private LookAndFeelInfo[] listeLAF;

	private JMenuItem optionCentrage;
	private JMenuItem optionZoomer;
	private JMenuItem optionDezoomer;
	private JCheckBoxMenuItem sommet;
	private JCheckBoxMenuItem nomSommet;
	private JCheckBoxMenuItem aretes;
	private JCheckBoxMenuItem dijkstra;
	private JCheckBoxMenuItem kruskal;

	public MenuAffichage(Controleur controleur, Fenetre fenetre) {
		super(Parametres.menuAffichage);

		this.fenetre = fenetre;
		// on récupère le controleur
		this.controleur = controleur;

		// on crée les items
		this.creerItems();
	}

	private void creerItems() {

		optionCentrage = new JMenuItem(Parametres.centrage);
		optionCentrage.setActionCommand(Parametres.centrage);
		optionCentrage.addActionListener(controleur.getControleurMenuAffichage());
		optionCentrage.setEnabled(false);
		this.add(optionCentrage);

		this.addSeparator();

		optionZoomer = new JMenuItem(Parametres.zoomPlus);
		optionZoomer.setActionCommand(Parametres.zoomPlus);
		optionZoomer.addActionListener(controleur.getControleurMenuAffichage());
		optionZoomer.setEnabled(false);
		this.add(optionZoomer);

		optionDezoomer = new JMenuItem(Parametres.zoomMoins);
		optionDezoomer.setActionCommand(Parametres.zoomMoins);
		optionDezoomer.addActionListener(controleur.getControleurMenuAffichage());
		// FIXME accelerator -
		optionDezoomer.setAccelerator(KeyStroke.getKeyStroke("-"));
		optionDezoomer.setEnabled(false);
		this.add(optionDezoomer);

		this.addSeparator();

		sommet = new JCheckBoxMenuItem(Parametres.affichageSommet);
		sommet.setActionCommand(Parametres.affichageSommet);
		sommet.addActionListener(controleur.getControleurMenuAffichage());
		sommet.setSelected(true);
		sommet.setEnabled(false);
		this.add(sommet);

		nomSommet = new JCheckBoxMenuItem(Parametres.affichageNomSommet);
		nomSommet.setActionCommand(Parametres.affichageNomSommet);
		nomSommet.addActionListener(controleur.getControleurMenuAffichage());
		nomSommet.setEnabled(false);
		this.add(nomSommet);

		aretes = new JCheckBoxMenuItem(Parametres.affichageArete);
		aretes.setActionCommand(Parametres.affichageArete);
		aretes.addActionListener(controleur.getControleurMenuAffichage());
		aretes.setSelected(true);
		aretes.setEnabled(false);
		this.add(aretes);

		dijkstra = new JCheckBoxMenuItem(Parametres.affichageDijkstra);
		dijkstra.setActionCommand(Parametres.affichageDijkstra);
		dijkstra.addActionListener(controleur.getControleurMenuAffichage());
		dijkstra.setSelected(true);
		dijkstra.setEnabled(false);
		this.add(dijkstra);

		kruskal = new JCheckBoxMenuItem(Parametres.affichageKruskal);
		kruskal.setActionCommand(Parametres.affichageKruskal);
		kruskal.addActionListener(controleur.getControleurMenuAffichage());
		kruskal.setSelected(true);
		kruskal.setEnabled(false);
		this.add(kruskal);

		this.addSeparator();

		creerMenuLookAndFeel();

	}

	private void creerMenuLookAndFeel() {

		JMenu jm = new JMenu();
		jm.setText("Look and Feel");
		ButtonGroup bg1 = new ButtonGroup();
		listeLAF = UIManager.getInstalledLookAndFeels();
		final JRadioButtonMenuItem[] tJrbi = new JRadioButtonMenuItem[listeLAF.length];
		for (int i = 0; i < listeLAF.length; i++) {
			tJrbi[i] = new JRadioButtonMenuItem();
			tJrbi[i].setText(listeLAF[i].getName());
			tJrbi[i].setSelected(i == 0);
			bg1.add(tJrbi[i]);
			tJrbi[i].addItemListener(new java.awt.event.ItemListener() {
				// Changer le Look And Feel
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					int i = 0;
					for (i = 0; i < tJrbi.length; ++i)
						if (tJrbi[i] == e.getSource())
							break;
					try {
						UIManager.setLookAndFeel(MenuAffichage.this.listeLAF[i].getClassName());
						SwingUtilities.updateComponentTreeUI(fenetre);
						// System.out.println(listeLAF[i]);
						// FIXME selectionner nimbus par defaut
					} catch (Exception ex1) {
						System.out.println(ex1);
					}
				}
			});
			jm.add(tJrbi[i]);
		}

		this.add(jm);
	}

	
	public void updateNouveauGraphe() {
		optionCentrage.setEnabled(true);
		optionZoomer.setEnabled(true);
		optionDezoomer.setEnabled(true);
		sommet.setEnabled(true);
		nomSommet.setEnabled(true);
		aretes.setEnabled(true);
		dijkstra.setEnabled(true);
		kruskal.setEnabled(true);
	}

	public void updateFermerGraphe() {
		optionCentrage.setEnabled(false);
		optionZoomer.setEnabled(false);
		optionDezoomer.setEnabled(false);
		sommet.setEnabled(false);
		nomSommet.setEnabled(false);
		aretes.setEnabled(false);
		dijkstra.setEnabled(false);
		kruskal.setEnabled(false);		
	}
}
