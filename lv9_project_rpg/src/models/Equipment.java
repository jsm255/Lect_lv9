package models;

public class Equipment {
	private int sort;		// 1 = 무기 2 = 방어구 3 = 장신구
	private String name;
	private int hp;
	private int atk;
	private int def;	
	private int have = 0;		// 보유 중
	private int wearing = 0;	// 장착 중
	private int cost;
	private int tempIdx;		// 잠시 임시 배열로 넘겼다가 구매 또는 판매해서 수치가 변동했을 때 사용
	
	public Equipment(int sort, String name, int hp, int atk, int def, int cost) {
		this.sort = sort;
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.cost = cost;
	}
	
	public Equipment(int sort, String name, int hp, int atk, int def, int cost, int have, int tempIdx) {
		this.sort = sort;
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.cost = cost;
		this.have = have;
		this.tempIdx = tempIdx;
	}
	
	public int getEquipSort() {
		return this.sort;
	}
	
	public String getEquipName() {
		return this.name;
	}
	
	public int getEquipHp() {
		return this.hp;
	}
	
	public int getEquipAtk() {
		return this.atk;
	}
	
	public int getEquipDef() {
		return this.def;
	}
	
	public int getEquipHave() {
		return this.have;
	}
	
	public void plusEquipHave(int have) {
		this.have += have;
	}
	
	public int getEquipCost() {
		return this.cost;
	}
	
	public int getTempIdx() {
		return this.tempIdx;
	}
	
	public int getEquipWearing() {
		return this.wearing;
	}
	
	public void plusEquipWearing(int plus) {
		this.wearing += plus;
	}
}
