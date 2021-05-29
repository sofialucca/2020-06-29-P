package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private Graph<Match, DefaultWeightedEdge> grafo;
	private Map<Integer, Match> idMap;
	private PremierLeagueDAO dao;
	private List<Adiacenti> coppieMatch;
	private List<Match> percorsoOttimo;
	private int costoOttimo;
	
	public Model() {
		dao = new PremierLeagueDAO();
	}
	
	public void creaGrafo(int mese, int minuti) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		idMap = new HashMap<>();
		
		dao.setMatches(idMap, mese);
		Graphs.addAllVertices(grafo, idMap.values());
		coppieMatch =  dao.getArchi(idMap, mese, minuti);
		
		for(Adiacenti a : coppieMatch) {
			Graphs.addEdge(grafo, a.getM1(), a.getM2(), a.getPeso());
		}
		
	}
	
	public List<Match> getVertex() {
		return new ArrayList<>(grafo.vertexSet());
	}
	
	public int getEdgeSize() {
		return grafo.edgeSet().size();
	}
	
	public List<Adiacenti> getConnessioni(){
		List<Adiacenti> result = new ArrayList<Adiacenti>();
		for(Adiacenti a : this.coppieMatch) {
			if(a.getPeso() == this.coppieMatch.get(0).getPeso()) {
				result.add(a);
			}else {
				break;
			}
		}
		return result;
	}
	
	public List<Match> cercaPercorso(Match m1, Match m2){
		percorsoOttimo = new ArrayList<>();
		costoOttimo = 0;
		List<Match> parziale = new ArrayList<>();
		parziale.add(m1);
		cerca(parziale,m1, m2);
		return percorsoOttimo;
	}

	private void cerca(List<Match> parziale,Match partenza, Match destinazione) {
		int pesoParziale = calcoloPeso(parziale);
		if(partenza.equals(destinazione)) {
			if(pesoParziale > costoOttimo) {
				costoOttimo = pesoParziale;
				percorsoOttimo = new ArrayList<>(parziale);
			}
			return;
		}
		
		for(Match m: Graphs.neighborListOf(grafo, partenza)) {
			if(!parziale.contains(m)) {
				if((m.teamAwayID != partenza.teamAwayID || m.teamHomeID != partenza.teamHomeID) && (m.teamAwayID != partenza.teamHomeID || m.teamHomeID != partenza.teamAwayID)) {
					parziale.add(m);
					cerca(parziale, m, destinazione);
					parziale.remove(m);
				}
			}
		}
		
	}

	public int calcoloPeso(List<Match> parziale) {
		int costo = 0;
		for (int i = 0;i<parziale.size()-1;i++) {
			DefaultWeightedEdge e = grafo.getEdge(parziale.get(i), parziale.get(i+1));
			costo += (int) grafo.getEdgeWeight(e);
		}
		return costo;
	}
	
}
