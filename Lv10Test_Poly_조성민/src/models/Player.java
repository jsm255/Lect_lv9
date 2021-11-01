package models;

public class Player extends Unit {
	
	String equip;

	public Player(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		this.equip = "x";
	}
	
}
