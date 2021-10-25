package controllers;

import java.util.ArrayList;

import models.Student;

public class SystemManager {
	
	private ArrayList<Student> students = new ArrayList<>();
	
	private static SystemManager instance = new SystemManager();
	
	private SystemManager() {}
	
	public static SystemManager getSM() {
		return instance;
	}
	
	public void run() {
		실험용메서드();
	}
	
	public void 실험용메서드() {
		this.students.add(new Student("sm", 1111));
		this.students.get(0).newSubject("국어");
		
		for(Student stu : this.students) {
			System.out.println(stu);
		}
	}
}
