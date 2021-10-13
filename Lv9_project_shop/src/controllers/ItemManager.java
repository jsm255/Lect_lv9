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
	}
	
	public void selectItems(int userCode) {
		System.out.print("구매할 품목번호를 입력 : ");
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
				bm.removeItemsFromBasket(this.items.get(i).getName(),
						this.items.get(i).getPrice());
				
				this.items.remove(i);
				i --;
			}
		}
	}
	
	public void printAllItems() {
		for(int i = 0; i<this.items.size(); i++) {
			System.out.println((i+1)+") "+this.items.get(i).getCatName()+" - "+
					this.items.get(i).getName()+" / "+
					this.items.get(i).getPrice()+"원");
		}
	}
	
	public void addItem(CategoryManager cm) {
		cm.printCategory();
		String catName = cm.selectCategoryToAddItem();
		
		if(catName.equals("실패!")) System.out.println("유효하지 않은 입력입니다.");
		else {
			System.out.print("품목 이름을 입력 : ");
			String name = Shop.scan.next();
			
			System.out.print("품목의 가격을 입력 : ");
			String price = Shop.scan.next();
			
			try {
				int itemPrice = Integer.parseInt(price);
				
				boolean found = false;
				for(int i = 0; i<this.items.size(); i++) {
					if(this.items.get(i).getName().equals(name) &&
							this.items.get(i).getPrice() == itemPrice)
						found = true;
				}
				
				if(found == true) System.out.println("품목명과 가격은 동시에 중복될 수 없습니다.");
				else {
					this.items.add(new Item(catName, name, itemPrice));
					
					System.out.println("품목 추가완료!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("숫자를 적어주세요!");
			}
		}
	}
	
	public void removeItem(BasketManager bm) {
		for(int i = 0; i<this.items.size(); i++) {
			System.out.println((i+1)+") "+this.items.get(i).getCatName()+" - "+
					this.items.get(i).getName()+" / " +
					this.items.get(i).getPrice()+"원");
		}
		
		System.out.print("삭제할 품목의 번호를 입력 : ");
		String input = Shop.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			
			if(sel >= 0 && sel < this.items.size()) {
				bm.removeItemsFromBasket(this.items.get(sel).getName(),
						this.items.get(sel).getPrice());
				this.items.remove(sel);
				
				System.out.println("품목리스트와 장바구니내의 해당 품목을 제거했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("숫자를! 입력! 하세요!");
		}
	}
}
