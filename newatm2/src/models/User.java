package models;

import java.util.ArrayList;

public class User {
	
	private int userCode;
	private String id;
	private String pw;
	private String name;
	private int accCnt;
	
	private ArrayList<Account> accs = new ArrayList<>();
	
	// 보유계좌?�� 객체주소 배열
	
	public User(int userCode, String id, String pw, String name) {
		this.userCode = userCode;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public int getCode() {
		return this.userCode;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAccCnt() {
		return this.accCnt;
	}
	
	public Account getAccs(int idx) {
		return this.accs.get(idx);
	}
	
	public void newAcc(int accCode, String pw) {
		this.accs.add(new Account(accCode, pw));
		this.accCnt ++;
	}
	
	public void newAcc(int accCode, String pw, int money) {
		this.accs.add(new Account(accCode, pw, money));
		this.accCnt ++;
	}
	
	public void delAcc(int idx) {
		this.accs.remove(idx);
		this.accCnt --;
	}
	
	public void changeMoney(int idx, int money) {
		this.accs.get(idx).changeMoney(money);
	}
}
