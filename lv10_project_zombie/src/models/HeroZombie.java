package models;

public class HeroZombie extends Unit implements Move, Special{

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
	public void specialty() {
		// TODO Auto-generated method stub
		
	}

}
