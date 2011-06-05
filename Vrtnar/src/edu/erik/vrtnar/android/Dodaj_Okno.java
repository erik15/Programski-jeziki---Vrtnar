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
import android.widget.Button;
import android.widget.Toast;

public class Dodaj_Okno extends Activity implements OnClickListener {


	public static final int OPEN_DNEVI=99;
	public static final String FILE_NAME_SAJENJE="sajenje.txt";
	private static final int ACTIVITY_BAZA = 4;  //Step 4.12
	private static final String TAG = null;
	public static final String PREF_NAME="SAJENJA";
	public PrintWriter myfile;
	ApplicationExample app; //Step 4.3
	Button shrani_sajenje;
	Button dnevi_sajenja;
	Menu meni;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugi);
        
        shrani_sajenje = (Button) findViewById(R.id.btn_shrani_sajenje);
        shrani_sajenje.setOnClickListener(this);
        dnevi_sajenja = (Button) findViewById(R.id.btn_dsajenja);
        dnevi_sajenja.setOnClickListener(this);
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
	
	@Override
	public void onClick(View arg0) {
		if (arg0.getId()== R.id.btn_dsajenja ){
		Intent i = new Intent();
		i.setClass(this, DneviTeden.class);
		startActivityForResult(i, OPEN_DNEVI);
		}
		
	}
	 @Override
	 protected void onActivityResult (int requestCode, int resultCode, Intent data) {
		 if (resultCode==RESULT_OK) {
			 if (requestCode==OPEN_DNEVI) {
				 String izbrani="";
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
					 izbrani +="ČET"; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sPET, false)) { //neki naredis
					 izbrani +="PET "; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sSOB, false)) { //neki naredis
					 izbrani +="SOB "; 
				 }
				 if (data.getBooleanExtra(DneviTeden.sNED, false)) { //neki naredis
					 izbrani +="NET "; 
				 }
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
