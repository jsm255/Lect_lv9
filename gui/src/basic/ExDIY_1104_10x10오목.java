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

class OmokPanel extends JPanel implements ActionListener{
	
	private final int SIZE = 10;
	private int[][] field = new int[SIZE][SIZE];
	private int turn = 1;
	
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
	}
	
	private void setTurnLabel() {
		this.whosTurn.setText("플레이어 " + String.valueOf(this.turn) + "의 차례");
		this.whosTurn.setForeground(Color.black);
		this.whosTurn.setBounds(450, 75, 300, 50);
		this.whosTurn.setFont(new Font("돋움", Font.BOLD, 28));
		
		add(this.whosTurn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean turnFinished = false;
		if(e.getSource() instanceof JButton) {
			JButton input = (JButton) e.getSource();
			
			for(int i = 0; i<SIZE; i++) {
				for(int j = 0; j<SIZE; j++) {
					if(this.buttons[i][j] == input) {
						if(this.turn == 1) {
							this.buttons[i][j].setBackground(new Color(64, 218, 178));
							this.buttons[i][j].setText("O");
							this.buttons[i][j].setForeground(Color.black);
							this.buttons[i][j].setFont(new Font("", Font.BOLD, 25));
							
							this.buttons[i][j].revalidate();
							
							this.turn = 2;
							turnFinished = true;
						}
						else if(this.turn == 2) {
							this.buttons[i][j].setBackground(new Color(190, 98, 131));
							this.buttons[i][j].setText("X");
							this.buttons[i][j].setForeground(Color.black);
							this.buttons[i][j].setFont(new Font("", Font.BOLD, 25));
							
							this.buttons[i][j].revalidate();
							
							this.turn = 1;
							turnFinished = true;
						}
						
					}
					if(turnFinished) break;		// 그만 돌고 빨리 나와
				}
				if(turnFinished) break;			// 그만 돌고 빨리 나와
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
