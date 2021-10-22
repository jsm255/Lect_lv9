package controllers;

import java.util.ArrayList;

import models.Hero;
import models.HeroZombie;
import models.Unit;
import models.Zombie;

public class TowerController {
	
	private Hero hero;
	private ArrayList <Zombie> zombies = new ArrayList<>();
	private HeroZombie hZombie;
	
	public static TowerController instance = new TowerController();
	private TowerController() {}
	public TowerController getTC() {
		return TowerController.instance;
	}
	
	public void run() {
		generate();
		printForDebug();
	}
	
	public void generate() {
		this.hero = new Hero("잉여 용사", 1, 100, 3, 2, 1);
		this.zombies.add(new Zombie("잉여 좀비", 1, 50, 2, 0, 3));
		this.zombies.add(new Zombie("좀 센 좀비", 4, 120, 4, 2, 6));
		this.zombies.add(new Zombie("앞 친구보다 강한 좀비", 8, 180, 7, 3, 9));
		this.hZombie = new HeroZombie("전 용사 좀비", 12, 250, 11, 5, 12);
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
		
		int zombieIdx;
		if(now % 3 == 0 && now < 12) {
			for(int i = 0; i<this.zombies.size(); i++) {
				if(this.zombies.get(i).getFloor() == now) zombieIdx = i;
			}
			
			
		}
		else if(now == 12) {
			
		}
		else {
			
		}
	}
	
	private void goUp() {
		
	}
	
}
