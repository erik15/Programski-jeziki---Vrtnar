package edu.erik.vrtnar.android;

public class Sajenja {
	
	@Override
	public String toString() {
		return "Sajenja [DbID=" + DbID + ", name=" + name + ", dnevi=" + dnevi
				+ "]";
	}

	public static final String SAJENJA_INC="PREF_INCREMENT_BUTTON";  //pref ime spremenljuvke
	
	private int st_sajenj;
	private long DbID;
	
	private String name = "Sadika";
	private String dnevi = "PON SRE";

	public String getDnevi() {
		return dnevi;
	}

	public void setDnevi(String dnevi) {
		this.dnevi = dnevi;
	}

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
	
