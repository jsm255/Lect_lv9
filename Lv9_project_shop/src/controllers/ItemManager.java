package controllers;

import java.util.ArrayList;

import models.Item;
import models.Shop;

public class ItemManager {
	
	private BasketManager bm = BasketManager.instance;
	
	public static ItemManager instance = new ItemManager();
	
	private ArrayList<Item> items = new ArrayList<>();
	
	ArrayList<Item> temp = new ArrayList<>();
	
	private ItemManager() {}
	
	public void newItem(String catName, String name, int price) {
		this.items.add(new Item(catName, name, price));
	}
	
	public int getItemPrice(int idx) {
		return this.items.get(idx).getPrice();
	}
	
	public void checkItems(String catName) {
		int cnt = 0;
		for(int i = 0; i<this.items.size(); i++) {
			if(this.items.get(i).getCatName().equals(catName)) cnt++;
		}
		
		if(cnt == 0) System.out.println("해당 카테고리에는 진열된 품목이 없습니다.");
		else printItems(catName);
	}
	
	public void printItems(String catName) {
		this.temp = new ArrayList<>();
		int displayNumber = 1;
		for(int i = 0; i<this.items.size(); i++) {
			if(this.items.get(i).getCatName().equals(catName)) {
				System.out.println((displayNumber)+") "+this.items.get(i).getName()+
						" - "+this.items.get(i).getPrice()+"원");
				displayNumber ++;
				this.temp.add(new Item(catName,
						this.items.get(i).getName(),this.items.get(i).getPrice()));
			}
		}
		
		System.out.print("구매할 품목번호를 입력 : ");
	}
	
	public void selectItems(int userCode) {
		String input = Shop.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
	
			bm.addInBasket(userCode, this.temp.get(sel).getName(),
					this.temp.get(sel).getPrice());
			
			
		} catch (Exception e) {
			System.out.println("오류 발생!");
		}
	}
	
	public void removeCategoryItems(String catName) {
		for(int i = 0; i<this.items.size(); i++) {
			if(this.items.get(i).getCatName().equals(catName)) {
				bm.removeCategoryBasket(this.items.get(i).getName(),
						this.items.get(i).getPrice());
				
				this.items.remove(i);
				i --;
			}
		}
	}
}
