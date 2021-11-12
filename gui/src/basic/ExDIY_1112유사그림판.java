package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		this.c = Color.green;
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

class PaintPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	
	private ArrayList<PaintNemo> nemo = new ArrayList<>();
	
	private int startX;
	private int startY;
	private int width;
	private int height;
	
	private int rectCnt = 0;
	
	private boolean drawing;
	private boolean shift;
	
	public PaintPanel() {
		setLayout(null);
		setBounds(0, 0, 700, 700);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setFocusable(true);
		addKeyListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i<this.nemo.size(); i++) {
			g.setColor(this.nemo.get(i).getC());
			g.drawRect(this.nemo.get(i).getX(), this.nemo.get(i).getY(),
					this.nemo.get(i).getW(), this.nemo.get(i).getH());
		}
		
		requestFocusInWindow();
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(this.shift) {
			if(x > this.startX && y > this.startY) {
				int longer = (x-this.startX) > (y-this.startY) ? x-this.startX : y-this.startY;
				this.nemo.get(this.rectCnt).setW(longer);
				this.nemo.get(this.rectCnt).setH(longer);
			}
			else if(x > this.startX && y < this.startY) {
				int longer = (x-this.startX) > (this.startY-y) ? x-this.startX : this.startY-y;
				this.nemo.get(this.rectCnt).setW(longer);
				this.nemo.get(this.rectCnt).setY(this.startY-longer);
				this.nemo.get(this.rectCnt).setH(longer);
			}
			else if(x < this.startX && y > this.startY) {
				int longer = (this.startX-x) > (y-this.startY) ? this.startX-x : y-this.startY;
				this.nemo.get(this.rectCnt).setX(this.startX-longer);
				this.nemo.get(this.rectCnt).setW(longer);
				this.nemo.get(this.rectCnt).setH(longer);
			}
			else if(y < this.startX && y < this.startY) {
				int longer = (this.startX-x) > (this.startY-y) ? this.startX-x : this.startY-y;
				this.nemo.get(this.rectCnt).setX(this.startX-longer);
				this.nemo.get(this.rectCnt).setW(longer);
				this.nemo.get(this.rectCnt).setY(this.startY-longer);
				this.nemo.get(this.rectCnt).setH(longer);
			}
		}
		else {
			if(x != this.startX) {
				if(x - this.startX > 0) {
					this.nemo.get(this.rectCnt).setW(x-this.startX);
				}
				else {
					this.nemo.get(this.rectCnt).setX(this.startX-(this.startX-x));
					this.nemo.get(this.rectCnt).setW(this.startX-x);
				}
			}
			if(y != this.startY) {
				if(y - this.startY > 0) {
					this.nemo.get(this.rectCnt).setH(y-this.startY);
				}
				else {
					this.nemo.get(this.rectCnt).setY(this.startY-(this.startY-y));
					this.nemo.get(this.rectCnt).setH(this.startY-y);
				}
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
		this.startX = e.getX();
		this.startY = e.getY();
		
		this.nemo.add(new PaintNemo(this.startX, this.startY, 0, 0));
		this.drawing = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.drawing) {
			if(this.nemo.get(this.rectCnt).getX() == 0 && this.nemo.get(this.rectCnt).getY() == 0) {
				this.nemo.remove(this.rectCnt);
			}
			else this.rectCnt ++;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SHIFT) this.shift = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SHIFT) this.shift = false;
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
