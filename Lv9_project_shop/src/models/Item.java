package models;

public class Item {
	private String itemName;
	private int itemPrice;
	
	public Item(String name, int price) {
		this.itemName = name;
		this.itemPrice = price;
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
