package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

class Frame extends JFrame {
	
	public boolean end = false;
	
	public Frame() {
		setLayout(null);
		setBounds(100, 100, 600, 800);
		setTitle("B U R G E R");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		revalidate();
	}
}

public class 연습 {

	public static void main(String[] args) {
		
		Frame f = new Frame();
		
		for(int i = 0; i<5; i++) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(i);
		}
		
		f.dispose();
	}
}
