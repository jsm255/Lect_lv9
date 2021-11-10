package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SnakeNemo {
	private int x,y,w,h;
	private Color c;
	
	public SnakeNemo(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
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

class SnakePanel extends JPanel implements KeyListener{
	
	private final int SIZE = 10;
	private SnakeNemo[][] map = new SnakeNemo[SIZE][SIZE];
	
	private ArrayList<String> snake = new ArrayList<>();
	
	private JLabel title = new JLabel();
	
	public SnakePanel() {
		setLayout(null);
		setBounds(0, 0, 700, 600);
		
		setFocusable(true);		// keyListener를 쓸 때 꼭 따라와야하는 친구!
		addKeyListener(this);	// keyListener!
		
		setNemo();
		setTitle();
		startSnake();
	}
	
	private void setTitle() {
		this.title.setBounds(50,50,200,50);
		this.title.setFont(new Font("", Font.BOLD, 35));
		this.title.setText("뱀!");
		
		add(this.title);
		revalidate();
	}
	
	private void setNemo() {
		int x = 50;
		int y = 100;
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.map[i][j] = new SnakeNemo(x,y,40,40,Color.black);
				
				x += 40;
			}
			x = 50;
			y += 40;
		}
	}
	
	private void startSnake() {
		this.snake.add("0 0");		// xy좌표를 이어붙임
		this.snake.add("0 1");
		this.snake.add("0 2");
		this.snake.add("0 3");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());	// 리스너가 받아온 키 값을 받음!
											// e.vk를 이용하면 키보드 키 값을 찾을 수 있다!
		
		if(e.getKeyCode() == e.VK_LEFT) {
			StringTokenizer st = new StringTokenizer(this.snake.get(0), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			if(x > 0) {
				x --;
				for(int i = 2; i>=0; i--) {
					this.snake.set(i+1, this.snake.get(i));
					System.out.println(this.snake.get(i+1));
				}
				this.snake.set(0, String.valueOf(y + " " + x));
			}
		}
		else if(e.getKeyCode() == e.VK_DOWN) {
			StringTokenizer st = new StringTokenizer(this.snake.get(0), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			if(y < SIZE-1) {
				y ++;
				for(int i = 2; i>=0; i--) {
					this.snake.set(i+1, this.snake.get(i));
					System.out.println(this.snake.get(i+1));
				}
				this.snake.set(0, String.valueOf(y + " " + x));
			}
		}
		else if(e.getKeyCode() == e.VK_RIGHT) {
			StringTokenizer st = new StringTokenizer(this.snake.get(0), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			if(x < SIZE-1) {
				x ++;
				for(int i = 2; i>=0; i--) {
					this.snake.set(i+1, this.snake.get(i));
					System.out.println(this.snake.get(i+1));
				}
				this.snake.set(0, String.valueOf(y + " " + x));
			}
		}
		else if(e.getKeyCode() == e.VK_UP) {
			StringTokenizer st = new StringTokenizer(this.snake.get(0), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			if(y > 0) {
				y --;
				for(int i = 2; i>=0; i--) {
					this.snake.set(i+1, this.snake.get(i));
					System.out.println(this.snake.get(i+1));
				}
				this.snake.set(0, String.valueOf(y + " " + x));
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.map[i][j].setC(Color.black);
			}
		}
		
		for(int i = 0; i<this.snake.size(); i++) {
			StringTokenizer st = new StringTokenizer(this.snake.get(i)," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			this.map[x][y].setC(i == 0 ? new Color(168, 230, 207) : new Color (255, 170, 165));
		}
		
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				g.setColor(this.map[i][j].getC());
				if(this.map[i][j].getC() != Color.black) 
					g.fillRect(this.map[i][j].getX(), this.map[i][j].getY(), 
							this.map[i][j].getW(), this.map[i][j].getH());
				else 
					g.drawRect(this.map[i][j].getX(), this.map[i][j].getY(), 
							this.map[i][j].getW(), this.map[i][j].getH());
			}
		}
		
		requestFocusInWindow();	// 키 리스너를 위한 포커스를 재요청!
		repaint();
	}
}

class SnakeFrame extends JFrame{
	
	public SnakeFrame() {
		setLayout(null);
		setBounds(100,100,700,600);
		setTitle("뱀!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new SnakePanel());
		
		setVisible(true);
		revalidate();
		
	}
}

public class ExDIY_1110스네이크 {

	public static void main(String[] args) {
		// 1단계 : 필드에 4칸짜리 뱀을 풀어놓고 움직이게 만들기
		
		// 오류 1 : 뱀 다음 좌표를 0 ~ 3 까지 옮겨버리면 좌표가 다 똑같아짐
		// 디버그 1 : i의 순서를 거꾸로 뒤집어서 해결

		SnakeFrame sf = new SnakeFrame();
	}

}
