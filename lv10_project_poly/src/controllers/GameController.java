package controllers;

import java.util.Random;
import java.util.Scanner;

public class GameController {
	
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	public static boolean game = true;
	public static int battleCnt = 1;
	
	private static GameController instance = new GameController();
	private GameController() {}
	public static GameController getGC() {
		return instance;
	}
	
	public void run() {
		BattleController bc = BattleController.getBC();
		UnitController uc = UnitController.getUC();
		
		start();
		
		uc.addPlayer();
		uc.generateEnemy();
		uc.shuffleEncounter();
		
		while(game && battleCnt <= 3) {
			bc.standbyPhase();
			bc.battlePhase();
		}
		ifOver();
	}
	
	private void start() {
		while(true) {
			System.out.println("\t\t ===== 대충 멋진 게임 타이틀 화면 =====");
			System.out.println();
			System.out.println("\t\t   시작하려면 '시작'을 입력하라는 글귀");
			
			System.out.print("입력 : ");
			String input = scan.next();
			
			if(input.equals("시작")) {
				System.out.println("게임 시작!");
				break;
			}			
		}
	}
	
	private void ifOver() {
		if(game == false && battleCnt == 99) System.out.println("사용자가 게임을 종료했습니다.");
		else if(game == false) {
			System.out.println("플레이어 중 한 명이 전투불능이 되었습니다.");
			System.out.println("전부 살아남아야 합니다.");
		}
		else if(battleCnt > 3) {
			System.out.println("모든 적대 유닛을 제거했습니다.");
			System.out.println("당신의 승리!");
		}
	}
	
	
}
