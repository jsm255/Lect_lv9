package controllers;

import java.util.ArrayList;

import models.Basket;

public class BasketManager {
	
	private ArrayList<Basket> baskets = new ArrayList<>();
	
	public static BasketManager instance = new BasketManager();
	
	private BasketManager() {}
	
	public void addInBasket(int userCode, String itemName) {
		boolean found = false;
		for(int i = 0; i<this.baskets.size(); i++) {
			if(this.baskets.get(i).getUserCode() == userCode &&
					this.baskets.get(i).getItemName().equals(itemName)) {
				found = true;
				this.baskets.get(i).plusCnt();
				System.out.println("장바구니에 수량이 추가되었습니다.");
			}
		}
		
		if(found == false) {
			this.baskets.add(new Basket(userCode,itemName));
			System.out.println("장바구니에 새로운 품목을 추가했습니다.");
		}
	}
	
	public void 장바구니용메서드() {
		for(int i = 0; i<this.baskets.size(); i++) {
			System.out.println(this.baskets.get(i).getUserCode()+") "+
					this.baskets.get(i).getItemName()+" / "+
					this.baskets.get(i).getCnt()+"개");
		}
	}
}
