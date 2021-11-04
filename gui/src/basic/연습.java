package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class 패널 extends JPanel implements ActionListener{
	
	private JButton bt = new JButton();
	private JLabel lb = new JLabel();
	
	public 패널(int x, int y, Color color) {
		setLayout(null);
		setBounds(x, y, 400, 400);
		setBackground(color);
		
		this.bt.setBounds(50, 50, 100, 100);
		this.bt.setBackground(new Color(210,210,210));
		this.bt.setText("클릭!");
		this.bt.setFont(new Font("굴림", Font.BOLD, 20));
		this.bt.setBorderPainted(false);
		this.bt.addActionListener(this);
		
		add(this.bt);
		
		this.lb.setBounds(150,150,100,100);
		this.lb.setText("쾅!");
		this.lb.setFont(new Font("굴림", Font.BOLD, 20));
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp = (JButton) e.getSource();
		
		if(e.getSource() == this.bt) {
			
			System.out.println("클릭!");
		}
		
	}
	
}


class 프레임 extends JFrame{
	
	public 프레임() {
		setLayout(null);
		setBounds(100, 100, 1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("제목");
		
		add(new 패널(100,100,new Color(139, 227, 225)));
		add(new 패널(500,500,new Color(211, 247, 173)));
		add(new 패널(100,500,new Color(149, 187, 118)));
		add(new 패널(500,100,new Color(151, 147, 92)));
		
		setVisible(true);
		revalidate();
	}
	
}



public class 연습 {

	public static void main(String[] args) {
		
		프레임 프레임 = new 프레임();

	}

}
