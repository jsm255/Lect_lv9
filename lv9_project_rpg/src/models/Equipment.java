package models;

public class Equipment {
	private int sort;		// 1 = 무기 2 = 방어구 3 = 장신구
	private String name;
	private int hp;
	private int atk;
	private int def;	
	
	public Equipment(int sort, String name, int hp, int atk, int def) {
		this.sort = sort;
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
	}
	
	public int getEquipSort() {
		return this.sort;
	}
	
	public String getEquipName() {
		return this.name;
	}
	
	public int getEquipHp() {
		return this.hp;
	}
	
	public int getEquipAtk() {
		return this.atk;
	}
	
	public int getEquipDef() {
		return this.def;
	}
}
