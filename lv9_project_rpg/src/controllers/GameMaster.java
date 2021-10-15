package controllers;

import java.util.Random;
import java.util.Scanner;

public class GameMaster {
	
	private boolean running = true;
	
	public static int gold = 50000;
	
	public static int partyMembers = 0;
	
	public static Random ran = new Random();
	public static Scanner scan = new Scanner(System.in);
	
	private MemberController mc = MemberController.instance;
	private ShopController sc = ShopController.instance;
	
	public void run() {
		임시멤버추가용메서드();
		sc.setBasicShopEquipments();
		while(this.running) {
			printMainMenu();
			selectMainMenu();
		}
	}
	
	public void 임시멤버추가용메서드() {
		mc.newMember();
		mc.newMember();
		mc.newMember();
	}
	
	public void 동작확인용메서드() {
		mc.newMember();
		
		System.out.println(mc.getName(0));
		System.out.println(mc.getLv(0));
		System.out.println(mc.getHp(0));
		System.out.println(mc.getAtk(0));
		System.out.println(mc.getDef(0));
		System.out.println(mc.getParty(0));
		
		sc.setBasicShopEquipments();
		
		for(int i = 0; i<sc.getEquipSize(); i++) {
			System.out.print(sc.getEquipSort(i)+" - ");
			System.out.print(sc.getEquipName(i)+") ");
			System.out.print(sc.getEquipHp(i)+"/");
			System.out.print(sc.getEquipAtk(i)+"/");
			System.out.println(sc.getEquipDef(i));
		}
	}
	
	public void printMainMenu() {
		System.out.println("1. 길드 진입\t2. 상점 진입\t3. 인벤토리 보기");
		System.out.println("4. 진행 내역 저장\t5. 불러오기\t0. 게임 종료");
	}
	
	public void selectMainMenu() {
		System.out.print("메뉴 선택 : ");
		String input = GameMaster.scan.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(sel >= 0 && sel <= 5) {
				if(sel == 1) mc.printGuildMenu();
				else if(sel == 2) sc.printShopMenu();
				else if(sel == 3) sc.selectInventory();
				else if(sel == 4) {
					
				}
				else if(sel == 5) {
					
				}
				else if(sel == 0) {
					this.running = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러!");
		}
		
	}
	
}
