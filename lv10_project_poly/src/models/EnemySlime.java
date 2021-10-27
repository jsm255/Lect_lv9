package models;

public class EnemySlime extends Unit{
	
	boolean recovery = true;
	int turn;

	public EnemySlime(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		// TODO Auto-generated constructor stub
	}
	
	public void noRecovery() {
		this.recovery = false;
		this.turn = 3;
	}
	
	public void specialty() {
		if(this.recovery) super.changeNowHp(super.maxHp);
		else {
			System.out.println("슬라임은 상처때문에 회복이 불가능하다!");
			this.turn --;
			System.out.println("남은 상처 지속시간 : "+this.turn +"턴");
			if(this.turn == 0) {
				System.out.println("슬라임의 상처가 사라졌다!");
				this.recovery = true;
			}
		}
	}

}
