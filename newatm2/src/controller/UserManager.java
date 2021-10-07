package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import models.Account;
import models.Bank;
import models.User;

// ctrl + shift + s 瑜? ?늻瑜대㈃ ?봽濡쒖젥?듃 ?궡 紐⑤뱺 ?뙆?씪?씠 ???옣?맖

public class UserManager {
	
	public static UserManager instance = new UserManager();
	private UserManager() {}
	// users : 以묒븰 ?쑀?? 珥? ?뜲?씠?꽣
	private ArrayList<User> users = new ArrayList<>();
	
	private String fileName = "ATM Project.txt";
	
	public void 디버그용메서드() {
		for(User temp : this.users) {
			System.out.printf("%d\t%s\t%s\t%s\n",temp.getCode(),
					temp.getId(),temp.getPw(),temp.getName());
			for(int i = 0; i<temp.getAccCnt(); i++) {
				System.out.printf("%d\t%s\t%d원\n",temp.getAccs(i).getAccCode(),
						temp.getAccs(i).getPw(),temp.getAccs(i).getMoney());
			}
		}
	}
	
	// 媛??엯
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
		System.out.print("본인 확인을 위해 pw를 입력 : ");
		String pw = Bank.scan.next();
		
		boolean correct = false;
		if(pw.equals(this.users.get(log).getPw())) correct = true;
		
		if(correct == false) System.out.println("비밀번호가 일치하지 않습니다.");
		else {
			this.users.remove(log);
			Bank.log = -1;
			System.out.println("탈퇴 처리가 완료되었습니다.");
		}
	}
	
	public void checkFile() {
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			br.readLine();
			
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("파일이 없습니다.");
			System.out.println("하나 작성합니다.");
			try {
				FileWriter fw = new FileWriter(fileName);
				
				fw.write("Dummy");
				
				fw.close();
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveAll() {
		try {
			FileWriter fw = new FileWriter(fileName);
			
			for(int i = 0; i<this.users.size(); i++) {
				fw.write(String.valueOf(this.users.get(i).getCode())+"/");
				fw.write(this.users.get(i).getId()+"/");
				fw.write(this.users.get(i).getPw()+"/");
				fw.write(this.users.get(i).getName());
				if(this.users.get(i).getAccCnt() != 0) {
					fw.write("\t");
					fw.write(String.valueOf(this.users.get(i).getAccCnt()));
					fw.write("\t");
					for(int j = 0; j<this.users.get(i).getAccCnt(); j++) {
						fw.write(String.valueOf(this.users.get(i).getAccs(j).getAccCode())+"/");
						fw.write(this.users.get(i).getAccs(j).getPw()+"/");
						fw.write(String.valueOf(this.users.get(i).getAccs(j).getMoney()));
						if(j != this.users.get(i).getAccCnt()-1) fw.write(" ");
					}
				}
				
				if(i != this.users.size()-1) fw.write("\n");
				
			}
			
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadAll() {
		try {
			FileReader fr = new FileReader(this.fileName);
			BufferedReader br = new BufferedReader(fr);
			
			this.users = new ArrayList<User>();
			
			int userIdx = 0;
			String getter = "";
			while((getter = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(getter,"\t");
				
				StringTokenizer info = new StringTokenizer(st.nextToken(),"/");
				
				this.users.add(new User(
						Integer.parseInt(info.nextToken()),info.nextToken(),
						info.nextToken(),info.nextToken()));
				
				int accCnt = Integer.parseInt(st.nextToken());
				StringTokenizer accs = new StringTokenizer(st.nextToken()," ");
				for(int i = 0; i<accCnt; i++) {
					StringTokenizer accInfo = new StringTokenizer(accs.nextToken(),"/");
					
					this.users.get(userIdx).newAcc(
							Integer.parseInt(accInfo.nextToken()),
							accInfo.nextToken(),Integer.parseInt(accInfo.nextToken()));
				}
				
				userIdx ++;
			}
			
			System.out.println("로드 완료.");
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkAllUsers() {
		for(int i = 0; i<this.users.size(); i++) {
			System.out.println("회원번호 : "+this.users.get(i).getCode()+" ==========");
			System.out.println("id : "+this.users.get(i).getId());
			System.out.println("pw : "+this.users.get(i).getPw());
			System.out.println("이름 : "+this.users.get(i).getName());
			
			if(i == this.users.size()-1) System.out.println("전체 "+(i+1)+"명 조회 완료.");
		}
	}
	
	public void checkAllAccs() {
		for(int i = 0; i<this.users.size(); i++) {
			System.out.printf("%s의 계좌 ===========\n",this.users.get(i).getName());
			for(int j = 0; j<this.users.get(i).getAccCnt(); j++) {
				System.out.println("계좌 번호 : "+this.users.get(i).getAccs(j).getAccCode());
				System.out.println("계좌 pw : "+this.users.get(i).getAccs(j).getPw());
				System.out.println("계좌 잔액 : "+this.users.get(i).getAccs(j).getMoney());
				if(j != this.users.get(i).getAccCnt()-1) System.out.println("==========");
			}
			System.out.println();
			
			if(i == this.users.size()-1) System.out.println("모든 사용자의 계좌 조회를 완료했습니다.");
		}
	}
}
