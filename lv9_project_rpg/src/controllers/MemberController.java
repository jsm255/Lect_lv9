package controllers;

import java.util.ArrayList;

import models.Member;

public class MemberController {
	
	private ArrayList <Member> members = new ArrayList<>();
	
	private int[] partyIdx;
	private int[] maxHps;
	private int[] nowHps;
	private int survivors;
	
	public static MemberController instance = new MemberController();
	
	private ShopController sc = ShopController.instance;
	
	private MemberController() {}
	
	public void newMember() {
		int hp = randomHp();
		if(GameMaster.partyMembers < 4) {
			this.members.add(new Member(randomName(), hp,
					randomAtk(hp), randomDef(hp), true));
			GameMaster.partyMembers ++;
		}
		else this.members.add(new Member(randomName(), hp,
				randomAtk(hp), randomDef(hp), false));
		System.out.println("새로운 멤버가 들어왔다!");
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
	
	public void setWeaponIdx(int memIdx, int equipIdx) {
		this.members.get(memIdx).setWeaponIdx(equipIdx);
	}
	
	public int getArmorIdx(int idx) {
		return this.members.get(idx).getArmorIdx();
	}
	
	public void setArmorIdx(int memIdx, int equipIdx) {
		this.members.get(memIdx).setArmorIdx(equipIdx);
	}
	
	public int getRingIdx(int idx) {
		return this.members.get(idx).getRingIdx();
	}
	
	public void setRingIdx(int memIdx, int equipIdx) {
		this.members.get(memIdx).setRingIdx(equipIdx);
	}
	
	public boolean getParty(int idx) {
		return this.members.get(idx).getParty();
	}
	
	public int getSize() {
		return this.members.size();
	}
	
	public int getSurvivors() {
		return this.survivors;
	}
	
	public void printGuildMenu() {
		while(true) {
			System.out.println("======== 길 드 ========");
			System.out.println("1. 길드원 조회\t2. 길드원 추가 모집\t3. 길드원 삭제");
			System.out.println("4. 파티원 모집 및 교체\t5. 정렬하기\t0.뒤로가기");
			int sel = selectGuildMenu();
			
			if(sel >= 0 && sel <= 5) {
				if(sel == 1) printAllMembers();
				else if(sel == 2) newMember();
				else if(sel == 3) kickMember();
				else if(sel == 4) editParty();
				else if(sel == 5) sortByAtk();
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
			System.out.print("  ├─ HP : "+(this.members.get(i).getHp()+plusHp)+"("+plusHp+") "+
					"Atk : "+(this.members.get(i).getAtk()+plusAtk)+"("+plusAtk+") "+
					"Def : "+(this.members.get(i).getDef()+plusDef)+"("+plusDef+") "+
					"파티 : ");
			
			if(this.members.get(i).getParty()) System.out.println("파티원");
			else System.out.println("파티 모집 중");
			
			System.out.print("  └─ 무기 : ");
			if(this.members.get(i).getWeaponIdx() != -1) 
				System.out.print(sc.getEquipName(
						this.members.get(i).getWeaponIdx())+" ");
			else System.out.print("없음 ");
			
			System.out.print("방어구 : ");
			if(this.members.get(i).getArmorIdx() != -1)
				System.out.print(sc.getEquipName(
						this.members.get(i).getArmorIdx())+" ");
			else System.out.print("없음 ");
			
			System.out.print("장신구 : ");
			if(this.members.get(i).getRingIdx() != -1)
				System.out.print(sc.getEquipName(
						this.members.get(i).getRingIdx()));
			else System.out.print("없음");
			System.out.println();
					
		}
	}
	
	public void printOneMember(int idx) {
		int plusHp = equipmentHp(idx);
		int plusAtk = equipmentAtk(idx);
		int plusDef = equipmentDef(idx);
		
		System.out.println(this.members.get(idx).getName()+" Lv"+
				this.members.get(idx).getLv());
		System.out.print("  ├─ HP : "+(this.members.get(idx).getHp()+plusHp)+"("+plusHp+") "+
				"Atk : "+(this.members.get(idx).getAtk()+plusAtk)+"("+plusAtk+") "+
				"Def : "+(this.members.get(idx).getDef()+plusDef)+"("+plusDef+") "+
				"파티 : ");
		
		if(this.members.get(idx).getParty()) System.out.println("파티원");
		else System.out.println("파티 모집 중");
		
		System.out.print("  └─ 무기 : ");
		if(this.members.get(idx).getWeaponIdx() != -1) 
			System.out.print(sc.getEquipName(
					this.members.get(idx).getWeaponIdx())+" ");
		else System.out.print("없음 ");
		
		System.out.print("방어구 : ");
		if(this.members.get(idx).getArmorIdx() != -1)
			System.out.print(sc.getEquipName(
					this.members.get(idx).getArmorIdx())+" ");
		else System.out.print("없음 ");
		
		System.out.print("장신구 : ");
		if(this.members.get(idx).getRingIdx() != -1)
			System.out.print(sc.getEquipName(
					this.members.get(idx).getRingIdx()));
		else System.out.print("없음");
		System.out.println();
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
	
	private void kickMember() {
		printAllMembers();
		System.out.print("추방할 멤버의 번호를 입력 : ");
		String input = GameMaster.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			
			if(sel >= 0 && sel < this.members.size()) {
				if(this.members.get(sel).getParty()) {
					GameMaster.partyMembers --;
					for(int i = 0; i<this.members.size(); i++) {
						if(this.members.get(i).getParty() == false &&
								GameMaster.partyMembers < 4) {
							this.members.get(i).changeParty();
							GameMaster.partyMembers ++;
						}
					}
				}
				this.members.remove(sel);
				System.out.println("추방 완료.");
			}
		} catch (Exception e) {
			System.out.println("유효하지 않은 입력입니다.");
		}
	}
	
	private void editParty() {
		printAllMembers();
		System.out.println("현재 파티원은 "+GameMaster.partyMembers+"명입니다.");
		System.out.print("파티에 추가하거나 내보낼 멤버의 번호를 입력 : ");
		String input = GameMaster.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			
			if(sel >= 0 && sel < this.members.size()) {
				if(this.members.get(sel).getParty()) {
					this.members.get(sel).changeParty();
					System.out.println("파티에서 내보냈습니다.");
				}
				else {
					if(GameMaster.partyMembers >= 4) 
						System.out.println("파티 멤버는 4명까지 모집 가능합니다.");
					else {
						this.members.get(sel).changeParty();
						System.out.println("파티에 추가했습니다.");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("유효하지 않은 입력입니다.");
		}
	}
	
	private void sortByAtk() {
		System.out.println("장비를 포함해 공격력이 높은 순으로 정렬합니다.");
		for(int i = 0; i<this.members.size(); i++) {
			for(int j = i; j<this.members.size(); j++) {
				if(this.members.get(i).getAtk() + equipmentAtk(i)
					< this.members.get(j).getAtk() + equipmentAtk(j)) {
					Member temp = this.members.get(i);
					this.members.set(i, this.members.get(j));
					this.members.set(j, temp);
				}
			}
		}
		System.out.println("정렬이 완료되었습니다.");
	}
	
	public void resetMembers() {
		this.members = new ArrayList<>();
	}
	
	public void loadMember(String name, int lv, int hp, int atk, int def, int weaponIdx,
			int armorIdx, int ringIdx, boolean party) {
		this.members.add(new Member(name, lv, hp, atk, def, weaponIdx,
				armorIdx, ringIdx, party));
	}
	
	public void recordPartyMembers() {
		this.survivors = GameMaster.partyMembers;
		this.partyIdx = new int[GameMaster.partyMembers];
		this.maxHps = new int[GameMaster.partyMembers];
		this.nowHps = new int[GameMaster.partyMembers];
		int hpsIdx = 0;
		for(int i = 0; i<this.members.size(); i++) {
			if(this.members.get(i).getParty()) {
				int plusHp = equipmentHp(i);
				this.partyIdx[hpsIdx] = i;
				this.maxHps[hpsIdx] = this.members.get(i).getHp()+plusHp;
				this.nowHps[hpsIdx] = this.members.get(i).getHp()+plusHp;
				hpsIdx ++;
			}
		}
	}
	
	public void printPartyMember() {
		for(int i = 0; i<this.partyIdx.length; i++) {
			if(this.nowHps[i] == -777) {
				System.out.println(this.members.get(this.partyIdx[i]).getName());
				System.out.println("\t[[[ 퇴각 ]]]");
			}
			else if(this.nowHps[i] < 0) {
				System.out.println(this.members.get(this.partyIdx[i]).getName());
				System.out.println("\t[[[ 전투 불능 ]]]");
			}
			int plusAtk = sc.getEquipAtk(this.partyIdx[i]);
			int plusDef = sc.getEquipDef(this.partyIdx[i]);
			
			System.out.println(this.members.get(this.partyIdx[i]).getName()+" Lv"+
					this.members.get(this.partyIdx[i]).getLv());
			System.out.println("  └─ HP : " + this.nowHps[i] + " / " + this.maxHps[i] + 
					"  Atk : "+(this.members.get(i).getAtk()+plusAtk)+
					"  Def : "+(this.members.get(i).getDef()+plusDef));
			
		}
	}
	
	public String getPartyMemberName(int partyIdx) {
		return this.members.get(this.partyIdx[partyIdx]).getName();
	}
	
	public void decidePlayerAction() {
		FightController fightc = FightController.instance;
		for(int i = 0; i<this.partyIdx.length; i++) {
			if(this.nowHps[i] == -777) continue;
			else {
				System.out.println(this.members.get(this.partyIdx[i]).getName()+"의 차례입니다.");
				System.out.println("행동을 선택하세요.");
				System.out.println("1. 공격  2. 회복  0. 퇴각");
				System.out.print("행동 입력 : ");
				String input = GameMaster.scan.next();
				
				try {
					int sel = Integer.parseInt(input);
					
					if(sel >= 0 && sel <= 2) {
						if( sel == 1) {
							int atk = this.members.get(this.partyIdx[i]).getAtk()+
									sc.getEquipAtk(this.partyIdx[i]);
							
							System.out.println(this.members.get(this.partyIdx[i]).getName()+
									"의 공격!");
							int crit = GameMaster.ran.nextInt(3);
							
							int damage = 0;
							if(crit != 2) damage = atk - fightc.getMobDef();
							else damage = (atk*3) - fightc.getMobDef();
							
							if(damage <= 0) System.out.println("효과가 없었다!");
							else {
								fightc.setMobNowHp(-damage);
								System.out.println(fightc.getMobName()+"에게 "+damage+"의 데미지!");
								if(crit == 2) System.out.println("치명타!");
							}
						}
						else if(sel == 2) {
							System.out.println(this.members.get(this.partyIdx[i]).getName()+
									"은 회복에 집중했다!");
							this.nowHps[i] = this.maxHps[i];
							
							System.out.println(this.members.get(this.partyIdx[i]).getName()+
									"의 체력이 모두 회복되었다!");
						}
						else if(sel == 0) {	// 퇴각시 hp를 -777로 만들어 퇴각 확인
											// 사망시 hp를 -로 놔두고 사망처리
							this.nowHps[i] = -777;
							System.out.println(this.members.get(this.partyIdx[i]).getName()+
									"은(는) 퇴각했습니다!");
							this.survivors --;
						}
					}
				} catch (Exception e) {
					System.out.println("유효하지 않은 입력 값입니다.");
				}
				
			}
		}
	}
	
	public void checkDowned() {
		for(int i = 0; i<this.nowHps.length; i++) {
			if(this.nowHps[i] < 0 && this.nowHps[i] != -777) {
				System.out.println(this.members.get(this.partyIdx[i]).getName()+
						"은(는) 저런 괴물들은 상대 못한다며 장비를 반납하고 길드를 뛰쳐나갔다.");
			}
		}
	}
}
