package models;

public class PlayerDefender extends Player {

	public PlayerDefender(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		super.skillName = "뚫리지 않는 방패";
		// TODO Auto-generated constructor stub
	}

	@Override
	public int specialty() {
		return 0;
		
	}

}
