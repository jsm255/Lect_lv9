package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

class GrimRect {
	int x,y,w,h;
	Color c;
	
	public GrimRect(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
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

class GrimBoard extends Ex45_1112패널길이줄이기 {
	// 이렇게하면 필요한것만 싹 가져와서 쓰면 그만이다! 마참내!
	
	private ArrayList<GrimRect> rects = new ArrayList<>();
	private ArrayList<GrimRect> circles = new ArrayList<>();
	
	private GrimRect rect = null;
	private int startX, startY;
	
	private boolean shift;
	
	private String[] btnText = {"ㅁ","ㅇ","ㅅ"};
	private JButton[] btn = new JButton[3];
	
	public GrimBoard() {
		setLayout(null);
		setBounds(0, 0, 700, 700);
		
		setButton();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setFocusable(true);
		addKeyListener(this);
	}
	
	private void setButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.rect != null) {
			g.setColor(this.rect.getC());
			g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
		}
		
		// rects
		for(int i = 0; i<this.rects.size(); i++) {
			GrimRect r = this.rects.get(i);
			g.setColor(r.getC());
			g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
		}
		
		requestFocusInWindow();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SHIFT) this.shift = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SHIFT) this.shift = false;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.startX = e.getX();
		this.startY = e.getY();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.rect.setC(Color.blue);
		this.rects.add(this.rect);
		this.rect = null;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		// 절대값! abs 메서드를 쓰자! abs : absolute의 약자
		int w = Math.abs(x - this.startX);
		int h = Math.abs(y - this.startY);
		
		if(shift)
			w = h;
		
		int rX = startX;
		int rY = startY;
		
		if(x < startX) rX = startX - w;
		if(y < startY) rY = startY - h;
		
		this.rect = new GrimRect(rX, rY, w, h, Color.red);
	}
	
}

class GrimFrame extends JFrame {
	
	public GrimFrame() {
		setLayout(null);
		setBounds(100, 100, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("그림판");
		
		add(new GrimBoard());
		
		setVisible(true);
		revalidate();
	}
}

public class Ex풀이_유사그림판 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new GrimFrame();
	}

}
