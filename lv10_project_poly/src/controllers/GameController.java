package controllers;

import java.util.Scanner;

public class GameController {
	
	public static Scanner scan = new Scanner(System.in);
	
	private static GameController instance = new GameController();
	private GameController() {}
	public static GameController getGC() {
		return instance;
	}
	
	public void run() {
		BattleController bc = BattleController.getBC();
		UnitController uc = UnitController.getUC();
		
		uc.addPlayer();
		uc.generateEnemy();
		
		bc.battlePhase();
	}
	
	
}
