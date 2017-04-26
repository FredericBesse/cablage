package fr.enac.iessa16.cablage.model.file;
import java.awt.Component;
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

import javax.swing.JFileChooser;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;


	

	public class ConstructeurGrapheFichierTexte {
		private GrapheTheorique graphe;
		private ArrayList<Sommet> points;
		
		private ArrayList<Sommet> sommets;
		private ArrayList<Arete> aretes;
		
		private Component parent;
		
		//Sommet sommet1;
	
	      // Nous déclarons nos objets en dehors du bloc try/catch
		public  ConstructeurGrapheFichierTexte(Component parent) {
			
			this.parent = parent;
			
			// TODO Auto-generated method stub
			this.sommets = new ArrayList<Sommet>();
			this.points = new ArrayList<Sommet>();
			this.aretes = new ArrayList<Arete>();
			
			
			JFileChooser fc = new JFileChooser("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/");
			
			int returnVal = fc.showOpenDialog(parent);
			fc.setVisible(true);
			 
			File fichier = null;
			
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	                fichier = fc.getSelectedFile();
	                //This is where a real application would open the file.
	               // log.append("Opening: " + file.getName() + "." + newline);
	            } else {
	                //log.append("Open command cancelled by user." + newline);
	            	//FIXME A CORRIGER
	            	return;
	            }
			
			
			//File fichier = new File("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/lfpo_map.txt");
			
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
		//	System.out.println("Chargement de "+s);
			
			while ((s = bis.readLine()) != null) {
				//System.out.println(s);
				
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
	     graphe =  new GrapheTheorique(sommets, aretes);
	    
	   }

		public GrapheTheorique getGraphe() {
			// TODO Auto-generated method stub
			return graphe;
		}
		
		
	
	
	
	
	private void chargerPoint(String s1)
	{
		
		//System.out.println("toto2 "+s1);

		String[] tab = s1.split(" ");
		
		//System.out.println("taille de tab = "+tab.length);
		if(tab.length == 4)
		{
		String[] tab1 = tab[3].split(",");
		double  longitude1 = Double.parseDouble(tab1[0]);
		double latitude1  = -Double.parseDouble(tab1[1]);
		String nom1 = tab[1];
		
		//System.out.println("lat="+latitude1+" lon="+longitude1);
		
		Sommet sommet1 = new Sommet(longitude1 , 
				latitude1 , 
				nom1);
		//System.out.println("sommet="+sommets);//+" "+sommets.size());
		points.add(sommet1);
		//arete = null;
		}
	
	
	
	

}
	
	
	private void chargerLigne(String s1)
	{
		
		String tab2[] = s1.split(" ");
		String tab3[];
		double longitude1 , latitude1;
		Sommet sommet1 ;
		Arete arete;
		Sommet sommetPrecedent = null;
		//System.out.println("Lecture de la ligne contenant "+tab2.length+" champs");
		for(int i =5 ; i<tab2.length;i++)
		{
			
		//	System.out.println("tab2["+i+"] = "+tab2[i]);
			
			tab3 = tab2[i].split(",");
			if (tab3.length == 2) 
			{
			//	System.out.println(" tab3[0] = "+tab3[0]);
				longitude1 = Double.parseDouble(tab3[0]);
				latitude1 = -Double.parseDouble(tab3[1]);
				
				
				
				sommet1 = new Sommet(longitude1,latitude1,"");
				
				if (!sommets.contains(sommet1))
					sommets.add(sommet1);
			
				
				if(sommetPrecedent != null)
				{
					

					if(!sommetPrecedent.equals(sommet1))
					{
						

						arete = new Arete(sommet1 , sommetPrecedent,25);
						aretes.add(arete);
					}
					else 
					{
						System.out.println("erreur : sommet origine = sommet destination donc boucle "+ s1);

					}
				}
					
				
				
				sommetPrecedent = sommet1;
				
				//for(j= i)
				//Arete aretes = new Arete()
			}
			
		}
		//String tab3[] = tab2[5].split(",");
		//double longitude1 = Double.parseDouble(tab2[0]);
		//double latitute1 = Double.parseDouble(tab2[1]);
		//double longitude2
		
		
		
	}
	
	
	
	
	}
