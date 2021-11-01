package models;

public class Enemy extends Unit {
	
	int debuff;

	public Enemy(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		this.debuff = 0;
	}

}
