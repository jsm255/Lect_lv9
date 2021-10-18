package controllers;

import models.Mob;

public class FightController {
	private Mob mob;
	
	public static FightController instance = new FightController();
	
	private FightController() {}
	
	public void newMob() {
		int rn = GameMaster.ran.nextInt(4);
		
		this.mob = new Mob(randomMobName(rn), mobLv(rn), mobHp(rn),
				mobAtk(rn), mobDef(rn), mobRegen(rn), mobGold(rn));
	}
	
	private String randomMobName(int rn) {
		String[] names = {"연습요원", "골렘", "드래곤", "아무튼 강한 몹"};
		return names[rn];
	}
	
	private int mobLv(int rn) {
		return (rn * 3) + 1;
	}
	
	private int mobHp(int rn) {
		return (rn + 1) * 120 + (rn * 30);
	}
	
	private int mobAtk(int rn) {
		return (rn + 1) * 7 + (rn * 7);
	}
	
	private int mobDef(int rn) {
		return rn * 2;
	}
	
	private int mobRegen(int rn) {
		return rn * 1;
	}
	
	private int mobGold(int rn) {
		return (rn + 1) * 500;
	}
	
	public String getMobName() {
		return this.mob.getName();
	}
	
	public int getMobLv() {
		return this.mob.getLv();
	}
	
	public int getMobMaxHp() {
		return this.mob.getMaxHp();
	}
	
	public int getMobNowHp() {
		return this.mob.getNowHp();
	}
	
	public void setMobNowHp(int change) {
		this.mob.setNowHp(change);
	}
	
	public int getMobAtk() {
		return this.mob.getAtk();
	}
	
	public int getMobDef() {
		return this.mob.getDef();
	}
	
	public int getMobRegen() {
		return this.mob.getRegen();
	}
	
	public int getMobGold() {
		return this.mob.getGold();
	}
	
	public void printFightMenu() {
		while(true) {
			System.out.println("========== 모의 훈련장 ==========");
			System.out.println("1. 랜덤 몹 소환\t2. 전투 시작\t0. 뒤로 가기");
			System.out.print("메뉴 선택 : ");
			int sel = selectMenu();
			
			if(sel == 1) {
				newMob();
				printMobWhileIdle();
			}
			else if(sel == 2) {
				if(this.mob == null || this.mob.getNowHp() == 0) {
					System.out.println("몹이 없습니다.");
					continue;
				}
				else {
					MemberController mc = MemberController.instance;
					startFight(mc);
				}
			}
			else if(sel == 0) break;
		}
	}
	
	private int selectMenu() {
		String input = GameMaster.scan.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(sel >= 0 && sel <= 2) return sel;
			else return -1;
		} catch (Exception e) {
			System.out.println("유효하지 않은 입력값입니다.");
			return -1;
		}
	}
	
	private void printMobWhileFight() {
		System.out.println("\t\t\t\t이름 : "+this.mob.getName()+" lv : "+this.mob.getLv());
		int hpBar = makeHpBar();
		System.out.print("\t\t\t\tHp : [");
		for(int i = 0; i<10; i++) {
			if(hpBar != 0) {
				hpBar --;
				System.out.print("■");
			}
			else System.out.print(" ");
		}
		System.out.println("]");
		System.out.println("\t\t\t\tAtk : "+this.mob.getAtk()+" Def : "+this.mob.getDef());
	}
	
	private void printMobWhileIdle() {
		System.out.println(this.mob.getName()+"이 출현했다!");
		System.out.println("이름 : "+this.mob.getName()+" lv : "+this.mob.getLv());
		int hpBar = makeHpBar();
		System.out.print("Hp : [");
		for(int i = 0; i<10; i++) {
			if(hpBar != 0) {
				hpBar --;
				System.out.print("■");
			}
			else System.out.print(" ");
		}
		System.out.println("]");
		System.out.println("Atk : "+this.mob.getAtk()+" Def : "+this.mob.getDef());
	}
	
	private int makeHpBar() {
		int divHp = this.mob.getMaxHp() / 10;
		int sum = 0;
		int hpBarCnt = 0;
		while(sum < this.mob.getNowHp()) {
			sum += divHp;
			hpBarCnt ++;
		}
		
		return hpBarCnt;
	}
	
	public void startFight(MemberController mc) {
		mc.recordPartyMembers();
		printMobWhileFight();
		mc.printPartyMember();
		decideMobAction(mc);
		decidePlayerAction(mc);
	}
	
	private void decideMobAction(MemberController mc) {
		int rn = GameMaster.ran.nextInt(3) + 1;
		
		if(rn == 1) {			// 공격
			int attack = GameMaster.ran.nextInt(GameMaster.partyMembers);
			System.out.println(this.mob.getName()+"은"+mc.getPartyMemberName(attack)+
					"을(를) 주시하고 있다.");
			this.mob.setAction(attack);
		}
		else if(rn == 2) {		// 방어 태세
			System.out.println(this.mob.getName()+"은 파티의 움직임을 지켜보고 있다.");
			this.mob.setAction(9);
		}
		else if(rn == 3) {		// 가만히 있기
			System.out.println(this.mob.getName()+"은 파티에 별로 관심이 없어보인다.");
			this.mob.setAction(0);
		}
	}
	
	private void decidePlayerAction(MemberController mc) {
		
	}
	
	
}
