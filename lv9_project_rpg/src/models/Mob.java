package models;

public class Mob {
	private String name;
	private int lv;
	private int maxHp;
	private int nowHp;
	private int atk;
	private int def;
	private int regenerate;
	private int gold;
	
	public Mob(String name, int lv, int maxHp, int atk, int def, int regen, int gold) {
		this.name = name;
		this.lv = lv;
		this.maxHp = maxHp;
		this.nowHp = maxHp;
		this.atk = atk;
		this.def = def;
		this.regenerate = regen;
		this.gold = gold;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLv() {
		return this.lv;
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	public int getNowHp() {
		return this.nowHp;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public int getRegen() {
		return this.regenerate;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public void setNowHp(int change) {
		this.nowHp += change;
	}
}
