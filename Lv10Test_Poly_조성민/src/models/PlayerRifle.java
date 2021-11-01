package models;

public class PlayerRifle extends Player implements Special{
	
	int skill;
	
	public PlayerRifle(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		this.skill = 5;
	}
	
	
}
