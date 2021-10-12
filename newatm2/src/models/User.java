package models;

import java.util.ArrayList;

public class User {
	
	private int userCode;
	private String id;
	private String pw;
	private String name;
	private int accCnt;
	private int repAccIdx = -1;
	
	private ArrayList<Account> accs = new ArrayList<>();
	
	// 蹂댁쑀怨꾩쥖?쓽 媛앹껜二쇱냼 諛곗뿴
	
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
	
	public int getRepAccIdx() {
		return this.repAccIdx;
	}
	
	public void setRepAccIdx(int idx) {
		this.repAccIdx = idx-1;
	}
	
	public void newAcc(int accCode, String pw) {
		this.accs.add(new Account(accCode, pw));
		this.accCnt ++;
		if(this.accCnt == 1) this.repAccIdx = 0;
	}
	
	public void newAcc(int accCode, String pw, int money, int repAccIdx) {
		this.accs.add(new Account(accCode, pw, money));
		this.accCnt ++;
		this.repAccIdx = repAccIdx;
	}
	
	public void delAcc(int idx) {
		this.accs.remove(idx);
		this.accCnt --;
		if(this.accCnt == 0) this.repAccIdx = -1;
	}
	
	public void changeMoney(int idx, int money) {
		this.accs.get(idx).changeMoney(money);
	}
}
