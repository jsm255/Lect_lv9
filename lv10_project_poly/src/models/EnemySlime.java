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
		if(this.recovery) {
			System.out.println("슬라임은 빠르게 자신의 상처를 회복하고 있다!");
			super.changeNowHp(super.maxHp);
		}
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
	
	@Override
	public String toString() {
		String str = "";
		
		str += "\t\t\t\t 슬라임\n";
		str += "\t\t\t\t  └─ Hp " + String.valueOf(super.nowHp) +
				"/" + String.valueOf(super.maxHp)+"\n";
		str += "\t\t\t\t  └─ Atk " + String.valueOf(super.atk) + 
				" Def " + String.valueOf(super.def)+"\n";
		if(this.recovery) str += "\t\t\t\t  ★ 막강한 회복력\n";
		else str += "\t\t\t\t ☆ 깊은 상처 " + String.valueOf(this.turn) +"턴\n";
		if(super.debuff != 0) str += 
		return str;
	}

}
