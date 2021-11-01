package controllers;

import models.Debuffable;
import models.EnemySlime;
import models.Player;
import models.PlayerDefender;
import models.PlayerSniper;
import models.PlayerSword;
import models.Unit;

public class BattleController {
	
	private static BattleController instance = new BattleController();
	private BattleController() {}
	public static BattleController getInstance() {
		return instance;
	}
	
	UnitController uc = UnitController.getInstance();
	GameController gc = GameController.getInstance();
	
	private Player player;
	private Unit enemy;
	
	public void standbyPhase() {
		UnitController uc = UnitController.getInstance();
		GameController gc = GameController.getInstance();
		ShopController sc = ShopController.getInstance();
		
		this.enemy = uc.getEnemy();
		
		while(true) {
			System.out.println("Round "+GameController.battleCnt);
			System.out.println("이번 적은 " + enemy.getName()+"이다!");
			System.out.println(this.enemy);
			try {
				int errorDetect = this.player.getNowHp();
				
				System.out.println("현재 선택된 플레이어 =========");
				System.out.println(this.player);
				System.out.println("1. 전투 진입  2. 플레이어 교체\n"
						+ "3. 상점 방문  4. 현재 플레이어 장비 교체  0. 종료");
				String input = GameController.scan.next();
				
				try {
					int sel = Integer.parseInt(input);
					
					if(sel >= 0 && sel <= 4) {
						if(sel == 1) {
							System.out.println("전투를 시작합니다.");
							break;
						}
						else if(sel == 2) selectPlayer();
						else if(sel == 3) sc.printShop();
						else if(sel == 4) sc.printInventory();
						else if(sel == 0) {
							GameController.game = false;
							GameController.battleCnt = 99;
						}
					}
				} catch (Exception e) {
					System.out.println("잘 못된 입력값입니다.");
				}
			} catch (Exception e) {
				System.out.println("현재 선택된 플레이어가 없습니다.");
				selectPlayer();
			}
		}
		
		
	}
	
	private void printAllPlayers() {
		for(int i = 0; i<uc.getPlayerSize(); i++ ) 
			System.out.println((i+1)+". "+uc.getPlayer(i));
	}
	
	private void selectPlayer() {
		System.out.println("전투에 투입할 플레이어를 선택 ==========");
		
		printAllPlayers();
		
		System.out.print("번호 입력 : ");
		String input = GameController.scan.next();
		
		try {
			int sel = Integer.parseInt(input) - 1;
			
			if(sel >= 0 && sel <= 2) {
				this.player = uc.getPlayer(sel);
			}
		} catch (Exception e) {
			System.out.println("잘 못된 입력입니다.");
		}
	}
	
	public void battlePhase() {
		
		while(player.getNowHp() > 0 && enemy.getNowHp() > 0) {
			System.out.println(this.enemy);
			System.out.println(this.player);
			if(this.player instanceof PlayerDefender) {
				PlayerDefender temp = (PlayerDefender)this.player;
				if(temp.getDefTurn() != 0) {
					System.out.println(" └─ ★ 뚫리지 않는 방패 : 지속 시간 " +
							temp.getDefTurn() + "턴");
				}
			}
			
			System.out.println("1. 공격  2. 스킬 사용  3. 회복");
			System.out.print("행동 선택 : ");
			String input = gc.scan.next();
			
			try {
				int sel = Integer.parseInt(input);
				
				if(sel >= 1 && sel <= 3) {
					if(sel == 1) {
						int dmg = player.attack(enemy);
						
						enemy.changeNowHp(-dmg);
						System.out.println(enemy.getName()+"에게 "+dmg+"의 데미지!");
						
					}
					else if(sel == 2) {
						int special = 0;
						if(this.player instanceof PlayerSword) 
							special = ((PlayerSword) this.player).specialty();
						else if(this.player instanceof PlayerSniper)
							special = ((PlayerSniper) this.player).specialty();
						else if(this.player instanceof PlayerDefender)
							special = ((PlayerDefender) this.player).specialty();
						
						if(special > 0) {
							if(special == 1) {
								int dmg = player.attack(player.getAtk()+2, enemy);
								
								enemy.changeNowHp(-dmg);
								System.out.println(enemy.getName()+"에게 "+dmg+"의 데미지!");
								if(enemy instanceof EnemySlime)
									((EnemySlime) enemy).noRecovery();
							}
							else if(special == 2) {
								int dmg = this.player.attack(this.player.getAtk()+9, enemy);
								this.enemy.changeNowHp(-dmg);
								System.out.println(this.enemy.getName()+"에게 "+dmg+"의 데미지!");
								if(this.enemy instanceof Debuffable) {
									this.enemy.changeDebuff(2);
									this.enemy.changeDebuffTurn(3);
								}
							}
						}
					}
					else if(sel == 3) {
						System.out.println("회복에 전념했다!");
						if(player.getNowHp() < player.getMaxHp()) {
							if(player.getNowHp() + 15 >= player.getMaxHp()) {
								player.changeNowHp(-player.getNowHp());
								player.changeNowHp(player.getMaxHp());
								
								System.out.println("체력이 모두 회복되었다!");
							}
							else {
								player.changeNowHp(15);
								System.out.println("체력을 15 회복했다!");
							}
						}
						else System.out.println("이미 충분히 회복되어 효과가 없다!");
					}
				}
			} catch (Exception e) {
				System.out.println("뭘 해야할지 모르겠다!");
			} finally {
				if(this.enemy.getNowHp() > 0) {
					if(enemy instanceof EnemySlime)
						((EnemySlime) enemy).specialty();
					int dmg = enemy.attack(player);
					
					player.changeNowHp(-dmg);
					System.out.println(player.getName()+"에게 " + dmg + "의 데미지!");
					
					if(this.player instanceof PlayerDefender) {
						if(((PlayerDefender) this.player).getDefTurn() >= 1) {
							((PlayerDefender) this.player).changeDefTurn();
							if(((PlayerDefender) this.player).getDefTurn() == 0) {
								((PlayerDefender) this.player).specialEnd();
								System.out.println("스킬의 효과가 끝났다!");
							}
						}
					}
				}
			}
		}
		winner();
	}
	
	private void winner() {
		if(this.player.getNowHp() <= 0) {
			System.out.println("플레이어의 패배!");
			GameController.game = false;
		}
		else {
			if(this.player instanceof PlayerDefender) {
				if(((PlayerDefender) this.player).getDefTurn() >= 1) {
					while(((PlayerDefender) this.player).getDefTurn() != 0) {
						((PlayerDefender) this.player).changeDefTurn();
						if(((PlayerDefender) this.player).getDefTurn() == 0) {
							((PlayerDefender) this.player).specialEnd();
							break;
						}
					}
				}
			}
			System.out.println("플레이어의 승리!");
			if(GameController.battleCnt != 3) 
				System.out.println("다음 라운드로 진출합니다!");
			GameController.battleCnt ++;
		}
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
