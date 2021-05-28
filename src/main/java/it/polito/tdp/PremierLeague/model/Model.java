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
	
}
