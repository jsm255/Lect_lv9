package models;

import java.util.ArrayList;

public class Student extends Functions{
	
	private ArrayList<Subject> subjects = new ArrayList<>();
	
	int code;
	
	public Student(String name, int code) {
		super(name);
		this.code = code;
	}
	
	public void newSubject(String subj) {
		this.subjects.add(new Subject(subj));
		System.out.println("과목이 추가되었습니다.");
		System.out.println("추가된 과목의 점수 기본값은 0점입니다.");
	}
	
	public void loadSubject(String subjName, int score) {
		this.subjects.add(new Subject(subjName, score));
	}
	
	public Subject getSubj(int idx) {
		return this.subjects.get(idx);
	}
	
	public int getSubjSize() {
		return this.subjects.size();
	}
	
	@Override
	public String toString() {
		String output = "";
		output += String.format("%d %s\t- ",this.code,super.name);
		for(int i = 0; i<this.subjects.size(); i++) {
			output += String.format("%s %d점",
					this.subjects.get(i).getName(),this.subjects.get(i).getInt());
			if(i != this.subjects.size()-1) output += " / ";
		}
		
		return output;
	}

	@Override
	public String getName() {
		return super.name;
	}
	
	@Override
	public int getInt() {
		return this.code;
	}
}
