package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 클래스 이름에 커서를 두고 이클립스 메뉴에 있는 source에서 'generate getter/setter'를 사용하면
// 편하게 게터 세터를 전부 만들 수 있다!!!!!!!
class Nemo {
	private int x, y, width, height;
	private Color c;
	
	public Nemo(int x, int y, int width, int height, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}

class CustomPanel extends JPanel implements MouseListener{
	
//	private Nemo nemo = new Nemo();
	
	private Nemo[][] map = new Nemo[3][3];
	
	public CustomPanel() {
		setLayout(null);
		setBounds(0,0,500,400);
		setBackground(Color.orange);
		
//		nemo.setX(100);
//		nemo.setY(100);
//		nemo.setWidth(200);
//		nemo.setHeight(200);
//		nemo.setC(Color.blue);
		
		setMap();
		
		// 패널에 혹은 지정한 컴포넌트 => 마우스 리스너를 달 수가 있다!
		addMouseListener(this);		// this : CustomPanel
		
	}
	
	private void setMap() {
		int x = 50;
		int y = 50;
		
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				this.map[i][j] = new Nemo(x,y,100,100,Color.red);
				x += 100;
			}
			x = 50;
			y += 100;
		}
		
	}

	@Override	// 무조건 반드시 꼭 J컴포넌트에서 가져와야함!!!!!!!
	protected void paintComponent(Graphics g) {		// 스레드를 돌고있음! 연중무휴임!
		super.paintComponent(g);	// 그래픽을 싹 지움
		
//		g.setColor(Color.red);	// 사각형의 색
//		g.drawRect(100, 100, 100, 100);		// 사각형을 그림!
//		g.fillRect(200, 200, 100, 100);		// 색이 채워진 사각형을 그림!
		
//		// 객체로 만들어놓으면 수정이 가능하다!
//		g.setColor(this.nemo.getC());
//		g.fillRect(this.nemo.getX(), this.nemo.getY(),
//				this.nemo.getWidth(), this.nemo.getHeight());
		
		// draw Map
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Nemo temp = this.map[i][j];
				g.setColor(temp.getC());
//				g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				g.fillRoundRect(temp.getX(), temp.getY(), temp.getWidth(),
						temp.getHeight(), temp.getWidth(), temp.getHeight());
			}
		}
		
		
		
		// 다시 그리기
		repaint();		// 리페인트는 꼭 추가할 것
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Click!");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("클!");
		
		int x = e.getX();
		int y = e.getY();
		
		System.out.println(x+", "+y);
		
//		if(x >= this.nemo.getX() && x < this.nemo.getX() + this.nemo.getWidth() && 
//				y >= this.nemo.getY() && y < this.nemo.getY() + this.nemo.getHeight()) {
//			this.nemo.setC(Color.red);
//		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("릭!");
	}

	@Override
	public void mouseEntered(MouseEvent e) {	// 리스너가 달린 영역에 마우스가 들어왔다!
		// TODO Auto-generated method stub
		System.out.println("Hello!");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Bye!");
	}
}

class CustomFrame extends JFrame{
	
	public CustomFrame() {
		setLayout(null);
		setTitle("Custom Frame");
		setBounds(50,50,500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new CustomPanel());
		
		setVisible(true);
		revalidate();
	}
}

public class Ex44_1105 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CustomFrame cf = new CustomFrame();
	}

}
