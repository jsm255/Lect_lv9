package kiosk;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class KioskCashPayPanel extends Utils {
	
	private JLabel label = new JLabel();
	private JTextField tf = new JTextField();	// 한줄 텍스트필드 (줄바꾸는건 텍스트에리어)
	
	public KioskCashPayPanel() {
		setLayout(null);
		setBounds(0, 0, 400, 400);
		
		setPanel();
	}

	private void setPanel() {
		this.label.setBounds(50, 50, 300, 100);
		this.label.setFont(new Font("", Font.PLAIN, 15));
		this.label.setText("투입하실 금액을 입력하세요.");
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.label.setVerticalAlignment(JLabel.CENTER);
		
		add(this.label);
		
		this.tf.setBounds(100, 150, 200, 50);
		this.tf.setFont(new Font("", Font.PLAIN, 20));
		
		add(this.tf);
	}
}

class KioskCardPayPanel extends Utils {
	
	private JLabel label = new JLabel();
	private JTextField tf = new JTextField();
	
	public KioskCardPayPanel() {
		setLayout(null);
		setBounds(0, 0, 400, 400);
		
		setPanel();
	}

	private void setPanel() {
		this.label.setBounds(50, 50, 300, 100);
		this.label.setFont(new Font("", Font.PLAIN, 15));
		this.label.setText("텍스트창에 (카드)를 입력해주세요.");
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.label.setVerticalAlignment(JLabel.CENTER);
		
		add(this.label);
		
		this.tf.setBounds(100, 150, 200, 50);
		this.tf.setFont(new Font("", Font.PLAIN, 20));
		
		add(this.tf);
	}
}

public class KioskFinalFrame extends JFrame{
	
	private String title = "";
	
	public KioskFinalFrame() {
		setLayout(null);
		setBounds(600, 300, 400, 400);
		setTitle(KioskPayPanel.credit ? (title = "카드 결제") : (title = "현금 결제"));
		
		add(KioskPayPanel.credit ? new KioskCardPayPanel() : new KioskCashPayPanel());
		
		setVisible(true);
		revalidate();
		
	}
}
