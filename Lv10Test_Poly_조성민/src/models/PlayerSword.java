package models;

public class PlayerSword extends Player implements Special{

	int skill;
	
	public PlayerSword(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		this.skill = 5;
	}
	
}
