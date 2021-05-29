package it.polito.tdp.PremierLeague.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		
		m.creaGrafo(5, 10);
		System.out.println(m.getConnessioni());
		System.out.println("******");
		System.out.println("match1: "+ m.getVertex().get(0));
		System.out.println("match2: " +  m.getVertex().get(1));
		List<Match> percorso = m.cercaPercorso(m.getVertex().get(0), m.getVertex().get(1));
		System.out.println(percorso);
		System.out.println("percorso: "+ m.calcoloPeso(percorso));
	}

}
