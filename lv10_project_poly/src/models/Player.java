package models;

public abstract class Player extends Unit{
	
	int skill = 5;
	String skillName;

	public Player(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		// TODO Auto-generated constructor stub
	}
	
	public abstract int specialty();
	
	public int getSkillCnt() {
		return this.skill;
	}
	
	public void changeSkill(int change) {
		this.skill += change;
	}
	
	public void resetSkill() {
		this.skill = 5;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += super.name + "\n";
		str += " └─ Hp "+String.valueOf(super.nowHp) + 
				"/" + String.valueOf(super.maxHp) +"\n";
		str += " └─ Atk " +String.valueOf(super.atk) + 
				" Def " + String.valueOf(super.def)+"\n";
		str += " └─ Skill : " + this.skillName + " / 남은 횟수 : ";
		for(int i = 5; i>=1; i--) {
			if(i <= skill) str += "◆ ";
			else str += "◇ ";
		}
		str += "\n";
		
		return str;
	}
	
	public String getSkillName() {
		return this.skillName;
	}

}
