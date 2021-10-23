package models;

public abstract class Unit {
	
	private String name;
	private int lv;
	private int maxHp;
	private int nowHp;
	private int atk;
	private int def;
	private int floor;
	
	public Unit(String name, int lv, int hp, int atk, int def, int floor) {
		this.name = name;
		this.lv = lv;
		this.maxHp = hp;
		this.nowHp = hp;
		this.atk = atk;
		this.def = def;
		this.floor = floor;
	}
	
	@Override
	public abstract String toString();
	
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
	
	public int getFloor() {
		return this.floor;
	}
	
	public void changeLv(int change) {
		this.lv += change;
	}
	
	public void changeAtk(int change) {
		this.atk += change;
	}
	
	public void changeDef(int change) {
		this.def += change;
	}
	
	public void changeMaxHp(int change) {
		this.maxHp += change;
	}
	
	public void changeNowHp(int change) {
		this.nowHp += change;
	}
	
	public void changeFloor(int change) {
		this.floor += change;
	}
	
}
