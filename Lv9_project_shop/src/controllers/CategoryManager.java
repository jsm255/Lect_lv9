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
	}
	
	public void selectCategory(int userCode) {
		System.out.print("카테고리 번호 입력 : ");
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
	
	public String selectCategoryToAddItem() {
		System.out.println("품목을 추가할 카테고리의 번호를 입력 : ");
		String input = Shop.scan.next();
		
		try {
			int sel = Integer.parseInt(input)-1;
			
			if(sel >= 0 && sel < this.cats.size()) {
				return this.cats.get(sel).getCategoryName();
			}
			else return "실패!";
		} catch (Exception e) {
			e.printStackTrace();
			return "실패!";
		}
	}
	
	public void addCategory() {
		System.out.println("추가할 카테고리 입력 : ");
		String input = Shop.scan.next();
		
		boolean found = false;
		for(Category cat : this.cats) {
			if(input.equals(cat.getCategoryName())) found = true;
		}
		
		if(found == true) System.out.println("중복되는 카테고리 명입니다.");
		else {
			this.cats.add(new Category(input));
			System.out.println("추가 완료.");
		}
	}
	
	public void removeCategory(BasketManager bm) {
		System.out.println("삭제할 카테고리 입력 : ");
		String input = Shop.scan.next();
		
		int idx = -1;
		for(int i = 0; i<this.cats.size(); i++) {
			if(this.cats.get(i).getCategoryName().equals(input)) idx = i;
		}
		
		if(idx != -1) {
			im.removeCategoryItems(this.cats.get(idx).getCategoryName());
			System.out.println("카테고리, 카테고리내 품목, 해당 품목을 가진 장바구니를 모두 삭제했습니다.");
			this.cats.remove(idx);
		}
		else System.out.println("일치하는 카테고리가 없습니다.");
	}
	
	public void findCategory(int idx) {
		
	}
}
