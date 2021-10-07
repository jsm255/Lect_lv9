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
		
		this.um.checkFile();
		// ?��?�� ?��?��
		while(isRun) {
			System.out.println(Bank.getName() + "ATM");
			if(Bank.log != -1) System.out.println("ȯ���մϴ�. "+this.um.getUserName());
			else System.out.println("ȯ���մϴ�. Guest");
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
			System.out.println("1.�α���\n2.ȸ������\n3.����\n9.������ ���");			
		}
		else {
			System.out.println("1.�α׾ƿ�\n2.ȸ��Ż��\n3.��ŷ�޴�");
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
					
					adminLogin();
				}
			}
			else {
				// 로그?�� ?��?��?�� ?��
				// 로그 ?��?��
				if(sel == 1) {
					Bank.log = -1;
					System.out.println("�α׾ƿ��Ǿ����ϴ�.");
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
			System.out.println("�ƹ�ư ���� �� �� �ƴٴ� �޼���");
		}
	}
	

	private void printBankingMenu() {
		System.out.println("1.���� ����\n2.���� öȸ\n3.�Ա�");
		System.out.println("4.���\n5.��ü\n6.��ȸ");
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
		System.out.print("������ id�� �Է�(admin) : ");
		String id = Bank.scan.next();
		
		System.out.print("������ pw�� �Է�(0000) : ");
		String pw = Bank.scan.next();
		
		if(id.equals("admin") && pw.equals("0000")) {
			Bank.log = -5;
			while(Bank.log == -5) {
				printAdminMenu();
				selectAdminMenu();
			}
		}
		else System.out.println("��ġ���� �ʽ��ϴ�..");
	}
	
	private void printAdminMenu() {
		System.out.println("1.����\n2.�ε�\n3.��ü ���� ��ȸ\n4.��ü ���� ��ȸ\n9.������ ��� ����");
	}
	private void selectAdminMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// ???��
				this.um.saveAll();
			}
			else if(sel == 2) {		// 로드
				this.um.loadAll();
			}
			else if(sel == 3) {		// ?���? ?��?? 조회
				this.um.checkAllUsers();
			}
			else if(sel == 4) {		// ?���? 계좌 조회
				this.um.checkAllAccs();
			}
			else if(sel == 9) {		// 종료
				Bank.log = -1;
				System.out.println("������ ��� ����.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
