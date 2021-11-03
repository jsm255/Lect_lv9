package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Panel extends JPanel implements ActionListener{
	
	JButton bt = new JButton();
	boolean button;
	
	public Panel() {
		setLayout(null);
		setBounds(100, 100, 250, 350);
		setBackground(Color.white);
		
		this.bt.setText("111");
		this.bt.setBounds(0,0,100,100);
		this.bt.setBackground(Color.yellow);
		this.bt.addActionListener(this);
		
		add(this.bt);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.bt && !this.button) {
			this.bt.setText("222");
			this.button = true;
		}
		else {
			this.bt.setText("111");
			this.button = false;
		}
		
	}
	
}

class newPanel extends JPanel{
	public newPanel() {
		setLayout(null);
		setBounds(0,0,500,600);
		setBackground(Color.orange);
	}
}

class Frame extends JFrame{
	
	// 생성자
	public Frame() {
		// 0. 레이아웃 초기화
		setLayout(null);
		
		// 1. 창 크기설정
		setBounds(50, 50, 500, 600);
		
		// 2. 창 타이틀 설정
		setTitle("타이틀");
		
		// 3. 종료 조건
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Panel());
		
		// 4. 창 보여주기
		setVisible(true);
		
		// 5. 갱신
		revalidate();
	}
	
}

public class 연습 {

	public static void main(String[] args) {
		
		Frame 프레임 = new Frame();

	}

}
