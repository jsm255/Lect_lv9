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
		
		try {
			this.player = uc.getPlayer(GameController.playerNum);
			System.out.println("현재 선택된 플레이어는 " + this.player.getName() + "다.");
			System.out.println(this.player);
			
			System.out.println("1. 전투 시작  2. 플레이어 변경");
			System.out.println("3. 상점  4. 인벤토리  0. 종료");
			int sel = returnSelect(0, 5);
			
			if(sel == 1) {
				
			}
			else if(sel == 2) {
				
			}
			else if(sel == 3) {
				
			}
			else if(sel == 4) {
				
			}
			else if(sel == 0) {
				GameController.playing = false;
				GameController.battleRound = 99;
			}
			
		} catch (Exception e) {
			System.out.println("플레이어를 선택하세요.");
			
			selectPlayer();
		}
	}
	
	private void selectPlayer() {
		UnitController uc = UnitController.getInstance();
		
		for(int i = 0; i<uc.getPlayerSize(); i++) 
			System.out.println((i+1) + " " + uc.getPlayer(i));
		
		GameController.playerNum = returnSelect(1, uc.getPlayerSize());
		
	}
	
	private int returnSelect(int start, int end) {
		String input = GameController.scan.next();
		
		try {
			int sel = -1;
			
			if(start == 0) sel = Integer.parseInt(input);
			else if(start == 1) sel = Integer.parseInt(input)-1;
			
			if(sel >= 0 && sel < end) return sel;
			else{
				System.out.println("입력 값이 이상합니다.");
				return -1;
			}
			
		} catch (Exception e) {
			System.out.println("대충 오류");
			return -1;
		}
	}
}
