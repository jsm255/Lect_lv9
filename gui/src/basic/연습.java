package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class 패널 extends JPanel implements ActionListener {
	
	JButton button = new JButton();
	
	public 패널() {
		setLayout(null);
		setBackground(Color.yellow);
		setBounds(75, 75, 300, 300);
		
		this.button.setBounds(100, 100, 100, 100);
		this.button.setText("BOOM!");
		this.button.addActionListener(this);
		
		add(this.button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.button == e.getSource()) System.out.println("쾅!");
		
	}
	
}

class 프레임 extends JFrame {
	public 프레임() {
		// 레이아웃
		setLayout(null);
		// 창크기
		setBounds(100,100,500,500);
		// 창타이틀
		setTitle("갸아아아ㄱ");
		// 창 종료조건 exit_on_close = 창을 닫으면 > 프로그램 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new 패널());
		
		// 창 보여주기
		setVisible(true);
		// 갱신하기
		revalidate();
	}
}



public class 연습 {

	public static void main(String[] args) {
		
		프레임 프레임 = new 프레임();

	}

}
