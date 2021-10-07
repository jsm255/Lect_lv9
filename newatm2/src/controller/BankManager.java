package controller;

import java.util.Scanner;

import models.Bank;

public class BankManager {
	
	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;

	public static BankManager instance = new BankManager();
	// BankManagerκ°? ? ?Ό?κ²? ???
	private BankManager() {}
	
	boolean isRun = true;

	public void run() {
		
		// ?€? ??
		while(isRun) {
			System.out.println(Bank.getName() + "ATM");
			if(Bank.log == -1) System.out.println("???©??€. "+this.um.getUserName());
			else System.out.println("???©??€. Guest");
			΅πΉφ±ΧΏλΈήΌ­΅ε();
			printMainMenu();
			selectMainMenu();
		}
	}
	
	private void ΅πΉφ±ΧΏλΈήΌ­΅ε() {
		um.΅πΉφ±ΧΏλΈήΌ­΅ε();
	}
	
	private void printMainMenu() {
		if(Bank.log == -1) {
			System.out.println("1.λ‘κ·Έ?Έ\n2.??κ°??\n3.μ’λ£\n9.κ΄?λ¦¬μλͺ¨λ");			
		}
		else {
			System.out.println("1.λ‘κ·Έ??\n2.????΄\n3.λ±νΉλ©λ΄");
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
					//??κ°?? λ©μ?λ₯? ?ΈμΆ?
					um.joinUser();
					// UserManager.instance.joinUser();
				}
				else if(sel == 3) {
					this.isRun = false;
				}
				else if(sel == 9) {
					// ?΄?λ―?
					// ?΄?λ―ΌμΌ? - ?°λ‘? μ²λ¦¬
					// κ΄?λ¦¬μλͺ¨λ -> ??Ό μ²λ¦¬(???₯ / λΆλ¬?€κΈ?)
					// ? μ²? ? ?? μ‘°ν / ? μ²? κ³μ’ μ‘°ν
					// ??΄? λΉλ²?? admin / 0000
					// κ΄?λ¦¬μ λͺ¨λ? ??? ??΄? λΉλ²? ?? ₯?κ²? ?λ©? ? ?―
					
					
				}
			}
			else {
				// λ‘κ·Έ?Έ ??? ?
				// λ‘κ·Έ ??
				if(sel == 1) {
					Bank.log = -1;
					System.out.println("λ‘κ·Έ?? μ²λ¦¬ ?λ£?.");
				}
				// ?? ??΄
				else if(sel == 2) this.um.quitMember(Bank.log);
				
				// λ±νΉ κΈ°λ₯
				else if(sel == 3) {
					printBankingMenu();
					selectBankingMenu();
				}
			}
		} catch (Exception e) {
			System.out.println("?λ¬΄νΌ λ­κ? ? λͺ? ???€? ??");
		}
	}
	

	private void printBankingMenu() {
		System.out.println("1.κ³μ’κ°μ€\n2.κ³μ’μ² ν\n3.?κΈ?");
		System.out.println("4.μΆκΈ\n5.?΄μ²?\n6.μ‘°ν");
	}
	
	private void selectBankingMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// κ³μ’ κ°μ€
				this.am.makeAccount();
			}
			else if(sel == 2) {		// κ³μ’ μ² ν
				this.am.deleteAccount();
			}
			else if(sel == 3) {		// ?κΈ?
				this.am.deposit();
			}
			else if(sel == 4) {		// μΆκΈ
				this.am.withdraw();
			}
			else if(sel == 5) {		// ?΄μ²?
				this.am.transfer();
			}
			else if(sel == 6) {		// μ‘°ν
				this.am.check();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void adminLogin() {
		System.out.print("κ΄?λ¦¬μ id ?? ₯(admin) : ");
		String id = Bank.scan.next();
		
		System.out.print("κ΄?λ¦¬μ pw ?? ₯(0000) : ");
		String pw = Bank.scan.next();
		
		if(id.equals("admin") && pw.equals("0000")) {
			Bank.log = -5;
			while(Bank.log == -5) {
				printAdminMenu();
			}
		}
		else System.out.println("? λ³΄κ? ?ΌμΉνμ§? ??΅??€.");
	}
	
	private void printAdminMenu() {
		System.out.println("1.???₯\n2.λ‘λ\n3.? μ²? ? ?? μ‘°ν\n4.? μ²? κ³μ’ μ‘°ν\n9.μ’λ£");
	}
	private void selectAdminMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// ???₯
				this.am.makeAccount();
			}
			else if(sel == 2) {		// λ‘λ
				this.am.deleteAccount();
			}
			else if(sel == 3) {		// ? μ²? ? ?? μ‘°ν
				this.am.deposit();
			}
			else if(sel == 4) {		// ? μ²? κ³μ’ μ‘°ν
				this.am.withdraw();
			}
			else if(sel == 9) {		// μ’λ£
				this.am.transfer();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
