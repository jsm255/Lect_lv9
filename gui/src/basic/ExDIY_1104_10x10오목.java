package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 버튼으로 하는 10x10 오목
// 프레임 -> 오목판 패널 -> 승무패 프레임
// 리셋?
// 승리스크린까지 만들었음
// 리셋 버튼을 만들어보자

class ResultFrame extends JFrame{
	
	private JLabel label = new JLabel();
	
	public ResultFrame(int winner) {
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

class OmokPanel extends JPanel implements ActionListener{
	
	private final int SIZE = 10;
	private int[][] field = new int[SIZE][SIZE];
	private int turn = 1;
	private int win = 0;
	
	private JButton[][] buttons = new JButton[SIZE][SIZE];
	private JLabel whosTurn = new JLabel();
	private JButton reset = new JButton();
	
	
	public OmokPanel() {
		setLayout(null);
		setBounds(0, 0, 800, 900);
		setBackground(new Color(183, 231, 120));
		revalidate();
		
		initialize();
		setTurnLabel();
		
	}
	
	private void initialize() {		// 리셋할 때도 그냥 이거 불러와서 재활용할 수 있게 디자인
									// 버튼을 다시 만드는 꼴이라 재활용은 못하겠다.
		this.turn = 1;
		int x = 100;
		int y = 200;
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.field[i][j] = 0;
				this.buttons[i][j] = new JButton();
				
				this.buttons[i][j].setBackground(Color.white);
				this.buttons[i][j].setBorderPainted(false);
				this.buttons[i][j].setBounds(x, y, 55, 55);
				this.buttons[i][j].addActionListener(this);
				
				add(this.buttons[i][j]);
				
				x += 60;
			}
			x = 100;
			y += 60;
		}
		
		this.reset.setBackground(new Color(237, 117, 117));
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
				
				this.buttons[i][j].setBackground(Color.white);
				this.buttons[i][j].setText("");
				this.buttons[i][j].revalidate();
				
				
				x += 60;
			}
			x = 100;
			y += 60;
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
		// 5개가 나란히 있어야함
		// 0~9 / 0~5 번부터 시작하는 건 승리 판정할 가치가 있음 6789는 없음.
		
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
			ResultFrame rf = new ResultFrame(this.win);
		}
		else if(this.win == 0 && draw == true) {
			this.win = 99;
			ResultFrame rf = new ResultFrame(this.win);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean turnFinished = false;
		if(e.getSource() instanceof JButton) {
			JButton input = (JButton) e.getSource();
			
			if(this.reset == input) reset();
			else {
				if(this.win == 0) {
					for(int i = 0; i<SIZE; i++) {
						for(int j = 0; j<SIZE; j++) {
							if(this.buttons[i][j] == input && this.field[i][j] == 0) {
								if(this.turn == 1) {
									this.buttons[i][j].setBackground(new Color(64, 218, 178));
									this.buttons[i][j].setText("O");
									this.buttons[i][j].setForeground(Color.black);
									this.buttons[i][j].setFont(new Font("", Font.BOLD, 25));
									
									this.buttons[i][j].revalidate();
									
									this.field[i][j] = this.turn;
									
									winCondition();
									turnFinished = true;
								}
								else if(this.turn == 2) {
									this.buttons[i][j].setBackground(new Color(190, 98, 131));
									this.buttons[i][j].setText("X");
									this.buttons[i][j].setForeground(Color.black);
									this.buttons[i][j].setFont(new Font("", Font.BOLD, 25));
									
									this.buttons[i][j].revalidate();
									
									this.field[i][j] = this.turn;
									
									winCondition();
									turnFinished = true;
								}
								
							}
							if(turnFinished) break;		// 그만 돌고 빨리 나와
						}
						if(turnFinished) break;			// 그만 돌고 빨리 나와
					}
					
					if(turnFinished) switchTurnLabel();
				}
			}
		}
	}
}

class OmokFrame extends JFrame{
	
	private OmokPanel panel;
	
	public OmokFrame() {
		setLayout(null);
		setBounds(50, 50, 800, 900);
		setTitle(" Oh! Mok! ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(this.panel = new OmokPanel());

		setVisible(true);
		revalidate();
	}
}

public class ExDIY_1104_10x10오목 {

	public static void main(String[] args) {
		
		OmokFrame of = new OmokFrame();

	}

}
