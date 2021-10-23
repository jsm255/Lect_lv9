package models;

public class Hero extends Unit implements Move, Special{
	private boolean survive = true;
	private int potion = 3;

	public Hero(String name, int lv, int hp, int atk, int def, int floor) {
		super(name, lv, hp, atk, def, floor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Unit opponent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return String.format("%s / lv %d\n └─ HP %d/%d  ATK %d DEF %d",
				super.getName(), super.getLv(), super.getNowHp(), super.getMaxHp(),
				super.getAtk(), super.getDef());
	}

	@Override
	// 포션이 있는가? 없는가?
	public boolean specialty() {
		
		if(this.potion != 0 && this.getNowHp() < this.getMaxHp()) {
			if(this.getNowHp() + 120 > this.getMaxHp()) {
				this.changeNowHp(-this.getNowHp());
				this.changeNowHp(this.getMaxHp());
				this.potion --;
				System.out.println("HP가 최대치까지 회복되었습니다!");
				return true;
			}
			else {
				this.changeNowHp(120);
				this.potion --;
				System.out.println("HP가 120만큼 회복되었습니다!");
				return true;
			}
		}
		else if(this.potion != 0 && this.getNowHp() == this.getMaxHp()) {
			System.out.println("체력이 이미 최대치입니다!");
			return false;
		}
		else {
			System.out.println("포션 잔량이 없습니다!");
			return false;
		}
		
	}
	
	public boolean getSurvive() {
		return this.survive;
	}
	
	public void setSurvive(boolean change) {
		this.survive = change;
	}
	
	public int getPotion() {
		return this.potion;
	}
	
	public void changePotion(int change) {
		this.potion += change;
	}
	
}
