package controllers;

public class BattleController {
	
	private static BattleController instance = new BattleController();
	private BattleController() {}
	public static BattleController getBC() {
		return instance;
	}
	
	
	public void 실험용메서드() {
		UnitController uc = UnitController.getUC();
		System.out.println("전투 시작!");
		System.out.println(uc.getEnemy(0));
		System.out.println(uc.getPlayer(0));
		
	}
	
}
