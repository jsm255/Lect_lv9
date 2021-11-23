package kiosk;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class KioskAdminManagePanel extends Utils {
	
	// 뭐가 얼마나 팔렸냐
	
	public static Vector<Vector<String>> sold = new Vector<>();
	private JTable table = new JTable();
	
	public static int earn = 0;
	
	private JButton checkEarn = new JButton();
	private JButton exit = new JButton();
	
	private JLabel label = new JLabel();
	
	public KioskAdminManagePanel() {
		setLayout(null);
		setBounds(0, 0, 600, 800);
		
		setPanel();
	}

	private void setPanel() {
		this.checkEarn.setBounds(100, 100, 100, 100);
		this.checkEarn.setText("총 매출 확인");
		this.checkEarn.addActionListener(this);
		
		add(this.checkEarn);
		
		this.exit.setBounds(300, 100, 100, 100);
		this.exit.setText("관리자 모드 나가기");
		this.exit.addActionListener(this);
		
		add(this.exit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.checkEarn) {
			JOptionPane.showMessageDialog(null, String.valueOf(earn + "원 벌었습니다!"));
		}
		else if(e.getSource() == this.exit) {
			KioskAdminFrame.kaf.dispose();
		}
	}
}
