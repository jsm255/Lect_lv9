package controllers;

import java.util.ArrayList;

import models.Category;
import models.Shop;

public class CategoryManager {
	private ArrayList<Category> cats = new ArrayList<>();
	
	private ItemManager im = ItemManager.instance;
	
	public static CategoryManager instance = new CategoryManager();
	
	private CategoryManager() {}
	
	public void 초기셋업메서드() {
		this.cats.add(new Category("기본"));
		this.im.newItem(this.cats.get(0).getCategoryName(), "풍선껌", 500);
		this.im.newItem(this.cats.get(0).getCategoryName(), "막대사탕", 300);
	}
	
	public void printCategory() {
		System.out.println("카테고리 ===============");
		for(int i = 0; i<this.cats.size(); i++) {
			System.out.println((i+1)+") "+this.cats.get(i).getCategoryName());
		}
		System.out.print("카테고리 입력 : ");
	}
	
	public void selectCategory(int userCode) {
		String input = Shop.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			
			im.printItems(this.cats.get(sel).getCategoryName());
			im.selectItems(userCode);
		} catch (Exception e) {
			System.out.println("오류 발생!");
			e.printStackTrace();
		}
	}
}
