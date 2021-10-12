package controllers;

import java.util.ArrayList;
import java.util.Random;

import models.Shop;
import models.User;

public class UserManager {
	
	public static UserManager instance = new UserManager();
	
	private UserManager() {}
	
	private ArrayList<User> users = new ArrayList<>();
	
	boolean adminEnd = false;
	
	public int getUserCode(int idx) {
		return this.users.get(idx).getCode();
	}
	
	public String getUserName(int idx) {
		return this.users.get(idx).getName();
	}
	
	public int getUserSpent(int idx) {
		return this.users.get(idx).getSpent();
	}
	
	public void plusUserSpent(int idx, int money) {
		this.users.get(idx).plusSpent(money);
	}
	
	public void 디버그용메서드() {
		for(User temp : this.users) {
			System.out.println(temp.getCode()+" "+temp.getName()+" "+temp.getSpent()+"원");
		}
	}
	
	public void newUser() {
		System.out.print("이름을 입력 : ");
		String name = Shop.scan.next();
		
		this.users.add(new User(randomCode(), name));
	}
	
	private int randomCode() {
		Random ran = new Random();
		while(true) {
			int code = ran.nextInt(9000)+1000;
			boolean found = false;
			for(User temp : this.users) {
				if(temp.getCode() == code) found = true;
			}
			if (found == false) return code;
		}
	}
	
	public void login() {
		System.out.print("이름을 입력 : ");
		String name = Shop.scan.next();
		
		int cnt = 0;
		for(int i = 0; i<this.users.size(); i++) {
			if(this.users.get(i).getName().equals(name)) cnt ++;
		}
		
		if(cnt == 0) System.out.println("해당하는 이름의 유저가 없습니다.");
		else if(cnt == 1) {
			for(int i = 0; i<this.users.size(); i++) {
				if(this.users.get(i).getName().equals(name)) Shop.Log = i;
			}
			System.out.println("환영합니다.");
		}
		else {
			System.out.println("같은 이름을 가진 유저가 있습니다.");
			System.out.print("회원코드를 입력 : ");
			int code = Shop.scan.nextInt();
			
			for(int i = 0; i<this.users.size(); i++) {
				if(this.users.get(i).getName().equals(name) &&
						this.users.get(i).getCode() == code) Shop.Log = i;
			}
			
			if(Shop.Log == -1) System.out.println("이름과 코드가 일치하는 유저가 없습니다.");
			else System.out.println("환영합니다.");
		}
	}
	
	public void printAdminMenu() {
		this.adminEnd = false;
		while(this.adminEnd == false) {
			System.out.println("!!!! 관리자 메뉴 !!!!");
			System.out.println("1. 유저 관리\n2. 카테고리 관리\n3. 품목 관리\n4. 장바구니 관리\n0. 종료");
			System.out.print("입력 : ");
			
			selectAdminMenu();
		}
	}
	
	public void selectAdminMenu() {
		String input = Shop.scan.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(sel >= 0 && sel <= 4) {
				if(sel == 1) {
					while(true) {
						System.out.println("1. 전체 유저 조회\n2. 유저 추가\n3. 유저 탈퇴\n0. 뒤로");
						input = Shop.scan.next();
						
						try {
							sel = Integer.parseInt(input);
							
							if(sel >= 0 && sel <= 3) {
								if(sel == 1) {
									if(this.users.size() == 0) System.out.println("유저가 없습니다!");
									else {
										for(int i = 0; i<this.users.size(); i++) {
											System.out.println(this.users.get(i).getCode()+") "+
													this.users.get(i).getName()+" / 결제한 금액 : "+
													this.users.get(i).getSpent()+"원");
										}
										System.out.println(this.users.size()+"명 조회 완료.");
									}
								}
								else if(sel == 2) newUser();
								else if(sel == 3) signout();
								else if(sel == 0) break;
								
							}
						} catch (Exception e) {
							System.out.println("오류가 낙타났다");
							e.printStackTrace();
						}
					}
				}
				else if(sel == 2) {
					while(true) {
						System.out.println("1. 전체 카테고리 조회\n2. 카테고리 추가\n"
								+ "3. 카테고리 삭제\n0. 뒤로");
						input = Shop.scan.next();
						
						try {
							sel = Integer.parseInt(input);
							
							if(sel >= 0 && sel <= 3) {
								CategoryManager cm = CategoryManager.instance;
								BasketManager bm = BasketManager.instance;
								if(sel == 1) cm.printCategory();
								else if(sel == 2) cm.addCategory();
								else if(sel == 3) cm.removeCategory(bm);
								else if(sel == 0) break;
								
							}
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("뭔가 오류가 낙타났다");
						}
					}
				}
				else if(sel == 3) {
					
				}
				else if(sel == 4) {
					
				}
				else if(sel == 0) {
					this.adminEnd = true;
				}
			}
		} catch (Exception e) {
			System.out.println("오류가 나타났다!");
			e.printStackTrace();
		}
	}
	
	private void signout() {
		BasketManager bm = BasketManager.instance;
		System.out.print("이름을 입력 : ");
		String name = Shop.scan.next();
		
		int cnt = 0;
		for(int i = 0; i<this.users.size(); i++) {
			if(this.users.get(i).getName().equals(name)) cnt ++;
		}
		
		if(cnt == 0) System.out.println("해당하는 이름의 유저가 없습니다.");
		else if(cnt == 1) {
			for(int i = 0; i<this.users.size(); i++) {
				if(this.users.get(i).getName().equals(name)) {
					bm.removePaidBasket(this.users.get(i).getCode());
					this.users.remove(i);
				}
			}
			System.out.println("탈퇴 완료.");
		}
		else {
			System.out.println("같은 이름을 가진 유저가 있습니다.");
			System.out.print("회원코드를 입력 : ");
			int code = Shop.scan.nextInt();
			
			boolean found = false;
			for(int i = 0; i<this.users.size(); i++) {
				if(this.users.get(i).getName().equals(name) &&
						this.users.get(i).getCode() == code) {
					bm.removePaidBasket(this.users.get(i).getCode());
					this.users.remove(i);
					found = true;
					System.out.println("탈퇴 완료.");
				}
			}
			if(found == false) System.out.println("코드와 이름이 일치하는 유저가 없습니다.");
		}
	}
	
}
