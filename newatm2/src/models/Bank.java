package models;

import java.util.Scanner;

public class Bank {
	
	public static int log = -1; // 로그?���?
	
	private static String name;
	
	public static Scanner scan = new Scanner(System.in);
	
	private Bank() {} // new�? ?��?�� Bank ?��?��?�� ?��?���? 막음
	
	public static String getName() {
		return Bank.name;
	}
	
	public static void setName(String name) {
		Bank.name = name;
	}
}
