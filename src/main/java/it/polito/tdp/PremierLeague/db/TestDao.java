package it.polito.tdp.PremierLeague.db;

import java.util.HashMap;
import java.util.Map;

import it.polito.tdp.PremierLeague.model.Match;

public class TestDao {

	public static void main(String[] args) {
		TestDao testDao = new TestDao();
		testDao.run();
	}
	
	public void run() {
		PremierLeagueDAO dao = new PremierLeagueDAO();
//		System.out.println("Players:");
//		System.out.println(dao.listAllPlayers());
//		System.out.println("Actions:");
//		System.out.println(dao.listAllActions());
//		System.out.println("Matches:");
//		System.out.println(dao.listAllMatches());
		Map<Integer,Match> mappa = new HashMap<>();
		dao.setMatches(mappa, 5);
		System.out.println(dao.getArchi(mappa, 5, 10));
	}

}
