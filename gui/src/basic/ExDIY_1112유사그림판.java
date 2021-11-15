package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

class PaintLine {
	private ArrayList<Integer> x;
	private ArrayList<Integer> y;
	private Color c;
	
	public PaintLine() {
		this.x = new ArrayList<>();
		this.y = new ArrayList<>();
	}
	
	public ArrayList<Integer> getAllX() {
		return this.x;
	}
	
	public ArrayList<Integer> getAllY() {
		return this.y;
	}
	
	public int[] getIntegerX() {
		int[] temp = new int[this.x.size()];
		for(int i = 0; i<temp.length; i++) {
			temp[i] = this.x.get(i);
		}
		return temp;
	}
	
	public int[] getIntegerY() {
		int[] temp = new int[this.y.size()];
		for(int i = 0; i<temp.length; i++) {
			temp[i] = this.y.get(i);
		}
		return temp;
	}
	
	public int getLastX() {
		return this.x.get(this.x.size()-1);
	}
	
	public int getLastY() {
		return this.y.get(this.y.size()-1);
	}
	
	public int getSize() {
		return this.x.size();
	}
	
	public void addX(int add) {
		this.x.add(add);
	}
	
	public void addY(int add) {
		this.y.add(add);
	}
	
	public void removeX() {
		this.x.remove(this.x.size()-1);
	}
	
	public void removeY() {
		this.y.remove(this.y.size()-1);
	}
	
	public Color getC() {
		return this.c;
	}
}

class PaintSemo {
	int[] x;
	int[] y;
	int tri = 3;
	Color c;
	
	public PaintSemo(int[] x, int[] y) {
		this.x = x;
		this.y = y;
		this.c = Color.black;
	}
	
	public int[] getAllX() {
		return this.x;
	}
	
	public int[] getAllY() {
		return this.y;
	}
	
	public int getX(int idx) {
		return this.x[idx];
	}
	
	public int getY(int idx) {
		return this.y[idx];
	}
	
	public void setX(int idx, int change) {
		this.x[idx] = change;
	}
	
	public void setY(int idx, int change) {
		this.y[idx] = change;
	}
	
	public Color getC() {
		return this.c;
	}
}

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

