package models;

import java.util.Scanner;

public class Bank {
	
	public static int log = -1; // ë¡œê·¸? •ë³?
	
	private static String name;
	
	public static Scanner scan = new Scanner(System.in);
	
	private Bank() {} // newë¥? ?†µ?•œ Bank ?ƒ?„±?´ ?•ˆ?˜ê²? ë§‰ìŒ
	
	public static String getName() {
		return Bank.name;
	}
	
	public static void setName(String name) {
		Bank.name = name;
	}
}
