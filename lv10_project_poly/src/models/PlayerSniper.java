package models;

public class PlayerSniper extends Player {

	public PlayerSniper(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		super.skillName = "관통의 일격";
		// TODO Auto-generated constructor stub
	}

	@Override
	public int specialty() {
		return 0;
		
	}

}
