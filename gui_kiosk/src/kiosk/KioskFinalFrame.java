package kiosk;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class KioskFinishPanel extends Utils {
	
	private int countDown = 4;
	
	private JLabel label = new JLabel();
	private JTextField tf = new JTextField();
	
	public KioskFinishPanel() {
		setLayout(null);
		setBounds(0, 0, 400, 400);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawString("결제가 완료되었습니다.", 100, 100);
			
		g.drawString(String.valueOf("잠시 후 종료됩니다."), 100, 150);
		
		if(this.countDown == 0) resetKiosk();
		else {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.countDown --;
		}
		
		repaint();
	}
	
	private void resetKiosk(){
		KioskAdminManagePanel.earn += KioskOrderPanel.yourPay;
		KioskOrderPanel.yourPay = 0;
		KioskPayPanel.paying = false;
		KioskPlacePanel.ordering = false;
		KioskPayPanel.kff.dispose();
		KioskOrderPanel.kpf.dispose();
		KioskFrame.kf.setContentPane(KioskFrame.kf.kpp = new KioskPlacePanel());
		KioskFrame.kf.revalidate();
		KioskFrame.swap = false;
		KioskOrderPanel.receipt = new Vector<>();
	}
}

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
		this.label.setFont(new Font("", Font.PLAIN, 12));
		this.label.setText("투입하실 금액을 입력하세요. (입력 후 Enter)");
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.label.setVerticalAlignment(JLabel.CENTER);
		
		add(this.label);
		
		this.tf.setBounds(100, 150, 200, 50);
		this.tf.setFont(new Font("", Font.PLAIN, 12));
		
		this.tf.setFocusable(true);
		this.tf.addKeyListener(this);
		
		add(this.tf);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		this.tf.requestFocusInWindow();
		if(e.getKeyCode() == e.VK_ENTER) {
			try {
				int input = Integer.parseInt(this.tf.getText());
				
				if(input < KioskOrderPanel.yourPay) {
					this.tf.setText("금액이 부족합니다!");
					this.tf.revalidate();
				}
				else {
					KioskPayPanel.change = input - KioskOrderPanel.yourPay;
					KioskPayPanel.kff.setContentPane(new KioskFinishPanel());
					revalidate();
				}
			} catch (Exception e2) {
				this.tf.setText("숫자를 입력하세요!");
				this.tf.revalidate();
			}
		}
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
		this.label.setFont(new Font("", Font.PLAIN, 12));
		this.label.setText("텍스트창에 (카드)를 입력해주세요. (입력 후 Enter)");
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.label.setVerticalAlignment(JLabel.CENTER);
		
		add(this.label);
		
		this.tf.setBounds(100, 150, 200, 50);
		this.tf.setFont(new Font("", Font.PLAIN, 12));
		this.tf.setFocusable(true);
		this.tf.addKeyListener(this);
		
		add(this.tf);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		this.tf.requestFocusInWindow();
		if(e.getKeyCode() == e.VK_ENTER) {
			try {
				String input = this.tf.getText();
				
				if(input.equals("카드")) {
					KioskPayPanel.kff.setContentPane(new KioskFinishPanel());
					revalidate();
				}
				else {
					this.tf.setText("카드가 아닌 것 같습니다!");
					this.tf.revalidate();
				}
			} catch (Exception e2) {
				this.tf.setText("카드가 아닌 것 같습니다!");
				this.tf.revalidate();
			}
		}
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
	
	public void disposeFrame() {
		dispose();
	}
}