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
		if(um.getAccCnt() >= 3) System.out.println("?���? ?��?��?�� 충분?�� ?���??���? ?��?��?��?��.");
		else {
			System.out.print("계좌?�� ?��?��?�� pw�? ?��?�� : ");
			String pw = Bank.scan.next();
			
			um.newAcc(randomAccCode(um), pw);
			System.out.println("계좌 개설?�� ?��료되?��?��?��?��.");
			System.out.println("개설 축하�? �? ?��?�� ?��금되?��?��?��?��.");
			System.out.println("계좌 ?���? ?���? ---------------");
			System.out.printf("계좌번호 : %d  비�?번호 : %s  계좌?��?�� : %d\n",
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
		if(um.getAccCnt() == 0) System.out.println("?��?��?�� ?��?��?��?��.");
		else {
			System.out.println("계좌 철회 메뉴?��?��?��.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?��?��?��?�� 계좌�? ?��?��?��?��.");
			else {
				System.out.print("?��?�� 계좌?�� 비�?번호�? ?��?�� : ");
				String pw = Bank.scan.next();
				
				if(um.getAcc(idx).getPw().equals(pw)) {
					um.delAcc(idx);
					System.out.println("계좌 철회�? ?���? ?��?��?��?��?��.");
				}
				else System.out.println("pw�? ?��치하�? ?��?��?��?��.");
			}
		}
	}
	
	public int searchAccount(UserManager um) {
		System.out.print("계좌 번호�? ?��?�� : ");
		int accCode = Bank.scan.nextInt();
		
		int idx = -1;
		for(int i = 0; i<um.getAccCnt(); i++) {
			if(um.getAcc(i).getAccCode() == accCode) idx = i;
		}
		return idx;
	}
	
	public void deposit() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?��?��?�� ?��?��?��?��.");
		else {
			System.out.println("?���? 메뉴?��?��?��.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?��?��?��?�� 계좌�? ?��?��?��?��.");
			else {
				System.out.print("?��금할 금액?�� ?��?�� : ");
				int money = Bank.scan.nextInt();
				
				if(money <= 0) System.out.println("0?��?��?�� 금액?? 무효?��?��?��.");
				else {
					um.getAcc(idx).changeMoney(money);
				}
			}
		}
	}
	
	public void withdraw() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?��?��?�� ?��?��?��?��.");
		else {
			System.out.println("출금 메뉴?��?��?��.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?��?��?��?�� 계좌�? ?��?��?��?��.");
			else {
				System.out.print("pw ?��?�� : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("출금?�� 금액?�� ?��?�� : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0?��?��?�� 금액?? 무효?��?��?��.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("?��?��?�� ?��?�� 금액보다 ?�� 금액?��?��?��!");
						else {
							um.getAcc(idx).changeMoney(-money);
							System.out.println("출금 ?���?!");
						}
					}
				}
				else System.out.println("pw�? ?��치하�? ?��?��?��?��.");
			}
		}
	}
	
	public void transfer() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("?��?��?�� ?��?��?��?��.");
		else if(um.getUsersSize() <= 1) System.out.println("?��체받?�� ?��?��?�� ?��?��?��?��.");
		else {
			System.out.println("?���? 메뉴?��?��?��.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("?��?��?��?�� 계좌�? ?��?��?��?��.");
			else {
				System.out.print("pw ?��?�� : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("?��체할 금액?�� ?��?�� : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0?��?��?�� 금액?? 무효?��?��?��.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("?��?��?�� ?��?�� 금액보다 ?�� 금액?��?��?��!");
						else {
							System.out.print("?��체받?�� ?��?��번호�? ?��?�� : ");
							int transCode = Bank.scan.nextInt();
							
							if(transCode == um.getAcc(idx).getAccCode()) System.out.println("?��?�� 계좌?��?��?��!");
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
									System.out.println("?��?��?��?�� 계좌�? 찾을 ?�� ?��?��?��?��?��.");
								else {
									um.getAcc(transUserIdx, transAccIdx).changeMoney(money);
									um.getAcc(idx).changeMoney(-money);
									System.out.println("?��체�? ?��료되?��?��?��?��!");
								}
							}
						}
					}
				}
				else System.out.println("pw�? ?��치하�? ?��?��?��?��.");
			}
		}
	}
	
	public void check() {
		
	}
	
}
