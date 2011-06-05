package edu.erik.vrtnar.android;

public class Sajenja {
	
	public static final String SAJENJA_INC="PREF_INCREMENT_BUTTON";  //pref ime spremenljuvke
	
	private int st_sajenj;
	private long DbID;
	
	private String name = "Sadika";

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Sajenja(){
		st_sajenj = 0;
	}
	
	public void Povecaj(){
		st_sajenj++;
		}
	
	public int getPovecaj(){
		return st_sajenj;
	}

	public void setDbID(long dbID) {
		DbID = dbID;
	}

	public long getDbID() {
		return DbID;
	}
}
	