class PaintPanel extends JPanel implements MouseListener, ActionListener,
									MouseMotionListener, KeyListener{
	
	private ArrayList<PaintNemo> nemo = new ArrayList<>();
	private ArrayList<PaintSemo> semo = new ArrayList<>();
	private ArrayList<PaintNemo> one = new ArrayList<>();
	private ArrayList<PaintLine> sun = new ArrayList<>();
	
	private PaintNemo circle;
	private PaintSemo triangle;
	private PaintLine line;
	
	private int lineX;
	private int lineY;
	
	private int startX;
	private int startY;
	private int width;
	private int height;
	
	private int rectCnt = 0;
	private int triCnt = 0;
	private int shape = 0; // 0 : Rect / 1 : Tri / 2 : Cir / 3 : Line
	
	private boolean drawing;
	private boolean shift;
	private boolean needRemove;
	
	private JButton bt = new JButton();
	
	public PaintPanel() {
		setLayout(null);
		setBounds(0, 0, 700, 700);
		
		setButton();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setFocusable(true);
		addKeyListener(this);
	}
	
	private void setButton() {
		this.bt.setBounds(550, 550, 100, 75);
		this.bt.addActionListener(this);
		
		add(this.bt);
	}

	// 삼각형 그리기
	// Graphics.drawPolygon(int[], int[], int)
	// ㄴ first int[] is the set of x values,
	// ㄴ the second int[] is the set of y values,
	// ㄴ and the int is the length of the array. (In a triangle's case, the int is going to be 3)
	// ㄴ 폴리건은 인자로 (x좌표배열,  y좌표배열, 꼭지점개수) 넘겨주면 되어요~
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i<this.nemo.size(); i++) {
			g.setColor(this.nemo.get(i).getC());
			g.drawRect(this.nemo.get(i).getX(), this.nemo.get(i).getY(),
					this.nemo.get(i).getW(), this.nemo.get(i).getH());
		}
		
		if(this.triangle != null) {
			g.setColor(Color.blue);
			g.drawPolygon(this.triangle.getAllX(), this.triangle.getAllY(), 3);
		}
		for(int i = 0; i<this.semo.size(); i++) {
			g.setColor(Color.blue);
			g.drawPolygon(this.semo.get(i).getAllX(), this.semo.get(i).getAllY(), 3);
		}
		
		if(this.circle != null) {
			g.setColor(Color.orange);
			g.drawRoundRect(this.circle.getX(), this.circle.getY(), this.circle.getW(),
					this.circle.getH(), this.circle.getW(), this.circle.getH());
		}
		for(int i = 0; i<this.one.size(); i++) {
			g.setColor(Color.orange);
			g.drawRoundRect(this.one.get(i).getX(), this.one.get(i).getY(),
					this.one.get(i).getW(), this.one.get(i).getH(),
					this.one.get(i).getW(), this.one.get(i).getH());
		}
		
		if(this.line != null) {
			g.setColor(Color.pink);
			g.drawPolyline(this.line.getIntegerX(), this.line.getIntegerY(), this.line.getSize());
		}
		for(int i = 0; i<this.sun.size(); i++) {
			g.setColor(Color.pink);
			g.drawPolyline(this.sun.get(i).getIntegerX(), this.sun.get(i).getIntegerY(), 
					this.sun.get(i).getSize());
		}
		
		updateBtText();
		
		requestFocusInWindow();
		repaint();
	}

	private void updateBtText() {
		String[] btText = {"Rectangle", "Triangle", "Circle", "Line"};
		Color[] colors = {Color.orange, Color.pink, Color.yellow, Color.gray};
		this.bt.setText(btText[this.shape]);
		this.bt.setBackground(colors[this.shape]);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(this.shape == 0) {
			// 이런거 말고 절대값을 사용하자! 
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
		
		else if(this.shape == 1) {
			// 꼭짓점 0 (x0, y0)을 시작점으로(startX, startY 취급) 잡고
			//  ㄴ 0번 꼭짓점은 마우스에 붙어있어야함!
			
			int w = Math.abs(x - this.startX);
			int h = Math.abs(y - this.startY);
			
			if(this.shift) w = h;
			
			int rY = startY + h;
			if(y < startY) rY = startY - h;
			
			int[] xs = {this.startX,this.startX-(w/2),this.startX+(w/2)};
			int[] ys = {this.startY,rY,rY};
			
			this.triangle = new PaintSemo(xs,ys);
		}
		else if(this.shape == 2) {
			int w = Math.abs(x - this.startX);
			int h = Math.abs(y - this.startY);
			
			if(this.shift) w = h;
			
			int rX = startX;
			int rY = startY;
			
			if(x < startX) rX = startX - w;
			if(y < startY) rY = startY - h;
			
			this.circle = new PaintNemo(rX, rY, w, h);
		}
		
		else if(this.shape == 3) {
			if(this.shift) {
				
				if(!this.needRemove) {	// 직선모드 진입했을때 좌표를 기억하기 위함
					this.lineX = this.line.getLastX();	// 마지막 좌표를 가져와야하니까!
					this.lineY = this.line.getLastY();
				}
				
				int w = Math.abs(x - this.lineX);
				int h = Math.abs(y - this.lineY);
				
				int tempX = this.lineX;
				int tempY = this.lineY;
				
				if(w > h) {
					tempX += w;
					if(x < this.lineX) tempX = this.lineX - w;
				}
				else if(w < h){
					tempY += h;
					if(y < this.lineY) tempY = this.lineY - h;
				}
				
				if(this.needRemove) {
					this.line.removeX();	// shift를 유지중엔 직선을 그린다.
					this.line.removeY();	// 다만 shift를 누르기 직전 점과 마우스의 위치에 있는 점만
										// 신경써야하므로 shift를 누르고 있을 때 기록된 다른 위치를 지운다.
				}
				
				if(!this.needRemove) this.needRemove = true;
				// 다만 직선을 그리려고 할 때 그 직전 점을 지워버리면 안되므로 첫 시작점은 지우지 않도록
				// 조건문을 추가
				
				this.line.addX(tempX);
				this.line.addY(tempY);
			}
			else {
				if(this.needRemove) this.needRemove = false;
				this.line.addX(x);
				this.line.addY(y);
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
		
		if(this.shape == 0) 
			this.nemo.add(new PaintNemo(this.startX, this.startY, 0, 0));
		else if(this.shape == 1) {
			int[] tempX = new int[3];
			int[] tempY = new int[3];
			for(int i = 0; i<3; i++) {
				tempX[i] = this.startX;
				tempY[i] = this.startY;
			}
			this.triangle = new PaintSemo(tempX, tempY);
		}
		else if(this.shape == 2) {
			
		}
		else if(this.shape == 3) {
			this.line = new PaintLine();
			this.line.addX(this.startX);
			this.line.addY(this.startY);
		}
		this.drawing = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.drawing) {
			if(this.shape == 0) {
				if(this.nemo.get(this.rectCnt).getX() == 0 &&
						this.nemo.get(this.rectCnt).getY() == 0) {
					this.nemo.remove(this.rectCnt);
				}
				else this.rectCnt ++;
			}
			else if(this.shape == 1) {
				this.semo.add(this.triangle);
			}
			else if(this.shape == 2) {
				this.one.add(this.circle);
			}
			else if(this.shape == 3) {
				if(this.needRemove) this.needRemove = false;
				this.sun.add(this.line);
			}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bt) {
			this.shape ++;
			if(this.shape == 4) this.shape = 0;
		}
		
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
