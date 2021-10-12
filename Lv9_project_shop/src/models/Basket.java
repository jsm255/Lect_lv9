package models;

public class Basket {
	private int userCode;
	private String itemName;
	private int cnt = 0;
	
	public Basket(int userCode, String itemName) {
		this.userCode = userCode;
		this.itemName = itemName;
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
}
