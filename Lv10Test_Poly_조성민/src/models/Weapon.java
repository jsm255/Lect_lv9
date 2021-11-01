package models;

public class Weapon {
	int cat;
	String name;
	int atk;
	int def;
	int price;
	int have;
	int equip;
	
	public Weapon(int cat, String name, int atk, int def, int price) {
		this.cat = cat;
		this.name = name;
		this.atk = atk;
		this.def = def;
		this.price = price;
		this.have = 0;
		this.equip = 0;
	}
	
	public int getCat() {
		return this.cat;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public int getHave() {
		return this.have;
	}
	
	public int getEquip() {
		return this.equip;
	}
	
	public void changeHave(int change) {
		this.have += change;
	}
	
	public void chagneEquip(int change) {
		this.equip += change;
	}
}
