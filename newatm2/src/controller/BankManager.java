package controller;

import java.util.Scanner;

import models.Bank;

public class BankManager {
	
	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;

	public static BankManager instance = new BankManager();
	// BankManager�? ?��?��?���? ?��?��?��
	private BankManager() {}
	
	boolean isRun = true;

	public void run() {
		
		// ?��?�� ?��?��
		while(isRun) {
			System.out.println(Bank.getName() + "ATM");
			if(Bank.log == -1) System.out.println("?��?��?��?��?��. "+this.um.getUserName());
			else System.out.println("?��?��?��?��?��. Guest");
			����׿�޼���();
			printMainMenu();
			selectMainMenu();
		}
	}
	
	private void ����׿�޼���() {
		um.����׿�޼���();
	}
	
	private void printMainMenu() {
		if(Bank.log == -1) {
			System.out.println("1.로그?��\n2.?��?���??��\n3.종료\n9.�?리자모드");			
		}
		else {
			System.out.println("1.로그?��?��\n2.?��?��?��?��\n3.뱅킹메뉴");
		}

	}
	
	private void selectMainMenu() {
		String input = Bank.scan.next();
		
		try {
			int sel = Integer.parseInt(input);
			
			if(Bank.log == -1) {
				if(sel == 1) {
					Bank.log = um.login();
				}
				else if(sel == 2) {
					//?��?���??�� 메소?���? ?���?
					um.joinUser();
					// UserManager.instance.joinUser();
				}
				else if(sel == 3) {
					this.isRun = false;
				}
				else if(sel == 9) {
					// ?��?���?
					// ?��?��민일?�� - ?���? 처리
					// �?리자모드 -> ?��?�� 처리(???�� / 불러?���?)
					// ?���? ?��?? 조회 / ?���? 계좌 조회
					// ?��?��?�� 비번?? admin / 0000
					// �?리자 모드?�� ?��?��?�� ?��?��?�� 비번?�� ?��?��?���? ?���? ?��?��
					
					
				}
			}
			else {
				// 로그?�� ?��?��?�� ?��
				// 로그 ?��?��
				if(sel == 1) {
					Bank.log = -1;
					System.out.println("로그?��?�� 처리 ?���?.");
				}
				// ?��?�� ?��?��
				else if(sel == 2) this.um.quitMember(Bank.log);
				
				// 뱅킹 기능
				else if(sel == 3) {
					printBankingMenu();
					selectBankingMenu();
				}
			}
		} catch (Exception e) {
			System.out.println("?��무튼 뭔�? ?�� �? ?��?��?��?�� ?��?��");
		}
	}
	

	private void printBankingMenu() {
		System.out.println("1.계좌개설\n2.계좌철회\n3.?���?");
		System.out.println("4.출금\n5.?���?\n6.조회");
	}
	
	private void selectBankingMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// 계좌 개설
				this.am.makeAccount();
			}
			else if(sel == 2) {		// 계좌 철회
				this.am.deleteAccount();
			}
			else if(sel == 3) {		// ?���?
				this.am.deposit();
			}
			else if(sel == 4) {		// 출금
				this.am.withdraw();
			}
			else if(sel == 5) {		// ?���?
				this.am.transfer();
			}
			else if(sel == 6) {		// 조회
				this.am.check();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void adminLogin() {
		System.out.print("�?리자 id ?��?��(admin) : ");
		String id = Bank.scan.next();
		
		System.out.print("�?리자 pw ?��?��(0000) : ");
		String pw = Bank.scan.next();
		
		if(id.equals("admin") && pw.equals("0000")) {
			Bank.log = -5;
			while(Bank.log == -5) {
				printAdminMenu();
			}
		}
		else System.out.println("?��보�? ?��치하�? ?��?��?��?��.");
	}
	
	private void printAdminMenu() {
		System.out.println("1.???��\n2.로드\n3.?���? ?��?? 조회\n4.?���? 계좌 조회\n9.종료");
	}
	private void selectAdminMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// ???��
				this.am.makeAccount();
			}
			else if(sel == 2) {		// 로드
				this.am.deleteAccount();
			}
			else if(sel == 3) {		// ?���? ?��?? 조회
				this.am.deposit();
			}
			else if(sel == 4) {		// ?���? 계좌 조회
				this.am.withdraw();
			}
			else if(sel == 9) {		// 종료
				this.am.transfer();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
