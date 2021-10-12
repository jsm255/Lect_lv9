package controllers;

import java.util.ArrayList;
import java.util.Random;

import models.Shop;
import models.User;

public class UserManager {
	
	public static UserManager instance = new UserManager();
	
	private UserManager() {}
	
	public ArrayList<User> users = new ArrayList<>();
	
	public int getUserCode(int idx) {
		return this.users.get(idx).getCode();
	}
	
	public String getUserName(int idx) {
		return this.users.get(idx).getName();
	}
	
	public int getUserSpent(int idx) {
		return this.users.get(idx).getSpent();
	}
	
	public void 디버그용메서드() {
		for(User temp : this.users) {
			System.out.println(temp.getCode()+" "+temp.getName()+" "+temp.getSpent()+"원");
		}
	}
	
	public void newUser() {
		System.out.print("이름을 입력 : ");
		String name = Shop.scan.next();
		
		this.users.add(new User(randomCode(), name));
	}
	
	public int randomCode() {
		Random ran = new Random();
		while(true) {
			int code = ran.nextInt(9000)+1000;
			boolean found = false;
			for(User temp : this.users) {
				if(temp.getCode() == code) found = true;
			}
			if (found == false) return code;
		}
	}
	
}
