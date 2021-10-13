package models;

import java.util.Random;

import controllers.MemberController;

public class Game {
	
	public static int gold = 50000;
	
	public static Random ran = new Random();
	
	private MemberController mc = MemberController.instance;
	
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
	}
	
}
