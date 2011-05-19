package edu.erik.naloga2_1.android;

import java.util.ArrayList;

import edu.erik.naloga2_1.data.DBAdapterRezultat;

import android.app.Application;
import android.database.Cursor;

public class ApplicationExample extends Application {
	//Step 4.1
	//Step 4.2 popravi AndroidManifest.xml
	public ArrayList<Rezultati> lista;
	RezultatiArrayAdapter stevci; //Step 4.9 Globalna lista
	Rezultati mojStevec;
	DBAdapterRezultat db;
	public void onCreate() {
        super.onCreate(); //ne pozabi
        db = new DBAdapterRezultat(this);
        lista = new ArrayList<Rezultati>(); //inicializirat
         init();
         fillFromDB();
        stevci = new RezultatiArrayAdapter(this, R.layout.stevec_layout,lista); //Step 4.10 Globalna lista
        
	}
	
	public void fillFromDB() {
		db.open();
		Cursor c = db.getAll();
		Rezultati tmp;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			tmp = new Rezultati();
			tmp.setName(c.getString(DBAdapterRezultat.POS_NAME));
			tmp.setStanje(c.getInt(DBAdapterRezultat.POS_VALUE));
			tmp.setDbID(c.getLong(DBAdapterRezultat.POS__ID));
			lista.add(tmp); 
		}
		c.close();
		db.close();
	}
	public void addDB(Rezultati s) {
		db.open();
		s.setDbID(db.insertStevc(s));
		db.close();	
	}
	
	public void init() {
		mojStevec = new Rezultati();
		mojStevec.setName("Skupni");
		lista.add(mojStevec);
	}
	public void add(Rezultati a) {
		//lista.add(a);
		Rezultati temp = new Rezultati(mojStevec);
		addDB(temp);
		stevci.add(a);  //Step 4.10 Globalna lista
	}
	public void remove(Rezultati a) {
		//lista.add(a);
		if (a!=null)
		stevci.remove(a);  //Step 4.10 Globalna lista
	}
}
