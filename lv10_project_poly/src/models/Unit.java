package models;

public class Unit {
	protected final String name;
	
	protected int maxHp;
	protected int nowHp;
	protected int atk;
	protected int def;
	protected int debuff;
	protected int debuffTurn;
	protected int defTemp;
	
	public Unit(String name, int hp, int atk, int def) {
		this.name = name;
		this.maxHp = hp;
		this.nowHp = hp;
		this.atk = atk;
		this.def = def;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	public int getNowHp() {
		return this.nowHp;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public void changeNowHp(int change) {
		if(this.nowHp + change >= this.maxHp) this.nowHp = this.maxHp;
		else this.nowHp += change;
	}
	
	public void changeAtk(int change) {
		this.atk += change;
	}
	
	public void changeDef(int change) {
		this.def += change;
	}
	
	public int attack(Unit opponent) {
		int atk = this.atk;
		int def = opponent.def;
		
		int dmg = atk - def;
		if(dmg <= 0) dmg = 1;
		
		if(this.debuff != 0) {
			this.debuffTurn --;
			if(this.debuffTurn == 0) {
				this.debuff = 0;
				this.def = this.defTemp;
				System.out.println(this.name + "의 디버프가 해제되었다!");
			}
		}
		
		return dmg;
	}
	
	public int attack(int atk, Unit opponent) {
		int def = opponent.def;
		
		int dmg = atk - def;
		if(dmg <= 0) dmg = 1;
		
		if(this.debuff != 0) {
			this.debuffTurn --;
			if(this.debuffTurn == 0) {
				this.debuff = 0;
				this.def = this.defTemp;
				System.out.println(this.name + "의 디버프가 해제되었다!");
			}
		}
		
		return dmg;
	}
	
	public int getDebuff() {
		return this.debuff;
	}
	
	public int getDebuffTurn() {
		return this.debuffTurn;
	}
	
	public void changeDebuffTurn(int change) {
		this.debuffTurn += change;
	}
	
	public void changeDebuff(int change) {
		this.debuff = change;
		
		if(this.debuff == 2) {
			this.defTemp = this.def;
			this.def /= 2;
		}
	}
	
	
}
