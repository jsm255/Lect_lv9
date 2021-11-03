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

// ƽ����
// �� ��ư 9���� ���� �迭 Ȱ��
// �� ��ŷ�� int[] �迭 Ȱ��
// �� �Ͽ� ���� ��ư�� ���� �ٸ��� ������

// �߰� = ���� ��ư �����

// ������ -> �г�

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
	
	// ����� ���� �����ӿ� �ٿ��� �г��� �����
	// �гο� ��ư�� ��ġ�ϰ� ��
	
	// ��ư�� ����� �ؿ��ٰ� ����
	
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
		
		setLayout(null);						// ���̾ƿ� �ʱ�ȭ
		setBounds(0,0,900,900);					// frame�� ���� ���ʳ��� 800,800 ũ���� �г� �ۼ�
		setBackground(new Color(47, 134, 166));			// ��Ȳ��
		
		this.label.setBounds(0,0,900,100);
		this.label.setText("ƽ �� ��!");
		this.label.setHorizontalAlignment(JLabel.CENTER);
		add(this.label);
		
		this.reset.setBounds(375, 750, 100, 100);
		this.reset.setBackground(new Color(52, 190, 130));
		this.reset.addActionListener(this);
		this.reset.setText("RESET");
		add(this.reset);
		
		for(int i = 0; i<9; i++) makeButton(i);	// ��ư ����� �޼��带 9��
		
		/*
		 * setBounds(0,0,TTTFrame.SIZE,TTTFrame.SIZE);
		 * int x = TTTFrame.SIZE/2-100*3/2;
		 * int y = TTTFrame.SIZE/2-100*3/2;
		 * 
		 */
		
	}
	
	public void makeButton(int idx) {			// 9�� ���� ģ��
		this.button[idx] = new JButton();					// ��ư ����
		this.button[idx].setBounds(this.placeX, this.placeY, 150, 150);
															// ��ư ��ġ ����
		this.button[idx].setBackground(Color.gray);			// ��ư �� ����
		this.button[idx].addActionListener(this);			// ��ư�� ������ �޾��ֱ�
		this.button[idx].setText("");
		add(this.button[idx]);								// ���� �����Ѵ���� ��ư�� �гο� �߰�
		
		if(this.placeX < 500) this.placeX += 200;	// ��ġ ������
		else if(this.placeX >= 500) {
			this.placeX = 150;
			this.placeY += 200;
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {	// ������ (��ư ������ �Ͼ�� ��)
		if(e.getSource() == this.reset) {
			this.placeX = 150;
			this.placeY = 150;
			this.win = 0;
			this.turn = 1;
			TTTFrame.frame.newPanel();
			System.out.println("�����մϴ�!");
		}
		
		if(this.win == 0) {		// ������ ���� ���ΰ�?
			for(int i = 0; i<this.button.length; i++) {	// ��ư �ּ� ã�����ؼ� ���� for��
				if(e.getSource() == this.button[i]) {	// �� ��ư�� �ʰ� �´°�
					if(this.ttt[i] == 0) {	// �� ��ư ��ġ�� �ش��ϴ� int�迭�ּ��� ���� 0�ΰ�
						this.ttt[i] = this.turn;	// �׷��� int�迭 �� turn���� �ٲٱ�
						if(this.turn == 1) {
							this.button[i].setText(String.valueOf("O"));// ��ư �� �ؽ�Ʈ ���� 1
							this.button[i].setBackground(new Color(242, 240, 19));	// ��ư ���� ����
						}
						else if(this.turn == 2) {
							this.button[i].setText(String.valueOf("X"));// ��ư �� �ؽ�Ʈ ���� 2
							this.button[i].setBackground(new Color(47, 221, 146));	// ��ư ���� ����
						}
						checkWin();		// �¸� ���� �˻�
					}
					else {
						System.out.println("�̹� ���� �ڸ��Դϴ�!");
						System.out.println("�ٽ� �����ϼ���.");
					}
				}
			}
		}
		else {
			
			System.out.printf("���ڴ� p%d!\n",this.win);
			System.out.println("���α׷��� �����ϼ���.");	// �����ϴ� ����� �𸣰����� ���� �����϶�
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
			for(int i = 0; i<9; i++) 	// ���� ���ڰ� �������ٸ� ��� ��ư �� ���ڸ� �����ٰ� �ٲ�
				this.button[i].setText("Finished!");	 
			System.out.printf("���ڴ� p%d!\n",this.win);
//			new ResultFrame(String.format("p%d�� �¸�!", this.win));
		}
			
		
	}
	
}

class TTTFrame2 extends JFrame{
	
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
					// ����� ȭ��ũ�� ��������
	public static int width = dm.width;
	public static int height = dm.height;
	
	private static final int SIZE = 700;
	
	public static TTTFrame2 frame;
	
	TTTPanel panel = new TTTPanel();
	
	public TTTFrame2() {		
		// 0. ���̾ƿ� �ʱ�ȭ
		setLayout(null);
		// 1. Ÿ��Ʋ
		setTitle("Tic Tac Toe");
		// 2. â ũ��
		setBounds(100, 100, 900, 900);
		// 3. ���� ���۷��̼�
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// ����� gui â�� ������ ���α׷��� �����Ѵ�.
		
		add(this.panel);	// �г��� ���� ������
		
		// 4. â �����ֱ�
		setVisible(true);	// �̰� �����ָ� ���ݱ��� �� �� ���� ����
		
		// 5. ����
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
	
//	public TTTFrame() {		// ����� Ǯ�� (ȭ�� ���߾ӿ� â ����)
//		super("Tic Tac Toe");
//		setLayout(null);
//		setBounds(width/2-SIZE/2, height/2-SIZE/2, SIZE, SIZE);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setVisible(true);
//		revalidate();
//	}
}

public class ExGUIDIY_ƽ����22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TTTFrame2.frame = new TTTFrame2();

	}

}
