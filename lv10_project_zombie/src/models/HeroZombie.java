package models;

public class HeroZombie extends Unit implements Move, Special{

	int cooldown = 4;
	
	public HeroZombie(String name, int lv, int hp, int atk, int def, int floor) {
		super(name, lv, hp, atk, def, floor);
	}

	@Override
	public int attack(Unit opponent) {
		System.out.println(super.getName() + "의 공격!");
		int zombieDmg = super.getAtk()-opponent.getDef();
		if(zombieDmg <= 0) zombieDmg = 1;
		
		System.out.println(opponent.getName()+"에게 "+zombieDmg+"의 데미지!");
		
		return zombieDmg;
		
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
