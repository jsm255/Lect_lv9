package controllers;

import java.util.ArrayList;

import models.Basket;
import models.Shop;

public class BasketManager {
	
	private ItemManager im = ItemManager.instance;
	private UserManager um = UserManager.instance;
	
	private ArrayList<Basket> baskets = new ArrayList<>();
	
	public static BasketManager instance = new BasketManager();
	
	private BasketManager() {}
	
	public void addInBasket(int userCode, String itemName, int price) {
		boolean found = false;
		for(int i = 0; i<this.baskets.size(); i++) {
			if(this.baskets.get(i).getUserCode() == userCode &&
					this.baskets.get(i).getItemName().equals(itemName) &&
					this.baskets.get(i).getPrice() == price) {
				found = true;
				this.baskets.get(i).plusCnt();
				System.out.println("장바구니에 수량이 추가되었습니다.");
			}
		}
		
		if(found == false) {
			this.baskets.add(new Basket(userCode,itemName,price));
			System.out.println("장바구니에 새로운 품목을 추가했습니다.");
		}
	}
	
	public void loadBasket(int userCode, String itemName, int price, int cnt) {
		this.baskets.add(new Basket(userCode, itemName, price, cnt));
	}
	
	public int getBasketSize() {
		return this.baskets.size();
	}
	
	public int getUserCode(int idx) {
		return this.baskets.get(idx).getUserCode();
	}
	
	public String getItemName(int idx) {
		return this.baskets.get(idx).getItemName();
	}
	
	public int getPrice(int idx) {
		return this.baskets.get(idx).getPrice();
	}
	
	public int getCnt(int idx) {
		return this.baskets.get(idx).getCnt();
	}
	
	public void 장바구니용메서드() {
		for(int i = 0; i<this.baskets.size(); i++) {
			System.out.println(this.baskets.get(i).getUserCode()+") "+
					this.baskets.get(i).getItemName()+" / 개당"+
					this.baskets.get(i).getPrice()+"원 / " + 
					this.baskets.get(i).getCnt()+"개");
		}
	}
	
	public void printBasket(int userCode) {
		int sum = 0;
		for(int i = 0; i<this.baskets.size(); i++) {
			if(this.baskets.get(i).getUserCode() == userCode) {
				System.out.println(this.baskets.get(i).getItemName()+" / "+
						this.baskets.get(i).getCnt()+"개");
				sum += (this.baskets.get(i).getCnt()*
						this.baskets.get(i).getPrice());
			}
		}
		if(sum > 0) {
			System.out.println("총 금액 : "+sum+"원");
			System.out.println("결제하시겠습니까?");
			System.out.print("y / n : ");
			selectPay(userCode, sum);
		}
		else System.out.println("장바구니에 물건이 없습니다!");
	}
	
	public void selectPay(int userCode, int sum) {
		String input = Shop.scan.next();
		
		if(input.equals("y")) {
			um.plusUserSpent(Shop.Log, sum);
			removePaidBasket(userCode);
			System.out.println("결제가 완료되었습니다.");
			Shop.plusIncome(sum);
		}
		else if(input.equals("n")) System.out.println("취소되었습니다.");
		else System.out.println("유효하지 않은 입력입니다. 결제를 취소합니다.");
	}
	
	public void removePaidBasket(int userCode) {
		for(int i = 0; i<this.baskets.size(); i++) {
			if(this.baskets.get(i).getUserCode() == userCode) {
				this.baskets.remove(i);
				i --;	// arrayList니까 하나 줄여줘야 다 돌려본다!
			}
		}
	}
	
	public void removeItemsFromBasket(String itemName, int price) {
		for(int i = 0; i<this.baskets.size(); i++) {
			if(this.baskets.get(i).getItemName().equals(itemName) &&
					this.baskets.get(i).getPrice() == price) {
				this.baskets.remove(i);
				i --;	
			}
		}
	}
	
	public void printAllBaskets() {
		for(int i = 0; i<this.baskets.size(); i++) {
			System.out.println((i+1)+") "+this.baskets.get(i).getUserCode()+
					" - "+this.baskets.get(i).getItemName()+" / 개당 "+
					this.baskets.get(i).getPrice()+"원 / "+
					this.baskets.get(i).getCnt()+"개");
		}
	}
	
	public void removeBasket() {
		printAllBaskets();
		
		System.out.print("삭제할 내역의 번호를 입력 : ");
		String input = Shop.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			if(sel >= 0 && sel < this.baskets.size()) {
				this.baskets.remove(sel);
				System.out.println("장바구니 내역 삭제 완료.");
			}
			else System.out.println("유효하지 않은 입력입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류!");
		}
	}
	
	public void resetBasket() {
		this.baskets = new ArrayList<>();
	}
}
