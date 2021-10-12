package controllers;

import java.util.ArrayList;
import java.util.Random;

import models.Shop;
import models.User;

public class UserManager {
	
	public static UserManager instance = new UserManager();
	
	private UserManager() {}
	
	private ArrayList<User> users = new ArrayList<>();
	
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
	
	public void login() {
		System.out.print("이름을 입력 : ");
		String name = Shop.scan.next();
		
		int cnt = 0;
		for(int i = 0; i<this.users.size(); i++) {
			if(this.users.get(i).getName().equals(name)) cnt ++;
		}
		
		if(cnt == 0) System.out.println("해당하는 이름의 유저가 없습니다.");
		else if(cnt == 1) {
			for(int i = 0; i<this.users.size(); i++) {
				if(this.users.get(i).getName().equals(name)) Shop.Log = i;
			}
			System.out.println("환영합니다.");
		}
		else {
			System.out.println("같은 이름을 가진 유저가 있습니다.");
			System.out.print("회원코드를 입력 : ");
			int code = Shop.scan.nextInt();
			
			for(int i = 0; i<this.users.size(); i++) {
				if(this.users.get(i).getName().equals(name) &&
						this.users.get(i).getCode() == code) Shop.Log = i;
			}
			
			if(Shop.Log == -1) System.out.println("이름과 코드가 일치하는 유저가 없습니다.");
			else System.out.println("환영합니다.");
		}
	}
	
}
