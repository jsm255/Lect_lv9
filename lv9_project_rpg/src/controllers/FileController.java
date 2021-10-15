package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class FileController {
	
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	private String memberFile = "Members.txt";
	private String equipFile = "Equipments.txt";
	private String masterFile = "GameMaster.txt";
	
	public static FileController instance = new FileController();
	
	private FileController() {}
	
	
	public void save() {
		MemberController mc = MemberController.instance;
		ShopController sc = ShopController.instance;
		
		try {
			file = new File(this.memberFile);
			fw = new FileWriter(file);
			
			for(int i = 0; i<mc.getSize(); i++) {
				fw.write(mc.getName(i)+"/");
				fw.write(String.valueOf(mc.getLv(i))+"/");
				fw.write(String.valueOf(mc.getHp(i))+"/");
				fw.write(String.valueOf(mc.getAtk(i))+"/");
				fw.write(String.valueOf(mc.getDef(i))+"/");
				fw.write(String.valueOf(mc.getWeaponIdx(i))+"/");
				fw.write(String.valueOf(mc.getArmorIdx(i))+"/");
				fw.write(String.valueOf(mc.getRingIdx(i))+"/");
				fw.write(String.valueOf(mc.getParty(i)));
				
				if(i != mc.getSize()-1) fw.write("\n");
			}
			
			fw.close();
			System.out.println("길드원 파일 저장 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			file = new File(this.equipFile);
			fw = new FileWriter(file);
			
			for(int i = 0; i<sc.getSize(); i++) {
				fw.write(String.valueOf(sc.getEquipSort(i))+"/");
				fw.write(sc.getEquipName(i)+"/");
				fw.write(String.valueOf(sc.getEquipHp(i))+"/");
				fw.write(String.valueOf(sc.getEquipAtk(i))+"/");
				fw.write(String.valueOf(sc.getEquipDef(i))+"/");
				fw.write(String.valueOf(sc.getEquipCost(i))+"/");
				fw.write(String.valueOf(sc.getEquipHave(i))+"/");
				fw.write(String.valueOf(-1)+"/");
				fw.write(String.valueOf(sc.getEquipWearing(i)));
				
				if(i != sc.getSize()-1) fw.write("\n");
			}
			
			fw.close();
			System.out.println("장비 파일 저장 완료!");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			file = new File(this.masterFile);
			fw = new FileWriter(file);
			
			fw.write(String.valueOf(GameMaster.gold)+"/"+
					String.valueOf(GameMaster.partyMembers));
			
			fw.close();
			System.out.println("마스터 파일 저장 완료!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void load() {
		MemberController mc = MemberController.instance;
		ShopController sc = ShopController.instance;
		
		mc.resetMembers();
		sc.resetEquips();
		
		try {
			file = new File(this.memberFile);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String reader = "";
			
			while((reader = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(reader,"/");
				
				mc.loadMember(st.nextToken(), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						loadParty(st.nextToken()));
			}
			
			br.close();
			fr.close();
			System.out.println("길드원 파일 로드 완료!");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			file = new File(this.equipFile);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String reader = "";
			
			while((reader = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(reader, "/");
				
				sc.loadEquips(Integer.parseInt(st.nextToken()), st.nextToken(), 
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()));
			}
			
			br.close();
			fr.close();
			System.out.println("장비 파일 로드 완료!");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			file = new File(this.masterFile);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			StringTokenizer st = new StringTokenizer(br.readLine(),"/");
			
			GameMaster.gold = Integer.parseInt(st.nextToken());
			GameMaster.partyMembers = Integer.parseInt(st.nextToken());
			
			br.close();
			fr.close();
			System.out.println("마스터 파일 로드 완료!");
		} catch (Exception e) {
			// TODO: handle exception
		} 
	}
	
	private boolean loadParty(String token) {
		if(token.equals("true")) return true;
		else return false;
	}
	
}
