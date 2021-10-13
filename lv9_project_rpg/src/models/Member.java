package models;

import java.util.Random;

public class Member {
	private String name;
	private int lv;
	private int hp;
	private int atk;
	private int def;
	private int weaponIdx;
	private int armorIdx;
	private int ringIdx;
	private boolean party;
	
	private Random ran = new Random();
	
	public Member(String name, int hp, int atk, int def) {
		this.name = name;
		this.lv = 1;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.weaponIdx = -1;
		this.armorIdx = -1;
		this.ringIdx = -1;
		this.party = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLv() {
		return this.lv;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public int getWeaponIdx() {
		return this.weaponIdx;
	}
	
	public int getArmorIdx() {
		return this.armorIdx;
	}
	
	public int getRingIdx() {
		return this.ringIdx;
	}
	
	public boolean getParty() {
		return this.party;
	}
}
