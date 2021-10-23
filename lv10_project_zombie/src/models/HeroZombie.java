package models;

public class HeroZombie extends Unit implements Move, Special{

	int cooldown = 4;
	
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
				super.getName(), super.getLv(), super.getNowHp(), super.getMaxHp(),
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
	
	public void printCooldown() {
		System.out.print("\t\t\t\t분노 게이지 [");
		for(int i = 4; i>0; i--) {
			if(i > this.cooldown) System.out.print("■");
			else System.out.print(" ");
		}
		System.out.println("]");
	}

}
