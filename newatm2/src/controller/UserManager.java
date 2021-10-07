package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import models.Account;
import models.Bank;
import models.User;

// ctrl + shift + s ë¥? ?ˆ„ë¥´ë©´ ?”„ë¡œì ?Š¸ ?‚´ ëª¨ë“  ?ŒŒ?¼?´ ???¥?¨

public class UserManager {
	
	public static UserManager instance = new UserManager();
	private UserManager() {}
	// users : ì¤‘ì•™ ?œ ?? ì´? ?°?´?„°
	private ArrayList<User> users = new ArrayList<>();
	
	private String fileName = "ATM Project.txt";
	
	public void µğ¹ö±×¿ë¸Ş¼­µå() {
		for(User temp : this.users) {
			System.out.printf("%d\t%s\t%s\t%s\n",temp.getCode(),
					temp.getId(),temp.getPw(),temp.getName());
			for(int i = 0; i<temp.getAccCnt(); i++) {
				System.out.printf("%d\t%s\t%d?›\n",temp.getAccs(i).getAccCode(),
						temp.getAccs(i).getPw(),temp.getAccs(i).getMoney());
			}
		}
	}
	
	// ê°??…
	public void joinUser() {
		System.out.print("id : ");
		String id = Bank.scan.next();
		System.out.print("pw : ");
		String pw = Bank.scan.next();
		System.out.print("name : ");
		String name = Bank.scan.next();
		
		User newUser = new User(randomCode(), id, pw, name);
		this.users.add(newUser);
	}
	
	private int randomCode() {
		Random ran = new Random();
		while(true) {
			int code = ran.nextInt(9000) + 1000;
			
			boolean found = false;
			for(User temp : users) {
				if(temp.getCode() == code) found = true;
			}
			if(found == true) continue;
			else return code;
		}
	}
	
	public int login() {
		System.out.print("id : ");
		String id = Bank.scan.next();
		System.out.print("pw : ");
		String pw = Bank.scan.next();
		
		for(int i = 0; i<this.users.size(); i++) {
			if(users.get(i).getId().equals(id) && users.get(i).getPw().equals(pw))
				return i;
		}
		return -1;
	}
	
	public String getUserName() {
		return this.users.get(Bank.log).getName();
	}
	
	public int getAccCnt() {
		return this.users.get(Bank.log).getAccCnt();
	}
	
	public int getAccCnt(int idx) {
		return this.users.get(idx).getAccCnt();
	}
	
	public Account getAcc(int AccNum) {
		return this.users.get(Bank.log).getAccs(AccNum);
	}
	
	public Account getAcc(int idx, int AccNum) {
		return this.users.get(idx).getAccs(AccNum);
	}
	
	public void newAcc(int accCode, String pw) {
		this.users.get(Bank.log).newAcc(accCode, pw);
	}
	
	public void delAcc(int idx) {
		this.users.get(Bank.log).delAcc(idx);
	}
	
	public int getUsersSize() {
		return this.users.size();
	}
		
	public void quitMember(int log) {
		System.out.print("ë³¸ì¸ ?™•?¸?„ ?œ„?•´ pw ?…? ¥ : ");
		String pw = Bank.scan.next();
		
		boolean correct = false;
		if(pw.equals(this.users.get(log).getPw())) correct = true;
		
		if(correct == false) System.out.println("?¸ì¦ì— ?‹¤?Œ¨?–ˆ?Šµ?‹ˆ?‹¤.");
		else {
			this.users.remove(log);
			Bank.log = -1;
			System.out.println("?ƒˆ?‡´ê°? ?™„ë£Œë˜?—ˆ?Šµ?‹ˆ?‹¤.");
		}
	}
	
	public void checkFile() {
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			br.close();
			fr.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void saveAll() {
		try {
			FileWriter fw = new FileWriter(fileName);
			
			
			
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
