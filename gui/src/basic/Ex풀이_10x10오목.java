package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ButtonPanel extends JPanel {

	public JButton reset = new JButton();
	
	public ButtonPanel() {
		setLayout(null);
		setBounds(0,0,OmokFrame2.SIZE, OmokFrame2.SIZE);
		setBackground(Color.lightGray);
		
		setResetButton();
	}

	private void setResetButton() {
		this.reset.setBounds(OmokFrame2.SIZE/2-150, OmokFrame2.SIZE/2-150, 300, 300);
		this.reset.setFont(new Font("",Font.PLAIN, 50));
		this.reset.setText("RESET");
		
		add(this.reset);
	}
}

class Result extends JFrame{
	
	private JLabel text = new JLabel();
	
	public Result(String str) {
		super("Game Over");
		setBounds(OmokFrame2.W/2-150, OmokFrame2.H/2-100, 300, 200);
		
		text.setText(str);
		text.setBounds(0, 0, 300, 200);
		text.setHorizontalAlignment(JLabel.CENTER);
		add(text);
		
		setVisible(true);
		revalidate();
	}
}

class OmokPanel2 extends JPanel implements ActionListener{
	
	private JLabel text = new JLabel("Omok Game");
	
	private final int SIZE = 10;
	private JButton[][] map = new JButton[SIZE][SIZE];
	
	private int[][] mark = new int[SIZE][SIZE];
	private int turn = 1;
	private int win;
	
	private ButtonPanel reset = new ButtonPanel();
	
	public OmokPanel2() {
		setLayout(null);
		setBounds(0,0,OmokFrame2.SIZE, OmokFrame2.SIZE);
		setBackground(Color.lightGray);
		
		// add(추가할 컴포넌트, 우선순위(int))
		// 0 부터 숫자가 커질수록 우선순위가 밀림
		
		// add하는 순서대로 컴포넌트의 우선순위가 top부터 정해진다!
		this.reset.reset.addActionListener(this);
		add(this.reset);
		this.reset.setVisible(false);
		
		setTitle();
		setMap();
	}
	
	private void setTitle() {
		this.text.setBounds(0, 0, OmokFrame2.SIZE, 100);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		this.text.setFont(new Font("",Font.BOLD,30));
		add(this.text);
	}
	
	
	private void setMap() {
		
		int x = OmokFrame2.SIZE/2-50*10/2;
		int y = OmokFrame2.SIZE/2-50*10/2;
		
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				JButton bt = new JButton();		// 버튼을 만들고
				
				// 버튼을 설정해서
				bt.setBounds(x, y, 50, 50);
				bt.setBackground(Color.pink);	// 핑크!
				bt.setOpaque(true);				// 투명하게
				bt.setBorderPainted(false);		// 외곽선 삭제
				bt.addActionListener(this);		// 버튼에 액션리스너를 달아주기
				// JButton에는 ActionListener를 씀(인터페이스!)
				// this는 : bt, 즉 자신을 의미함! 이벤트가 발생하면, actionPerformed() 메소드가 동작한다.
				
				this.map[i][j] = bt;			// 주소를 제공
				
				// 패널에다가 버튼을 달아준다!
				add(this.map[i][j]);	// add(bt)
				
				// 버튼 달아주고 좌표를 옮기러간다!
				x += 50 +1;
				
			}
			x = OmokFrame2.SIZE/2-50*10/2;
			y += 50 + 1;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.reset.reset) {
				resetGame();
			}
			else {
				// 맵 안에 있는 버튼이 맞는지,
				// 어떤 버튼인지
				// turn 에 따라서 -> marking
				for(int i = 0 ; i<SIZE; i++) {
					for(int j = 0 ; j<SIZE; j++) {
						if(target == this.map[i][j] && this.mark[i][j] == 0) {
							// 아직 마킹이 되지 않은 자리라면
							
							if(this.turn == 1) target.setBackground(Color.red);
							else if(this.turn == 2) target.setBackground(Color.blue);
							
							this.mark[i][j] = this.turn;
							
							checkWin();
							
							this.turn = this.turn == 1 ? 2 : 1;
						}
					}
				}
			}
		}
	}

	private void resetGame() {
		this.mark = new int[SIZE][SIZE];
		this.turn = 1;
		this.win = 0;
		
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.map[i][j].setBackground(Color.white);
			}
		}
		
		// 컴포넌트를 삭제
		this.reset.setVisible(false);
	}

	private void checkWin() {
		this.win = this.win == 0 ? checkHori() : this.win;
		this.win = this.win == 0 ? checkVerti() : this.win;
		this.win = this.win == 0 ? checkDia() : this.win;
		this.win = this.win == 0 ? checkReverse() : this.win;
		
		if(this.win != 0) {
			this.reset.setVisible(true);	// 게임이 끝나면 리셋버튼을 추가
			new Result(String.format("p%d Win!", this.win));
		}
			
	}

	private int checkReverse() {
		for(int i = 4; i<SIZE; i++) {
			for(int j = 0; j<SIZE-4; j++) {
				if(this.mark[i][j] == this.turn) {
					int cnt = 0;
					for(int k = 0; k<5; k++) {
						if(this.mark[i-k][j+k] == this.turn) cnt ++;
					}
					if(cnt == 5) return this.win;
				}
			}
		}
		return 0;
	}

	private int checkDia() {
		for(int i = 0; i<SIZE-4; i++) {
			for(int j = 0; j<SIZE-4; j++) {
				if(this.mark[i][j] == this.turn) {
					int cnt = 0;
					for(int k = 0; k<5; k++) {
						if(this.mark[i+k][j+k] == this.turn) cnt ++;
					}
					if(cnt == 5) return this.turn;
				}
			}
		}
		return 0;
	}

	private int checkVerti() {
		for(int i = 0; i<SIZE-4; i++) {
			for(int j = 0; j<SIZE; j++) {
				if(this.mark[i][j] == this.turn) {
					int cnt = 0;
					for(int k = 0; k<5; k++) {
						if(this.mark[i+k][j] == this.turn) cnt ++;
					}
					if(cnt == 5) return this.turn;
				}
			}
		}
		return 0;
	}

	private int checkHori() {
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0 ; j<SIZE-4; j++) {
				if(this.mark[i][j] == this.turn) {
					int cnt = 0;
					for(int k = 0; k<5; k++) {
						if(this.mark[i][j+k] == this.turn) cnt ++;
					}
					if(cnt == 5) return this.turn;
					
				}
			}
		}
		
		return 0;
	}
	
}

class OmokFrame2 extends JFrame{
	
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
															// 스크린 값을 가져왔음!
	public static final int W = dm.width;
	public static final int H = dm.height;
	
	public static final int SIZE = 700;
	
	private OmokPanel2 panel = new OmokPanel2();
	
	public OmokFrame2() {
		super("Omok"); // setTitle(String str)
		setLayout(null);
		setBounds(W/2-SIZE/2, H/2-SIZE/2, SIZE, SIZE); 
		// setLocation(int x, int y), setSize(int width, int height)을 한꺼번에 쓰는 메서드이다!
								// 나중에 좌표 또는 창의 크기만 별도로 변경할 일이 생긴다면 따로 써보자.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(panel);
		
		setVisible(true);
		revalidate();
	}
	
}

public class Ex풀이_10x10오목 {

	public static void main(String[] args) {
		
		OmokFrame2 game = new OmokFrame2();
		

	}

}
