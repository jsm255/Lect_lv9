package controllers;

import java.util.Random;
import java.util.Scanner;

public class GameController {
	
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	public static int gold = 20000;
	
	private static GameController instance = new GameController();
	private GameController() {}
	public static GameController getInstance() {
		return instance;
	}
	
	public void run() {
		System.out.println("run");
	}

}
