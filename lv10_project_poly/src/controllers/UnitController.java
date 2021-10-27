package controllers;

import java.util.ArrayList;

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
	private ArrayList<Unit> EnemyList = new ArrayList<>();
	
	public void addPlayer() {
		this.playerList.add(new PlayerSword("검", 150, 10, 10));
		this.playerList.add(new PlayerSniper("총", 150, 18, 3));
		this.playerList.add(new PlayerDefender("방패", 150, 6, 20));
	}
	
	public void generateEnemy() {
		this.EnemyList.add(new EnemySlime("슬라임", 150, 5, 5));
		
	}
	
	public Player getPlayer(int idx) {
		return this.playerList.get(idx);
	}
	
	public Unit getEnemy(int idx) {
		return this.EnemyList.get(idx);
	}
}
