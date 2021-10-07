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
		
		this.um.checkFile();
		// ?‹¤?–‰ ?‹œ?‘
		while(isRun) {
			System.out.println(Bank.getName() + "ATM");
			if(Bank.log != -1) System.out.println("È¯¿µÇÕ´Ï´Ù. "+this.um.getUserName());
			else System.out.println("È¯¿µÇÕ´Ï´Ù. Guest");
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
			System.out.println("1.·Î±×ÀÎ\n2.È¸¿ø°¡ÀÔ\n3.Á¾·á\n9.°ü¸®ÀÚ ¸ğµå");			
		}
		else {
			System.out.println("1.·Î±×¾Æ¿ô\n2.È¸¿øÅ»Åğ\n3.¹ğÅ·¸Ş´º");
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
					
					adminLogin();
				}
			}
			else {
				// ë¡œê·¸?¸ ?˜?—ˆ?„ ?•Œ
				// ë¡œê·¸ ?•„?›ƒ
				if(sel == 1) {
					Bank.log = -1;
					System.out.println("·Î±×¾Æ¿ôµÇ¾ú½À´Ï´Ù.");
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
			System.out.println("¾Æ¹«Æ° ¹º°¡ Àß ¸ø µÆ´Ù´Â ¸Ş¼¼Áö");
		}
	}
	

	private void printBankingMenu() {
		System.out.println("1.°èÁÂ °³¼³\n2.°èÁÂ Ã¶È¸\n3.ÀÔ±İ");
		System.out.println("4.Ãâ±İ\n5.ÀÌÃ¼\n6.Á¶È¸");
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
		System.out.print("°ü¸®ÀÚ id¸¦ ÀÔ·Â(admin) : ");
		String id = Bank.scan.next();
		
		System.out.print("°ü¸®ÀÚ pw¸¦ ÀÔ·Â(0000) : ");
		String pw = Bank.scan.next();
		
		if(id.equals("admin") && pw.equals("0000")) {
			Bank.log = -5;
			while(Bank.log == -5) {
				printAdminMenu();
				selectAdminMenu();
			}
		}
		else System.out.println("ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù..");
	}
	
	private void printAdminMenu() {
		System.out.println("1.ÀúÀå\n2.·Îµå\n3.ÀüÃ¼ À¯Àú Á¶È¸\n4.ÀüÃ¼ °èÁÂ Á¶È¸\n9.°ü¸®ÀÚ ¸ğµå Á¾·á");
	}
	private void selectAdminMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// ???¥
				this.um.saveAll();
			}
			else if(sel == 2) {		// ë¡œë“œ
				this.um.loadAll();
			}
			else if(sel == 3) {		// ? „ì²? ?œ ?? ì¡°íšŒ
				this.um.checkAllUsers();
			}
			else if(sel == 4) {		// ? „ì²? ê³„ì¢Œ ì¡°íšŒ
				this.um.checkAllAccs();
			}
			else if(sel == 9) {		// ì¢…ë£Œ
				Bank.log = -1;
				System.out.println("°ü¸®ÀÚ ¸ğµå Á¾·á.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
