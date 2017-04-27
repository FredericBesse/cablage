package fr.enac.iessa16.cablage.model.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;
import fr.enac.iessa16.cablage.view.Fenetre;

/**
 * Classe ConstructeurGrapheFichierTexte
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ConstructeurGrapheFichierTexte {
	
	// Le logger
	private Logger LOGGER = LogManager.getLogger(ConstructeurGrapheFichierTexte.class);
	
	private GrapheTheorique graphe;
	
	// FIXME utiliser une seule liste pour les points et les
	// sommet et informer l'utilisateur s'il y a une différence
	private ArrayList<Sommet> points;
	private ArrayList<Sommet> sommets;
	private ArrayList<Arete> aretes;
	
	private Fenetre fenetre;
	private Modele modele;

	
	/**
	 * Constructeur de la classe ConstructeurGrapheFichierTexte
	 * 
	 * @param fenetre
	 */
	public ConstructeurGrapheFichierTexte(Modele modele, Fenetre fenetre) {
		
		// Initialisation des variables
		this.graphe = null;
		this.sommets = new ArrayList<Sommet>();
		this.points = new ArrayList<Sommet>();
		this.aretes = new ArrayList<Arete>();
		
		this.fenetre = fenetre;
		this.modele = modele;
		
		this.chargerFichier();
	}
	
	
	private void chargerFichier() {
		
		int returnVal;
		File fichier = null;
		String s = null;

		// Création du JFileChooser
		JFileChooser fc = new JFileChooser("file/");
		returnVal = fc.showOpenDialog(fenetre);
		fc.setVisible(true);
		

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			fichier = fc.getSelectedFile();
			
			FileReader fis = null;
			try {
				fis = new FileReader(fichier);
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				modele.erreur("Erreur", "Fichier non trouvé !");
			}
	
			BufferedReader bis = new BufferedReader(fis);
			
			try {
	
				s = bis.readLine();
				
				while ((s = bis.readLine()) != null) {
	
					if (s.startsWith("P")) {
						chargerPoint(s);
					}
					if (s.startsWith("L")) {
						chargerLigne(s);
					}
				}
			} catch (IOException e) {
				//e.printStackTrace();
				modele.erreur("Erreur", "Erreur de lecture du fichier !");
			}
			
			try {
				bis.close();
			} catch (IOException e) {
				//e.printStackTrace();
				modele.erreur("Erreur", "Erreur pendant la fermeture du fichier !");
			}
			graphe = new GrapheTheorique(sommets, aretes);					
		}

	}

	

	private void chargerPoint(String ligne) {

		String[] tab = ligne.split(" ");

		if (tab.length == 4) {
			String[] tab1 = tab[3].split(",");
			double longitude1 = Double.parseDouble(tab1[0]);
			double latitude1 = -Double.parseDouble(tab1[1]);
			String nom1 = tab[1];

			// System.out.println("lat="+latitude1+" lon="+longitude1);

			Sommet sommet1 = new Sommet(longitude1, latitude1, nom1);
			// System.out.println("sommet="+sommets);//+" "+sommets.size());
			points.add(sommet1);
			// arete = null;
		} else {
			LOGGER.warn("Erreur dans le format des données : "+ligne);
		}
	}

	
	private void chargerLigne(String ligne) {

		String tab2[] = ligne.split(" ");
		String tab3[];
		double longitude1, latitude1;
		Sommet sommet1;
		Arete arete;
		Sommet sommetPrecedent = null;
		

		for (int i = 5; i < tab2.length; i++) {

			tab3 = tab2[i].split(",");
			
			if (tab3.length == 2) {

				longitude1 = Double.parseDouble(tab3[0]);
				latitude1 = -Double.parseDouble(tab3[1]);

				sommet1 = new Sommet(longitude1, latitude1, "");

				if (!sommets.contains(sommet1))
					sommets.add(sommet1);

				if (sommetPrecedent != null) {

					if (!sommetPrecedent.equals(sommet1)) {

						arete = new Arete(sommet1, sommetPrecedent, 25);
						
						// TODO ajouter un test pour vérifier l'unicité des aretes (ajouter .equals dans Aretes)
						aretes.add(arete);
						
					} else {
						LOGGER.warn("Erreur dans le format des données (origine=destination -> boucle) : "+ligne);
					}
				}

				sommetPrecedent = sommet1;
			}
		}
	}

	
	public GrapheTheorique getGraphe() {
		return graphe;
	}	
}
