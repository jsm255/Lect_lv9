package controllers;

import java.util.ArrayList;

import models.Equipment;

public class ShopController {
	
	private ArrayList<Equipment> equips = new ArrayList<>();
	
	private ArrayList<Equipment> equipTemp = new ArrayList<>();
	
	public static ShopController instance = new ShopController();
	
	private ShopController() {}
	
	public void setBasicShopEquipments() {
		this.equips.add(new Equipment(1, "멋진 칼", 0, 2, 0));
		this.equips.add(new Equipment(1, "무거운 해머", 0, 4, -2));
		this.equips.add(new Equipment(1, "총알 없는 총", 0, 1, 0));
		this.equips.add(new Equipment(2, "티셔츠", 5, 0, 0));
		this.equips.add(new Equipment(2, "정장", 10, 0, 1));
		this.equips.add(new Equipment(2, "무거운 갑옷", 0, 0, 5));
		this.equips.add(new Equipment(3, "검은 반지", 2, 1, -1));
		this.equips.add(new Equipment(3, "방사능 팔찌", -10, 10, -5));
		this.equips.add(new Equipment(3, "이상한 목걸이", 0, -1, 7));
	}
	
	public int getEquipSize() {
		return this.equips.size();
	}
	
	public int getEquipSort(int idx) {
		return this.equips.get(idx).getEquipSort();
	}
	
	public String getEquipName(int idx) {
		return this.equips.get(idx).getEquipName();
	}
	
	public int getEquipHp(int idx) {
		return this.equips.get(idx).getEquipHp();
	}
	
	public int getEquipAtk(int idx) {
		return this.equips.get(idx).getEquipAtk();
	}
	
	public int getEquipDef(int idx) {
		return this.equips.get(idx).getEquipDef();
	}
}
