package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Square {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color c;
	
	public Square(int x, int y, int width, int height, Color c) {
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

class ResultFrame3 extends JFrame{
	
	private JLabel label = new JLabel();
	
	public ResultFrame3(int winner) {
		setLayout(null);
		setBounds(250, 350, 400, 200);
		
		if(winner == 99) {
			setTitle("교착상태에 빠졌습니다!");
			this.label.setText("무승부입니다!");
		}
		else {
			setTitle("승자가 결정됐습니다!");
			this.label.setText("플레이어 "+winner+"의 승리!");
		}
		
		this.label.setBounds(0, 0, 400, 200);
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.label.setVerticalAlignment(JLabel.CENTER);
		this.label.setFont(new Font("", Font.BOLD, 23));
		
		add(this.label);
		
		setVisible(true);
		revalidate();
		
	}
}

class OmokPanel3 extends JPanel implements MouseListener, ActionListener{
	
	private final int SIZE = 10;
	private int[][] field = new int[SIZE][SIZE];
	private int turn = 1;
	private int win = 0;
	
	private Square front[][] = new Square[SIZE-1][SIZE-1];	// 보이는 건 네모가 적다!
	private Square back[][] = new Square[SIZE][SIZE];
	private JLabel whosTurn = new JLabel();
	private JButton reset = new JButton();
	
	
	public OmokPanel3() {
		setLayout(null);
		setBounds(0, 0, 800, 900);
		setBackground(new Color(255, 252, 193));
		revalidate();
		
		initialize();
		setTurnLabel();
		
		addMouseListener(this);
		
	}
	
	private void initialize() {		// 리셋할 때도 그냥 이거 불러와서 재활용할 수 있게 디자인
									// 버튼을 다시 만드는 꼴이라 재활용은 못하겠다.
		this.turn = 1;
		
		// 보이는 판
		int x = 120;
		int y = 250;
		for(int i = 0; i<SIZE-1; i++) {
			for(int j = 0; j<SIZE-1; j++) {
				
				this.front[i][j] = new Square(x,y,60,60,Color.black);
				
				x += 60;
			}
			x = 120;
			y += 60;
		}
		
		// 안보이는 판 (실제 작동)
		x = 90;
		y = 220;
		
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.back[i][j] = new Square(x,y,60,60,new Color(255, 252, 193));

				
				
				x += 60;
				
			}
			x = 90;
			y += 60;
		}
		
		this.reset.setBackground(new Color(171, 114, 192));
		this.reset.setBounds(100, 75, 100, 75);
		this.reset.setText("리셋?");
		this.reset.addActionListener(this);
		
		add(this.reset);
	}
	
	private void reset() {
		this.turn = 1;
		this.win = 0;
		int x = 100;
		int y = 200;
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.field[i][j] = 0;
				
				
				x += 50;
			}
			x = 100;
			y += 50;
		}
		switchTurnLabel();
	}
	
	private void setTurnLabel() {
		this.whosTurn.setText("플레이어 " + String.valueOf(this.turn) + "의 차례");
		this.whosTurn.setForeground(Color.black);
		this.whosTurn.setBounds(450, 75, 300, 50);
		this.whosTurn.setFont(new Font("돋움", Font.BOLD, 28));
		
		add(this.whosTurn);
	}
	
	private void switchTurnLabel() {
		this.whosTurn.setText("플레이어 " + String.valueOf(this.turn) + "의 차례");
		
		this.whosTurn.revalidate();
	}
	
	private void winCondition() {
		
		// horizon
		int cnt = 0;
		for(int i = 0; i<this.SIZE; i++) {
			for(int j = 0; j<=this.SIZE/2; j++) {
				if(this.field[i][j] == this.turn) {
					for(int k = j; k <= j+4; k++) {
						if(this.field[i][k] == this.turn) cnt ++;
						else {
							cnt = 0;
							break;
						}
						if(cnt == 5) this.win = this.turn;
					}
				}
			}
		}
		
		// vertical
		cnt = 0;
		for(int j = 0; j<this.SIZE; j++) {
			for(int i = 0; i<=this.SIZE/2; i++) {
				if(this.field[i][j] == this.turn) {
					for(int k = i; k<=i+4; k++) {
						if(this.field[k][j] == this.turn) cnt ++;
						else {
							cnt = 0;
							break;
						}
						if(cnt == 5) this.win = this.turn;
					}
				}
			}
		}
		
		// diagonal1
		cnt = 0;
		for(int i = 0; i<=this.SIZE/2; i++) {
			for(int j = 0; j<=this.SIZE/2; j++) {
				if(this.field[i][j] == this.turn) {
					for(int k = 0; k<=4; k++) {
						if(this.field[i+k][j+k] == this.turn) cnt++;
						else {
							cnt = 0;
							break;
						}
						if(cnt == 5) this.win = this.turn;
					}
				}
			}
		}
		
		// diagonal2
		cnt = 0;
		for(int i = 0; i<=this.SIZE/2; i++) {
			for(int j = this.SIZE-1; j>=this.SIZE/2 - 1; j--) {
				if(this.field[i][j] == this.turn) {
					for(int k = 0; k<=4; k++) {
						if(this.field[i+k][j-k] == this.turn) cnt++;
						else {
							cnt = 0;
							break;
						}
						if(cnt == 5) this.win = this.turn;
					}
				}
			}
		}
		
		// 무승부 조건
		boolean draw = true;
		if(this.win == 0) {
			for(int i = 0; i<this.SIZE; i++) {
				for(int j = 0; j<this.SIZE; j++) {
					if(this.field[i][j] == 0) draw = false;
				}
			}
		}
		
		if(this.win == 0 && draw == false) this.turn = this.turn == 1 ? 2 : 1;
		else if(this.win != 0){
			System.out.println("승자 결정! 플레이어 " + this.turn +"의 승리!");
			ResultFrame3 rf = new ResultFrame3(this.win);
		}
		else if(this.win == 0 && draw == true) {
			this.win = 99;
			ResultFrame3 rf = new ResultFrame3(this.win);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean turnFinished = false;
		if(e.getSource() instanceof JButton) {
			JButton input = (JButton) e.getSource();
			
			if(this.reset == input) reset();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				
				Square temp = this.back[i][j];
				if(this.field[i][j] == 0) {
					this.back[i][j].setC(new Color(255, 252, 193));
					g.setColor(temp.getC());
				}
				else if(this.field[i][j] == 1) {
					this.back[i][j].setC(Color.black);
					g.setColor(temp.getC());
					g.fillRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(),
							temp.getWidth(), temp.getHeight());
				}
				else if(this.field[i][j] == 2) {
					this.back[i][j].setC(new Color(203, 161, 210));
					g.setColor(temp.getC());
					g.fillRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(),
							temp.getWidth(), temp.getHeight());
				}
//				else if(this.field[i][j] == 5) {
//					this.back[i][j].setC(Color.red);
//					g.setColor(temp.getC());
//					g.drawRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(),
//							temp.getWidth(), temp.getHeight());
//				}
			}
		}
		
		for(int i = 0; i<SIZE-1; i++) {
			for(int j = 0; j<SIZE-1; j++) {
				
				Square temp = this.front[i][j];
				g.setColor(temp.getC());
				g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				
			}
		}
		
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(this.win == 0) {
			boolean turnFinished = false;
			for(int i = 0; i<SIZE; i++) {
				for(int j = 0; j<SIZE; j++) {
					if(x >= this.back[i][j].getX() && x < this.back[i][j].getX() + 60 &&
							y >= this.back[i][j].getY() && y < this.back[i][j].getY() + 60) {
						if(this.field[i][j] == 0) {
							this.field[i][j] = this.turn;
							
							winCondition();
							turnFinished = true;
						}
					}
					if(turnFinished) break;			// 그만 돌고 빨리 나와
				}
				if(turnFinished) break;			// 그만 돌고 빨리 나와
			}
			if(turnFinished) switchTurnLabel();	
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		int x = e.getX();
//		int y = e.getY();
//		
//		for(int i = 0; i<SIZE; i++) {
//			for(int j = 0; j<SIZE; j++) {
//				if(x >= this.back[i][j].getX() && x < this.back[i][j].getX() + 60 &&
//						y >= this.back[i][j].getY() && y < this.back[i][j].getY() + 60) {
//					this.field[i][j] = 5;
//					System.out.println("오!");
//				}
//			}
//		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
//		int x = e.getX();
//		int y = e.getY();
//		
//		for(int i = 0; i<SIZE; i++) {
//			for(int j = 0; j<SIZE; j++) {
//				if(x < this.back[i][j].getX() || x >= this.back[i][j].getX() + 60 ||
//						y < this.back[i][j].getY() || y >= this.back[i][j].getY() + 60) {
//					this.field[i][j] = 0;
//					System.out.println("에!");
//				}
//			}
//		}
	}
}

class OmokFrame3 extends JFrame{
	
	private OmokPanel3 panel;
	
	public OmokFrame3() {
		setLayout(null);
		setBounds(50, 50, 800, 900);
		setTitle(" Oh! Mok! ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(this.panel = new OmokPanel3());

		setVisible(true);
		revalidate();
	}
}

public class ExDIY_1107_10x10오목2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OmokFrame3 of3 = new OmokFrame3();

	}

}
