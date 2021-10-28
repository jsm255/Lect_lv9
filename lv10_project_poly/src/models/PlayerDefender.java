package models;

public class PlayerDefender extends Player {
	
	private int defTemp;
	private int defTurn;
	

	public PlayerDefender(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		super.skillName = "뚫리지 않는 방패";
		// TODO Auto-generated constructor stub
		this.defTemp = super.def;
	}

	@Override
	public int specialty() {
		if(super.skill >= 1) {
			System.out.println("상대의 공격을 더욱 효과적으로 막아낼 준비가 되었다!");
			System.out.println("방어력이 2배 상승!");
			
			super.changeDef(def);
			
			this.defTurn = 3;
			
			super.skill --;
			return 3;
		}
		else {
			System.out.println("스킬 사용 가능 횟수를 모두 소진했다!");
			return 0;
		}
	}
	
	public int getDefTurn() {
		return this.defTurn;
	}
	
	public void changeDefTurn() {
		this.defTurn --;
	}
	
	public void specialEnd() {
		super.changeDef(-this.defTemp);
	}

}
