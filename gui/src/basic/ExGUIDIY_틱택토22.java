package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 틱택토
// ㄴ 버튼 9개를 가진 배열 활용
// ㄴ 마킹용 int[] 배열 활용
// ㄴ 턴에 따라 버튼의 색이 다르게 지정됨

// 추가 = 리셋 버튼 만들기

// 프레임 -> 패널

//class ResultFrame extends JFrame{
//	
//	private JLabel text = new JLabel();
//	
//	// 300 * 200
//	public ResultFrame(String input) {
//		super("Game Clear");
//		setLayout(null);
//		setBounds(TTTFrame.width/2-300/2, TTTFrame.height/2-200/2, 300, 200);
//		
//		text.setBounds(0,0,300,200);
//		text.setText(input);
//		text.setHorizontalAlignment(JLabel.CENTER);
//		add(text);
//		
//		setVisible(true);
//		revalidate();
//		
//	}
//}

class TTTPanel2 extends JPanel implements ActionListener{
	
	// 만들어 놓은 프레임에 붙여줄 패널을 만든다
	// 패널에 버튼을 배치하게 됨
	
	// 버튼을 만들고 밑에다가 세팅
	
	private Scanner scan = new Scanner(System.in);
	
	private int[] ttt = new int[9];
	private JButton[] button = new JButton[9];
	private JButton reset = new JButton();
	private JLabel label = new JLabel();
	
	private int turn = 1;
	private int win = 0;
	
	private int placeX = 150;
	private int placeY = 150;
	
	public TTTPanel2() {
		
		setLayout(null);						// 레이아웃 초기화
		setBounds(0,0,900,900);					// frame의 맨위 왼쪽끝에 800,800 크기의 패널 작성
		setBackground(new Color(47, 134, 166));			// 주황색
		
		this.label.setBounds(0,0,900,100);
		this.label.setText("틱 택 토!");
		this.label.setHorizontalAlignment(JLabel.CENTER);
		add(this.label);
		
		this.reset.setBounds(375, 750, 100, 100);
		this.reset.setBackground(new Color(52, 190, 130));
		this.reset.addActionListener(this);
		this.reset.setText("RESET");
		add(this.reset);
		
		for(int i = 0; i<9; i++) makeButton(i);	// 버튼 만드는 메서드를 9번
		
		/*
		 * setBounds(0,0,TTTFrame.SIZE,TTTFrame.SIZE);
		 * int x = TTTFrame.SIZE/2-100*3/2;
		 * int y = TTTFrame.SIZE/2-100*3/2;
		 * 
		 */
		
	}
	
