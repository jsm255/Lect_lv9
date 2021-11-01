package models;

public class Weapon {
	protected int category;
	protected String name;
	protected int atk;
	protected int def;
	protected int price;
	protected int have = 0;
	protected int equipped = 0;
	
	public Weapon(int cat, String name, int atk, int def, int price) {
		this.category = cat;
		this.name = name;
		this.atk = atk;
		this.def = def;
		this.price = price;
	}
	
	public Weapon(int cat, String name, int atk, int def, int price, int have, int equipped) {
		this.category = cat;
		this.name = name;
		this.atk = atk;
		this.def = def;
		this.price = price;
		this.have = have;
		this.equipped = equipped;
	}
	
	public int getWeaponCategory() {
		return this.category;
	}
	
	public String getWeaponName() {
		return this.name;
	}
	
	public int getWeaponAtk() {
		return this.atk;
	}
	
	public int getWeaponDef() {
		return this.def;
	}
	
	public int getWeaponPrice() {
		return this.price;
	}
	
	public int getWeaponHave() {
		return this.have;
	}
	
	public int getWeaponEquipped() {
		return this.equipped;
	}
	
	public void changeWeaponHave(int change) {
		this.have += change;
	}
	
	public void changeWeaponEquipped(int change) {
		this.equipped += change;
	}
}
