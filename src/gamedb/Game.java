package gamedb;

import java.util.HashMap;

public class Game {
	
	int id;
	public String name;
	public HashMap<Integer, String> games;
	
	public Game() {
	}
	
	public Game(int id, HashMap<Integer, String> games) {
		this.id = id;
		this.games = games;
	}
}
