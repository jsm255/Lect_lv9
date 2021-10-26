package models;

import java.util.ArrayList;

public class Student {
	
	private ArrayList<Subject> subjects = new ArrayList<>();
	
	String name;
	int code;
	
	public Student(String name, int code) {
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public void newSubject(String subj) {
		this.subjects.add(new Subject(this.name, this.code, subj));
		System.out.println("과목이 추가되었습니다.");
		System.out.println("추가된 과목의 점수 기본값은 0점입니다.");
	}
	
	public void loadSubject(String subjName, int score) {
		this.subjects.add(new Subject(this.name, this.code, subjName, score));
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
		output += String.format("%d %s\t- ",this.code,this.name);
		for(int i = 0; i<this.subjects.size(); i++) {
			output += String.format("%s %d점",
					this.subjects.get(i).getSubjName(),this.subjects.get(i).getScore());
			if(i != this.subjects.size()-1) output += " / ";
		}
		
		return output;
	}

	
	
	
}
