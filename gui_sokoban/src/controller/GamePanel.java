package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GamePanel extends Utility{
	
	private JButton reset = new JButton();
	
	private final int EMPTY = 0;
	private final int WALL = 1;
	private final int BOX = 5;
	private final int GOAL = 3;
	private final int PLAYER = 7;
	private final int ARRIVED = 8;
	
	private final int SIZE = 8;
	private final int BLOCK = 50;
	
	private ImageIcon boxImage = new ImageIcon(new ImageIcon("image/box.png").
			getImage().getScaledInstance(BLOCK, BLOCK, Image.SCALE_SMOOTH));
	private ImageIcon guyImage = new ImageIcon(new ImageIcon("image/guy.png").
			getImage().getScaledInstance(BLOCK, BLOCK, Image.SCALE_SMOOTH));
	private ImageIcon arrivedImage = new ImageIcon(new ImageIcon("image/arrivedBox.png").
			getImage().getScaledInstance(BLOCK, BLOCK, Image.SCALE_SMOOTH));
	
	private int[][] map = {{EMPTY,EMPTY,WALL,WALL,WALL,WALL,WALL,EMPTY},
							{WALL,WALL,WALL,EMPTY,EMPTY,EMPTY,WALL,EMPTY},
							{WALL,GOAL,PLAYER,BOX,EMPTY,EMPTY,WALL,EMPTY},
							{WALL,WALL,WALL,EMPTY,BOX,GOAL,WALL,EMPTY},
							{WALL,GOAL,WALL,WALL,BOX,EMPTY,WALL,EMPTY},
							{WALL,EMPTY,WALL,EMPTY,GOAL,EMPTY,WALL,WALL},
							{WALL,BOX,EMPTY,ARRIVED,BOX,BOX,GOAL,WALL},
							{WALL,EMPTY,EMPTY,EMPTY,GOAL,EMPTY,EMPTY,WALL}};
	
	private int[][] mapTemp;
			
	public GamePanel() {
		setLayout(null);
		setBounds(0, 0, 800, 700);
		
		generateMap();
		
		setFocusable(true);
		addKeyListener(this);
		
	}

	private void generateMap() {	// 리셋할때 갖다 복사
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.mapTemp[i][j] = this.map[i][j];
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 50;
		int y = 150;
		for(int i = 0; i<this.SIZE; i++) {
			for(int j = 0; j<this.SIZE; j++) {
				if(this.map[i][j] == EMPTY) {
					g.setColor(Color.gray);
					g.fillRect(x, y, BLOCK, BLOCK);
				}
				else if(this.map[i][j] == WALL) {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(x, y, BLOCK, BLOCK);
				}
				else if(this.map[i][j] == BOX) {
					g.drawImage(this.boxImage.getImage(), x, y, null);
				}
				else if(this.map[i][j] == PLAYER) {
					g.drawImage(this.guyImage.getImage(), x, y, null);
				}
				else if(this.map[i][j] == GOAL) {
					g.setColor(Color.gray);
					g.fillRect(x, y, BLOCK, BLOCK);
					g.setColor(Color.red);
					g.fillRoundRect(x+15, y+15, 20, 20, 20, 20);
				}
				else if(this.map[i][j] == ARRIVED) {
					g.drawImage(this.arrivedImage.getImage(), x, y, null);
				}
				
				x += 50;
			}
			x = 50;
			y += 50;
		}
		
		requestFocusInWindow();
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		super.actionPerformed(e);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyReleased(e);
	}
}
