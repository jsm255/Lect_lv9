package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import models.Hero;
import models.HeroZombie;
import models.Unit;
import models.Zombie;

public class TowerController {
	
	private Hero hero;
	private ArrayList <Zombie> zombies = new ArrayList<>();
	private HeroZombie hZombie;
	
	private Scanner scan = new Scanner(System.in);
	private Random ran = new Random();
	
	public static TowerController instance = new TowerController();
	private TowerController() {}
	public TowerController getTC() {
		return TowerController.instance;
	}
	
	public void run() {
		generate();
		
		while(this.hero.getFloor() <= 12) tower();
		
	}
	
	public void generate() {
		this.hero = new Hero("잉여 용사", 1, 100, 3, 2, 1);
		this.zombies.add(new Zombie("잉여 좀비", 3, 70, 5, 1, 3));
		this.zombies.add(new Zombie("좀 센 좀비", 7, 150, 8, 3, 6));
		this.zombies.add(new Zombie("앞 친구보다 강한 좀비", 11, 200, 10, 6, 9));
		this.hZombie = new HeroZombie("전 용사 좀비", 15, 300, 15, 9, 12);
	}
	
	public void printForDebug() {
		System.out.println(this.hero);
		for(int i = 0; i<this.zombies.size(); i++) {
			System.out.println(this.zombies.get(i));
		}
		System.out.println(this.hZombie);
	}
	
	public void tower() {
		int now = this.hero.getFloor();
		System.out.println("=|=|=|=|=|=|=|=|=|=| " + now + "층 |=|=|=|=|=|=|=|=|=|=");
		
		int zombieIdx;
		if(now % 3 == 0 && now < 12) {
			for(int i = 0; i<this.zombies.size(); i++) {
				if(this.zombies.get(i).getFloor() == now) zombieIdx = i;
			}
			
			
		}
		else if(now == 12) {
			
		}
		else {
			restPlace();
			goUp();
		}
	}
	
	private void goUp() {
		System.out.println("계단을 올랐다.");
		int rn = ran.nextInt(3);
		if(rn == 0) System.out.println("... 5 계단으로 층을 나누는 의미가 있을까?");
		else if(rn == 1) System.out.println("계단이 아니라 에스컬레이터였다!");
		else if(rn == 2) System.out.println("낡아빠진 계단을 조심히 올라갔다.");
		this.hero.changeFloor(1);
	}
	
	private void restPlace() {
		System.out.println("이곳에는 아무도 없습니다.");
		System.out.println("잠깐 휴식하거나 수련을 할 수 있을 것 같습니다.");
		
		int turn = 2;
		while(turn != 0) {
			System.out.println(this.hero);
			System.out.println("남은 행동 수 : "+turn);
			System.out.println("1. 수련하기  2. 휴식하기  0. 그만 올라가기");
			String input = scan.next();
			
			try {
				int sel = Integer.parseInt(input);
				
				if(sel >= 0 && sel <= 2) {
					if(sel == 1) {
						System.out.println("잠깐 수련을 했다!");
						lvUp();
						turn --;
					}
					else if(sel == 2) {
						System.out.println("잠깐 휴식했다!");
						if(this.hero.getNowHp() < this.hero.getMaxHp()) {
							if(this.hero.getNowHp() + 40 > this.hero.getMaxHp()) {
								this.hero.changeNowHp(-this.hero.getNowHp());
								this.hero.changeNowHp(this.hero.getMaxHp());
								System.out.println("체력이 모두 회복되었다!");
								turn --;
							}
							else {
								this.hero.changeNowHp(40);
								System.out.println("체력이 40만큼 회복되었다!");
								turn --;
							}
						}
						else {
							System.out.println("체력이 이미 가득차있다!");
							System.out.println("다른 행동을 하는게 나을 것 같다.");
						}
					}
					else {
						System.out.println("딱히 할 일이 없다. 그냥 올라가자.");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("유효하지 않은 입력입니다!");
			}
			
		}
	}
	
	private void lvUp() {
		int atkUp = 0;
		int defUp = 0;
		for(int i = 0; i<3; i++) {
			int rn = ran.nextInt(2);
			if(rn == 0) atkUp ++;
			else if(rn == 1) defUp ++;
		}
		this.hero.changeLv(1);
		System.out.println("lv Up!");
		this.hero.changeMaxHp(15);
		this.hero.changeNowHp(15);
		System.out.println("15 hp만큼 올랐다!");
		if(atkUp > 0) {
			this.hero.changeAtk(atkUp);
			System.out.println(atkUp+" atk만큼 올랐다!");
		}
		if(defUp > 0) {
			this.hero.changeDef(defUp);
			System.out.println(defUp+" def만큼 올랐다!");
		}
	}
	
}
