package fr.enac.iessa16.cablage.model;

import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;

public class MaClasse implements AStarAdmissibleHeuristic<Sommet>
{

	@Override
	public double getCostEstimate(Sommet origine, Sommet destination) {
		// TODO Auto-generated method stub
		return origine.Calculdistance(destination);
	}
	


	

}
