package controllers;

import java.util.ArrayList;

import models.Game;
import models.Member;

public class MemberController {
	
	private ArrayList <Member> members = new ArrayList<>();
	
	public static MemberController instance = new MemberController();
	
	private MemberController() {}
	
	public void newMember() {
		int hp = randomHp();
		this.members.add(new Member(randomName(), hp, randomAtk(hp), randomDef(hp)));
	}
	
	private String randomName() {
		while(true) {
			char first = (char)(Game.ran.nextInt(26) + 65);
			char second = (char)(Game.ran.nextInt(26) + 97);
			char third = (char)(Game.ran.nextInt(26) + 97);
			
			String name = "";
			name += first;
			name += second;
			name += third;
			
			if(checkName(name)) continue;
			else return name;
		}
	}
	
	private boolean checkName(String name) {
		for(Member temp : this.members) {
			if(temp.getName().equals(name)) return true;
		}
		return false;
	}
	
	private int randomHp() {
		int hp = Game.ran.nextInt(51) + 50;	// 50~100
		
		return hp;
	}
	
	private int randomAtk(int hp) {
		if(hp <= 60) {
			int atk = Game.ran.nextInt(4) + 7;	// 7~10;
			
			return atk;
		}
		else if(hp > 60 && hp <= 80) {
			int atk = Game.ran.nextInt(3) + 4;	// 4~6;
			
			return atk;
		}
		else {
			int atk = Game.ran.nextInt(3) + 1;	// 1~3;
			
			return atk;
		}
	}
	
	private int randomDef(int hp) {
		if(hp <= 60) {
			int def = Game.ran.nextInt(3);		// 0~2;
			
			return def;
		}
		else if(hp > 60 && hp <= 80) {
			int def = Game.ran.nextInt(4) + 2;		// 2~5;
			
			return def;
		}
		else {
			int def = Game.ran.nextInt(3) + 6;		// 6~8;
			
			return def;
		}
	}
	
	public String getName(int idx) {
		return this.members.get(idx).getName();
	}
	
	public int getLv(int idx) {
		return this.members.get(idx).getLv();
	}
	
	public int getHp(int idx) {
		return this.members.get(idx).getHp();
	}
	
	public int getAtk(int idx) {
		return this.members.get(idx).getAtk();
	}
	
	public int getDef(int idx) {
		return this.members.get(idx).getDef();
	}
	
	public int getWeaponIdx(int idx) {
		return this.members.get(idx).getWeaponIdx();
	}
	
	public int getArmorIdx(int idx) {
		return this.members.get(idx).getArmorIdx();
	}
	
	public int getRingIdx(int idx) {
		return this.members.get(idx).getRingIdx();
	}
	
	public boolean getParty(int idx) {
		return this.members.get(idx).getParty();
	}
	
}
