package models;

public class Zombie extends Unit implements Move{

	public Zombie(String name, int lv, int hp, int atk, int def, int floor) {
		super(name, lv, hp, atk, def, floor);
	}

	@Override
	public int attack(Unit opponent) {
		System.out.println(super.getName() + "의 공격!");
		int zombieDmg = super.getAtk()-opponent.getDef();
		if(zombieDmg <= 0) zombieDmg = 1;
		
		System.out.println("잉여 용사에게 "+zombieDmg+"의 데미지!");
		
		return zombieDmg;
	}

	@Override
	public String toString() {
		return String.format("\t\t\t\t%s / lv %d\n\t\t\t\t └─ HP %d/%d  ATK %d DEF %d",
				super.getName(), super.getLv(), super.getNowHp(), super.getMaxHp(),
				super.getAtk(), super.getDef());
	}
	
}
