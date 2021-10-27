package controllers;

public class GameController {
	
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
		
		bc.실험용메서드();
	}
	
	
}
