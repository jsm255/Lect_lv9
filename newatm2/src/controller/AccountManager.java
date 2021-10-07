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
		if(um.getAccCnt() >= 3) System.out.println("���� ������ �̹� �ִ��Դϴ�.");
		else {
			System.out.print("������ ��й�ȣ�� �Է� : ");
			String pw = Bank.scan.next();
			
			um.newAcc(randomAccCode(um), pw);
			System.out.println("���°� �����Ǿ����ϴ�.");
			System.out.println("���� ���ϱ� õ���� ���޵Ǿ����ϴ�.");
			System.out.println("�������� ---------------");
			System.out.printf("���¹�ȣ : %d  ��й�ȣ : %s  �����ܾ� : %d\n",
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
		if(um.getAccCnt() == 0) System.out.println("���°� �����ϴ�.");
		else {
			System.out.println("���� öȸ �޴��Դϴ�.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("�ش��ϴ� ���°� �����ϴ�.");
			else {
				System.out.print("pw �Է� : ");
				String pw = Bank.scan.next();
				
				if(um.getAcc(idx).getPw().equals(pw)) {
					um.delAcc(idx);
					System.out.println("öȸ�� �Ϸ�Ǿ����ϴ�.");
				}
				else System.out.println("pw�� ��ġ���� �ʽ��ϴ�.");
			}
		}
	}
	
	public int searchAccount(UserManager um) {
		System.out.print("���� ��ȣ�� �Է� : ");
		int accCode = Bank.scan.nextInt();
		
		int idx = -1;
		for(int i = 0; i<um.getAccCnt(); i++) {
			if(um.getAcc(i).getAccCode() == accCode) idx = i;
		}
		return idx;
	}
	
	public void deposit() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("���°� �����ϴ�.");
		else {
			System.out.println("�Ա� �޴��Դϴ�.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("�ش��ϴ� ���°� �����ϴ�.");
			else {
				System.out.print("�Ա��� �ݾ��� �Է� : ");
				int money = Bank.scan.nextInt();
				
				if(money <= 0) System.out.println("0������ �ݾ��� ��ȿ�Դϴ�.");
				else {
					um.getAcc(idx).changeMoney(money);
				}
			}
		}
	}
	
	public void withdraw() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("���°� �����ϴ�.");
		else {
			System.out.println("��� �޴��Դϴ�.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("�ش��ϴ� ���°� �����ϴ�.");
			else {
				System.out.print("pw �Է� : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("����� �ݾ��� �Է� : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0������ �ݾ��� ��ȿ�Դϴ�.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("���� �ܾ׺��� ���� �ݾ��� �ԷµǾ����ϴ�!");
						else {
							um.getAcc(idx).changeMoney(-money);
							System.out.println("����� �Ϸ�Ǿ����ϴ�.");
						}
					}
				}
				else System.out.println("pw�� ��ġ���� �ʽ��ϴ�.");
			}
		}
	}
	
	public void transfer() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("���°� �����ϴ�.");
		else if(um.getUsersSize() <= 1) System.out.println("��ü�� ����ڰ� �����ϴ�.");
		else {
			System.out.println("��ü �޴��Դϴ�.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("�ش��ϴ� ���°� �����ϴ�.");
			else {
				System.out.print("pw �Է� : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.print("��ü�� �ݾ��� �Է� : ");
					int money = Bank.scan.nextInt();
					
					if(money <= 0) System.out.println("0������ �ݾ��� ��ȿ�Դϴ�.");
					else {
						if(money > um.getAcc(idx).getMoney())
							System.out.println("���� �ܾ׺��� ���� �ݾ��� �Է��߽��ϴ�!");
						else {
							System.out.print("��ü���� ���¸� �Է� : ");
							int transCode = Bank.scan.nextInt();
							
							if(transCode == um.getAcc(idx).getAccCode()) System.out.println("���� �����Դϴ�!");
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
									System.out.println("�ش��ϴ� ���¸� ã�� �� �������ϴ�.");
								else {
									um.getAcc(transUserIdx, transAccIdx).changeMoney(money);
									um.getAcc(idx).changeMoney(-money);
									System.out.println("��ü�� �Ϸ�Ǿ����ϴ�!");
								}
							}
						}
					}
				}
				else System.out.println("pw�� ��ġ���� �ʽ��ϴ�.");
			}
		}
	}
	
	public void check() {
		UserManager um = UserManager.instance;
		if(um.getAccCnt() == 0) System.out.println("���°� �����ϴ�.");
		else {
			System.out.println("��ȸ �޴��Դϴ�.");
			int idx = searchAccount(um);
			
			if(idx == -1) System.out.println("�ش��ϴ� ���°� �����ϴ�.");
			else {
				System.out.print("pw �Է� : ");
				String pw = Bank.scan.next();
				
				if(pw.equals(um.getAcc(idx).getPw())) {
					System.out.println("���� ��ȣ : "+um.getAcc(idx).getAccCode());
					System.out.println("���� �ܾ� : "+um.getAcc(idx).getMoney());
				}
				else System.out.println("pw�� ��ġ���� �ʽ��ϴ�.");
			}
		}
	}
	
}
