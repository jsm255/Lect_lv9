package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.EnemyBandit;
import models.EnemyGolem;
import models.EnemySlime;
import models.Player;
import models.PlayerDefender;
import models.PlayerSniper;
import models.PlayerSword;
import models.Unit;

public class UnitController {
	
	private static UnitController instance = new UnitController();
	private UnitController() {}
	public static UnitController getUC() {
		return instance;
	}
	
	private ArrayList<Player> playerList = new ArrayList<>();
	private Map<String, Unit> EnemyList = new HashMap<>();
	private String[] encounter = {"슬라임", "골렘", "도적"};
	
	public void addPlayer() {
		this.playerList.add(new PlayerSword("검", 100, 13, 3));
		this.playerList.add(new PlayerSniper("총", 100, 19, 1));
		this.playerList.add(new PlayerDefender("방패", 100, 9, 10));
	}
	
	public void generateEnemy() {
		this.EnemyList.put("슬라임", new EnemySlime("슬라임", 120, 10, 2));
		this.EnemyList.put("도적", new EnemyBandit("도적", 120, 5, 6));
		this.EnemyList.put("골렘", new EnemyGolem("골렘", 120, 10, 5));
	}
	
	public void shuffleEncounter() {
		for(int i = 0; i<100; i++) {
			int n = GameController.ran.nextInt(this.encounter.length);
			
			String temp = this.encounter[0];
			this.encounter[0] = this.encounter[n];
			this.encounter[n] = temp;
		}
	}
	
	public Player getPlayer(int idx) {
		return this.playerList.get(idx);
	}
	
	public Unit getEnemy() {
		return this.EnemyList.get(this.encounter[GameController.battleCnt-1]);
	}
	
	public int getPlayerSize() {
		return this.playerList.size();
	}
}
