package basic;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 버튼으로 하는 10x10 오목
// 프레임 -> 오목판 패널 -> 승무패 프레임
// 리셋?

class OmokPanel extends JPanel{
	
	public OmokPanel() {
		// TODO Auto-generated constructor stub
	}
	
}

class OmokFrame extends JFrame{
	
	public OmokFrame() {
		setLayout(null);
		setBounds(100, 100, 1200, 1400);
		setTitle(" Oh! Mok! ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		

		setVisible(true);
		revalidate();
	}
}

public class ExDIY_1104_10x10오목 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
