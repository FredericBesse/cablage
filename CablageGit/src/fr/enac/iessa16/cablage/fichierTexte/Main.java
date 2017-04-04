package fr.enac.iessa16.cablage.fichierTexte;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
//Packages à importer afin d'utiliser les objets
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import fr.enac.iessa16.cablage.model.Arete;
import fr.enac.iessa16.cablage.model.GrapheTheorique;
import fr.enac.iessa16.cablage.model.Sommet;


	

	public class Main {
		private GrapheTheorique graphe;
		private ArrayList<Sommet> points;
		
		private ArrayList<Sommet> sommets;
		private ArrayList<Arete> arete;
		
		//Sommet sommet1;
	
	      // Nous déclarons nos objets en dehors du bloc try/catch
		public  Main() {
			// TODO Auto-generated method stub
			this.sommets = new ArrayList<Sommet>();
			this.points = new ArrayList<Sommet>();
			this.arete = new ArrayList<Arete>();
			
			File fichier = new File("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/lfpo_map.txt");
			
		FileReader fis = null;
		try {
			fis = new FileReader(fichier);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fichier non trouvé");
		}
		
		BufferedReader bis = new BufferedReader(fis);
		String s = null;
	
		try {
			
			s = bis.readLine();
			System.out.println("Chargement de "+s);
			
			while ((s = bis.readLine()) != null) {
				System.out.println(s);
				
				if(s.startsWith("P"))
				{
				chargerPoint(s);
				}
				if(s.startsWith("L"))
				{
					chargerLigne(s);
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erreur de lecture du fichier");
		}
		System.out.println(s);
	     try {
			bis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fichier non fermé");
		}
	     graphe =  new GrapheTheorique(sommets, arete);
	    
	   }

		public GrapheTheorique getGraphe() {
			// TODO Auto-generated method stub
			return graphe;
		}
		
		
	
	
	
	
	private void chargerPoint(String s1)
	{
		
		//System.out.println("toto2 "+s1);

		String[] tab = s1.split(" ");
		
		System.out.println("taille de tab = "+tab.length);
		if(tab.length == 4)
		{
		String[] tab1 = tab[3].split(",");
		double longitude1 = Double.parseDouble(tab1[0]);
		double latitude1 = Double.parseDouble(tab1[1]);
		String nom1 = tab[1];
		
		//System.out.println("lat="+latitude1+" lon="+longitude1);
		
		Sommet sommet1 = new Sommet(
				longitude1 , 
				latitude1 , 
				nom1);
		//System.out.println("sommet="+sommets);//+" "+sommets.size());
		points.add(sommet1);
		//arete = null;
		}
	
	
	
	

}
	
	private void chargerLigne(String s1)
	{
		
		//String tab2[] = 
		
		
		
		
	}
	
	
	
	
	}
