package models;

public class EnemyGolem extends Unit implements Debuffable{
	
	public EnemyGolem(String name, int hp, int atk, int def) {
		super(name, hp, atk, (def * 4));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		String str = "";
		
		str += "\t\t\t\t 골렘\n";
		str += "\t\t\t\t  └─ Hp " + String.valueOf(super.nowHp) +
				"/" + String.valueOf(super.maxHp)+"\n";
		str += "\t\t\t\t  └─ Atk " + String.valueOf(super.atk) + 
				" Def " + String.valueOf(super.def)+"\n";
		if(super.debuff != 0) {
			if(super.debuff == 2) 
				str += "\t\t\t\t ☆ 관통상 " + String.valueOf(super.debuffTurn) + "턴\n";
		}
		return str;
	}

}
