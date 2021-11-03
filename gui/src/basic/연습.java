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
	
	// ������
	public Frame() {
		// 0. ���̾ƿ� �ʱ�ȭ
		setLayout(null);
		
		// 1. â ũ�⼳��
		setBounds(50, 50, 500, 600);
		
		// 2. â Ÿ��Ʋ ����
		setTitle("Ÿ��Ʋ");
		
		// 3. ���� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Panel());
		
		// 4. â �����ֱ�
		setVisible(true);
		
		// 5. ����
		revalidate();
	}
	
}

public class ���� {

	public static void main(String[] args) {
		
		Frame ������ = new Frame();

	}

}
