package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;


class First extends Thread{
	
	public boolean stop;
	
	public First() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		this.stop = true;
		
		while(this.stop) {
			System.out.println("First >>>");
			try {
				Thread.sleep(250);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class Second implements Runnable{

	public boolean stop;
	
	public Second() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		this.stop = true;
		
		while(this.stop) {
			System.out.println("Second >>>");
			try {
				Thread.sleep(250);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			
	}
	
}


public class 연습 {

	public static void main(String[] args) {
		
		First first = new First();
		first.start();
		
		
		Runnable sec = (Runnable) new Second();
		Thread thread = new Thread(sec);
		thread.start();
		
		for(int i = 0; i<10; i++) {
			System.out.println(i+"!");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(i >= 7) {
				first.stop = false;
				if(sec instanceof Second) {
					Second second = (Second) sec;
					second.stop = false;
				}
			}
		}
		
		
	}
}
