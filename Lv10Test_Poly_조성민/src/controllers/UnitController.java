package controllers;

import java.util.ArrayList;

import models.Enemy;
import models.EnemySlime;
import models.Player;
import models.PlayerRifle;
import models.PlayerSword;

public class UnitController {
	
	private static UnitController instance = new UnitController();
	private UnitController() {}
	public static UnitController getInstance() {
		return instance;
	}
	
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Enemy> enemys = new ArrayList<>();
	
	public void generateUnits() {
		this.players.add(new PlayerSword("검사", 200, 10, 3));
		this.players.add(new PlayerRifle("사수", 200, 12, 1));	
		
		this.enemys.add(new EnemySlime("슬라임", 180, 10, 1));
	}
	
	public void 디버그용출력메서드() {
		for(Player p : this.players) System.out.println(p);
		for(Enemy e : this.enemys) System.out.println(e);
	}
	
	
	
	
}
