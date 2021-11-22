package kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class KioskOrderPanel extends Utils{
	
	public static Vector<Vector<String>> receipt = new Vector<>();
	public static JTable table;
	
	public static int yourPay = 0;

	private boolean menu = true;
	
	private JButton burger = new JButton();
	private JButton side = new JButton();
	private JButton pay = new JButton();
	
	private final int NAME = 0;
	private final int QUANTITY = 1;
	private final int PRICE = 2;
	
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
		
		addMouseListener(this);
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
		
		this.pay.setBounds(450, 20, 100, 50);
		this.pay.setBackground(Color.pink);
		this.pay.setText("결제하기");
		this.pay.addActionListener(this);
		
		add(this.burger);
		add(this.side);
		add(this.pay);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int sum = 0;
		for(int i = 0; i<receipt.size(); i++) {
			sum += Integer.parseInt(receipt.get(i).get(PRICE));
		}
		yourPay = sum;
		
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString("총 "+String.valueOf(sum)+"원", 30, 40);
		
		
		g.setFont(new Font("", Font.PLAIN, 13));
		int x = 30;
		int y = 215;
		for(int i = 0; i<this.DISPLAY; i++) {
			if(this.menu) g.drawImage(this.burgers[i].getImg().getImage(),
					this.burgers[i].getX(), this.burgers[i].getY(), null);
			else g.drawImage(this.sides[i].getImg().getImage(),
					this.sides[i].getX(), this.sides[i].getY(), null);
			
			if(this.menu) {
				g.drawString(this.burgerName[i], x, y);
				y += 15;
				g.drawString(String.valueOf(this.burgerPrice[i]+"원"), x, y);
				y -= 15;	// 잠깐 내려서 가격을 적고 다시 위로
			}
			else {
				g.drawString(this.sideName[i], x, y);
				y += 15;
				g.drawString(String.valueOf(this.sidePrice[i]+"원"), x, y);
				y -= 15;	// 잠깐 내려서 가격을 적고 다시 위로
			}
			
			x += 140;
			if(i % 4 == 3) {
				x = 30;
				y += 160;
			}
		}
		
		this.table.revalidate();
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
		else if(e.getSource() == this.pay) {
			new KioskPayFrame();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(this.menu) {
			for(int i = 0; i<DISPLAY; i++) {
				if(x >= this.burgers[i].getX() &&
						x <= this.burgers[i].getX()+this.burgers[i].getW() &&
						y >= this.burgers[i].getY() &&
						y <= this.burgers[i].getY()+this.burgers[i].getH()) {
					Vector<String> temp = new Vector<>();
					temp.add(this.burgerName[i]);
					temp.add(String.valueOf(1));
					temp.add(String.valueOf(this.burgerPrice[i]));
					
					boolean found = false;
					for(int j = 0; j<receipt.size(); j++) {
						// 이미 있다면 수량만 하나 추가
						if(receipt.get(j).get(NAME).equals(temp.get(NAME))) {
							receipt.get(j).set(QUANTITY,String.valueOf(
									Integer.parseInt(receipt.get(j).get(QUANTITY))+1));
							
							receipt.get(j).set(PRICE, String.valueOf(
									Integer.parseInt(receipt.get(j).get(PRICE))+
									Integer.parseInt(temp.get(PRICE))));
							found = true;
						}
					}
					if(!found) receipt.add(temp);
				}
			}
		}
		else {
			for(int i = 0; i<DISPLAY; i++) {
				if(x >= this.sides[i].getX() &&
						x <= this.sides[i].getX()+this.sides[i].getW() &&
						y >= this.sides[i].getY() &&
						y <= this.sides[i].getY()+this.sides[i].getH()) {
					Vector<String> temp = new Vector<>();
					temp.add(this.sideName[i]);
					temp.add(String.valueOf(1));
					temp.add(String.valueOf(this.sidePrice[i]));
					
					boolean found = false;
					for(int j = 0; j<receipt.size(); j++) {
						// 이미 있다면 수량만 하나 추가
						if(receipt.get(j).get(NAME).equals(temp.get(NAME))) {
							receipt.get(j).set(QUANTITY,String.valueOf(
									Integer.parseInt(receipt.get(j).get(QUANTITY))+1));
							
							receipt.get(j).set(PRICE, String.valueOf(
									Integer.parseInt(receipt.get(j).get(PRICE))+
									Integer.parseInt(temp.get(PRICE))));
							found = true;
						}
					}
					if(!found) receipt.add(temp);
				}
			}
		}
	}
}
