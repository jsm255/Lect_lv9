package controllers;

import java.util.ArrayList;

import models.Item;
import models.Shop;

public class ItemManager {
	public static ItemManager instance = new ItemManager();
	
	private ArrayList<Item> items = new ArrayList<>();
	
	private ItemManager() {}
	
	public void newItem(String catName, String name, int price) {
		this.items.add(new Item(catName, name, price));
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
		for(int i = 0; i<this.items.size(); i++) {
			if(this.items.get(i).getCatName().equals(catName)) {
				System.out.println((i+1)+") "+this.items.get(i).getName()+
						" - "+this.items.get(i).getPrice()+"원");
			}
		}
		
		System.out.print("구매할 품목을 입력 (0으로 종료) : ");
	}
	
	public void selectItems() {
		String input = Shop.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			if(sel >= 0 && sel < this.items.size()) {
				
			}
		} catch (Exception e) {
			System.out.println("유효하지 않은 입력입니다.");
		}
	}
}
