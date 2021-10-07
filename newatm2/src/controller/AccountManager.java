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
		if(um.getAccCnt() >= 3) System.out.println("계좌 개수가 이미 최대입니다.");
		else {
			System.out.print("지정할 비밀번호를 입력 : ");
			String pw = Bank.scan.next();
			
			um.newAcc(randomAccCode(um), pw);
			System.out.println("계좌가 생성되었습니다.");
			System.out.println("생성 축하금 천원이 지급되었습니다.");
			System.out.println("계좌정보 ---------------");
			System.out.printf("계좌번호 : %d  비밀번호 : %s  계좌잔액 : %d\n",
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
		if(um.getAccCnt() == 0) System.out.println("계좌가 없습니다.");
		else {
			System.out.println("계좌 철회 메뉴입니다.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("해당하는 계좌가 없습니다.");
			else {
				System.out.print("pw 입력 : ");
				String pw = Bank.scan.next();
				
				if(um.getAcc(idx).getPw().equals(pw)) {
					um.delAcc(idx);
					System.out.println("철회가 완료되었습니다.");
				}
				else System.out.println("pw가 일치하지 않습니다.");
			}
		}
	}
	
	public int searchAccount(UserManager um) {
		System.out.print("계좌 번호를 입력 : ");
		int accCode = Bank.scan.nextInt();
		
		int idx = -1;
		for(int i = 0; i<um.getAccCnt(); i++) {
			if(um.getAcc(i).getAccCode() == accCode) idx = i;
		}
		return idx;
	}
	
	public void deposit() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("계좌가 없습니다.");
		else {
			System.out.println("입금 메뉴입니다.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("해당하는 계좌가 없습니다.");
			else {
				System.out.print("입금할 금액을 입력 : ");
				int money = Bank.scan.nextInt();
				
				if(money <= 0) System.out.println("0이하의 금액은 무효입니다.");
				else {
					um.getAcc(idx).changeMoney(money);
				}
			}
		}
	}
	
	public void withdraw() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("계좌가 없습니다.");
		else {
			System.out.println("출금 메뉴입니다.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("해당하는 계좌가 없습니다.");
			else {
				System.out.print("pw 입력 : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("출금할 금액을 입력 : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0이하의 금액은 무효입니다.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("계좌 잔액보다 많은 금액이 입력되었습니다!");
						else {
							um.getAcc(idx).changeMoney(-money);
							System.out.println("출금이 완료되었습니다.");
						}
					}
				}
				else System.out.println("pw가 일치하지 않습니다.");
			}
		}
	}
	
	public void transfer() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("계좌가 없습니다.");
		else if(um.getUsersSize() <= 1) System.out.println("이체할 사용자가 없습니다.");
		else {
			System.out.println("이체 메뉴입니다.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("해당하는 계좌가 없습니다.");
			else {
				System.out.print("pw 입력 : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("이체할 금액을 입력 : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0이하의 금액은 무효입니다.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("계좌 잔액보다 많은 금액을 입력했습니다!");
						else {
							System.out.print("이체받을 계좌를 입력 : ");
							int transCode = Bank.scan.nextInt();
							
							if(transCode == um.getAcc(idx).getAccCode()) System.out.println("현재 계좌입니다!");
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
									System.out.println("해당하는 계좌를 찾을 수 없었습니다.");
								else {
									um.getAcc(transUserIdx, transAccIdx).changeMoney(money);
									um.getAcc(idx).changeMoney(-money);
									System.out.println("이체가 완료되었습니다!");
								}
							}
						}
					}
				}
				else System.out.println("pw가 일치하지 않습니다.");
			}
		}
	}
	
	public void check() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("계좌가 없습니다.");
		else {
			System.out.println("조회 메뉴입니다.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("해당하는 계좌가 없습니다.");
			else {
				System.out.print("pw 입력 : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.println("계좌 번호 : "+um.getAcc(idx).getAccCode());
					System.out.println("계좌 잔액 : "+um.getAcc(idx).getMoney());
				}
				else System.out.println("pw가 일치하지 않습니다.");
			}
		}
	}
	
}
