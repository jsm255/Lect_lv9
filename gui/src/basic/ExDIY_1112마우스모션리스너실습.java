package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MMLNemo {
	private int x, y, w, h;
	private Color c;
	
	public MMLNemo(int x, int y, int w, int h) {
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

	public int getH() {
		return h;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
}

class MMLPanel extends JPanel implements MouseListener, MouseMotionListener{
	
	private MMLNemo nemo;
	
	private boolean moving;
	
	private int preX;
	private int preY;
	
	public MMLPanel() {
	
		setLayout(null);
		setBounds(0, 0, 700, 700);
		
		this.nemo = new MMLNemo(300,300,100,100);
		
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(this.nemo.getC());
		g.drawRect(this.nemo.getX(), this.nemo.getY(), this.nemo.getW(), this.nemo.getH());
		
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.moving) {
			int x = e.getX();
			int y = e.getY();
			if(this.preX != x || this.preY != y) {
				this.nemo.setX(this.nemo.getX() + (x - this.preX));
				this.nemo.setY(this.nemo.getY() + (y - this.preY));
				this.preX = x;
				this.preY = y;
			}
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(x >= this.nemo.getX() && x < this.nemo.getX() + this.nemo.getW() &&
				y >= this.nemo.getY() && y < this.nemo.getY() + this.nemo.getH()) {
			this.moving = true;
			this.preX = x;
			this.preY = y;
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		this.moving = false;
		
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

class MMLFrame extends JFrame{
	
	public MMLFrame() {
		setLayout(null);
		setTitle("마우스 모션 리스너");
		setBounds(100, 100, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new MMLPanel());
		
		setVisible(true);
		revalidate();		
	}
	
}

public class ExDIY_1112마우스모션리스너실습 {

	public static void main(String[] args) {
		// MouseMotionListener를 활용해서
		// 사각형 객체를 클릭 -> 드래그 하는 동안
		// 사각형이 마우스를 따라서 무빙
		
		new MMLFrame();

	}

}
