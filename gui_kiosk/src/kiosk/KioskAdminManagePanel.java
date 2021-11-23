package kiosk;

import javax.swing.JButton;
import javax.swing.JLabel;

public class KioskAdminManagePanel extends Utils {
	
	public static int earn = 0;
	
	private JButton exit = new JButton();
	private JLabel label = new JLabel();
	
	public KioskAdminManagePanel() {
		setLayout(null);
		setBounds(0, 0, 600, 800);
		
		setPanel();
	}

	private void setPanel() {
		this.label.setBounds(100, 100, 300, 100);
		this.label.setText(String.valueOf(earn) + "원 벌었습니다!");
		
		add(this.label);
	}
}
