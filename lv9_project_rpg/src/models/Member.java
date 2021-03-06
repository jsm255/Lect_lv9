package models;

import java.util.Random;

import controllers.GameMaster;

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
	
	public Member(String name, int hp, int atk, int def, boolean party) {
		this.name = name;
		this.lv = 1;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.weaponIdx = -1;
		this.armorIdx = -1;
		this.ringIdx = -1;
		this.party = party;
	}
	
	public Member(String name, int lv, int hp, int atk, int def, int weaponIdx,
			int armorIdx, int ringIdx, boolean party) {
		this.name = name;
		this.lv = lv;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.weaponIdx = weaponIdx;
		this.armorIdx = armorIdx;
		this.ringIdx = ringIdx;
		this.party = party;
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
	
	public void setWeaponIdx(int idx) {
		this.weaponIdx = idx;
	}
	
	public int getArmorIdx() {
		return this.armorIdx;
	}
	
	public void setArmorIdx(int idx) {
		this.armorIdx = idx;
	}
	
	public int getRingIdx() {
		return this.ringIdx;
	}
	
	public void setRingIdx(int idx) {
		this.ringIdx = idx;
	}
	
	public boolean getParty() {
		return this.party;
	}
	
	public void changeParty() {
		this.party = this.party ? false : true;
		if(this.party) GameMaster.partyMembers ++;
		else GameMaster.partyMembers --;
	}
}
