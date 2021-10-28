package models;

public class PlayerSniper extends Player implements Special{

	public PlayerSniper(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		super.skillName = "관통의 일격";
		// TODO Auto-generated constructor stub
	}

	@Override
	public int specialty() {
		if(super.skill >= 1) {
			System.out.println("특수 탄환을 발사하여 상대에게 치명적인 관통상을 입혔다!");
			
			super.skill --;
			return 2;
		}
		else {
			System.out.println("스킬 사용 가능 횟수를 모두 소진했다!");
			return 0;
		}		
	}

}
