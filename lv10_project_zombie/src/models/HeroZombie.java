package models;

public class HeroZombie extends Unit implements Move, Special{

	int cooldown = 5;
	
	public HeroZombie(String name, int lv, int hp, int atk, int def, int floor) {
		super(name, lv, hp, atk, def, floor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Unit opponent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return String.format("\t\t\t\t%s / lv %d\n\t\t\t\t └─ HP %d/%d  ATK %d DEF %d",
				super.getName(), super.getLv(), super.getMaxHp(), super.getNowHp(),
				super.getAtk(), super.getDef());
	}

	@Override
	// 필살기가 쿨타임이 되었는가? 안 되었는가?
	public boolean specialty() {
		if(this.cooldown == 0) {
			this.cooldown = 3;
			return true;
		}
		else {
			this.cooldown --;
			return false;
		}
	}

}
