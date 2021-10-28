package models;

public class EnemyBandit extends Unit{

	public EnemyBandit(String name, int hp, int atk, int def) {
		super(name, hp, (atk * 4), def);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		String str = "";
		
		str += "\t\t\t\t 도적\n";
		str += "\t\t\t\t  └─ Hp " + String.valueOf(super.nowHp) +
				"/" + String.valueOf(super.maxHp)+"\n";
		str += "\t\t\t\t  └─ Atk " + String.valueOf(super.atk) + 
				" Def " + String.valueOf(super.def)+"\n";
		if(this.recovery) str += "\t\t\t\t  ★ 막강한 회복력\n";
		else str += "\t\t\t\t ☆ 깊은 상처 " + String.valueOf(this.turn) +"턴";
		return str;
	}

}
