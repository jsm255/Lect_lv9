package models;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
	private static int income = 0;
	
	private static String shopName;
	public static int Log = -1;
	
	public static Scanner scan = new Scanner(System.in);
	
	private Shop() {}
	
	public static String getName() {
		return Shop.shopName;
	}
	
	public static void setName(String name) {
		Shop.shopName = name;
	}
	
	public static int getIncome() {
		return Shop.income;
	}
	
	public static void plusIncome(int money) {
		Shop.income += money;
	}
}
