package fr.enac.iessa16.cablage.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

import javax.swing.JInternalFrame;

import com.sun.prism.Graphics;

import java.awt.*;
import java.awt.print.*;

public class  Imprimer {
   
	public Imprimer (DessinDuGrapheParDefaut dessin) { 
	   
	// Récupère un PrinterJob
	      PrinterJob job = PrinterJob.getPrinterJob();
	      // Définit son contenu à imprimer
	      job.setPrintable(dessin);
	      // Affiche une boîte de choix d'imprimante
	      if (job.printDialog()){
	         try {
	            // Effectue l'impression
	            job.print();
	         } catch (PrinterException ex) {
	            ex.printStackTrace();
	         }
	      }
	   
	   
   }
   
  
}
