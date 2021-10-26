package models;

public class Subject extends Functions{
	
	private int score;
	
	public Subject(String subj) {
		super(subj);
		this.score = 0;
	}
	
	public Subject(String subj, int score) {
		super(subj);
		this.score = score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int getInt() {
		return this.score;
	}
}
