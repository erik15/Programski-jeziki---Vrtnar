package edu.erik.vrtnar.android;

import java.util.ArrayList;


import edu.erik.vrtnar.data.DBAdapterSajenja;

import android.app.Application;
import android.database.Cursor;

public class ApplicationExample extends Application {
	//Step 4.1
	//Step 4.2 popravi AndroidManifest.xml
	public ArrayList<Sajenja> lista;
	SajenjeArrayAdapter stevci; //Step 4.9 Globalna lista
	Sajenja s;
	DBAdapterSajenja db;
	public ArrayList<String> array_spinner1;
	public void onCreate() {
        super.onCreate(); //ne pozabi
        db = new DBAdapterSajenja(this);
        lista = new ArrayList<Sajenja>(); //inicializirat
         init();
         fillFromDB();
         array_spinner1= new ArrayList<String>();
        stevci = new SajenjeArrayAdapter(this, R.layout.sajenje_layout,lista); //Step 4.10 Globalna lista
        
	}
	public void getname()
	{
		db.open();
		Cursor c = db.getAll();
		array_spinner1.clear();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) 
		{
			array_spinner1.add(c.getString(DBAdapterSajenja.POS_NAME));
		}
		c.close();
		db.close();
	}
	public void fillFromDB() {
		db.open();
		Cursor c = db.getAll();
		Sajenja tmp;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			tmp = new Sajenja();
			tmp.setName(c.getString(DBAdapterSajenja.POS_NAME));
			//tmp.setStanje(c.getInt(DBAdapterSajenja.POS_VALUE));
			//tmp.setDbID(c.getLong(DBAdapterSajenja.POS__ID));
			lista.add(tmp); 
		}
		c.close();
		db.close();
	}
	public void addDB(Sajenja s) {
		db.open();
		s.setDbID(db.insertSajenje(s));
		db.close();	
	}
	
	public void init() {
		s = new Sajenja();
		s.setName("Izberi sajenje");
		lista.add(s);
	}
	public void remove(Sajenja a) {
		//lista.add(a);
		if (a!=null)
		stevci.remove(a);  //Step 4.10 Globalna lista
	}
}