	public void makeButton(int idx) {			// 9번 도는 친구
		this.button[idx] = new JButton();					// 버튼 정의
		this.button[idx].setBounds(this.placeX, this.placeY, 150, 150);
															// 버튼 위치 설정
		this.button[idx].setBackground(Color.gray);			// 버튼 색 설정
		this.button[idx].addActionListener(this);			// 버튼에 리스너 달아주기
		this.button[idx].setText("");
		add(this.button[idx]);								// 위에 설정한대로의 버튼을 패널에 추가
		
		if(this.placeX < 500) this.placeX += 200;	// 위치 설정용
		else if(this.placeX >= 500) {
			this.placeX = 150;
			this.placeY += 200;
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {	// 리스너 (버튼 누르면 일어나는 일)
		if(e.getSource() == this.reset) {
			this.placeX = 150;
			this.placeY = 150;
			this.win = 0;
			this.turn = 1;
			TTTFrame.frame.newPanel();
			System.out.println("리셋합니다!");
		}
		
		if(this.win == 0) {		// 게임이 진행 중인가?
			for(int i = 0; i<this.button.length; i++) {	// 버튼 주소 찾기위해서 도는 for문
				if(e.getSource() == this.button[i]) {	// 이 버튼이 너가 맞는가
					if(this.ttt[i] == 0) {	// 이 버튼 위치에 해당하는 int배열주소의 값이 0인가
						this.ttt[i] = this.turn;	// 그러면 int배열 값 turn으로 바꾸기
						if(this.turn == 1) {
							this.button[i].setText(String.valueOf("O"));// 버튼 위 텍스트 변경 1
							this.button[i].setBackground(new Color(242, 240, 19));	// 버튼 색도 변경
						}
						else if(this.turn == 2) {
							this.button[i].setText(String.valueOf("X"));// 버튼 위 텍스트 변경 2
							this.button[i].setBackground(new Color(47, 221, 146));	// 버튼 색도 변경
						}
						checkWin();		// 승리 조건 검사
					}
					else {
						System.out.println("이미 놓인 자리입니다!");
						System.out.println("다시 선택하세요.");
					}
				}
			}
		}
		else {
			
			System.out.printf("승자는 p%d!\n",this.win);
			System.out.println("프로그램을 종료하세요.");	// 종료하는 방법을 모르겠으니 직접 종료하라
		}
	}
	
	public void checkWin() {
		int cnt = 0;
		for(int i = 0; i<this.ttt.length; i++) {
			if(this.ttt[i] == this.turn) cnt ++;
			if(cnt == 3) this.win = this.turn;
			if(i % 3 == 2) cnt = 0;
		}
		
		cnt = 0;
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<=6; j+=3) {
				if(this.ttt[i+j] == this.turn) cnt ++;
				if(cnt == 3) this.win = this.turn;
				if(j == 6) cnt = 0;
			}
		}
		
		cnt = 0;
		for(int i = 0; i<=8; i+=4) {
			if(this.ttt[i] == this.turn) cnt ++;
			if(cnt == 3) this.win = this.turn;
		}
		
		cnt = 0;
		for(int i = 2; i<=6; i+=2) {
			if(this.ttt[i] == this.turn) cnt ++;
			if(cnt == 3) this.win = this.turn;
		}

		if(this.win == 0) this.turn = this.turn == 1 ? 2 : 1;
		else {
			for(int i = 0; i<9; i++) 	// 만약 승자가 정해졌다면 모든 버튼 위 글자를 끝났다고 바꿈
				this.button[i].setText("Finished!");	 
			System.out.printf("승자는 p%d!\n",this.win);
//			new ResultFrame(String.format("p%d의 승리!", this.win));
		}
			
		
	}
	
}

class TTTFrame2 extends JFrame{
	
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
					// 사용자 화면크기 가져오기
	public static int width = dm.width;
	public static int height = dm.height;
	
	private static final int SIZE = 700;
	
	public static TTTFrame2 frame;
	
	TTTPanel panel = new TTTPanel();
	
	public TTTFrame2() {		
		// 0. 레이아웃 초기화
		setLayout(null);
		// 1. 타이틀
		setTitle("Tic Tac Toe");
		// 2. 창 크기
		setBounds(100, 100, 900, 900);
		// 3. 종료 오퍼레이션
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 띄워진 gui 창이 닫히면 프로그램을 종료한다.
		
		add(this.panel);	// 패널을 갖다 붙힌다
		
		// 4. 창 보여주기
		setVisible(true);	// 이거 안해주면 지금까지 한 게 전부 허사다
		
		// 5. 갱신
		revalidate();
	}
	
	public void newPanel() {
		remove(this.panel);
		this.panel = new TTTPanel();
		add(this.panel);
		this.panel.revalidate();
		this.panel.repaint();
		revalidate();
	}
	
//	public TTTFrame() {		// 강사님 풀이 (화면 정중앙에 창 띄우기)
//		super("Tic Tac Toe");
//		setLayout(null);
//		setBounds(width/2-SIZE/2, height/2-SIZE/2, SIZE, SIZE);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setVisible(true);
//		revalidate();
//	}
}

public class ExGUIDIY_틱택토22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TTTFrame2.frame = new TTTFrame2();

	}

}
