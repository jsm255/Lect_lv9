package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class NemoNemo {
	int x,y,w,h;
	Color c;
	
	public NemoNemo(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = Color.black;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}	
}

class PushPanel3 extends JPanel implements ActionListener, MouseListener {
	
	private final int LEFT = 0;
	private final int DOWN = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	
	private final int SIZE = 700;
	
	private int dir;
	private boolean isMoving ;
	
	private JButton[] btn = new JButton[4];
	
	private NemoNemo nemo1 = null;
	private NemoNemo nemo2 = null;
	
	public PushPanel3() {
		setLayout(null);
		setBounds(0, 0, SIZE, SIZE);
		
		this.dir = 5;
		
		setBtn();
		setNemo();
	}
	
	private void setNemo() {
		Random rn = new Random();
		
		int rX = rn.nextInt(SIZE - 500);
		int rY = rn.nextInt(SIZE - 500);
		
		this.nemo1 = new NemoNemo(rX, rY, 100, 100);
		
		// nemo2 는 nemo1 과 겹치지 않도록!
		while(true){
			rX = rn.nextInt(SIZE - 500);
			rY = rn.nextInt(SIZE - 500);
			
			// 검증
			boolean check = false;
			
			// X축이 겹침, y축도 해당
			if(rX >= this.nemo1.getX() && rX < this.nemo1.getX() + 100 && 
					rY >= this.nemo1.getY() && rY < this.nemo1.getY() + 100) {
				check = true;
			}
			if(rX + 100 >= this.nemo1.getX() && rX + 100 < this.nemo1.getX() + 100 &&
					rY >= this.nemo1.getY() && rY < this.nemo1.getY() + 100) {
				check = true;
			}
			if(rX >= this.nemo1.getX() && rX < this.nemo1.getX() + 100 &&
					rY + 100 >= this.nemo1.getY() && rY + 100 < this.nemo1.getY() + 100) {
				check = true;
			}
			if(rX + 100 >= this.nemo1.getX() && rX + 100 < this.nemo1.getX() + 100 && 
					rY + 100 >= this.nemo1.getY() && rY + 100 < this.nemo1.getY() + 100) {
				check = true;
			}
			
			if(!check) {
				this.nemo2 = new NemoNemo(rX, rY, 100, 100);
				break;
			}
		}
	}
	
	private void setBtn() {
		
		String[] text = {"←","↓","→","↑"};
		
		int x = 500;
		int y = 550;
		for(int i = 0; i<4; i++) {
			JButton bt = new JButton();
			bt.setBounds(x,y,50,50);
			bt.setText(text[i]);
			bt.addMouseListener(this);	// this : 버 튼 버 튼
			add(bt);
			
			this.btn[i] = bt;
			
			x += 50;
			
			if(i == this.btn.length -1-1) {
				y -= 50;
				x -= 100;
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw Rect
		if(this.nemo1 != null && this.nemo2 != null) {
			
			g.setColor(Color.black);
			g.drawRect(this.nemo1.getX(), this.nemo1.getY(),
					this.nemo1.getW(), this.nemo1.getH());
			g.setColor(Color.black);
			g.drawRect(this.nemo2.getX(), this.nemo2.getY(),
					this.nemo2.getW(), this.nemo2.getH());
			
		}
		
		if(isMoving) update();
		
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.isMoving = true;
		
		// check Button
		if(e.getSource() instanceof JButton) {
			for(int i = 0; i<this.btn.length; i++) {
				JButton target = (JButton) e.getSource();
				
				if(target == this.btn[LEFT]) {
					this.dir = LEFT;
				}
				else if(target == this.btn[DOWN]) {
					this.dir = DOWN;
				}
				else if(target == this.btn[RIGHT]) {
					this.dir = RIGHT;
				}
				else if(target == this.btn[UP]) {
					this.dir = UP;
				}
			}
		}
		
	}
	
	private void update() {
		NemoNemo me = this.nemo1;
		NemoNemo box = this.nemo2;
		if(this.dir == LEFT) {
			if(me.getX() > box.getX())
			this.nemo1.setX(this.nemo1.getX() - 1);
		}
		else if(this.dir == DOWN) {
			this.nemo1.setY(this.nemo1.getY() + 1);
		}
		else if(this.dir == RIGHT) {
			this.nemo1.setX(this.nemo1.getX() + 1);
		}
		else if(this.dir == UP) {
			this.nemo1.setY(this.nemo1.getY() - 1);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.isMoving = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class PushPush extends JFrame{
	
	public PushPush() {
		super("Push Push");
		setLayout(null);
		setBounds(50, 50, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new PushPanel3());
		
		setVisible(true);
		revalidate();
	}
}

public class Ex풀이_PushPush {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PushPush pp = new PushPush();

	}

}
