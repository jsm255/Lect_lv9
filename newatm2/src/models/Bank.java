package models;

import java.util.Scanner;

public class Bank {
	
	public static int log = -1; // λ‘κ·Έ? λ³?
	
	private static String name;
	
	public static Scanner scan = new Scanner(System.in);
	
	private Bank() {} // newλ₯? ?΅? Bank ??±?΄ ??κ²? λ§μ
	
	public static String getName() {
		return Bank.name;
	}
	
	public static void setName(String name) {
		Bank.name = name;
	}
}
