package controller;

import java.util.Scanner;

import models.Bank;

public class BankManager {
	
	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;

	public static BankManager instance = new BankManager();
	// BankManager媛? ?쑀?씪?븯寃? ?릺?뿀?쓬
	private BankManager() {}
	
	boolean isRun = true;

	public void run() {
		
		this.um.checkFile();
		// ?떎?뻾 ?떆?옉
		while(isRun) {
			System.out.println(Bank.getName() + "ATM");
			if(Bank.log != -1) System.out.println("환영합니다. "+this.um.getUserName());
			else System.out.println("환영합니다. Guest");
			디버그용메서드();
			printMainMenu();
			selectMainMenu();
		}
	}
	
	private void 디버그용메서드() {
		um.디버그용메서드();
	}
	
	private void printMainMenu() {
		if(Bank.log == -1) {
			System.out.println("1.로그인\n2.회원가입\n3.종료\n9.관리자 모드");			
		}
		else {
			System.out.println("1.로그아웃\n2.회원탈퇴\n3.뱅킹메뉴");
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
					//?쉶?썝媛??엯 硫붿냼?뱶瑜? ?샇異?
					um.joinUser();
					// UserManager.instance.joinUser();
				}
				else if(sel == 3) {
					this.isRun = false;
				}
				else if(sel == 9) {
					// ?뼱?뱶誘?
					// ?뼱?뱶誘쇱씪?븣 - ?뵲濡? 泥섎━
					// 愿?由ъ옄紐⑤뱶 -> ?뙆?씪 泥섎━(???옣 / 遺덈윭?삤湲?)
					// ?쟾泥? ?쑀?? 議고쉶 / ?쟾泥? 怨꾩쥖 議고쉶
					// ?븘?씠?뵒 鍮꾨쾲?? admin / 0000
					// 愿?由ъ옄 紐⑤뱶?뿉 ?솕?쓣?븣 ?븘?씠?뵒 鍮꾨쾲?쓣 ?엯?젰?븯寃? ?븯硫? ?맆?벏
					
					adminLogin();
				}
			}
			else {
				// 濡쒓렇?씤 ?릺?뿀?쓣 ?븣
				// 濡쒓렇 ?븘?썐
				if(sel == 1) {
					Bank.log = -1;
					System.out.println("로그아웃되었습니다.");
				}
				// ?쉶?썝 ?깉?눜
				else if(sel == 2) this.um.quitMember(Bank.log);
				
				// 諭낇궧 湲곕뒫
				else if(sel == 3) {
					printBankingMenu();
					selectBankingMenu();
				}
			}
		} catch (Exception e) {
			System.out.println("아무튼 뭔가 잘 못 됐다는 메세지");
		}
	}
	

	private void printBankingMenu() {
		System.out.println("1.계좌 개설\n2.계좌 철회\n3.입금");
		System.out.println("4.출금\n5.이체\n6.조회");
	}
	
	private void selectBankingMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// 怨꾩쥖 媛쒖꽕
				this.am.makeAccount();
			}
			else if(sel == 2) {		// 怨꾩쥖 泥좏쉶
				this.am.deleteAccount();
			}
			else if(sel == 3) {		// ?엯湲?
				this.am.deposit();
			}
			else if(sel == 4) {		// 異쒓툑
				this.am.withdraw();
			}
			else if(sel == 5) {		// ?씠泥?
				this.am.transfer();
			}
			else if(sel == 6) {		// 議고쉶
				this.am.check();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void adminLogin() {
		System.out.print("관리자 id를 입력(admin) : ");
		String id = Bank.scan.next();
		
		System.out.print("관리자 pw를 입력(0000) : ");
		String pw = Bank.scan.next();
		
		if(id.equals("admin") && pw.equals("0000")) {
			Bank.log = -5;
			while(Bank.log == -5) {
				printAdminMenu();
				selectAdminMenu();
			}
		}
		else System.out.println("일치하지 않습니다..");
	}
	
	private void printAdminMenu() {
		System.out.println("1.저장\n2.로드\n3.전체 유저 조회\n4.전체 계좌 조회\n9.관리자 모드 종료");
	}
	private void selectAdminMenu() {
		String input = Bank.scan.next();
		try {
			int sel = Integer.parseInt(input);
			if(sel == 1) {			// ???옣
				this.um.saveAll();
			}
			else if(sel == 2) {		// 濡쒕뱶
				this.um.loadAll();
			}
			else if(sel == 3) {		// ?쟾泥? ?쑀?? 議고쉶
				this.um.checkAllUsers();
			}
			else if(sel == 4) {		// ?쟾泥? 怨꾩쥖 議고쉶
				this.um.checkAllAccs();
			}
			else if(sel == 9) {		// 醫낅즺
				Bank.log = -1;
				System.out.println("관리자 모드 종료.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
