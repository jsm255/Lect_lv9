package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Box {
	
	private int x,y,w,h;
	private ImageIcon image;
	
	public Box() {
		this.image = new ImageIcon(new ImageIcon("image/box.png").
				getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}

	public ImageIcon getImage() {
		return image;
	}
	
}
