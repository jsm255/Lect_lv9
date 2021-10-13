package models;

import java.util.Random;

import controllers.MemberController;
import controllers.ShopController;

public class Game {
	
	public static int gold = 50000;
	
	public static Random ran = new Random();
	
	private MemberController mc = MemberController.instance;
	private ShopController sc = ShopController.instance;
	
	public void run() {
		동작확인용메서드();
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
	
}
