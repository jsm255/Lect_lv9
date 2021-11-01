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
}
