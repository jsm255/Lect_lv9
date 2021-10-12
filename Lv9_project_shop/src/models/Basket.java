package models;

public class Basket {
	private String itemName;
	private int cnt = 0;
	
	public Basket(String itemName) {
		this.itemName = itemName;
		this.cnt ++;
	}
	
	public void plusCnt() {
		this.cnt ++;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public int getCnt() {
		return this.cnt;
	}
}
