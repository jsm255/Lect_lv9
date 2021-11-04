package basic;

import java.awt.Font;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

class Clock extends JFrame {

	private JLabel timer = new JLabel();
	
	public Clock() {
		super("clock");
		setLayout(null);
		setBounds(100,100,400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTimer();
		
		setVisible(true);
		revalidate();
	}
	
	private void setTimer() {
		this.timer.setText("ready?");
		this.timer.setFont(new Font("", Font.BOLD, 50));
		this.timer.setBounds(0, 0, 400, 400);
		this.timer.setHorizontalAlignment(JLabel.CENTER);
		this.timer.setVerticalAlignment(JLabel.CENTER);
		add(this.timer);
	}
	
	public void setTime(int time) {
		this.timer.setText(String.format("%4d.%3d", time/1000, time%1000));
											// 숫자는 몇칸을 차지할지를 정하는 것.
	}
	
}

public class Ex42_1103타이머 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Clock clock = new Clock();
		Scanner scan = new Scanner(System.in);
		
		int n = 0;
		String input;
		Boolean go = false;
		while(true) {
			if(go == false && (input = scan.next()).equals("시작")) {
				go = true;
				
			}
			else if(go) {
				n++;					// 타이머가 1 증가
				clock.setTime(n);		// 위의 레이블에 타이머를 제공
				try {
					Thread.sleep(1);	// 1밀리초 대기
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
			
		}
	}

}
