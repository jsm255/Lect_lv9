package models;

public class Basket {
	private int userCode;
	private String itemName;
	private int price;
	private int cnt = 0;
	
	public Basket(int userCode, String itemName, int price) {
		this.userCode = userCode;
		this.itemName = itemName;
		this.price = price;
		this.cnt ++;
	}
	
	public void plusCnt() {
		this.cnt ++;
	}
	
	public int getUserCode() {
		return this.userCode;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public int getCnt() {
		return this.cnt;
	}
	
	public int getPrice() {
		return this.price;
	}
}
