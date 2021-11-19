package basic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class ExPanel extends JPanel implements KeyListener{
	
	// 텍스트 필드는 줄바꾸기가 불가능하다!
	JTextField jf = new JTextField();		// 텍스트 필드 : 문자를 입력할 수 있는 박스
	
	// 줄바꿈이 필요할때는 텍스트 에리어를 쓴다!
	JTextArea ja = new JTextArea();
	
	public ExPanel() {
		setLayout(null);
		setBounds(0, 0, 400, 500);
		
		setTextField();
		setTextArea();
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
