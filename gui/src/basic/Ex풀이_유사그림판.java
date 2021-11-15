package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
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
	
	private final int RECTANGLE = 0;
	private final int CIRCLE = 1;
	private final int TRIANGLE = 2;
	
	private int type;
	
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
		int x = 30;
		int y = 50;
		
		for(int i = 0; i<this.btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x,y,50,50);
			this.btn[i].setText(btnText[i]);
			this.btn[i].addActionListener(this);
			
			add(this.btn[i]);
			
			y += 50 + 3;
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// sample triangle
		// g.drawPolygon(int[], int[], int);
		// 1. x좌표의 배열
		// 2. y좌표의 배열
		// 3. 좌표의 갯수(꼭짓점의 갯수)
		int[] xx = new int[3];
		int[] yy = new int[3];
		xx[0] = 100;
		yy[0] = 100;
		xx[1] = 150;
		yy[1] = 200;
		xx[2] = 50;
		yy[2] = 200;
		
		g.drawPolygon(xx, yy, 3);
		
		// this.rect : 임시 객체 ->
		if(this.rect != null) {
			g.setColor(this.rect.getC());
			
			if(this.type == RECTANGLE) {
				g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
			}
			else if(this.type == CIRCLE) {
				g.drawRoundRect(this.rect.getX(), this.rect.getY(),
						this.rect.getW(), this.rect.getH(), this.rect.getW(), this.rect.getH());
			}
		}
		
		// rects
		for(int i = 0; i<this.rects.size(); i++) {
			GrimRect r = this.rects.get(i);
			g.setColor(r.getC());
			g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
		}
		// circles
		for(int i = 0; i<this.circles.size(); i++) {
			GrimRect r = this.circles.get(i);
			g.setColor(r.getC());
			g.drawRoundRect(r.getX(), r.getY(), r.getW(), r.getH(), r.getW(), r.getH());
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
		if(this.type == RECTANGLE) this.rects.add(this.rect);
		else if(this.type == CIRCLE) this.circles.add(this.rect);
		else if(this.type == TRIANGLE) 
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btn[RECTANGLE]) this.type = RECTANGLE;
		else if(e.getSource() == this.btn[CIRCLE]) this.type = CIRCLE;
		else if(e.getSource() == this.btn[TRIANGLE]) this.type = TRIANGLE;
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
