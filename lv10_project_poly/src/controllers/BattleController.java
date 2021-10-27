package controllers;

public class BattleController {
	
	private static BattleController instance = new BattleController();
	private BattleController() {}
	public static BattleController getBC() {
		return instance;
	}
	
	UnitController uc = UnitController.getUC();
	
	public void method() {
	}
	
}
