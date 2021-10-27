package models;

public class PlayerSword extends Player {

	public PlayerSword(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		super.skillName = "깊은 상처";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void specialty() {
		// TODO Auto-generated method stub

		System.out.println("상대가 회복을 할 수 없게 깊은 상처를 냈다!");
	}

}
