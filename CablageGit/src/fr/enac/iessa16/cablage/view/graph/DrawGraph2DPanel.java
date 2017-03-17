package fr.enac.iessa16.cablage.view.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.enac.iessa16.cablage.model.Model;
import fr.enac.iessa16.cablage.model.graph.Arete;
import fr.enac.iessa16.cablage.model.graph.Graphe;
import fr.enac.iessa16.cablage.model.graph.Sommet;
import fr.enac.iessa16.cablage.view.ViewParameters;

public class DrawGraph2DPanel extends JPanel implements MouseListener{
	
	private BufferedImage image = null;
	private Model model;
	
	
	public DrawGraph2DPanel(Model model) {
		
		super();
		
		this.model = model;
		
		this.addMouseListener(this);
		
		//this.setPreferredSize(new Dimension(1100,600));
		
		try {
			image = ImageIO.read(new URL("https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318&markers=color:red%7Clabel:C%7C40.718217,-73.998284&key=AIzaSyABrzv3EoXRNgraD15R12UduLzOBpwg14A"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void paint(Graphics g)
	{
		
		if (ViewParameters.isDrawBackgroundImage) {
			drawBackgroundImage(g);
		}
		
		if (ViewParameters.isDrawVertex) {
			drawVertex(g);
		}
		
		if (ViewParameters.isDrawEdge) {
			drawEdge(g);
		}
		
		
		
		
		
		
		
		
	}
	
	private void drawBackgroundImage(Graphics g) {
		if (image != null) {
			ImageObserver observer = null;
			g.drawImage(image, 0, 0, observer);
		}
	}

	private void drawVertex(Graphics g) {
		
		int i, x, y, size;
		size = ViewParameters.vertexSize;
		
		
		
		Graphe graphe = model.getGraph();
		
		if (graphe != null) {
			ArrayList<Sommet> sommets = graphe.getListeSommets();
			
			/*for (Sommet sommet : sommets) {
				
				g.fillOval(, 50, 50, 50);
				
			}*/
			
			for (i = 0; i < sommets.size(); i++) {
				
				g.setColor(Color.red);
				
				x = this.longitudeToX(sommets.get(i).getLongitude()) - size/2;
				y = this.latitudeToY(sommets.get(i).getLatitude()) - size/2;
				g.fillOval(x, y, size, size);
				
				g.setColor(Color.black);
				g.drawString(sommets.get(i).getNom(), x, y);
				
				//System.out.println("Affichage sommet x="+x+" y="+y);
				
			}
		
		}
		
		//g.drawRect(50, 50, 200, 200);
		
		/*g.fillOval(500, 50, 50, 50);
		g.fillOval(900, 50, 50, 50);
		
		g.setColor(Color.pink);
		g.fillOval(275, 400, 50, 50);
		g.setColor(Color.red);
		g.fillOval(750, 400, 50, 50);*/
		
	}
	
	private void drawEdge(Graphics g) {
		//g.drawLine(50+25, 50+25, 500+25, 50+25);
		
		int i, x1, y1, x2 ,y2;
		
		
		
		Graphe graphe = model.getGraph();
		
		if (graphe != null) {
			ArrayList<Arete> aretes = graphe.getListeAretes();
			
			/*for (Sommet sommet : sommets) {
				
				g.fillOval(, 50, 50, 50);
				
			}*/
			
			Arete arete;
			Sommet sommet1, sommet2;
			
			for (i = 0; i < aretes.size(); i++) {
				
				g.setColor(Color.red);
				
				arete = aretes.get(i);
				sommet1 = arete.getSommet1();
				sommet2 = arete.getSommet2();
				
				x1 = longitudeToX(sommet1.getLongitude());
				y1 = latitudeToY(sommet1.getLatitude());
				
				x2 = longitudeToX(sommet2.getLongitude());
				y2 = latitudeToY(sommet2.getLatitude());
				
				g.drawLine(x1, y1, x2, y2);
				//System.out.println("Affichage sommet x="+x+" y="+y);
				
			}
		
		}
		
		
		
		
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.println("clique "+e.getX()+" "+e.getY());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	private int latitudeToY(double latitude) {
		return (int)latitude;
		//TODO conversion affine
	}
	
	
	private int longitudeToX(double longitude) {
		return (int) longitude;
		//TODO conversion affine
	}
	
	
	
	
	
	
}
