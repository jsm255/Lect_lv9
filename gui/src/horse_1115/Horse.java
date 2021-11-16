package horse_1115;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Horse {
	
	private Image image;
	private ImageIcon imageIcon;
	
	private int go;
	private int rank;
	private int time;
	
	public Horse(Image image) {
		this.image = image.getScaledInstance(100, 80, Image.SCALE_SMOOTH);
		this.imageIcon = new ImageIcon(this.image);
		
		this.go = 0;
		this.rank = -1;
		this.time = 0;
	}

	public Image getImage() {
		return image;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public int getGo() {
		return go;
	}

	public void setGo(int go) {
		this.go = go;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
}
