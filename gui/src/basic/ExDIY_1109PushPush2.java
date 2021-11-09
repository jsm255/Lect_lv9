package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 네모가 붙어버렸음
// 조작 가능 네모가 밑에서 위로 올릴경우 (위 아래로 움직일때 붙어나옴)
// 조작 가능 네모가 위에서 밑으로 내릴경우 (모든 방향 다 붙어나옴)
// 왼쪽이랑 아래를 뚫고나감

// 마우스를 누르고있을때 그 위치를 판정해서 방향을 넘겨주면
// 계속 돌고있는 페인트컴포넌트 스레드에서 업데이트(이동)를 진행해주는 방식


class Nemo3 {
	int x, y, w, h;
	Color c;
	
	public Nemo3(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getW() {
		return this.w;
	}
	
	public int getH() {
		return this.h;
	}
	
	public Color getC() {
		return this.c;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setC(Color c) {
		this.c = c;
	}
}

class PushPanel2 extends JPanel implements MouseListener{
	
	private Nemo3 nemo1, nemo2;
	private JButton[] arrows = new JButton[4];
	
	private int dir = 5;
	
	private boolean isMoving;
	
	public PushPanel2() {
		setLayout(null);
		setBounds(0, 0, 800, 800);
		setBackground(new Color(224, 231, 241));
		
		
		this.nemo1 = new Nemo3(500, 500, 50, 50, new Color(174, 189, 119));
		this.nemo2 = new Nemo3(500, 300, 50, 50, new Color(27, 75, 54));
		
		for(int i = 0; i< this.arrows.length; i++) {
			this.arrows[i] = new JButton();
			this.arrows[i].addMouseListener(this);
		}
		this.arrows[0].setBounds(675, 675, 50, 50);
		this.arrows[0].setBackground(new Color(83, 143, 106));
		this.arrows[0].setText("∧");
		this.arrows[1].setText("＜");
		this.arrows[2].setText("∨");
		this.arrows[3].setText("＞");
		add(this.arrows[0]);
		int x = 600;
		int y = 750;
		for(int i = 1; i< this.arrows.length; i++) {
			this.arrows[i].setBounds(x, y, 50, 50);
			this.arrows[i].setBackground(new Color(83, 143, 106));
			add(this.arrows[i]);
			x += 75;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(this.nemo1.getC());
		g.drawRect(this.nemo1.getX(), this.nemo1.getY(),
				this.nemo1.getW(), this.nemo1.getH());
		
		g.setColor(this.nemo2.getC());
		g.fillRect(this.nemo2.getX(), this.nemo2.getY(), 
				this.nemo2.getW(), this.nemo2.getH());
		
		if(isMoving) moving();
		
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.isMoving = true;
		
		JButton bt = (JButton) e.getSource();
		
		for(int i = 0; i<this.arrows.length; i++) {
			if(bt == this.arrows[i]) this.dir = i;
		}
		
	}
	
	private void moving() {
		Nemo3 me = this.nemo1;
		Nemo3 obj = this.nemo2;
		if(this.dir == 0) {
			// x 축에 겹치는 부분이 있고 
			if(me.getX() > obj.getX() && me.getX() < obj.getX() + obj.getW() || 
					me.getX()+me.getW() > obj.getX() &&
					me.getX()+me.getW() < obj.getX() + obj.getW() || 
					me.getX() == obj.getX()) {
				// x축이 겹치면
				
				if(me.getY() > obj.getY() && 
						me.getY() - 1 < obj.getY() + obj.getH()) {
					// 내가 목표를 위로 밀면
					if(obj.getY() - 1 <= 0) {
						// 화면 끝이면
						obj.setY(0);
						me.setY(50);
					}
					else {
						// 화면 끝은 아니면
						obj.setY(obj.getY()-1);
						me.setY(me.getY()-1);
					}
				}
				else {
					// x축은 겹치지만 밀리지는 않으면
					if(me.getY() - 1 < 0) {
						// 내가 화면 끝이면
						me.setY(0);
					}
					else me.setY(me.getY()-1);
				}
			}
			else {
				// x축이 안겹치면
				if(me.getY() - 1 < 0) {
					// 내가 화면 끝이면
					me.setY(0);
				}
				else {
					// 그게 아니면
					me.setY(me.getY()-1);
				}
			}
			
		}
		else if(this.dir == 1) {
			// y축에 겹치는 부분
			if(me.getY() > obj.getY() && me.getY() < obj.getY() + obj.getH() ||
					me.getY()+me.getH() > obj.getY() &&
					me.getY()+me.getH() < obj.getY() + obj.getH() ||
					me.getY() == obj.getY()) {
				// y축이 겹치면
				
				if(me.getX() > obj.getX() && 
						me.getX() - 1 < obj.getX()+obj.getW()) {
					// 내가 목표를 왼쪽으로 밀면
					if(obj.getX() - 1 < 0) {
						//화면 끝이면
						obj.setX(0);
						me.setX(50);
					}
					else {
						// 화면 끝은 아니면
						obj.setX(obj.getX()-1);
						me.setX(me.getX()-1);
					}
				}
				else {
					// y축은 겹치는데 밀리지 않으면
					if(me.getX() - 1 < 0) {
						// 근데 나가면
						me.setX(0);
					}
					else me.setX(me.getX()-1);
				}
			}
			else {
				// 안겹치면
				if(me.getX() - 1 < 0) {
					// 근데 나가면
					me.setX(0);
				}
				else {
					// 안 나가면
					me.setX(me.getX()-1);
				}
			}
		}
		else if(this.dir == 2) {
			// x 축에 겹치는 부분
			if(me.getX() > obj.getX() && me.getX() < obj.getX() + obj.getW() || 
					me.getX()+me.getW() > obj.getX() &&
					me.getX()+me.getW() < obj.getX() + obj.getW() ||
					me.getX() == obj.getX()) {
				// x축이 겹치면
				
				if(me.getY() < obj.getY() && 
						me.getY() + me.getH() + 1 > obj.getY()) {
					// 내가 목표를 아래로 밀면
					if(obj.getY() + obj.getH() + 1 > this.getHeight()) {
						// 화면 끝이면
						obj.setY(this.getHeight()-obj.getH());
						me.setY(this.getHeight()-obj.getH()-me.getH());
					}
					else {
						// 화면 끝은 아니면
						obj.setY(obj.getY()+1);
						me.setY(me.getY()+1);
					}
				}
				else {
					// x축은 겹치지만 밀리지는 않으면
					if(me.getY() + me.getH() + 1 > this.getHeight()) {
						// 내가 화면밖으로 나가면
						me.setY(this.getHeight()-50);
					}
					else me.setY(me.getY()+1);
				}
			}
			else {
				// x축이 안겹치면
				if(me.getY() + me.getH() + 1 > this.getHeight()) {
					// 내가 화면밖으로 나가면
					me.setY(this.getHeight()-50);
				}
				else {
					// 그건 아니면
					me.setY(me.getY()+1);
				}
			}
		}
		else if(this.dir == 3) {
			// y축에 겹치는 부분
			if(me.getY() > obj.getY() && me.getY() < obj.getY() + obj.getH() ||
					me.getY()+me.getH() > obj.getY() &&
					me.getY()+me.getH() < obj.getY() + obj.getH() ||
					me.getY() == obj.getY()) {
				// y축이 겹치면
				
				if(me.getX() < obj.getX() && 
						me.getX() + me.getW() + 1 > obj.getX()) {
					// 내가 목표를 오른쪽으로 밀면
					if(obj.getX() + obj.getW() + 1 > this.getWidth()) {
						//화면 끝이면
						obj.setX(this.getWidth() - obj.getW());
						me.setX(this.getWidth() - obj.getW() - me.getW());
					}
					else {
						// 화면 끝은 아니면
						obj.setX(obj.getX()+1);
						me.setX(me.getX()+1);
					}
				}
				else {
					// y축은 겹치는데 밀리지 않으면
					if(me.getX() + me.getW() + 1 > this.getWidth()) {
						// 근데 나가면
						me.setX(this.getWidth() - me.getW());
					}
					else me.setX(me.getX()+1);
				}
			}
			else {
				// 안겹치면
				if(me.getX() + me.getW() + 1 > this.getWidth()) {
					// 근데 나가면
					me.setX(this.getWidth() - me.getW());
				}
				else {
					// 안 나가면
					me.setX(me.getX()+1);
				}
			}
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

class PushFrame2 extends JFrame{
	
	private PushPanel2 pp = new PushPanel2();
	
	
	public PushFrame2() {
		
		setLayout(null);
		setBounds(50, 50, 900, 900);
		setTitle("Push Push");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(this.pp);
		
		setVisible(true);
		revalidate();
		
		
	}
}

public class ExDIY_1109PushPush2 {

	public static void main(String[] args) {
		
		PushFrame2 pf = new PushFrame2();

	}

}
