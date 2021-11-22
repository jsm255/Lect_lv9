package kiosk;

import javax.swing.JFrame;

public class KioskFrame extends JFrame{
	
	private KioskPlacePanel kpp;
	private KioskOrderPanel kop = new KioskOrderPanel();
	
	private boolean swap = false;
	
	public KioskFrame() {
		setLayout(null);
		setBounds(100, 100, 600, 800);
		setTitle("B U R G E R");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.add(this.kpp = new KioskPlacePanel());
		
		setVisible(true);
		revalidate();
		
		while(true) {
			revalidate();
			if(KioskPlacePanel.where.compareTo("") != 0 && !this.swap) {
				this.setContentPane(kop);
				this.swap = true;
			}
		}
	}
}
