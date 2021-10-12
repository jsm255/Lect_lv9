package models;

import java.util.ArrayList;

public class Category {
	private String catName;
	private ArrayList<Item> items = new ArrayList<>();
	
	public Category(String name) {
		this.catName = name;
	}
	
	public String getCategoryName() {
		return this.catName;
	}
	
	public Item getItemArray(int idx) {
		return this.items.get(idx);
	}
	
	public void changeCategoryName(String name) {
		this.catName = name;
	}
}
