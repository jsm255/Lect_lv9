package controllers;

import java.util.ArrayList;

import models.Rifle;
import models.Sword;
import models.Weapon;

public class ShopController {
	
	private static ShopController instance = new ShopController();
	private ShopController() {}
	public static ShopController getInstance() {
		return instance;
	}
	
	private ArrayList<Weapon> weapons = new ArrayList<>();
	private ArrayList<Weapon> weaponsTemp = new ArrayList<>();
	
	public void makeBasicEquips() {
		this.weapons.add(new Sword("단검", 2, 0, 1500));
		this.weapons.add(new Sword("장검", 4, 0, 3000));
		this.weapons.add(new Sword("대검", 8, 1, 6000));
		this.weapons.add(new Rifle("권총", 2, 0, 1500));
		this.weapons.add(new Rifle("소총", 4, 0, 3000));
		this.weapons.add(new Rifle("대포", 8, 1, 6000));
	}
	
	public Weapon getWeapon(int idx) {
		return this.weapons.get(idx);
	}
	
	public Weapon getWeapon(String name) {
		int idx = -1;
		for(int i = 0; i<this.weapons.size(); i++) {
			if(name.equals(this.weapons.get(i).getName())) idx = i;
		}
		
		return this.weapons.get(idx);
	}
	
	public void printShop() {
		System.out.println(" ======= 상 점 ======= ");
		System.out.println("1. 검  2. 총기  0. 나가기");
		int sel = returnSelect(0, 3);
		
		printCat(sel);
	}
	
	private int returnSelect(int start, int end) {
		String input = GameController.scan.next();
		
		try {
			int sel = -1;
			
			if(start == 0) sel = Integer.parseInt(input);
			else if(start == 1) sel = Integer.parseInt(input)-1;
			
			if(sel >= 0 && sel < end) return sel;
			else{
				System.out.println("입력 값이 이상합니다.");
				return -1;
			}
			
		} catch (Exception e) {
			System.out.println("대충 오류");
			return -1;
		}
	}
	
	private void printCat(int cat) {
		this.weaponsTemp = new ArrayList<>();
		
		for(Weapon temp : this.weapons) {
			if(temp.getCat() == cat && cat == 1) {
				
				this.weaponsTemp.add(new Sword(temp.getName(), temp.getAtk(), temp.getDef(),
						temp.getPrice(),temp.getHave(),temp.getEquip()));
			}
		}
	}
}
