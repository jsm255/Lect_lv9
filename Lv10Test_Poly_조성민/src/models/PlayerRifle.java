package models;

public class PlayerRifle extends Player implements Special{
	
	// 관통 - 방어력 감소
	
	public PlayerRifle(String name, int hp, int atk, int def) {
		super(name, hp, atk, def, "꿰뚫기");
	}
	
}
