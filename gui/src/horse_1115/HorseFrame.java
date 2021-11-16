package horse_1115;

import javax.swing.JFrame;

public class HorseFrame extends JFrame{
	
	public HorseFrame() {
		setLayout(null);
		setBounds(100, 100, 800, 700);
		setTitle("다그닥다그닥");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new HorsePanel());
		
		setVisible(true);
		revalidate();
	}
}
