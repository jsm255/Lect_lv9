package controller;

import java.util.ArrayList;
import java.util.Random;

import models.Account;
import models.Bank;
import models.User;

public class AccountManager {
	
	public static AccountManager instance = new AccountManager();
	private AccountManager() {}
	
	public void makeAccount() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() >= 3) System.out.println("?΄λ―? ?΅?₯? μΆ©λΆ? ?μ§??κ³? ??΅??€.");
		else {
			System.out.print("κ³μ’? ?¬?©?  pwλ₯? ?? ₯ : ");
			String pw = Bank.scan.next();
			
			um.newAcc(randomAccCode(um), pw);
			System.out.println("κ³μ’ κ°μ€?΄ ?λ£λ??΅??€.");
			System.out.println("κ°μ€ μΆνκΈ? μ²? ??΄ ?κΈλ??΅??€.");
			System.out.println("κ³μ’ ?ΈλΆ? ? λ³? ---------------");
			System.out.printf("κ³μ’λ²νΈ : %d  λΉλ?λ²νΈ : %s  κ³μ’??‘ : %d\n",
					um.getAcc(um.getAccCnt()-1).getAccCode(),
					um.getAcc(um.getAccCnt()-1).getPw(),
					um.getAcc(um.getAccCnt()-1).getMoney());
		}
	}
	
	private int randomAccCode(UserManager um) {
		Random ran = new Random();
		while(true) {
			String tempCode = "";
			int temp1 = ran.nextInt(9000) + 1000;
			int temp2 = ran.nextInt(90) + 10;
			int temp3 = ran.nextInt(900) + 100;
			
			tempCode += String.valueOf(temp1);
			tempCode += String.valueOf(temp2);
			tempCode += String.valueOf(temp3);
			
			int code = Integer.parseInt(tempCode);
			
			boolean found = false;
			for(int i = 0; i<um.getAccCnt(); i++) {
				Account temp = um.getAcc(i);
				if(temp.getAccCode() == code) found = true;
			}
			if(found == true) continue;
			else return code;
		}
	}
	
	public void deleteAccount() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?΅?₯?΄ ??΅??€.");
		else {
			System.out.println("κ³μ’ μ² ν λ©λ΄???€.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?΄?Ή?? κ³μ’κ°? ??΅??€.");
			else {
				System.out.print("?΄?Ή κ³μ’? λΉλ?λ²νΈλ₯? ?? ₯ : ");
				String pw = Bank.scan.next();
				
				if(um.getAcc(idx).getPw().equals(pw)) {
					um.delAcc(idx);
					System.out.println("κ³μ’ μ² νκ°? ?λ£? ???΅??€.");
				}
				else System.out.println("pwκ°? ?ΌμΉνμ§? ??΅??€.");
			}
		}
	}
	
	public int searchAccount(UserManager um) {
		System.out.print("κ³μ’ λ²νΈλ₯? ?? ₯ : ");
		int accCode = Bank.scan.nextInt();
		
		int idx = -1;
		for(int i = 0; i<um.getAccCnt(); i++) {
			if(um.getAcc(i).getAccCode() == accCode) idx = i;
		}
		return idx;
	}
	
	public void deposit() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?΅?₯?΄ ??΅??€.");
		else {
			System.out.println("?κΈ? λ©λ΄???€.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?΄?Ή?? κ³μ’κ°? ??΅??€.");
			else {
				System.out.print("?κΈν  κΈμ‘? ?? ₯ : ");
				int money = Bank.scan.nextInt();
				
				if(money <= 0) System.out.println("0?΄?? κΈμ‘?? λ¬΄ν¨???€.");
				else {
					um.getAcc(idx).changeMoney(money);
				}
			}
		}
	}
	
	public void withdraw() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?΅?₯?΄ ??΅??€.");
		else {
			System.out.println("μΆκΈ λ©λ΄???€.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?΄?Ή?? κ³μ’κ°? ??΅??€.");
			else {
				System.out.print("pw ?? ₯ : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("μΆκΈ?  κΈμ‘? ?? ₯ : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0?΄?? κΈμ‘?? λ¬΄ν¨???€.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("?΅?₯? ?? κΈμ‘λ³΄λ€ ?° κΈμ‘???€!");
						else {
							um.getAcc(idx).changeMoney(-money);
							System.out.println("μΆκΈ ?λ£?!");
						}
					}
				}
				else System.out.println("pwκ°? ?ΌμΉνμ§? ??΅??€.");
			}
		}
	}
	
	public void transfer() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?΅?₯?΄ ??΅??€.");
		else if(um.getUsersSize() <= 1) System.out.println("?΄μ²΄λ°? ?¬??΄ ??΅??€.");
		else {
			System.out.println("?΄μ²? λ©λ΄???€.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?΄?Ή?? κ³μ’κ°? ??΅??€.");
			else {
				System.out.print("pw ?? ₯ : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("?΄μ²΄ν  κΈμ‘? ?? ₯ : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0?΄?? κΈμ‘?? λ¬΄ν¨???€.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("?΅?₯? ?? κΈμ‘λ³΄λ€ ?° κΈμ‘???€!");
						else {
							System.out.print("?΄μ²΄λ°? ?΅?₯λ²νΈλ₯? ?? ₯ : ");
							int transCode = Bank.scan.nextInt();
							
							if(transCode == um.getAcc(idx).getAccCode()) System.out.println("??¬ κ³μ’???€!");
							else {
								int transUserIdx = -1;
								int transAccIdx = -1;
								for(int i = 0; i<um.getUsersSize(); i++) {
									for(int j = 0; j<um.getAccCnt(i); j++) {
										Account temp = um.getAcc(i, j);
										if(temp.getAccCode() == transCode) {
											transUserIdx = i;
											transAccIdx = j;
										}
									}
								}
								
								if(transUserIdx == -1 || transAccIdx == -1)
									System.out.println("?΄?Ή?? κ³μ’λ₯? μ°Ύμ ? ???΅??€.");
								else {
									um.getAcc(transUserIdx, transAccIdx).changeMoney(money);
									um.getAcc(idx).changeMoney(-money);
									System.out.println("?΄μ²΄κ? ?λ£λ??΅??€!");
								}
							}
						}
					}
				}
				else System.out.println("pwκ°? ?ΌμΉνμ§? ??΅??€.");
			}
		}
	}
	
	public void check() {
		
	}
	
}
