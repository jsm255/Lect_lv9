package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

import models.Shop;

public class FileManager {

	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	private String usersFile = "usersFile.txt";
	private String catsFile = "catsFile.txt";
	private String itemsFile = "itemsFile.txt";
	private String basketsFile = "basketsFile.txt";
	
	private CategoryManager cm = CategoryManager.instance;
	private ItemManager im = ItemManager.instance;
	private UserManager um = UserManager.instance;
	private BasketManager bm = BasketManager.instance;
	
	public static FileManager instance = new FileManager();
	
	private FileManager() {}
	
	public void saveAll() {
		try {
			this.file = new File("Income.txt");
			this.fw = new FileWriter(this.file);
			
			this.fw.write(String.valueOf(Shop.getIncome()));
			
			this.fw.close();
			System.out.println("매출 정보 저장 완료!");
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			String data = saveUsers();
			this.file = new File(this.usersFile);
			this.fw = new FileWriter(this.file);
			
			this.fw.write(data);
			
			this.fw.close();
			System.out.println("유저 정보 저장 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String data = saveCats();
			this.file = new File(this.catsFile);
			this.fw = new FileWriter(this.file);
			
			this.fw.write(data);
			
			this.fw.close();
			System.out.println("카테고리 정보 저장 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String data = saveItems();
			this.file = new File(this.itemsFile);
			this.fw = new FileWriter(this.file);
			
			this.fw.write(data);
			
			this.fw.close();
			System.out.println("품목 정보 저장 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String data = saveBaskets();
			this.file = new File(this.basketsFile);
			this.fw = new FileWriter(this.file);
			
			this.fw.write(data);
			
			this.fw.close();
			System.out.println("장바구니 정보 저장 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadAll() {
		cm.resetCategory();
		im.resetItem();
		um.resetUsers();
		bm.resetBasket();
		
		try {
			this.file = new File("Income.txt");
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			int cash = Integer.parseInt(br.readLine());
			if(Shop.getIncome() != 0) {
				Shop.plusIncome(-Shop.getIncome());
				Shop.plusIncome(cash);
			}
			else Shop.plusIncome(cash);
			
			this.br.close();
			this.fr.close();
			System.out.println("매출 정보 로드 완료!");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			this.file = new File(usersFile);
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			String reader = "";
			while((reader = br.readLine()) != null) {
				
				StringTokenizer st = new StringTokenizer(reader, "/");
				um.loadUser(Integer.parseInt(st.nextToken()),
						st.nextToken(),Integer.parseInt(st.nextToken()));
			}
			this.br.close();
			this.fr.close();
			System.out.println("유저 정보 로드 완료!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("유저 로드 에러");
		}
		
		try {
			this.file = new File(catsFile);
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			String reader = "";
			while((reader = br.readLine()) != null) {
				
				cm.loadCategory(reader);
			}
			this.br.close();
			this.fr.close();
			System.out.println("카테고리 정보 로드 완료!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("카테고리 로드 에러");
		}
		
		try {
			this.file = new File(itemsFile);
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			String reader = "";
			while((reader = br.readLine()) != null) {
				
				StringTokenizer st = new StringTokenizer(reader, "/");
				
				im.newItem(st.nextToken(), st.nextToken(),
						Integer.parseInt(st.nextToken()));
			}
			this.br.close();
			this.fr.close();
			System.out.println("품목 정보 로드 완료!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("품목 로드 에러");
		}
		
		try {
			this.file = new File(basketsFile);
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			String reader = "";
			while((reader = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(reader, "/");
				
				bm.loadBasket(Integer.parseInt(st.nextToken()),
						st.nextToken(), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
				
			}
			this.br.close();
			this.fr.close();
			System.out.println("장바구니 정보 로드 완료!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("장바구니 로드 에러");
		}
	}
	
	private String saveUsers() {
		String data = "";
		
		for(int i = 0; i<um.getUsersSize(); i++) {
			data += um.getUserCode(i) + "/";
			data += um.getUserName(i) + "/";
			data += um.getUserSpent(i);
			if(i != um.getUsersSize()-1) data += "\n";
		}
		return data;
	}
	
	private String saveCats() {
		String data = "";
		
		for(int i = 0; i<cm.getCatsSize(); i++) {
			data += cm.getCatName(i);
			if(i != cm.getCatsSize()-1) data += "\n";
		}
		return data;
	}
	
	private String saveItems() {
		String data = "";
		
		for(int i = 0; i<im.getItemSize(); i++) {
			data += im.getItemCat(i) + "/";
			data += im.getItemName(i) + "/";
			data += im.getItemPrice(i);
			if(i != im.getItemSize()-1) data += "\n";
		}
		return data;
	}
	
	private String saveBaskets() {
		String data = "";
		
		for(int i = 0; i<bm.getBasketSize(); i++) {
			data += bm.getUserCode(i) + "/";
			data += bm.getItemName(i) + "/";
			data += bm.getPrice(i) + "/";
			data += bm.getCnt(i);
			if(i != bm.getBasketSize()-1) data += "\n";
		}
		return data;
	}
}
