package models;

public abstract class Functions {
	protected final String name;
	
	public Functions(String name) {
		this.name = name;
	}
	
	public abstract String getName();
	public abstract int getInt();
}
