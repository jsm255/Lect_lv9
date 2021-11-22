package kiosk;

import javax.swing.JFrame;

public class KioskOrderFrame extends JFrame{
	
	private KioskOrderPanel kop;
	
	public KioskOrderFrame() {
		setLayout(null);
		setBounds(700, 100, 600, 800);
		setTitle("I will have order!");
		
		this.add(this.kop = new KioskOrderPanel());
		
		setVisible(true);
		revalidate();

	}
}
