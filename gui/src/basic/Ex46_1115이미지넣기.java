package basic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ImagePanel extends JPanel{
	
	ImageIcon icon = null;
	
	public ImagePanel(ImageIcon icon) {
		setLayout(null);
		setBounds(0, 0, 400, 500);
		
		this.icon = icon;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// drawImage(Image image, int x, int y, null(매핑타입));
		g.drawImage(icon.getImage(), 0, 0, null);
		
		repaint();
	}
}

class Ex03 extends JFrame{
	
	JLabel image = null;	// 라벨에다가 이미지도 붙여줄 수 있다!
	
	// ImageIcon
	// imageicon 객체를 초기화 "path"에 있는 이미지로!
	// 뒤에 .getimage를 붙이면 이미지를 가져온다.
	Image im = new ImageIcon("images/몰루.jpg").
			getImage().getScaledInstance(400,500,Image.SCALE_SMOOTH);
	ImageIcon icon = new ImageIcon(im);
	
	public Ex03() {
		super("image");
		setLayout(null);
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		setImageLabel();
		add(new ImagePanel(icon));
		
		setVisible(true);
		revalidate();
	}
	
//	private void setImageLabel() {
//		this.image = new JLabel(icon);	
//		
//		this.image.setBounds(0, 0, 400, 500);
//		add(this.image);
//	}
}

public class Ex46_1115이미지넣기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Ex03();
	}

}
