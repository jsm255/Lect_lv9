package models;

public abstract class Functions {
	protected final String name;
	
	public Functions(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public abstract int getInt();
}
