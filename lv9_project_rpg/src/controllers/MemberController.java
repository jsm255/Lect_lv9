package controllers;

import java.util.ArrayList;

import models.Member;

public class MemberController {
	
	private ArrayList <Member> members = new ArrayList<>();
	
	public static MemberController instance = new MemberController();
	
	private ShopController sc = ShopController.instance;
	
	private MemberController() {}
	
	public void newMember() {
		int hp = randomHp();
		this.members.add(new Member(randomName(), hp, randomAtk(hp), randomDef(hp)));
	}
	
	private String randomName() {
		while(true) {
			char first = (char)(GameMaster.ran.nextInt(26) + 65);
			char second = (char)(GameMaster.ran.nextInt(26) + 97);
			char third = (char)(GameMaster.ran.nextInt(26) + 97);
			
			String name = "";
			name += first;
			name += second;
			name += third;
			
			if(checkName(name)) continue;
			else return name;
		}
	}
	
	private boolean checkName(String name) {
		for(Member temp : this.members) {
			if(temp.getName().equals(name)) return true;
		}
		return false;
	}
	
	private int randomHp() {
		int hp = GameMaster.ran.nextInt(51) + 50;	// 50~100
		
		return hp;
	}
	
	private int randomAtk(int hp) {
		if(hp <= 60) {
			int atk = GameMaster.ran.nextInt(4) + 7;	// 7~10;
			
			return atk;
		}
		else if(hp > 60 && hp <= 80) {
			int atk = GameMaster.ran.nextInt(3) + 4;	// 4~6;
			
			return atk;
		}
		else {
			int atk = GameMaster.ran.nextInt(3) + 1;	// 1~3;
			
			return atk;
		}
	}
	
	private int randomDef(int hp) {
		if(hp <= 60) {
			int def = GameMaster.ran.nextInt(3);		// 0~2;
			
			return def;
		}
		else if(hp > 60 && hp <= 80) {
			int def = GameMaster.ran.nextInt(4) + 2;		// 2~5;
			
			return def;
		}
		else {
			int def = GameMaster.ran.nextInt(3) + 6;		// 6~8;
			
			return def;
		}
	}
	
	public String getName(int idx) {
		return this.members.get(idx).getName();
	}
	
	public int getLv(int idx) {
		return this.members.get(idx).getLv();
	}
	
	public int getHp(int idx) {
		return this.members.get(idx).getHp();
	}
	
	public int getAtk(int idx) {
		return this.members.get(idx).getAtk();
	}
	
	public int getDef(int idx) {
		return this.members.get(idx).getDef();
	}
	
	public int getWeaponIdx(int idx) {
		return this.members.get(idx).getWeaponIdx();
	}
	
	public int getArmorIdx(int idx) {
		return this.members.get(idx).getArmorIdx();
	}
	
	public int getRingIdx(int idx) {
		return this.members.get(idx).getRingIdx();
	}
	
	public boolean getParty(int idx) {
		return this.members.get(idx).getParty();
	}
	
	public void printGuildMenu() {
		while(true) {
			System.out.println("======== 길 드 ========");
			System.out.println("1. 길드원 조회\t2. 길드원 추가 모집\t3. 길드원 삭제");
			System.out.println("4. 파티원 모집 및 교체\t5. 정렬하기\t0.뒤로가기");
			int sel = selectGuildMenu();
			
			if(sel >= 0 && sel <= 5) {
				if(sel == 1) printAllMembers();
				else if(sel == 2) {
					
				}
				else if(sel == 3) {
					
				}
				else if(sel == 4) {
					
				}
				else if(sel == 5) {
					
				}
				else if(sel == 0) break;
			}
		}
	}
	
	public int selectGuildMenu() {
		System.out.println("메뉴 선택 : ");
		String input = GameMaster.scan.next();
		
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요!");
			return 9;
		}
	}
	
	public void printAllMembers() {
		System.out.println("======= 멤버 리스트 =======");
		for(int i = 0; i<this.members.size(); i++) {
			int plusHp = equipmentHp(i);
			int plusAtk = equipmentAtk(i);
			int plusDef = equipmentDef(i);
			
			System.out.println((i+1)+") "+this.members.get(i).getName()+" Lv"+
					this.members.get(i).getLv());
			System.out.print("HP : "+(this.members.get(i).getHp()+plusHp)+"("+plusHp+") "+
					"Atk : "+(this.members.get(i).getAtk()+plusAtk)+"("+plusAtk+") "+
					"Def : "+(this.members.get(i).getDef()+plusDef)+"("+plusDef+") "+
					"파티 : ");
			
			if(this.members.get(i).getParty()) System.out.println("파티원");
			else System.out.println("파티 모집 중");
			
			if(this.members.get(i).getWeaponIdx() != -1) 
				System.out.print("무기 : "+sc.getEquipName(
						this.members.get(i).getWeaponIdx())+" ");
			if(this.members.get(i).getArmorIdx() != -1)
				System.out.println("방어구 : "+sc.getEquipName(
						this.members.get(i).getArmorIdx())+" ");
			if(this.members.get(i).getRingIdx() != -1)
				System.out.println("장신구 : "+sc.getEquipName(
						this.members.get(i).getRingIdx()));
					
		}
	}
	
	private int equipmentHp(int i) {
		int plusHp = 0;
		if(this.members.get(i).getWeaponIdx() != -1) {
			plusHp += sc.getEquipHp(this.members.get(i).getWeaponIdx());
		}
		if(this.members.get(i).getArmorIdx() != -1) {
			plusHp += sc.getEquipHp(this.members.get(i).getArmorIdx());
		}
		if(this.members.get(i).getRingIdx() != -1) {
			plusHp += sc.getEquipHp(this.members.get(i).getRingIdx());
		}
		return plusHp;
	}
	
	private int equipmentAtk(int i) {
		int plusAtk = 0;
		if(this.members.get(i).getWeaponIdx() != -1) {
			plusAtk += sc.getEquipAtk(this.members.get(i).getWeaponIdx());
		}
		if(this.members.get(i).getArmorIdx() != -1) {
			plusAtk += sc.getEquipAtk(this.members.get(i).getArmorIdx());
		}
		if(this.members.get(i).getRingIdx() != -1) {
			plusAtk += sc.getEquipAtk(this.members.get(i).getRingIdx());
		}
		return plusAtk;
	}
	
	private int equipmentDef(int i) {
		int plusDef = 0;
		if(this.members.get(i).getWeaponIdx() != -1) {
			plusDef += sc.getEquipDef(this.members.get(i).getWeaponIdx());
		}
		if(this.members.get(i).getArmorIdx() != -1) {
			plusDef += sc.getEquipDef(this.members.get(i).getArmorIdx());
		}
		if(this.members.get(i).getRingIdx() != -1) {
			plusDef += sc.getEquipDef(this.members.get(i).getRingIdx());
		}
		return plusDef;
	}
	
}
