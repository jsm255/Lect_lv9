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
		this.equipTemp = new ArrayList<>();
		for(int i = 0; i<this.equips.size(); i++) {
			if(this.equips.get(i).getEquipHave() > this.equips.get(i).getEquipWearing()) {
				this.equipTemp.add(new Equipment(this.equips.get(i).getEquipSort(),
						this.equips.get(i).getEquipName(), this.equips.get(i).getEquipHp(),
						this.equips.get(i).getEquipAtk(), this.equips.get(i).getEquipDef(),
						this.equips.get(i).getEquipCost(),
						this.equips.get(i).getEquipHave() - this.equips.get(i).getEquipWearing(),
						i));
			}
		}
		
		System.out.println("캐릭터가 장비 중이라면 집계되지 않습니다.");
		if(this.equipTemp.size() == 0) System.out.println("남는 장비가 없습니다!");
		else {
			for(int i = 0; i<this.equipTemp.size(); i++) {
				System.out.println((i+1)+") "+this.equipTemp.get(i).getEquipName() +
						"\n  └─ HP "+this.equips.get(i).getEquipHp() + " Atk " +
						this.equipTemp.get(i).getEquipAtk() + " Def " + 
						this.equipTemp.get(i).getEquipDef() + "\n" +
						this.equipTemp.get(i).getEquipCost() + "Gold\t" + 
						this.equipTemp.get(i).getEquipHave() + "개 보유중");
			}
			
			System.out.println("중고 매입가는 기존 가격의 50%입니다.");
			System.out.print("판매할 품목 선택 : ");
			String input = GameMaster.scan.next();
			
			try {
				int sel = Integer.parseInt(input)-1;
				
				if(sel >= 0 && sel < this.equipTemp.size()) {
					if(this.equipTemp.get(sel).getEquipHave() == 0) 
						System.out.println("가지고 있는 물건이 없습니다!");
					else {
						this.equips.get(this.equipTemp.get(sel).getTempIdx()).plusEquipHave(-1);
						GameMaster.gold += (this.equipTemp.get(sel).getEquipCost() / 2);
						System.out.println("판매했습니다.");
					}
				}
			} catch (Exception e) {
				System.out.println("유효하지 않은 입력입니다.");
			}
		}
	}
	
	public void selectInventory() {
		while(true) {
			System.out.println("1.장비 장착\t2. 장비 해제\t0. 뒤로 가기");
			System.out.print("메뉴 입력 : ");
			String input = GameMaster.scan.next();
			
			try {
				int sel = Integer.parseInt(input);
				
				if(sel >= 0 && sel <= 2) {
					if(sel == 1) printInventoryToEquip();
					else if(sel == 2) printInventoryToTakeOff();
					else if(sel == 0) break;
				}
			} catch (Exception e) {
				System.out.println("유효하지 않은 입력입니다.");
			}
		}
	}
	
	private void printInventoryToEquip() {
		MemberController mc = MemberController.instance;
		
		this.equipTemp = new ArrayList<>();
		for(int i = 0; i<this.equips.size(); i++) {
			if(this.equips.get(i).getEquipHave() > 0) {
				this.equipTemp.add(new Equipment(this.equips.get(i).getEquipSort(),
						this.equips.get(i).getEquipName(), this.equips.get(i).getEquipHp(),
						this.equips.get(i).getEquipAtk(), this.equips.get(i).getEquipDef(),
						this.equips.get(i).getEquipCost(),
						this.equips.get(i).getEquipHave(),
						i, this.equips.get(i).getEquipWearing()));
			}
		}
		
		if(this.equipTemp.size() == 0) System.out.println("남는 장비가 없습니다!");
		else {
			for(int i = 0; i<this.equipTemp.size(); i++) {
				System.out.println((i+1)+") "+this.equipTemp.get(i).getEquipName() +
							"  " + this.equipTemp.get(i).getEquipHave() + "개 보유중" + 
							"  " + this.equipTemp.get(i).getEquipWearing() + "개 장비중" + 
							"\n  └─ HP "+this.equips.get(i).getEquipHp() + " Atk " +
							this.equipTemp.get(i).getEquipAtk() + " Def " + 
							this.equipTemp.get(i).getEquipDef() + "\n" +
							this.equipTemp.get(i).getEquipCost() + "Gold\t");
			}
			
			System.out.print("장착시킬 장비 번호를 입력 : ");
			String input = GameMaster.scan.next();
			
			try {
				int sel = Integer.parseInt(input)-1;
				
				if(sel >= 0 && sel < this.equipTemp.size()) {
					if(this.equipTemp.get(sel).getEquipHave() >
						this.equipTemp.get(sel).getEquipWearing()) {
						mc.printAllMembers();
						
						System.out.print("장비를 장착할 길드원을 선택 : ");
						String input2 = GameMaster.scan.next();
						
						try {
							int sel2 = Integer.parseInt(input2)-1;
							
							if(this.equipTemp.get(sel).getEquipSort() == 1) {
								if(mc.getWeaponIdx(sel2) == -1) {
									mc.setWeaponIdx(sel2, this.equipTemp.get(sel).getTempIdx());
									this.equips.get(this.equipTemp.get(sel).getTempIdx()).plusEquipWearing(1);
									System.out.println("장착 완료.");
								}
								else {
									this.equips.get(mc.getWeaponIdx(sel2)).
										plusEquipWearing(-1);
									mc.setWeaponIdx(sel2, this.equipTemp.get(sel).getTempIdx());
									this.equips.get(this.equipTemp.get(sel).getTempIdx()).plusEquipWearing(1);
									System.out.println("장비를 교체했습니다.");
								}
							}
							else if(this.equipTemp.get(sel).getEquipSort() == 2) {
								if(mc.getArmorIdx(sel2) == -1) {
									mc.setArmorIdx(sel2, this.equipTemp.get(sel).getTempIdx());
									this.equips.get(this.equipTemp.get(sel).getTempIdx()).plusEquipWearing(1);
									System.out.println("장착 완료.");
								}
								else {
									this.equips.get(mc.getArmorIdx(sel2)).
										plusEquipWearing(-1);
									mc.setArmorIdx(sel2, this.equipTemp.get(sel).getTempIdx());
									this.equips.get(this.equipTemp.get(sel).getTempIdx()).plusEquipWearing(1);
									System.out.println("장비를 교체했습니다.");
								}
							}
							else if(this.equipTemp.get(sel).getEquipSort() == 3) {
								if(mc.getRingIdx(sel2) == -1) {
									mc.setRingIdx(sel2, this.equipTemp.get(sel).getTempIdx());
									this.equips.get(this.equipTemp.get(sel).getTempIdx()).plusEquipWearing(1);
									System.out.println("장착 완료.");
								}
								else {
									this.equips.get(mc.getRingIdx(sel2)).
										plusEquipWearing(-1);
									mc.setRingIdx(sel2, this.equipTemp.get(sel).getTempIdx());
									this.equips.get(this.equipTemp.get(sel).getTempIdx()).plusEquipWearing(1);
									System.out.println("장비를 교체했습니다.");
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("유효하지 않은 입력입니다.");
						}
					}
					
					else System.out.println("남은 장비가 없습니다! 장착 해제 후 착용시켜주세요!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유효하지 않은 입력입니다.");
			}
		}
	}
	
	private void printInventoryToTakeOff() {
		MemberController mc = MemberController.instance;
		
		mc.printAllMembers();
		
		System.out.print("장비를 해제할 길드원을 선택 : ");
		String input = GameMaster.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			
			mc.printOneMember(sel);
			
			System.out.println("1. 무기\t2. 방어구\t3. 장신구");
			System.out.print("해제할 장비 번호 입력 : ");
			String input2 = GameMaster.scan.next();
			
			try {
				int sel2 = Integer.parseInt(input2);
				
				if(sel2 >= 1 && sel2 <= 3) {
					if(sel2 == 1) {
						if(mc.getWeaponIdx(sel) == -1) 
							System.out.println("착용한 장비가 없습니다!");
						else {
							this.equips.get(mc.getWeaponIdx(sel)).plusEquipWearing(-1);
							mc.setWeaponIdx(sel, -1);
							System.out.println("착용해제 완료.");
						}
					}
					else if(sel2 == 2) {
						if(mc.getArmorIdx(sel) == -1) 
							System.out.println("착용한 장비가 없습니다!");
						else {
							this.equips.get(mc.getArmorIdx(sel)).plusEquipWearing(-1);
							mc.setArmorIdx(sel, -1);
							System.out.println("착용해제 완료.");
						}
					}
					else if(sel2 == 3) {
						if(mc.getRingIdx(sel) == -1) 
							System.out.println("착용한 장비가 없습니다!");
						else {
							this.equips.get(mc.getRingIdx(sel)).plusEquipWearing(-1);
							mc.setRingIdx(sel, -1);
							System.out.println("착용해제 완료.");
						}
					}
				}
				else System.out.println("잘못된 입력입니다. 뒤로 돌아갑니다.");
			} catch (Exception e) {
				System.out.println("유효하지 않은 입력입니다!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("유효하지 않은 입력입니다.");
		}
	}
}
