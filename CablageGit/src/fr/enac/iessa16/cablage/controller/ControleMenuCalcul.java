package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;

/**
 * Classe 
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleMenuCalcul implements ActionListener {

	//Attribut de la classe ControleMenuCalcul
	Modele model;
	
	//Constructeur qui prend en argument le model de type DonneesAAfficher
	public ControleMenuCalcul(Modele model) {
		super();
		// TODO Auto-generated constructor stub
		this.model = model;
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		//On stocke l'option cliqué dans une string.
		//Puis on compare cette string aux differentes options possibles
		//Si cette string correspond à l'option "CalculerDikstra" , on appelle le methode de implementé dans la classe DonneesAAfficher. 
		if(s.equals("CalculerDjikstra"))
		{
			
			System.out.println("on va calculer Djikstra");
			model.calculerDjikstra();
		}
		//Idem pour l'option Calculer Kruskal
		if(s.equals("CalculerKruskal"))
		{
			System.out.println("on va calculer Kruskal");
			//On appelle la methode calculerKruskal implementé dans la classe DonneesAAfficher .
			model.calculerKruskal();
		}

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
