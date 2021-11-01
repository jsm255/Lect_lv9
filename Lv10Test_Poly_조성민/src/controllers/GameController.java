package controllers;

import java.util.Random;
import java.util.Scanner;

public class GameController {
	
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	public static int gold = 20000;
	public static int battleRound = 1;
	public static int playerNum = -1;
	
	private static GameController instance = new GameController();
	private GameController() {}
	public static GameController getInstance() {
		return instance;
	}
	
	public void run() {
		UnitController uc = UnitController.getInstance();
		BattleController bc = BattleController.getInstance();
		
		uc.generateUnits();
		uc.shuffleEnemys();
		
		while(battleRound <= 3) {
			bc.standbyPhase();
		}
		
	}

}
