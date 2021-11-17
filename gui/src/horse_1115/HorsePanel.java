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
	private int time = 0;
	
	public HorsePanel() {
		setLayout(null);
		setBounds(0, 0, 800, 700);
		
		makeHorse();
		
		placeButton();
	}
	
	private void placeButton() {
		this.button.setBounds(50, 50, 80, 50);
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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(racing) {
			timer();
			g.setFont(new Font("",Font.PLAIN,15));
//			g.drawString(String.valueOf((time/1000)+"."+(time%1000)), 140, 70);
			
//			// 소수점 첫번째 자리에 0이 안나와서 만든 코드
//			int[] temp = new int[4];
//			int div = 1000;
//			int timeTemp = this.time;
//			for(int i = 0; i<temp.length; i++) {
//				if(timeTemp / div > 0) {
//					temp[i] = timeTemp / div;
//					timeTemp -= temp[i] * div;
//					div /= 10;
//				}
//				else {
//					temp[i] = 0;
//					div /= 10;
//				}
//			}
//			g.drawString(String.format("%d.%d%d%d", temp[0], temp[1], temp[2], temp[3]), 140, 70);
			
			// %03d 로 하면 자릿수 표시 앞에 0을 입력해주면 035 같은 수도 잘 출력해줄 수 있음
			g.drawString(String.format("%d.%03d", time/1000, time%1000), 140, 70);
			nowRacing();
		}
		else {
			g.setFont(new Font("",Font.PLAIN,15));
			g.drawString("Ready?", 140, 70);
		}
		
		int y = 150;
		for(int i = 0; i<this.horses.length; i++) {
			g.drawImage(this.horses[i].getImage(), this.horses[i].getGo(), y, null);
			
			if(this.horses[i].getRank() != -1) {
				g.setColor(Color.black);
				g.setFont(new Font("",Font.BOLD,15));
				g.drawString(String.format("%d등 %d.%03d초", 
						this.horses[i].getRank(), this.horses[i].getTime()/1000, 
						this.horses[i].getTime()%1000), 600, y+30);
			}
			
			y += 81;
			if(i != this.horses.length-1) g.drawLine(0, y, 800, y);
			y ++;
		}
		
		repaint();
	}

	private void timer() {
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.time ++;
	}

	private void nowRacing() {
		boolean finished = false;
		this.racing = false;
		for(int i = 0; i<this.horses.length; i++) {
			Random ran = new Random();
			
			int go = ran.nextInt(3) - 1;
			if(go <= 0) go = 0;
			
			if(this.horses[i].getRank() == -1 && this.horses[i].getGo() + go < 700) {
				this.horses[i].setGo(this.horses[i].getGo() + go);
			}
			else {
				if(this.horses[i].getRank() == -1 && !finished) {
					this.horses[i].setGo(700);
					this.horses[i].setRank(this.rank);
					this.rank ++;
					this.horses[i].setTime(this.time);
					finished = true;
				}
			}
			
			if(this.horses[i].getRank() == -1) this.racing = true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton temp = (JButton) e.getSource();
			
			if(temp == this.button) {
				for(int i = 0; i<this.horses.length; i++) {
					this.horses[i].setGo(0);
					this.horses[i].setRank(-1);
					this.horses[i].setTime(0);
					this.time = 0;
					this.rank = 1;
				}
				this.racing = true;
			}
		}
		
	}

}
