package basic;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
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

class LogPanel extends ZUtil {
	
	private final int SIZE = 2;
	
	private JButton confirm = new JButton();
	
	private JLabel[] lbs = new JLabel[SIZE];
	private JTextField[] tfs = new JTextField[SIZE];
	
	private JTextArea notice = new JTextArea();
	
	public LogPanel() {
		setLayout(null);
		setBounds(0, 0, 500, 300);
		
		makeOutfit();
		
	}

	private void makeOutfit() {
		String[] temp = {"ID : ","PW : "};
		int x = 50;
		int y = 50;
		for(int i = 0; i<SIZE; i++) {
			this.lbs[i] = new JLabel();
			this.lbs[i].setBounds(x, y, 100, 30);
			this.lbs[i].setFont(new Font("",Font.BOLD, 20));
			this.lbs[i].setText(temp[i]);
			
			x += 100;
			
			this.tfs[i] = new JTextField();
			this.tfs[i].setBounds(x, y, 120, 30);
			
			x = 50;
			y += 40;
			
			add(this.lbs[i]);
			add(this.tfs[i]);
		}
		
		this.confirm.setBounds(330, 60, 100, 50);
		this.confirm.setFont(new Font("",Font.BOLD,15));
		this.confirm.setText("Confirm!");
		this.confirm.addActionListener(this);
		
		add(this.confirm);
		
		this.notice.setBounds(100, 130, 300, 100);
		this.notice.setFont(new Font("",Font.PLAIN, 20));
		
		add(this.notice);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.confirm) {
			boolean empty = false;
			for(int i = 0; i<SIZE; i++) {
				if(this.tfs[i].getText().equals("")) empty = true;
			}
			
			if(empty == true) {
				this.notice.setText("빈 공간이 있습니다!");
			}
			else {
				for(int i = 0; i<SelPanel.users.size(); i++) {
					if(this.tfs[0].getText().equals(SelPanel.users.get(i).get(0)) && 
							this.tfs[1].getText().equals(SelPanel.users.get(i).get(1)))
						SelPanel.logged = i;
				}
				
				if(SelPanel.logged != -1) {
					this.notice.setText(String.format("환영합니다. %s.", 
							SelPanel.users.get(SelPanel.logged).get(2)));
				}
				else {
					this.notice.setText("일치하는 회원이 없습니다.");
				}
			}
		}
	}
}

class LogFrame extends JFrame {
	
	public LogFrame() {
		setLayout(null);
		setBounds(100, 100, 500, 300);
		setTitle("Log In");
		
		add(new LogPanel());
		
		setVisible(true);
		revalidate();
	} 
}

class SignPanel extends ZUtil {
	
	private final int SIZE = 3;
	
	private JButton confirm = new JButton();
	
	private JLabel[] lbs = new JLabel[SIZE];
	private JTextField[] tfs = new JTextField[SIZE];
	
	private JTextArea notice = new JTextArea();
	
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
			this.lbs[i].setBounds(x, y, 100, 30);
			this.lbs[i].setFont(new Font("",Font.BOLD, 20));
			this.lbs[i].setText(temp[i]);
			
			x += 100;
			
			this.tfs[i] = new JTextField();
			this.tfs[i].setBounds(x, y, 120, 30);
			
			x = 50;
			y += 40;
			
			add(this.lbs[i]);
			add(this.tfs[i]);
		}
		
		this.confirm.setBounds(330, 80, 100, 50);
		this.confirm.setFont(new Font("",Font.BOLD,15));
		this.confirm.setText("Confirm!");
		this.confirm.addActionListener(this);
		
		add(this.confirm);
		
		this.notice.setBounds(100, 180, 300, 70);
		this.notice.setFont(new Font("",Font.PLAIN, 20));
		
		add(this.notice);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.confirm) {
			boolean empty = false;
			for(int i = 0; i<SIZE; i++) {
				if(this.tfs[i].getText().equals("")) empty = true;
			}
			
			if(empty == true) {
				this.notice.setText("빈 공간이 있습니다!");
			}
			else {
				boolean check = false;
				for(int i = 0; i<SelPanel.users.size(); i++) {
					if(this.tfs[0].getText().equals(SelPanel.users.get(i).get(0)))
						check = true;
				}
				
				if(check) this.notice.setText("ID가 중복됩니다!");
				else {
					Vector<String> temp = new Vector<>();
					for(int i = 0; i<SIZE; i++) {
						temp.add(this.tfs[i].getText());
						this.tfs[i].setText("");
					}
					SelPanel.users.add(temp);
					this.notice.setText("회원가입 완료!");
					for(int i = 0; i<SelPanel.users.size(); i++) {
						for(int j = 0; j<SelPanel.users.get(i).size(); j++) {
							System.out.print(SelPanel.users.get(i).get(j)+ " ");
						}
						System.out.println();
					}
				}
			}
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
	
	public static Vector<Vector<String>> users = new Vector<>();
	
	public static int logged = -1;
	private int usersSize = 0;
	
	private boolean loading = false;
	
	private JButton log = new JButton();
	private JButton sign = new JButton();
	private JButton load = new JButton();
	
	private String fileName = "Log&Sign.txt";
	private File file = new File(this.fileName);
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	public SelPanel() {
		setLayout(null);
		setBounds(0, 0, 500, 300);
		
		editButtons();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("",Font.BOLD,20));
		
		if(logged == -1) g.drawString("환영합니다. Guest.", 150, 80);
		else g.drawString(String.format("환영합니다. %s.", users.get(logged).get(2)), 150, 80);
		
		checkAndSave();
		
		repaint();
	}

	private void checkAndSave() {
		if(!this.loading) {
			if(this.usersSize != users.size()) {
				this.usersSize = users.size();
				
				try {
					this.fw = new FileWriter(this.file);
					
					for(int i = 0; i<users.size(); i++) {
						this.fw.write(String.format("%s/%s/%s\n", users.get(i).get(0),
								users.get(i).get(1), users.get(i).get(2)));
					}
					
					this.fw.close();
					
					System.out.println("저장 끝!");
					
				} catch (Exception e) {
					System.out.println("몬가... 몬가함");
				}
			}
		}
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
		
		this.load.setBounds(100, 200, 300, 50);
		this.load.setText("Load");
		this.load.addActionListener(this);
		
		add(this.load);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.log) new LogFrame();
		else if(e.getSource() == this.sign) new SignFrame();
		else if(e.getSource() == this.load) loading();
	}

	private void loading() {
		
		try {
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			this.loading = true;
			users = new Vector<>();
			this.usersSize = 0;
			
			String getter = "";
			while((getter = this.br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(getter, "/");
				Vector<String> temp = new Vector<>();
				
				for(int i = 0; i<3; i++) {
					temp.add(st.nextToken());
				}
				
				users.add(temp);
			}
			
			this.usersSize = users.size();
			
			this.loading = false;
			System.out.println("로딩 끝!");
			
			this.br.close();
			this.fr.close();
		} catch (Exception e) {
			System.out.println("나띵!");
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
