package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class FileManager {
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	private String fileName = "Students.txt";
	
	// singleton
	private static FileManager instance = new FileManager();
	private FileManager() {}
	public static FileManager getFM() {
		return instance;
	}
	
	public void save() {
		SystemManager sm = SystemManager.getSM();
		
		try {
			this.file = new File(this.fileName);
			this.fw = new FileWriter(this.file);
			
			for(int i = 0; i<sm.getStudentSize(); i++) {
				this.fw.write(String.format("%s %d %d", sm.getStudent(i).getName(),
						sm.getStudent(i).getInt(), sm.getStudent(i).getSubjSize()));
				if(sm.getStudent(i).getSubjSize() != 0) {
					this.fw.write(" ");
					for(int j = 0; j<sm.getStudent(i).getSubjSize(); j++) {
						this.fw.write(String.format("%s/%d", 
								sm.getStudent(i).getSubj(j).getName(),
								sm.getStudent(i).getSubj(j).getInt()));
						if(j != sm.getStudent(i).getSubjSize()-1) this.fw.write(",");
					}
				}
				if(i != sm.getStudentSize()-1) this.fw.write("\n");
			}
			
			this.fw.close();
			
			System.out.println("저장 완료.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("뭔가 에러");
		}
	}
	
	public void load() {
		SystemManager sm = SystemManager.getSM();
		sm.resetStudents();
		
		try {
			this.file = new File(this.fileName);
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			int stuIdx = 0;
			String loading = "";
			while((loading = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(loading, " ");
				sm.loadStudent(st.nextToken(), Integer.parseInt(st.nextToken()));
				
				int subjSize = Integer.parseInt(st.nextToken());
				if(subjSize >= 1) {
					StringTokenizer allSubjAndScore = new StringTokenizer(st.nextToken(), ",");
					for(int i = 0; i<subjSize; i++) {
						StringTokenizer last = 
								new StringTokenizer(allSubjAndScore.nextToken(), "/");
						sm.loadSubject(stuIdx, last.nextToken(),
								Integer.parseInt(last.nextToken()));
					}
				}
				
				stuIdx ++;
			}
			
			this.br.close();
			this.fr.close();
			
			System.out.println("로드 완료.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			System.out.println("뭔가 에러 2");
		}
	}
}
