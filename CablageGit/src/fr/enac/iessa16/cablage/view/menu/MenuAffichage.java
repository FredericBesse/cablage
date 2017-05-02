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
import fr.enac.iessa16.cablage.view.Parametres;

@SuppressWarnings("serial")
public class MenuAffichage extends JMenu {

	private LookAndFeelInfo[] listeLAF;

	public MenuAffichage(Controleur controleur) {
		super(Parametres.menuAffichage);

		JMenuItem optionCentrage = new JMenuItem(Parametres.centrage);
		this.add(optionCentrage);
		optionCentrage.setActionCommand(Parametres.centrage);
		optionCentrage.addActionListener(controleur.getControleurMenuAffichage());

		this.addSeparator();

		JMenuItem optionZoomer = new JMenuItem(Parametres.zoomPlus);
		this.add(optionZoomer);
		optionZoomer.setActionCommand(Parametres.zoomPlus);
		optionZoomer.addActionListener(controleur.getControleurMenuAffichage());

		JMenuItem optionDezoomer = new JMenuItem(Parametres.zoomMoins);
		optionDezoomer.setActionCommand(Parametres.zoomMoins);
		optionDezoomer.addActionListener(controleur.getControleurMenuAffichage());
		optionCentrage.setAccelerator(KeyStroke.getKeyStroke("-"));
		this.add(optionDezoomer);

		this.addSeparator();

		JCheckBoxMenuItem sommet = new JCheckBoxMenuItem(Parametres.affichageSommet);
		sommet.setActionCommand(Parametres.affichageSommet);
		sommet.addActionListener(controleur.getControleurMenuAffichage());
		sommet.setSelected(true);
		this.add(sommet);

		JCheckBoxMenuItem nomSommet = new JCheckBoxMenuItem(Parametres.affichageNomSommet);
		nomSommet.setActionCommand(Parametres.affichageNomSommet);
		nomSommet.addActionListener(controleur.getControleurMenuAffichage());
		this.add(nomSommet);

		JCheckBoxMenuItem aretes = new JCheckBoxMenuItem(Parametres.affichageArete);
		aretes.setActionCommand(Parametres.affichageArete);
		aretes.addActionListener(controleur.getControleurMenuAffichage());
		aretes.setSelected(true);
		this.add(aretes);

		JCheckBoxMenuItem dijkstra = new JCheckBoxMenuItem(Parametres.affichageDijkstra);
		dijkstra.setActionCommand(Parametres.affichageDijkstra);
		dijkstra.addActionListener(controleur.getControleurMenuAffichage());
		dijkstra.setSelected(true);
		this.add(dijkstra);

		JCheckBoxMenuItem kruskal = new JCheckBoxMenuItem(Parametres.affichageKruskal);
		kruskal.setActionCommand(Parametres.affichageKruskal);
		kruskal.addActionListener(controleur.getControleurMenuAffichage());
		kruskal.setSelected(true);
		this.add(kruskal);
		
		this.addSeparator();

		creerMenuLookAndFeel();

	}

	private void creerMenuLookAndFeel() {
		
		JMenu jm = new JMenu();
	    jm.setText("Look and Feel");
	      ButtonGroup bg1 = new ButtonGroup();
	      listeLAF = UIManager.getInstalledLookAndFeels ();
	      JRadioButtonMenuItem[] tJrbi = new JRadioButtonMenuItem[listeLAF.length];
	      for (int i=0; i < listeLAF.length; i++) {
	          tJrbi[i] = new JRadioButtonMenuItem();
	          tJrbi[i].setText(listeLAF[i].getName ());
	          tJrbi[i].setSelected(i==0);
	          bg1.add(tJrbi[i]);
	          tJrbi[i].addItemListener(new java.awt.event.ItemListener() {
	             // Changer le Look And Feel
	             public void itemStateChanged(java.awt.event.ItemEvent e) {
	             int i = 0;
	             for(i = 0; i< tJrbi.length; ++i)
	            	 if(tJrbi[i]==e.getSource())
	            		 break;
	                try{
	                   UIManager.setLookAndFeel(MenuAffichage.this.listeLAF[i].getClassName());
	                   SwingUtilities.updateComponentTreeUI(MenuAffichage.this);
	                   //System.out.println(listeLAF[i]);
	                }catch (Exception ex1) {System.out.println(ex1);}
	             }
	          });
	          jm.add(tJrbi[i]);
	      }
		
	      this.add(jm);
	}

}
