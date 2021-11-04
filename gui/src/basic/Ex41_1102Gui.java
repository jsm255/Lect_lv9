package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// GUI
// Graphic User Interface
//  ㄴ awt(사용자의 os, 무거움) & swing(java 라이브러리, 더 가벼움) 의 두 종류 기술로 나뉨

// UI 엘리먼트(요소)를 담는 컨테이너

// 최상위 컨테이너 : JFrame	=> 얘가 없으면 아무것도 담을 수 없다.
// 일반적 컨테이너 : JPanel
// 컴포넌트 : JButton, JLabel, JTextField 등등 (모든 J는 자바의 약자)

// 프로젝트 만들 때 모듈 파일을 만들어줘야한다! (이후 모듈 파일에 requires java.desktop; 를 써줄 것!)

class ManualPanel extends JPanel{
	public ManualPanel(int x, int y, int width, int height, Color c) {
		setBounds(x,y,width,height);
		setBackground(c);
	}
}

class MyPanel extends JPanel{	// 패널은 프레임에 붙임!
	public MyPanel(int x, int y) {
		
		// JFrame 위에서의 위치임!
		setBounds(x,y,250,200);
		setBackground(Color.green); 	// 눈뽕이 심하다고한다
	}
}

class Contents extends JPanel implements ActionListener{
	
	// 버튼 만들기
	// JButton 클래스를 import -> 객체를 생성
	
	// 레이블 만들기
	// JLabel 클래스를 import -> 객체 생성
	
	private JButton bt = new JButton();
	private JLabel lb = new JLabel();
	private boolean click;
	
	public Contents() {
		// 생성자에서 파라미터로 색도 받을 수 있다.
		// Color 변수명으로 해두면 됨
		// 값을 넘길때는 new Color(rgb값) 또는 Color.white 이런식
		
		setLayout(null);
		setBounds(0, 0, 500, 400);
//		setBackground(Color.orange);
		// 이 색들이 싫으면 colorhunt라는 곳에서 마음에 드는 색을 찾아 rgb값을 갖다 붙혀주면 구현이 가능!
		setBackground(new Color(52, 190, 130));
		
//		System.out.println(this.bt);
		// 버튼 속성 설정
		// 컴퓨터 os가 맥이면 밑의 두줄을 추가
		// bt.setOpaque(true);				// 버튼의 투명도
		// bt.setBorderPainted(false);		// 버튼의 외곽선 삭제
		
		this.bt.setBounds(100, 100, 100, 100);	// 버튼의 크기와 위치를 설정
		this.bt.setText("PUSH!");				// 버튼 위의 글씨
		this.bt.setBackground(Color.gray);		// 버튼의 색
		this.bt.setForeground(Color.yellow);	// 글자의 색
		
		this.bt.addActionListener(this);		// 버튼에 리스너(안테나)를 달아줬음!
		
		// 패널에 버튼을 달아줌
		add(this.bt);
		
		
		this.lb.setBounds(300, 100, 100, 100);
		this.lb.setText("레이블!");
		this.lb.setBackground(Color.white);
		
		// 폰트 설정 = new Font("글씨체", 글씨에 적용할 효과, 글씨 크기)
		// 글씨체는 시스템에 있는 글씨체만 가능!
		// 글씨에 적용할 효과가 없다면 plain으로 해주면 됨 - 별 효과 없는 숫자를 쓰면 plain으로 적용
		// 글씨 크기에 제한은 딱히 없지만 글씨 크기가 객체보다 크면 짤림
		this.lb.setFont(new Font("", Font.PLAIN, 25));
		
		add(this.lb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton input = (JButton) e.getSource();	// 입력받은 소스를 JButton으로 형변환(캐스팅)
		
		System.out.println(e.getSource());
		System.out.println("Press X to pay respect");
		
		System.out.println(input == this.bt);
		System.out.println(e.getSource() == this.bt);
		
		if(input == this.bt) {
			this.click = this.click ? false : true;
			if(this.click)
				this.bt.setBackground(Color.red);
			else
				this.bt.setBackground(Color.gray);
		}
	}
}

// JFrame 만들기
class MyFrame extends JFrame {	// 윈도우를 띄우는 용도로 씀
	
	public MyFrame() {
		// JFrame 설정
		// 무조건 모두 외운다!
		// 윈도우가 종료되어도 프로그램이 자동으로 종료되지 않으므로 수동으로 꺼주는 코드를 넣어야한다.
		
		// 0.
		// 기본 레이아웃 구성의 설정 -> 순서대로 나열하는 식
		setLayout(null);
		
		// 1.
		// 타이틀 설정
		// super("title");	=> 다만, super는 생성자이므로 제일 위에 두던가 해야함
		// setTitle("title");
		// 둘 중 하나를 선택해서 쓰면 된다.
		setTitle("MyFrame");
//		setTitle("MyFrame123");		
		
		// 2.
		// 크기 설정
		// setBounds(x,y,width,height);
		setBounds(50,50,500,400);
		
		// 3.
		// 종료 조건
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit_on_close = x를 누르면 프로그램이 끝!
		
		// add
//		add(new MyPanel(0,0));
//		add(new MyPanel(250,0));
//		add(new MyPanel(0,200));
//		add(new MyPanel(250,200));
//		add(new ManualPanel(250,0,250,200,Color.blue));
//		add(new ManualPanel(0,0,250,200,Color.orange));
//		add(new ManualPanel(250,200,250,400,Color.pink));
		add(new Contents());
		
		// 4.
		// 보이기
		// setVisible(true); <= 이걸 해줘야 보인다!
		setVisible(true);
		
		// 5.
		// 갱신
		revalidate();
		// 설명에는 뭔가 변화가 있을 때마다 자동으로 호출된다고 적혀있기는 하다
		
	}
	
}


public class Ex41_1102Gui {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyFrame frame = new MyFrame();

	}

}
