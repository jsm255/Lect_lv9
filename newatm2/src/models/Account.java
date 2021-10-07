package models;

public class Account {
	int accCode;
	String pw;
	int money;
	
	
	public Account(int accCode, String pw) {
		this.accCode = accCode;
		this.pw = pw;
		this.money = 1000;
	}
	
	public int getAccCode() {
		return this.accCode;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void changeMoney(int money) {
		this.money += money;
	}
	
}
