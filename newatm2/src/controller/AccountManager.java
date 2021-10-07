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
		if(um.getAccCnt() >= 3) System.out.println("?´ë¯? ?†µ?¥?„ ì¶©ë¶„?ˆ ?†Œì§??•˜ê³? ?ˆ?Šµ?‹ˆ?‹¤.");
		else {
			System.out.print("ê³„ì¢Œ?— ?‚¬?š©?•  pwë¥? ?…? ¥ : ");
			String pw = Bank.scan.next();
			
			um.newAcc(randomAccCode(um), pw);
			System.out.println("ê³„ì¢Œ ê°œì„¤?´ ?™„ë£Œë˜?—ˆ?Šµ?‹ˆ?‹¤.");
			System.out.println("ê°œì„¤ ì¶•í•˜ê¸? ì²? ?›?´ ?…ê¸ˆë˜?—ˆ?Šµ?‹ˆ?‹¤.");
			System.out.println("ê³„ì¢Œ ?„¸ë¶? ? •ë³? ---------------");
			System.out.printf("ê³„ì¢Œë²ˆí˜¸ : %d  ë¹„ë?ë²ˆí˜¸ : %s  ê³„ì¢Œ?”?•¡ : %d\n",
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
		if(um.getAccCnt() == 0) System.out.println("?†µ?¥?´ ?—†?Šµ?‹ˆ?‹¤.");
		else {
			System.out.println("ê³„ì¢Œ ì² íšŒ ë©”ë‰´?…?‹ˆ?‹¤.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?•´?‹¹?•˜?Š” ê³„ì¢Œê°? ?—†?Šµ?‹ˆ?‹¤.");
			else {
				System.out.print("?•´?‹¹ ê³„ì¢Œ?˜ ë¹„ë?ë²ˆí˜¸ë¥? ?…? ¥ : ");
				String pw = Bank.scan.next();
				
				if(um.getAcc(idx).getPw().equals(pw)) {
					um.delAcc(idx);
					System.out.println("ê³„ì¢Œ ì² íšŒê°? ?™„ë£? ?˜?—ˆ?Šµ?‹ˆ?‹¤.");
				}
				else System.out.println("pwê°? ?¼ì¹˜í•˜ì§? ?•Š?Šµ?‹ˆ?‹¤.");
			}
		}
	}
	
	public int searchAccount(UserManager um) {
		System.out.print("ê³„ì¢Œ ë²ˆí˜¸ë¥? ?…? ¥ : ");
		int accCode = Bank.scan.nextInt();
		
		int idx = -1;
		for(int i = 0; i<um.getAccCnt(); i++) {
			if(um.getAcc(i).getAccCode() == accCode) idx = i;
		}
		return idx;
	}
	
	public void deposit() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?†µ?¥?´ ?—†?Šµ?‹ˆ?‹¤.");
		else {
			System.out.println("?…ê¸? ë©”ë‰´?…?‹ˆ?‹¤.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?•´?‹¹?•˜?Š” ê³„ì¢Œê°? ?—†?Šµ?‹ˆ?‹¤.");
			else {
				System.out.print("?…ê¸ˆí•  ê¸ˆì•¡?„ ?…? ¥ : ");
				int money = Bank.scan.nextInt();
				
				if(money <= 0) System.out.println("0?´?•˜?˜ ê¸ˆì•¡?? ë¬´íš¨?…?‹ˆ?‹¤.");
				else {
					um.getAcc(idx).changeMoney(money);
				}
			}
		}
	}
	
	public void withdraw() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?†µ?¥?´ ?—†?Šµ?‹ˆ?‹¤.");
		else {
			System.out.println("ì¶œê¸ˆ ë©”ë‰´?…?‹ˆ?‹¤.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?•´?‹¹?•˜?Š” ê³„ì¢Œê°? ?—†?Šµ?‹ˆ?‹¤.");
			else {
				System.out.print("pw ?…? ¥ : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("ì¶œê¸ˆ?•  ê¸ˆì•¡?„ ?…? ¥ : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0?´?•˜?˜ ê¸ˆì•¡?? ë¬´íš¨?…?‹ˆ?‹¤.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("?†µ?¥?— ?ˆ?Š” ê¸ˆì•¡ë³´ë‹¤ ?° ê¸ˆì•¡?…?‹ˆ?‹¤!");
						else {
							um.getAcc(idx).changeMoney(-money);
							System.out.println("ì¶œê¸ˆ ?™„ë£?!");
						}
					}
				}
				else System.out.println("pwê°? ?¼ì¹˜í•˜ì§? ?•Š?Šµ?‹ˆ?‹¤.");
			}
		}
	}
	
	public void transfer() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?†µ?¥?´ ?—†?Šµ?‹ˆ?‹¤.");
		else if(um.getUsersSize() <= 1) System.out.println("?´ì²´ë°›?„ ?‚¬?Œ?´ ?—†?Šµ?‹ˆ?‹¤.");
		else {
			System.out.println("?´ì²? ë©”ë‰´?…?‹ˆ?‹¤.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?•´?‹¹?•˜?Š” ê³„ì¢Œê°? ?—†?Šµ?‹ˆ?‹¤.");
			else {
				System.out.print("pw ?…? ¥ : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("?´ì²´í•  ê¸ˆì•¡?„ ?…? ¥ : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0?´?•˜?˜ ê¸ˆì•¡?? ë¬´íš¨?…?‹ˆ?‹¤.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("?†µ?¥?— ?ˆ?Š” ê¸ˆì•¡ë³´ë‹¤ ?° ê¸ˆì•¡?…?‹ˆ?‹¤!");
						else {
							System.out.print("?´ì²´ë°›?„ ?†µ?¥ë²ˆí˜¸ë¥? ?…? ¥ : ");
							int transCode = Bank.scan.nextInt();
							
							if(transCode == um.getAcc(idx).getAccCode()) System.out.println("?˜„?¬ ê³„ì¢Œ?…?‹ˆ?‹¤!");
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
									System.out.println("?•´?‹¹?•˜?Š” ê³„ì¢Œë¥? ì°¾ì„ ?ˆ˜ ?—†?—ˆ?Šµ?‹ˆ?‹¤.");
								else {
									um.getAcc(transUserIdx, transAccIdx).changeMoney(money);
									um.getAcc(idx).changeMoney(-money);
									System.out.println("?´ì²´ê? ?™„ë£Œë˜?—ˆ?Šµ?‹ˆ?‹¤!");
								}
							}
						}
					}
				}
				else System.out.println("pwê°? ?¼ì¹˜í•˜ì§? ?•Š?Šµ?‹ˆ?‹¤.");
			}
		}
	}
	
	public void check() {
		
	}
	
}
