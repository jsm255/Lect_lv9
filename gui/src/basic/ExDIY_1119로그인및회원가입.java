package basic;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//로그인 & 회원가입
// ㄴ 메인 프레임에 버튼 두 개
// ㄴ 로그인과 회원가입
// ㄴ 버튼을 누르면 -> 팝업(새로운 프레임) -> 텍스트 입력
// ㄴ 회원가입 정보는 vector에 저장
// ㄴ 로그인 할 때도 vector를 참조

// Vector<Vector<String>> users = new Vector<>(); {{id,pw,name},{id,pw,name}.....}
// User : Vector<String>
// ㄴ add(id) : 중복예외처리
// ㄴ add(pw)
// ㄴ add(name)

// 옵션 - 파일처리

class SignPanel extends ZUtil {
	
	private final int SIZE = 3;
	
	private JButton confirm = new JButton();
	
	private JLabel[] lbs = new JLabel[SIZE];
	private JTextField[] tfs = new JTextField[SIZE];
	
	public SignPanel() {
		setLayout(null);
		setBounds(0, 0, 500, 300);
		
		makeOutfit();
	}

	private void makeOutfit() {
		String[] temp = {"ID : ","PW : ","NAME : "};
		int x = 50;
		int y = 50;
		for(int i = 0; i<SIZE; i++) {
			this.lbs[i] = new JLabel();
			this.lbs[i].setBounds(x, y, 100, 50);
			this.lbs[i].setText(temp[i]);
			
			x += 100;
			
			this.tfs[i] = new JTextField();
			this.tfs[i].setBounds(x, y, 100, 50);
			
			x = 50;
			y += 50;
			
			add(this.lbs[i]);
			add(this.tfs[i]);
		}
		
	}
}

class SignFrame extends JFrame {
	
	public SignFrame() {
		setLayout(null);
		setBounds(100, 100, 500, 300);
		setTitle("회원가입!");
		
		add(new SignPanel());
		
		setVisible(true);
		revalidate();
	}
}

class SelPanel extends ZUtil { 
	
	private Vector<Vector<String>> users = new Vector<>();
	
	private JButton log = new JButton();
	private JButton sign = new JButton();
	
	public SelPanel() {
		setLayout(null);
		setBounds(0, 0, 500, 300);
		
		editButtons();
	}

	private void editButtons() {
		this.log.setBounds(100, 120, 100, 60);
		this.log.setText("Log In");
		this.log.addActionListener(this);
		
		add(this.log);
		
		this.sign.setBounds(300,120,100,60);
		this.sign.setText("Sign In");
		this.sign.addActionListener(this);
		
		add(this.sign);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.log) {
			
		}
		else if(e.getSource() == this.sign) {
			new SignFrame();
		}
		
	}
}

class SelFrame extends JFrame {
	
	public SelFrame() {
		setLayout(null);
		setBounds(100, 100, 500, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("선택!");
		
		add(new SelPanel());
		
		setVisible(true);
		revalidate();
	} 
}

public class ExDIY_1119로그인및회원가입 {

	public static void main(String[] args) {
		
		new SelFrame();
		

	}

}
