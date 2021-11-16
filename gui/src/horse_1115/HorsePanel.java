package horse_1115;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HorsePanel extends JPanel implements ActionListener{
	
	private Image image = new ImageIcon("images/horse1.png").getImage().
			getScaledInstance(150,100,Image.SCALE_SMOOTH);
	private ImageIcon icon = new ImageIcon(image);
	
	private Horse[] horses = new Horse[5];
	
	private JButton button = new JButton();
	
	private boolean racing;
	
	private int rank = 1;
	
	public HorsePanel() {
		setLayout(null);
		setBounds(0, 0, 800, 700);
		
		makeHorse();
		
		placeButton();
		horseBase();
	}
	
	private void placeButton() {
		this.button.setBounds(50, 100, 100, 75);
		this.button.setText("시작!");
		this.button.addActionListener(this);
		add(this.button);
	}

	public void makeHorse() {
		for(int i = 0; i<this.horses.length; i++) {
			String imagePath = String.format("images/horse%d.png", i+1);
			
			Image image = new ImageIcon(imagePath).getImage();
			
			this.horses[i] = new Horse(image);
		}
	}
	
	public void horseBase() {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(racing) nowRacing();
		
		int y = 150;
		for(int i = 0; i<this.horses.length; i++) {
			g.drawImage(this.horses[i].getImage(), this.horses[i].getGo(), y, null);
			
			if(this.horses[i].getRank() != -1) {
				g.setColor(Color.black);
				g.setFont(new Font("",Font.BOLD,15));
				g.drawString(String.format("%d등 %d초", 
						this.horses[i].getRank(), this.horses[i].getTime()), 600, y+30);
			}
			
			y += 81;
			if(i != this.horses.length-1) g.drawLine(0, y, 800, y);
			y ++;
		}
		
		repaint();
	}

	private void nowRacing() {
		boolean finished = false;
		for(int i = 0; i<this.horses.length; i++) {
			Random ran = new Random();
			
			int go = ran.nextInt(2);
			
			if(this.horses[i].getRank() == -1 && this.horses[i].getGo() + go < 700) {
				this.horses[i].setGo(this.horses[i].getGo() + go);
			}
			else {
				if(this.horses[i].getRank() == -1 && !finished) {
					this.horses[i].setGo(700);
					this.horses[i].setRank(this.rank);
					this.rank ++;
					finished = true;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton temp = (JButton) e.getSource();
			
			if(temp == this.button) this.racing = true;
		}
		
	}

}
