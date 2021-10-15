package controllers;

import java.util.ArrayList;

import models.Equipment;

public class ShopController {
	
	private ArrayList<Equipment> equips = new ArrayList<>();
	
	private ArrayList<Equipment> equipTemp = new ArrayList<>();
	
	public static ShopController instance = new ShopController();
	
	private ShopController() {}
	
	public void setBasicShopEquipments() {
		this.equips.add(new Equipment(1, "멋진 칼", 0, 2, 0, 2000));
		this.equips.add(new Equipment(1, "무거운 해머", 0, 4, -2, 3500));
		this.equips.add(new Equipment(1, "총알 없는 총", 0, 1, 0, 1000));
		this.equips.add(new Equipment(2, "티셔츠", 5, 0, 0, 1500));
		this.equips.add(new Equipment(2, "정장", 10, 0, 1, 2800));
		this.equips.add(new Equipment(2, "무거운 갑옷", 0, 0, 5, 3800));
		this.equips.add(new Equipment(3, "검은 반지", 2, 1, -1, 3000));
		this.equips.add(new Equipment(3, "방사능 팔찌", -10, 10, -5, 4500));
		this.equips.add(new Equipment(3, "이상한 목걸이", 0, -1, 7, 5000));
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
	
	public int getEquipHave(int idx) {
		return this.equips.get(idx).getEquipHave();
	}
	
	public void plusEquipHave(int idx, int have) {
		this.equips.get(idx).plusEquipHave(have);
	}
	
	public void printShopMenu() {
		while(true) {
			System.out.println("======== 유사 상점 =========");
			System.out.println("1. 물건 구매하기\t2. 물건 판매하기\t0. 뒤로 가기");
			System.out.print("메뉴 선택 : ");
			String input = GameMaster.scan.next();
			
			try {
				int sel = Integer.parseInt(input);
				
				if(sel >= 0 && sel <= 2) {
					if(sel == 1) buyEquipments();
					else if(sel == 2) sellEquipments();
					else if(sel == 0) break;
				}
			} catch (Exception e) {
				System.out.println("입력이 유효하지 않습니다.");
			}
		}
	}
	
	private void buyEquipments() {
		while(true) {
			System.out.println("======== 물건 구매하기 =========");
			System.out.println("1. 무기\t2. 방어구\t3. 장신구\t0. 뒤로 가기");
			System.out.print("메뉴 선택 : ");
			String input = GameMaster.scan.next();
			
			try {
				int sel = Integer.parseInt(input);
				
				if(sel >= 0 && sel <= 3) {
					if(sel >= 1 && sel <= 3) buyThings(sel);
					else if(sel == 0) break;
				}
			} catch (Exception e) {
				System.out.println("입력이 유효하지 않습니다.");
			}
		}
	}
	
	private void buyThings(int sel) {
		System.out.println(sel);
		this.equipTemp = new ArrayList<>();
		for(int i = 0; i<this.equips.size(); i++) {
			if(this.equips.get(i).getEquipSort() == sel) {
				this.equipTemp.add(new Equipment(this.equips.get(i).getEquipSort(),
						this.equips.get(i).getEquipName(), this.equips.get(i).getEquipHp(),
						this.equips.get(i).getEquipAtk(), this.equips.get(i).getEquipDef(),
						this.equips.get(i).getEquipCost(), this.equips.get(i).getEquipHave(),
						i));
			}
		}
		
		for(int i = 0; i<this.equipTemp.size(); i++) {
			System.out.println((i+1)+") "+this.equipTemp.get(i).getEquipName() +
						"\n  └─ HP "+this.equips.get(i).getEquipHp() + " Atk " +
						this.equipTemp.get(i).getEquipAtk() + " Def " + 
						this.equipTemp.get(i).getEquipDef() + "\n" +
						this.equipTemp.get(i).getEquipCost() + "Gold\t" + 
						this.equipTemp.get(i).getEquipHave() + "개 보유중");
		}
		
		System.out.println("보유 중인 골드 : "+GameMaster.gold+"Gold");
		System.out.print("품목 선택(없는 번호를 입력하면 뒤로 가기) : ");
		String input = GameMaster.scan.next();
		
		try {
			int buySelect = Integer.parseInt(input) - 1;
			
			if(buySelect >= 0 && buySelect < this.equipTemp.size()) {
				if(GameMaster.gold >= this.equipTemp.get(buySelect).getEquipCost()) {
					GameMaster.gold -= this.equipTemp.get(buySelect).getEquipCost();
					this.equips.get(this.equipTemp.get(buySelect).getTempIdx()).plusEquipHave(1);
					System.out.println("구매가 완료되었습니다!");
				}
				else System.out.println("골드가 부족합니다!");
			}
		} catch (Exception e) {
			System.out.println("유효하지 않은 입력입니다.");
		}
	}
	
	private void sellEquipments() {
		
	}
}
