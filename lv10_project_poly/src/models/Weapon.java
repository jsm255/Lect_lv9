package models;

public class Weapon {
	protected int category;
	protected String name;
	protected int atk;
	protected int def;
	protected int price;
	
	public Weapon(int cat, String name, int atk, int def, int price) {
		this.category = cat;
		this.name = name;
		this.atk = atk;
		this.def = def;
		this.price = price;
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
}
