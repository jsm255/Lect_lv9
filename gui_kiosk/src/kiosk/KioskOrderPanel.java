package kiosk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class KioskOrderPanel extends Utils{
	
	public static Vector<Vector<String>> receipt = new Vector<>();
	public static JTable table;

	private boolean menu = true;
	
	private JButton burger = new JButton();
	private JButton side = new JButton();
		
	private final int DISPLAY = 12;
	private KioskImage[] burgers = new KioskImage[DISPLAY];
	private KioskImage[] sides = new KioskImage[DISPLAY];
	
	private String[] burgerName = {"싸이버거","내슈빌핫치킨버거",
			"언빌리버블버거","디럭스불고기버거","휠렛버거","새우불고기버거","통새우버거","불고기버거",
			"싸이플렉스버거","텍사스바베큐치킨버거","에그불고기버거","딥치즈버거"};
	private String[] sideName = {"케이준양념감자","코울슬로","후라이드텐더2조각","할라피뇨너겟",
			"콘샐러드","치즈스틱2조각","콜라","사이다","레몬에이드","아메리카노","오렌지주스","초코라떼"};
	
	private int[] burgerPrice = {3500, 4000, 5000, 3500, 3500, 3500, 4000, 2500, 6000,
			5000, 3200, 3800};
	private int[] sidePrice = {1500, 1200, 2000, 3000, 1500, 2000, 1300, 1300, 1500, 
			1500, 1100, 1700};
	
	public KioskOrderPanel() {
		setLayout(null);
		setBounds(0, 0, 600, 800);
		
		setButtons();
		generateArrays();
		generateReceipt();
	}
	
	private void generateReceipt() {
		Vector<String> colName = new Vector<>();
		colName.add("MenuName");
		colName.add("Quantity");
		colName.add("Price");
		
		this.table = new JTable(receipt, colName);
		
		this.table.setBounds(50, DISPLAY, DISPLAY, DISPLAY);
		this.table.setBorder(new LineBorder(Color.orange));
		this.table.setGridColor(Color.black);
		
		JScrollPane sp = new JScrollPane(this.table);	// 스크롤을 달아줄 친구
		sp.setBounds(50, 550, 500, 175);
		
		add(sp);
	}
	
	private void generateArrays() {
		int x = 30;
		int y = 100;
		
		for(int i = 0; i<this.DISPLAY; i++) {
			this.burgers[i] = new KioskImage(x, y, "borgar", i);
			this.sides[i] = new KioskImage(x, y, "side", i);
			
			
			x += 140;
			if(i % 4 == 3) {
				x = 30;
				y += 160;
			}
		}
	}

	private void setButtons() {
		this.burger.setBounds(200, 20, 75, 30);
		this.burger.setText("Burger");
		this.burger.addActionListener(this);
		
		this.side.setBounds(325, 20, 75, 30);
		this.side.setText("Side");
		this.side.addActionListener(this);
		
		add(this.burger);
		add(this.side);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i<this.DISPLAY; i++) {
			if(this.menu) g.drawImage(this.burgers[i].getImg().getImage(),
					this.burgers[i].getX(), this.burgers[i].getY(), null);
			else g.drawImage(this.sides[i].getImg().getImage(),
					this.sides[i].getX(), this.sides[i].getY(), null);
		}
		
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.burger) {
			this.menu = true;
		}
		else if(e.getSource() == this.side) {
			this.menu = false;
		}
	}
}
