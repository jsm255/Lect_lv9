package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class �г� extends JPanel implements ActionListener {
	
	JButton button = new JButton();
	
	public �г�() {
		setLayout(null);
		setBackground(Color.yellow);
		setBounds(75, 75, 300, 300);
		
		this.button.setBounds(100, 100, 100, 100);
		this.button.setText("BOOM!");
		this.button.addActionListener(this);
		
		add(this.button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.button == e.getSource()) System.out.println("��!");
		
	}
	
}

class ������ extends JFrame {
	public ������() {
		// ���̾ƿ�
		setLayout(null);
		// âũ��
		setBounds(100,100,500,500);
		// âŸ��Ʋ
		setTitle("���ƾƾƤ�");
		// â �������� exit_on_close = â�� ������ > ���α׷� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new �г�());
		
		// â �����ֱ�
		setVisible(true);
		// �����ϱ�
		revalidate();
	}
}



public class ���� {

	public static void main(String[] args) {
		
		������ ������ = new ������();

	}

}
