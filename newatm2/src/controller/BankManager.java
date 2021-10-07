package controller;

import java.util.Scanner;

import models.Bank;

public class BankManager {
	
	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;

	public static BankManager instance = new BankManager();
	// BankManagerê°? ?œ ?¼?•˜ê²? ?˜?—ˆ?Œ
	private BankManager() {}
	
	boolean isRun = true;

	public void run() {
		
		// ?‹¤?–‰ ?‹œ?‘
		while(isRun) {
			System.out.println(Bank.getName() + "ATM");
			if(Bank.log == -1) System.out.println("?™˜?˜?•©?‹ˆ?‹¤. "+this.um.getUserName());
			else System.out.println("?™˜?˜?•©?‹ˆ?‹¤. Guest");
			µğ¹ö±×¿ë¸Ş¼­µå();
			printMainMenu();
			selectMainMenu();
		}
	}
	
	private void µğ¹ö±×¿ë¸Ş¼­µå() {
		um.µğ¹ö±×¿ë¸Ş¼­µå();
	}
	
	private void printMainMenu() {
		if(Bank.log == -1) {
			System.out.println("1.ë¡œê·¸?¸\n2.?šŒ?›ê°??…\n3.ì¢…ë£Œ\n9.ê´?ë¦¬ìëª¨ë“œ");			
		}
		else {
			System.out.println("1.ë¡œê·¸?•„?›ƒ\n2.?šŒ?›?ƒˆ?‡´\n3.ë±…í‚¹ë©”ë‰´");
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
					//?šŒ?›ê°??… ë©”ì†Œ?“œë¥? ?˜¸ì¶?
					um.joinUser();
					// UserManager.instance.joinUser();
				}
				else if(sel == 3) {
					this.isRun = false;
				}
				else if(sel == 9) {
					// ?–´?“œë¯?
					// ?–´?“œë¯¼ì¼?•Œ - ?”°ë¡? ì²˜ë¦¬
					// ê´?ë¦¬ìëª¨ë“œ -> ?ŒŒ?¼ ì²˜ë¦¬(???¥ / ë¶ˆëŸ¬?˜¤ê¸?)
					// ? „ì²? ?œ ?? ì¡°íšŒ / ? „ì²? ê³„ì¢Œ ì¡°íšŒ
					// ?•„?´?”” ë¹„ë²ˆ?? admin / 0000
					// ê´?ë¦¬ì ëª¨ë“œ?— ?™”?„?•Œ ?•„?´?”” ë¹„ë²ˆ?„ ?…? ¥?•˜ê²? ?•˜ë©? ? ?“¯
					
					
				}
			}
			else {
				// ë¡œê·¸?¸ ?˜?—ˆ?„ ?•Œ
				// ë¡œê·¸ ?•„?›ƒ
				if(sel == 1) {
					Bank.log = -1;
					System.out.println("ë¡œê·¸?•„?›ƒ ì²˜ë¦¬ ?™„ë£?.");
				}
				// ?šŒ?› ?ƒˆ?‡´
				else if(sel == 2) this.um.quitMember(Bank.log);
				
				// ë±…í‚¹ ê¸°ëŠ¥
				else if(sel == 3) {
					printBankingMenu();
					selectBankingMenu();
				}
			}
		} catch (Exception e) {
			System.out.println("?•„ë¬´íŠ¼ ë­”ê? ?˜ ëª? ?˜?—ˆ?‹¤?Š” ?‘œ?‹œ");
		}
	}
	

	private void printBankingMenu() {
		System.out.println("1.ê³„ì¢Œê°œì„¤\n2.ê³„ì¢Œì² íšŒ\n3.?…ê¸?");
		System.out.println("4.ì¶œê¸ˆ\n5.?´ì²?\n6.ì¡°íšŒ");
	}
	
	private void selectBankingMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// ê³„ì¢Œ ê°œì„¤
				this.am.makeAccount();
			}
			else if(sel == 2) {		// ê³„ì¢Œ ì² íšŒ
				this.am.deleteAccount();
			}
			else if(sel == 3) {		// ?…ê¸?
				this.am.deposit();
			}
			else if(sel == 4) {		// ì¶œê¸ˆ
				this.am.withdraw();
			}
			else if(sel == 5) {		// ?´ì²?
				this.am.transfer();
			}
			else if(sel == 6) {		// ì¡°íšŒ
				this.am.check();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void adminLogin() {
		System.out.print("ê´?ë¦¬ì id ?…? ¥(admin) : ");
		String id = Bank.scan.next();
		
		System.out.print("ê´?ë¦¬ì pw ?…? ¥(0000) : ");
		String pw = Bank.scan.next();
		
		if(id.equals("admin") && pw.equals("0000")) {
			Bank.log = -5;
			while(Bank.log == -5) {
				printAdminMenu();
			}
		}
		else System.out.println("? •ë³´ê? ?¼ì¹˜í•˜ì§? ?•Š?Šµ?‹ˆ?‹¤.");
	}
	
	private void printAdminMenu() {
		System.out.println("1.???¥\n2.ë¡œë“œ\n3.? „ì²? ?œ ?? ì¡°íšŒ\n4.? „ì²? ê³„ì¢Œ ì¡°íšŒ\n9.ì¢…ë£Œ");
	}
	private void selectAdminMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// ???¥
				this.am.makeAccount();
			}
			else if(sel == 2) {		// ë¡œë“œ
				this.am.deleteAccount();
			}
			else if(sel == 3) {		// ? „ì²? ?œ ?? ì¡°íšŒ
				this.am.deposit();
			}
			else if(sel == 4) {		// ? „ì²? ê³„ì¢Œ ì¡°íšŒ
				this.am.withdraw();
			}
			else if(sel == 9) {		// ì¢…ë£Œ
				this.am.transfer();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
