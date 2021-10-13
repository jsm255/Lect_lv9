package controllers;

import models.Shop;

public class ShopManager {
	
	private CategoryManager cm = CategoryManager.instance;
	private ItemManager im = ItemManager.instance;
	private UserManager um = UserManager.instance;
	private BasketManager bm = BasketManager.instance;
	
	public static ShopManager instance = new ShopManager();
	
	private boolean end = false;
	
	private ShopManager() {}
	
	public void run() {
		this.cm.초기셋업메서드();
		while(this.end == false) {
			um.디버그용메서드();
			bm.장바구니용메서드();
			printMainMenu();
			selectMainMenu();
		}
	}
	
	public void printMainMenu() {
		System.out.println(Shop.getName() + " ===============");
		System.out.println("총 매출 "+Shop.getIncome()+"원");
		if(Shop.Log == -1) {
			System.out.println("환영합니다. Guest.");
			System.out.println("1. 회원가입\n2. 로그인\n3. 관리자모드\n0. 종료");
			System.out.print("입력 : ");
		}
		else {
			System.out.println("환영합니다. "+this.um.getUserName(Shop.Log)+".");
			System.out.println("1. 로그아웃\n2. 상품구매\n3. 장바구니\n0. 종료");
			System.out.print("입력 : ");
		}
	}
	
	public void selectMainMenu() {
		if(Shop.Log == -1) {
			String input = Shop.scan.next();
			try {
				int sel = Integer.parseInt(input);
				if(sel >= 0 && sel <= 3) {
					if(sel == 1) um.newUser();
					else if(sel == 2) um.login();
					else if(sel == 3) um.printAdminMenu();
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
				if(sel >= 0 && sel <= 3) {
					if(sel == 1) {
						Shop.Log = -1;
						System.out.println("안녕히 가십시오.");
					}
					else if(sel == 2) {
						cm.printCategory();
						cm.selectCategory(um.getUserCode(Shop.Log));
					}
					else if(sel == 3) {
						bm.printBasket(um.getUserCode(Shop.Log));
					}
					else if(sel == 0) this.end = true;
				}
			} catch (Exception e) {
				System.out.println("유효하지 않은 입력입니다.");
			}
		}
	}
}
