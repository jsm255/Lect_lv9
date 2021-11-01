package models;

public class Shield extends Weapon{

	public Shield(String name, int atk, int def, int price) {
		super(3, name, atk, def, price);
		// TODO Auto-generated constructor stub
	}

	public Shield(String name, int atk, int def, int price, int have, int equipped) {
		super(1, name, atk, def, price, have, equipped);
	}
}
