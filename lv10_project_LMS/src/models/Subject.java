package models;

public class Subject extends Student {
	
	private String subjName;
	private int score;
	
	public Subject(String name, int code, String subj) {
		super(name, code);
		this.subjName = subj;
		this.score = 0;
	}
	
	public String getSubjName() {
		return this.subjName;
	}	
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
