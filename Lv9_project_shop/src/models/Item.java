package models;

public class Item {
	private String catName;
	private String itemName;
	private int itemPrice;
	
	public Item(String catName, String name, int price) {
		this.catName = catName;
		this.itemName = name;
		this.itemPrice = price;
	}
	
	public String getCatName() {
		return this.catName;
	}
	
	public String getName() {
		return this.itemName;
	}
	
	public int getPrice() {
		return this.itemPrice;
	}
	
	public void setPrice(int price) {
		this.itemPrice = price;
	}
	
}
