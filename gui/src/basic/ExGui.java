package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// GUI
// Graphic User Interface
//  �� awt(������� os, ���ſ�) & swing(java ���̺귯��, �� ������) �� �� ���� ����� ����

// UI ������Ʈ(���)�� ��� �����̳�

// �ֻ��� �����̳� : JFrame	=> �갡 ������ �ƹ��͵� ���� �� ����.
// �Ϲ��� �����̳� : JPanel
// ������Ʈ : JButton, JLabel, JTextField ��� (��� J�� �ڹ��� ����)

// ������Ʈ ���� �� ��� ������ ���������Ѵ�! (���� ��� ���Ͽ� requires java.desktop; �� ���� ��!)

class ManualPanel extends JPanel{
	public ManualPanel(int x, int y, int width, int height, Color c) {
		setBounds(x,y,width,height);
		setBackground(c);
	}
}

class MyPanel extends JPanel{	// �г��� �����ӿ� ����!
	public MyPanel(int x, int y) {
		
		// JFrame �������� ��ġ��!
		setBounds(x,y,250,200);
		setBackground(Color.green); 	// ������ ���ϴٰ��Ѵ�
	}
}

class Contents extends JPanel implements ActionListener{
	
	// ��ư �����
	// JButton Ŭ������ import -> ��ü�� ����
	
	
	private JButton bt = new JButton();
	private boolean click;
	
	public Contents() {
		
		setLayout(null);
		setBounds(0, 0, 500, 400);
//		setBackground(Color.orange);
		// �� ������ ������ colorhunt��� ������ ������ ��� ���� ã�� rgb���� ���� �����ָ� ������ ����!
		setBackground(new Color(52, 190, 130));
		
		// ��ư �Ӽ� ����
		System.out.println(this.bt);
		// ��ǻ�� os�� ���̸� ���� ������ �߰�
		// bt.setOpaque(true);				// ��ư�� ����
		// bt.setBorderPainted(false);		// ��ư�� �ܰ��� ����
		
		this.bt.setBounds(100, 100, 100, 100);	// ��ư�� ũ��� ��ġ�� ����
		this.bt.setText("PUSH!");				// ��ư ���� �۾�
		this.bt.setBackground(Color.gray);		// ��ư�� ��
		
		this.bt.addActionListener(this);		// ��ư�� ������(���׳�)�� �޾�����!
		
		// �гο� ��ư�� �޾���
		add(this.bt);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.getSource());
		System.out.println("Press X to pay respect");
		
		if(e.getSource() == this.bt) {
			this.click = this.click ? false : true;
			if(this.click)
				this.bt.setBackground(Color.red);
			else
				this.bt.setBackground(Color.gray);
		}
	}
}

// JFrame �����
class MyFrame extends JFrame {	// �����츦 ���� �뵵�� ��
	
	public MyFrame() {
		// JFrame ����
		// ������ ��� �ܿ��!
		// �����찡 ����Ǿ ���α׷��� �ڵ����� ������� �����Ƿ� �������� ���ִ� �ڵ带 �־���Ѵ�.
		
		// 0.
		// �⺻ ���̾ƿ� ������ ���� -> ������� �����ϴ� ��
		setLayout(null);
		
		// 1.
		// Ÿ��Ʋ ����
		// super("title");	=> �ٸ�, super�� �������̹Ƿ� ���� ���� �δ��� �ؾ���
		// setTitle("title");
		// �� �� �ϳ��� �����ؼ� ���� �ȴ�.
		setTitle("MyFrame");
//		setTitle("MyFrame123");		
		
		// 2.
		// ũ�� ����
		// setBounds(x,y,width,height);
		setBounds(50,50,500,400);
		
		// 3.
		// ���� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit_on_close = x�� ������ ���α׷��� ��!
		
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
		// ���̱�
		// setVisible(true); <= �̰� ����� ���δ�!
		setVisible(true);
		
		// 5.
		// ����
		revalidate();
		// ������ ���� ��ȭ�� ���� ������ �ڵ����� ȣ��ȴٰ� �����ֱ�� �ϴ�
		
	}
	
}


public class ExGui {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyFrame frame = new MyFrame();

	}

}
