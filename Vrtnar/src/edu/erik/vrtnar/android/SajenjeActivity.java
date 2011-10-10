package edu.erik.vrtnar.android;


import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SajenjeActivity extends Activity implements OnClickListener {

	public static final int OPEN_DNEVI=99;
	public static final String FILE_NAME_SAJENJE="sajenje.txt";
	private static final String TAG = null;
	public static final String PREF_NAME="SAJENJA";
	public PrintWriter myfile;
	ApplicationExample app; //Step 4.3
	String izbrani="";
	Button shrani_sajenje;
	Button dnevi_sajenja;
	Menu meni;
	TextView textViewIzbraniDnevi;
	EditText vnos;
	Spinner sp;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugi);
        
        app = (ApplicationExample) getApplication();
        shrani_sajenje = (Button) findViewById(R.id.btn_shrani_sajenje);
        shrani_sajenje.setOnClickListener(this);
        dnevi_sajenja = (Button) findViewById(R.id.btn_dsajenja);
        dnevi_sajenja.setOnClickListener(this);
        vnos = (EditText) findViewById(R.id.edit_vnos);
        sp = (Spinner) findViewById(R.id.spinnerSajenja);
        app.getname();

        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, app.array_spinner1);
	    adapter2.setNotifyOnChange(true);
	    sp.setAdapter(adapter2);
		
       // sp.setAdapter(app.stevci);
       // textViewIzbraniDnevi = (TextView) findViewById(R.id.textViewIzbraniDnevi);
	}
	@Override
	public void onStart() {
		super.onStart();
		try {
			myfile = new PrintWriter(openFileOutput(FILE_NAME_SAJENJE, Context.MODE_PRIVATE));
		} catch (IOException e) {
			Log.e(TAG,"Datoteka napake "+e.toString());
			e.printStackTrace();
	}
	}

	@Override
	public void onResume() { //pref predno user vidi nastavim prave vrednosti
		super.onResume();
		SharedPreferences settings = getSharedPreferences(PREF_NAME,0); //pref odprem preferences
		int tmp = settings.getInt(Sajenja.SAJENJA_INC, 0); //pref preberem staro vrednost
		//showKlikRandom(); //pref osve�im na ekranu kar se vidi
	}
	
	@Override
	public void onPause() { //pref uporabnik ali OS zapusti pogled, potrebno shranit
		super.onPause();
		SharedPreferences settings = getSharedPreferences(PREF_NAME,0); //pref odprem
		SharedPreferences.Editor editor = settings.edit(); //pref dam v edit mode
		//editor.putInt(Sajenja.SAJENJA_INC, stej.getPovecaj()); //pref nastavim novo vrednost
		editor.commit(); //pref shranim novo vrednost
		if (myfile!=null){
			myfile.close();
		}
	}
	
	public void onClick(View arg0) {
		 if (arg0.getId()== R.id.btn_dsajenja ){
             Intent i = new Intent();
             i.setClass(this, DneviTeden.class);
             Bundle b = new Bundle();
             b.putString(DneviTeden.DNEVI_ALL, "");
             i.putExtras(b);
             startActivityForResult(i, OPEN_DNEVI);
             }
         if (arg0.getId()== R.id.btn_shrani_sajenje ){ //manjka
             Sajenja s = new Sajenja();
             //s.setName(vnos.getText().toString());
             
             //DODAJANJE IZ SPINNERA
            if(vnos.getText().toString().equals("Vnesi sajenje...") || vnos.getText().toString().equals(""))
            {
            String a=sp.getSelectedItem().toString();
             s.setName(a);
             s.setDnevi(izbrani);
             
             app.addDB(s);
			 Toast.makeText(this,"Dodano:"+ s.getName()+" v "+s.getDnevi(), Toast.LENGTH_SHORT)
				.show(); 
            }
            else
            {
            	s.setName(vnos.getText().toString());
                s.setDnevi(izbrani);

                app.addDB(s);
   			 Toast.makeText(this,"Dodano:"+ s.getName()+" v "+s.getDnevi(), Toast.LENGTH_SHORT)
   				.show(); 
            }
             finish();
         	}
		
	}
	 @Override
	 protected void onActivityResult (int requestCode, int resultCode, Intent data) {
		 if (resultCode==RESULT_OK) {
			 if (requestCode==OPEN_DNEVI) {
				
				 if (data.getBooleanExtra(DneviTeden.sPON, false)) { //neki naredis
					 izbrani +="PON "; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sTOR, false)) { //neki naredis
					 izbrani +="TOR "; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sSRE, false)) { //neki naredis
					 izbrani +="SRE "; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sCET, false)) { //neki naredis
					 izbrani +="ČET "; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sPET, false)) { //neki naredis
					 izbrani +="PET "; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sSOB, false)) { //neki naredis
					 izbrani +="SOB "; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sNED, false)) { //neki naredis
					 izbrani +="NED "; 
				 }
				 //textViewIzbraniDnevi.setText(izbrani);
				 Toast.makeText(this, izbrani, Toast.LENGTH_SHORT)
					.show(); 
			 }
		 }
	 }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		meni = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, meni);
		return true;
	}
	
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {			
		case R.id.btn_dsajenja:
			Intent i = new Intent();
			i.setClass(this, MenuPreferences.class);
			startActivityForResult(i, R.id.btn_dsajenja);
			return true;

		default:// Generic catch all for all the other menu resources
			if (!item.hasSubMenu()) {
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT)
				.show();
				return true;
			}
			break;
		}
		return false;
	}
}
