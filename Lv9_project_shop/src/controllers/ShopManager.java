package controllers;

import models.Shop;

public class ShopManager {
	
	private UserManager um = UserManager.instance;
	
	public static ShopManager instance = new ShopManager();
	
	private boolean end = false;
	
	private ShopManager() {}
	
	public void run() {
		while(this.end == false) {
			this.um.디버그용메서드();
			printMainMenu();
			selectMainMenu();
		}
	}
	
	public void printMainMenu() {
		System.out.println(Shop.getName() + " ===============");
		if(Shop.Log == -1) {
			System.out.println("1. 회원가입\n2. 로그인\n3. 관리자모드\n0. 종료");
			System.out.print("입력 : ");
		}
		else {
			System.out.println("1. 로그아웃\n2. 상품구매\n3. 장바구니\n0. 종료");
			System.out.print("입력 : ");
		}
	}
	
	public void selectMainMenu() {
		if(Shop.Log == -1) {
			String input = Shop.scan.next();
			try {
				int sel = Integer.parseInt(input);
				if(sel >= 0 && sel <= 2) {
					if(sel == 1) {
						this.um.newUser();
					}
					else if(sel == 2) {
						
					}
					else if(sel == 3) {
						
					}
					else if(sel == 0) this.end = true;
				}
			} catch (Exception e) {
				System.out.println("유효하지 않은 입력입니다.");
			}
		}
		else {
			String input = Shop.scan.next();
			try {
				int sel = Integer.parseInt(input);
				if(sel >= 0 && sel <= 2) {
					if(sel == 1) {
						Shop.Log = -1;
						System.out.println("안녕히 가십시오.");
					}
					else if(sel == 2) {
						
					}
					else if(sel == 3) {
						
					}
					else if(sel == 0) this.end = true;
				}
			} catch (Exception e) {
				System.out.println("유효하지 않은 입력입니다.");
			}
		}
	}
}
