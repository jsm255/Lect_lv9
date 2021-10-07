package models;

import java.util.Scanner;

public class Bank {
	
	public static int log = -1; // 濡쒓렇?젙蹂?
	
	private static String name;
	
	public static Scanner scan = new Scanner(System.in);
	
	private Bank() {} // new瑜? ?넻?븳 Bank ?깮?꽦?씠 ?븞?릺寃? 留됱쓬
	
	public static String getName() {
		return Bank.name;
	}
	
	public static void setName(String name) {
		Bank.name = name;
	}
}
