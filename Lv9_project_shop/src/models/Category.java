package models;

public class Category {
	private String catName;
	
	public Category(String name) {
		this.catName = name;
	}
	
	public String getCategoryName() {
		return this.catName;
	}
	
	public void changeCategoryName(String name) {
		this.catName = name;
	}
}
