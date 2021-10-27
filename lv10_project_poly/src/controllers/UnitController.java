package controllers;

import models.EnemySlime;

public class UnitController {
	
	private static UnitController instance = new UnitController();
	private UnitController() {}
	public static UnitController getUC() {
		return instance;
	}
	
	protected EnemySlime slime = new EnemySlime("슬라임", 150, 5, 5);
}
