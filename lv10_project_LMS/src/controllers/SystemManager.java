package controllers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.Student;

public class SystemManager {
	
	private ArrayList<Student> students = new ArrayList<>();
	private int log = -1;
	
	Random ran = new Random();
	Scanner scan = new Scanner(System.in);
	
	// singleton (private(static) private public(static))
	private static SystemManager instance = new SystemManager();
	private SystemManager() {}
	public static SystemManager getSM() {
		return instance;
	}
	
	public void run() {
		printMainMenu();
	}
	
	public void 실험용메서드() {
		this.students.add(new Student("sm", 1111));
		this.students.get(0).newSubject("국어");
		
		for(Student stu : this.students) {
			System.out.println(stu);
		}
	}
	
	public void printMainMenu() {
		while(true) {
			if(this.log != -1) {
				
			}
			else {
				System.out.println("========== L M S ==========");
				System.out.println("1. 학생 등록  2. 간단 로그인  0. 종료");
				System.out.print("메뉴 입력 : ");
				int sel = catchInteger();
				
				if(sel >= 0 && sel <= 2) {
					if(sel == 1) newStudent();
					else if(sel == 2) simpleLogin();
					else if(sel == 0) break;
				}
			}
		}
	}
	
	private int catchInteger() {
		String input = scan.next();
		try {
			int sel = Integer.parseInt(input);
			
			return sel;
		} catch (Exception e) {
			System.out.println("유효하지 않은 입력입니다.");
			return -1;
		}
	}
	
	public void newStudent() {
		System.out.print("등록할 학생의 이름을 입력 : ");
		String name = scan.next();
		
		this.students.add(new Student(name, randomCode()));
		
		System.out.println("등록 완료.");
	}
	
	private int randomCode() {
		while(true) {
			int rCode = ran.nextInt(9000)+1000;
			
			boolean found = false;
			for(Student stu : this.students) {
				if(rCode == stu.getCode()) found = true;
			}
			
			if(found == false) return rCode;
		}
	}
	
	private void simpleLogin() {
		System.out.print("이름을 입력 : ");
		String name = scan.next();
		
		int cnt = 0;
		for(Student stu : this.students) {
			if(name.equals(stu.getName())) cnt ++;
		}
		
		if(cnt == 0) System.out.println("해당 이름을 가진 학생이 없습니다.");
		else if(cnt == 1) {
			for(int i = 0; i<this.students.size(); i++) {
				if(name.equals(this.students.get(i).getName())) this.log = i;
			}
			
			System.out.println("로그인되었습니다.");
		}
		else {
			System.out.println("같은 이름의 학생이 여러 명입니다.");
			System.out.print("학생 코드를 입력 : ");
			int code = catchInteger();
			
			boolean found = false;
			for(int i = 0; i<this.students.size(); i++) {
				if(name.equals(this.students.get(i).getName()) && 
						code == this.students.get(i).getCode()) {
					found = true;
					this.log = i;
				}
			}
			
			if(found == true) System.out.println("로그인되었습니다.");
			else System.out.println("해당 코드와 이름이 일치하는 학생이 없습니다.");
		}
	}
}
