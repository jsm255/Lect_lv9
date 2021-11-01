package controllers;

import models.Enemy;
import models.Player;

public class BattleController {
	
	private static BattleController instance = new BattleController();
	private BattleController() {}
	public static BattleController getInstance() {
		return instance;
	}
	
	private Enemy enemy;
	private Player player;
	
	public void standbyPhase() {
		UnitController uc = UnitController.getInstance();
		
		this.enemy = uc.getEnemy();
		
		System.out.println("다음 상대할 적은 " + this.enemy.getName() + "이다!");
		System.out.println(uc.getEnemy());
		
		GameController.scan.next();
	}

}
