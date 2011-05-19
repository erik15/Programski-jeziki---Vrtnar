package edu.erik.naloga2_1.android;

public class Rezultati {
	public static final String STEVEC_INC="PREF_INCREMENT_BUTTON";  //pref ime spremenljuvke
	
	private int st;
	private long DbID;
	private int tezavnost=10;
	
	private String name = "Erik";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public Rezultati()
	{
		st=0;
	}
	
	public Rezultati (Rezultati mojrezultat)
	{
		st=mojrezultat.st;
		name = mojrezultat.name;
	}
	public void Povecaj(){
	st++;
	}
	public int getPovecaj(){
		return st;
	}
	
	public void Reset(){
		st=0;
	}
	
	public void setStanje(int st){
		this.st = st;
	}

	public void setDbID(long dbID) {
		DbID = dbID;
	}

	public long getDbID() {
		return DbID;
	}

	public void setTezavnost(int tezavnost) {
		this.tezavnost = tezavnost;
	}

	public int getTezavnost() {
		return tezavnost;
	}





}
