package models;

public class User {
	private int code;
	private String name;
	private int spent;
	
	public User(int code, String name) {
		this.code = code;
		this.name = name;
		this.spent = 0;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSpent() {
		return this.spent;
	}
	
	public void plusSpent(int spent) {
		this.spent += spent;
	}
}
