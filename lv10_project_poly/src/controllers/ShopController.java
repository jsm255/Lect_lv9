package controllers;

import java.util.ArrayList;

import models.Player;
import models.PlayerDefender;
import models.PlayerSniper;
import models.PlayerSword;
import models.Rifle;
import models.Shield;
import models.Sword;
import models.Weapon;

public class ShopController {
	
	ArrayList<Weapon> weapons = new ArrayList<>();
	ArrayList<Weapon> temp = new ArrayList<>();
	
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
	
	public void printShop() {
		while(true) {
			System.out.println("\t ===== 상 점 ===== ");
			System.out.println("1. 검  2. 총  3. 방패  0. 상점 나가기");
			System.out.print("메뉴 선택 : ");
			String input = GameController.scan.next();
			
			try {
				int sel = Integer.parseInt(input);
				
				if(sel >= 0 && sel <= 3) {
					if (sel == 0) break;
					else {
						this.temp = new ArrayList<>();
						for(Weapon wea : this.weapons) {
							if(wea.getWeaponCategory() == sel) {
								if(sel == 1) 
									this.temp.add(new Sword(wea.getWeaponName(), 
											wea.getWeaponAtk(), wea.getWeaponDef(),
											wea.getWeaponPrice(), wea.getWeaponHave(),
											wea.getWeaponEquipped()));
								else if(sel == 2) 
									this.temp.add(new Rifle(wea.getWeaponName(), 
											wea.getWeaponAtk(), wea.getWeaponDef(),
											wea.getWeaponPrice(), wea.getWeaponHave(),
											wea.getWeaponEquipped()));
								else if(sel == 3) 
									this.temp.add(new Shield(wea.getWeaponName(), 
											wea.getWeaponAtk(), wea.getWeaponDef(),
											wea.getWeaponPrice(), wea.getWeaponHave(),
											wea.getWeaponEquipped()));
							}
						}
						
						printShopCategory();
					}
				}
			} catch (Exception e) {
				System.out.println("입력이 잘 못 되었습니다.");
			}
		}
	}
	
	private void printShopCategory() {
		System.out.println("\t ===== 상 점 =====");
		for(int i = 0; i<this.temp.size(); i++) {
			System.out.printf("%d. %s Atk %d Def %d  %dGold  %d개 보유중\n",(i+1),
					this.temp.get(i).getWeaponName(), this.temp.get(i).getWeaponAtk(),
					this.temp.get(i).getWeaponDef(), this.temp.get(i).getWeaponPrice(),
					this.temp.get(i).getWeaponHave());
		}
		System.out.println("보유 금액 : "+ GameController.gold+"Gold");
		System.out.print("구매할 품목 번호 입력 : ");
		String input = GameController.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			
			if(sel >= 0 && sel < this.temp.size()) {
				if(GameController.gold < this.temp.get(sel).getWeaponPrice()) 
					System.out.println("돈이 부족합니다!");
				else {
					GameController.gold -= this.temp.get(sel).getWeaponPrice();
					for(int i = 0; i<this.weapons.size(); i++) {
						if(this.weapons.get(i).getWeaponName().
								equals(this.temp.get(sel).getWeaponName())) {
							this.weapons.get(i).changeWeaponHave(1);
						}
					}
					System.out.println(this.temp.get(sel).getWeaponName()+"을(를) 구매했습니다!");
				}
			}
		} catch (Exception e) {
			System.out.println("입력 값이 유효하지 않습니다.");
		}
	}
	
	public void printInventory() {
		BattleController bc = BattleController.getInstance();
		
		Player player = bc.getPlayer();
		int category = 0;
		
		System.out.println("\t ===== 인벤토리 =====");
		if(player instanceof PlayerSword) category = 1;
		else if(player instanceof PlayerSniper) category = 2;
		else if(player instanceof PlayerDefender) category = 3;
		
		for(int i = 0; i<this.weapons.size(); i++) {
			if(this.weapons.get(i).getWeaponHave() >= 1) 
				System.out.printf("%d. %s  %d개 보유중  %d개 장비중\n", (i+1),
						this.weapons.get(i).getWeaponName(), this.weapons.get(i).getWeaponHave(),
						this.weapons.get(i).getWeaponEquipped());
		}
		System.out.println("현재 캐릭터가 장비 중인 무기 : ");
		System.out.print("장착할 장비를 입력 : ");
		
	}
	
}
