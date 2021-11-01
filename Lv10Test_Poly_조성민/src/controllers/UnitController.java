package controllers;

public class UnitController {
	
	private static UnitController instance = new UnitController();
	private UnitController() {}
	public static UnitController getInstance() {
		return instance;
	}
	
	
	
}
