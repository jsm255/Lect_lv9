package basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class JoinFrame extends JFrame {
	
	JTextField idField = new JTextField();
	JTextField pwField = new JTextField();
	JTextField nameField = new JTextField();
	
	public JoinFrame() {
		setLayout(null);
		setBounds(200, 200, 300, 400);
		
		setTextField();
		
		setVisible(true);
		revalidate();
	}

	private void setTextField() {
		// TODO Auto-generated method stub
		
	}
}

class ExPanel extends JPanel implements KeyListener, ActionListener{
	
	// 텍스트 필드는 줄바꾸기가 불가능하다!
	JTextField jf = new JTextField();		// 텍스트 필드 : 문자를 입력할 수 있는 박스
	
	// 줄바꿈이 필요할때는 텍스트 에리어를 쓴다!
	JTextArea ja = new JTextArea();
	
	//로그인 & 회원가입
	// ㄴ 메인 프레임에 버튼 두 개
	// ㄴ 로그인과 회원가입
	// ㄴ 버튼을 누르면 -> 팝업(새로운 프레임) -> 텍스트 입력
	// ㄴ 회원가입 정보는 vector에 저장
	// ㄴ 로그인 할 때도 vector를 참조

	Vector<Vector<String>> users = new Vector<>(); // {{id,pw,name},{id,pw,name}.....}
	// User : Vector<String>
	// ㄴ add(id) : 중복예외처리
	// ㄴ add(pw)
	// ㄴ add(name)

	// 옵션 - 파일처리
	
	JButton login = new JButton();
	JButton join = new JButton();
	
	public ExPanel() {
		setLayout(null);
		setBounds(0, 0, 400, 500);
		
		setTextField();
		setTextArea();
		
		setButtons();
	}

	private void setButtons() {
		this.login.setBounds(100, 100, 100, 100);
		this.login.setText("login");
		this.login.addActionListener(this);
		
		add(this.login);
		
		this.join.setBounds(100, 100, 100, 100);
		this.join.setText("join");
		this.join.addActionListener(this);
		
		add(this.join);
		
		// 단순 팝업창을 띄울때에만 사용(권장 X)
		JOptionPane.showMessageDialog(null, "버튼 세팅 완료!");
		// this.joinFrame.dispose(); // 프레임(만!)에 대한 창 닫기
	}

	private void setTextArea() {
		ja.setBounds(100, 180, 200, 200);
		
		add(ja);
	}

	private void setTextField() {
		jf.setBounds(100, 100, 100, 30);
		
		jf.setFocusable(true);
		jf.addKeyListener(this);
		add(jf);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ENTER) {
			System.out.println(jf.getText());
		}

		requestFocusInWindow();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class Ex47_1119텍스트필드에리어 extends JFrame {

	public Ex47_1119텍스트필드에리어() {
		setLayout(null);
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new ExPanel());
		
		setVisible(true);
		revalidate();
	}
	
	public static void main(String[] args) {
		new Ex47_1119텍스트필드에리어();
	}

}
