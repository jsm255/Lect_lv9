package models;

public class Unit {
	String name;
	int maxHp;
	int nowHp;
	int atk;
	int def;
	
	public Unit(String name, int hp, int atk, int def) {
		this.name = name;
		this.maxHp = hp;
		this.nowHp = hp;
		this.atk = atk;
		this.def = def;
	}
}
