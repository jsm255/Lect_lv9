package controllers;

import models.EnemySlime;
import models.Player;
import models.Unit;

public class BattleController {
	
	private static BattleController instance = new BattleController();
	private BattleController() {}
	public static BattleController getBC() {
		return instance;
	}
	
	UnitController uc = UnitController.getUC();
	GameController gc = GameController.getGC();
	
	private Player player;
	private Unit enemy;
	
	public void 실험용메서드() {
		System.out.println("전투 시작!");
		System.out.println(uc.getEnemy(0));
		System.out.println(uc.getPlayer(0));
	}
	
	public void battlePhase() {
		Unit enemy = uc.getEnemy(0);
		Player player = uc.getPlayer(0);
		System.out.println(uc.getEnemy(0));
		System.out.println(uc.getPlayer(0));
		
		System.out.println("1. 공격  2. 스킬 사용  3. 회복");
		System.out.print("행동 선택 : ");
		String input = gc.scan.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(sel >= 1 && sel <= 3) {
				if(sel == 1) {
					int dmg = player.attack(enemy);
					
					enemy.changeNowHp(-dmg);
					System.out.println(enemy.getName()+"에게 "+dmg+"의 데미지!");
					
				}
				else if(sel == 2) {
					int special = player.specialty();
					
					if(special > 0) {
						if(special == 1) {
							if(enemy instanceof EnemySlime)
								((EnemySlime) enemy).noRecovery();
						}
						else if(special == 2) {
							
						}
						else if(special == 3) {
							
						}
					}
				}
				else if(sel == 3) {
					
				}
			}
		} catch (Exception e) {
			System.out.println("뭘 해야할지 모르겠다!");
		} finally {
			if(enemy instanceof EnemySlime)
				((EnemySlime) enemy).noRecovery();
			enemy.attack(player);
		}
	}
	
}
