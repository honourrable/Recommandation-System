package gamedb;

import java.util.HashMap;

public class User {
	
	int id;
	public HashMap<Integer, Integer> score;
	
	public User() {
	}

	public User(int id, HashMap<Integer, Integer> score) {
		this.id = id;
		this.score = score;
	}
}
