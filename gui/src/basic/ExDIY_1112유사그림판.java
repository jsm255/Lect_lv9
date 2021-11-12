package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class PaintNemo {
	int x,y,w,h;
	Color c;
	
	public PaintNemo(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = Color.orange;
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
}

class PaintPanel extends JPanel implements MouseListener, MouseMotionListener{
	
	private PaintNemo nemo;
	
	private int startX;
	private int startY;
	private int width;
	private int height;
	
	public PaintPanel() {
		setLayout(null);
		setBounds(0, 0, 700, 700);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.nemo != null) {
			g.setColor(this.nemo.getC());
			g.drawRect(this.nemo.getX(), this.nemo.getY(), this.nemo.getW(), this.nemo.getH());
		}
		
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(x != this.startX || y != this.startY) {
			this.nemo.setW(x - this.startX);
			this.nemo.setH(y - this.startY);
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
		this.startX = e.getX();
		this.startY = e.getY();
		
		this.nemo = new PaintNemo(this.startX, this.startY, 0, 0);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

class PaintFrame extends JFrame{
	
	public PaintFrame() {
		setLayout(null);
		setTitle("유사 그림판");
		setBounds(100, 100, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new PaintPanel());
		
		setVisible(true);
		revalidate();
	}
}

public class ExDIY_1112유사그림판 {

	public static void main(String[] args) {
		// 클릭 = 지점 지정
		// 클릭 -> 드래그 = 사각형 크기 설정
		// 다시 클릭하면 원래 있던 사각형은 삭제
		// 크기가 반전되는게 포인트
		
		new PaintFrame();

	}

}
