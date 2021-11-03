package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 작동은 제대로 하는데 판때기에 변화가 없음
// 타이머 while문에 들어가긴 하는데 작동을 못함
// 조건문을 통과 못하고 있음

class FinishFrame extends JFrame {
	public FinishFrame() {
		
		// 종료조건은 없어도 됨
		setLayout(null);
		setBounds(400, 400, 400, 400);
		setTitle("You Win!");
		setVisible(true);
		revalidate();
		
	}
}

class NumberPanel extends JPanel implements ActionListener{
	
	private int[] front = new int[25];
	private int[] back = new int[25];
	
	public static boolean playing = false;
	
	public static int next = 1;
	private long startTime;
	private long gameTime;
	
	private Random ran = new Random();
	
	private JButton[] buttons = new JButton[25];
	private JLabel label = new JLabel();
	private JLabel timer = new JLabel();
	
	public NumberPanel() {
		setLayout(null);
		setBounds(0,0,1000,1000);
		setBackground(new Color(199, 190, 162));
		
		generateNumbers();
		placeButtons();
		makeNextLabel();
		makeTimeLabel();
		
	}
	
	public void clockStart() {
		this.startTime = System.currentTimeMillis();
	}
	
	public void clockRevalidate() {
		this.gameTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("sss:SSS");
		
		this.timer.setText(sdf.format(this.gameTime-this.startTime));
		
		this.timer.revalidate();
	}
	
	private void makeTimeLabel() {
		SimpleDateFormat sdf = new SimpleDateFormat("sss:SSS");
		this.timer.setBounds(200, 50, 300, 100);
		this.timer.setFont(new Font("",Font.PLAIN,30));
		this.timer.setText(String.valueOf(sdf.format(0)));
		
		add(this.timer);
	}
	
	private void makeNextLabel() {
		this.label.setBounds(600, 50, 300, 100);
		this.label.setFont(new Font("",Font.PLAIN,30));
		this.label.setText("이번 숫자 : " + String.valueOf(this.next));
//		this.label.setHorizontalAlignment(this.label.CENTER);
//		this.label.setVerticalAlignment(this.label.CENTER);
		
		add(this.label);
	}
	
	private void placeButtons() {
		int x = 200;
		int y = 200;
		
		for(int i = 0; i<this.buttons.length; i++) {
			this.buttons[i] = new JButton();
			
			this.buttons[i].setBackground(new Color(154, 148, 131));
			this.buttons[i].setText(String.valueOf(this.front[i]));
			this.buttons[i].setFont(new Font("", Font.PLAIN, 25));;
			this.buttons[i].setBounds(x, y, 120, 120);
			this.buttons[i].addActionListener(this);
			
			add(this.buttons[i]);
			
			if(x < 680) x += 120;
			else {
				x = 200;
				y += 120;
			}
		}
	}
	
	private void generateNumbers() {
		for(int i = 0; i<this.front.length; i++) {
			this.front[i] = i+1;
		}
		for(int i = 0; i<this.back.length; i++) {
			this.back[i] = i+26;
		}
		
		for(int i = 0; i<100; i++) {
			int n = ran.nextInt(this.front.length);
			
			int temp = this.front[0];
			this.front[0] = this.front[n];
			this.front[n] = temp;
		}
		for(int i = 0; i<100; i++) {
			int n = ran.nextInt(this.front.length);
			
			int temp = this.back[0];
			this.back[0] = this.back[n];
			this.back[n] = temp;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton trigger = (JButton) e.getSource();
		
		for(int i = 0; i<this.buttons.length; i++) {
			if(trigger == this.buttons[i] && 
					Integer.parseInt(this.buttons[i].getText()) == next) {

					next ++;
					
					if(this.back[i] != 99) {
						this.buttons[i].setText(String.valueOf(this.back[i]));
						this.buttons[i].setBackground(new Color(229, 220, 195));
						this.back[i] = 99;
					}
					else {
						this.buttons[i].setText("");
						this.buttons[i].setBackground(new Color(199, 190, 162));
					}
					
					if(this.next != 51) {
						this.label.setText("이번 숫자 : " + String.valueOf(next));
						this.label.revalidate();
					}
					else {
						this.label.setText("승리!");
						this.label.revalidate();
						
						FinishFrame ff = new FinishFrame();
					}
					
			}
		}
	}
}

class NumberFrame extends JFrame{
	
	NumberPanel np;
	
	
	public NumberFrame() {
		
		// 프레임 사전 준비 6종 세트
		setLayout(null);
		setBounds(100, 100, 1000, 1000);
		setTitle("1 To 50!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(this.np = new NumberPanel());
		setVisible(true);
		revalidate();
		
		while(NumberPanel.next <= 50) {
			if(NumberPanel.playing == false && NumberPanel.next == 2) {
				NumberPanel.playing = true;
				this.np.clockStart();
				this.np.clockRevalidate();
			}
			else if(NumberPanel.playing == true && NumberPanel.next >= 2){
				this.np.clockRevalidate();
			}
			revalidate();
		}
	}
}

public class ExDIY_1to50 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NumberFrame nf = new NumberFrame();
	}

}
