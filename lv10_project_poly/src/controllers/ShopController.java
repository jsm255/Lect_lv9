package controllers;

import java.util.ArrayList;

import models.Rifle;
import models.Shield;
import models.Sword;
import models.Weapon;

public class ShopController {
	
	ArrayList<Weapon> weapons = new ArrayList<>();
	
	private static ShopController instance = new ShopController();
	private ShopController() {}
	public static ShopController getInstance() {
		return instance;
	}
	
	public void setBasicWeapons() {
		this.weapons.add(new Sword("초심자용 단도", 2, 0, 1500));
		this.weapons.add(new Sword("숙련자용 단도", 5, 0, 3500));
		this.weapons.add(new Sword("푸른 장검", 8, -2, 5000));
		this.weapons.add(new Sword("무거운 대검", 13, -5, 8000));
		
		this.weapons.add(new Rifle("공기 권총", 2, 0, 1500));
		this.weapons.add(new Rifle("실탄 권총", 6, 0, 3500));
		this.weapons.add(new Rifle("저격 소총", 10, -3, 5000));
		this.weapons.add(new Rifle("대장갑용 저격 소총", 16, -5, 8000));
		
		this.weapons.add(new Shield("깃털 방패", 1, 1, 1500));
		this.weapons.add(new Shield("목제 방패", 2, 3, 3500));
		this.weapons.add(new Shield("철제 방패", 4, 6, 5000));
		this.weapons.add(new Shield("티타늄 합금 방패", 5, 10, 8000));
	}
	
	public void 디버그용메서드() {
		for(Weapon wea : this.weapons) {
			System.out.println(wea.getWeaponCategory() + " " + wea.getWeaponName() + " " +
					wea.getWeaponAtk() + " " + wea.getWeaponDef() + " " + wea.getWeaponPrice());
		}
	}
	
}
