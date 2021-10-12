package models;

public class User {
	private String name;
	private int spent;
	
	public User(String name) {
		this.name = name;
		this.spent = 0;
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
