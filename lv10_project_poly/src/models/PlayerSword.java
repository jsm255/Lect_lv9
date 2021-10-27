package models;

public class PlayerSword extends Unit {

	public PlayerSword(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		// TODO Auto-generated constructor stub
	}
	
	public void specialty() {
		System.out.println("상대가 회복을 할 수 없게 깊은 상처를 냈다!");
	}

}
