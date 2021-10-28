package models;

public class PlayerSword extends Player {

	public PlayerSword(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		super.skillName = "깊은 상처";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int specialty() {
		if(super.skill >= 1) {
			System.out.println("상대가 회복을 할 수 없게 깊은 상처를 냈다!");
			
			super.skill --;
			return 1;
		}
		else {
			System.out.println("스킬 사용 가능 횟수를 모두 소진했다!");
			return 0;
		}
	}

}
