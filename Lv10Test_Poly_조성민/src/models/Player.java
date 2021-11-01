package models;

public class Player extends Unit {
	
	protected String equip;
	protected String skill;
	protected int skillCnt;

	public Player(String name, int hp, int atk, int def, String skill) {
		super(name, hp, atk, def);
		this.equip = "x";
		this.skill = skill;
		this.skillCnt = 5;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		str += super.name + "\n";
		str += " └─ Hp " + super.nowHp + "/" + super.maxHp + "\n";
		str += " └─ Atk " + super.atk + " Def " + super.def + "\n";
		str += " └─ ★ " + this.skill + " ";
		for(int i = 1; i<=5; i++) {
			if(i <= skillCnt) str += "◆ ";
			else str += "◇ ";
		}
		
		return str;
	}
	
	public String getEquip() {
		return this.equip;
	}
	
	public String getSkillName() {
		return this.skill;
	}
	
	public int getSkillCnt() {
		return this.skillCnt;
	}
	
}
