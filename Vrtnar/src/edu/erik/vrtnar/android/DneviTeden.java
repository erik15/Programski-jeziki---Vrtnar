package edu.erik.vrtnar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class DneviTeden extends Activity implements OnClickListener {
	public static final String sPON="PON";
	public static final String sTOR="TOR";
	public static final String sSRE="SRE";
	public static final String sCET="CET";
	public static final String sPET="PET";
	public static final String sSOB="SOB";
	public static final String sNED="NED";
	CheckBox cbPon;
	CheckBox cbTor;
	CheckBox cbSre;
	CheckBox cbCet;
	CheckBox cbPet;
	CheckBox cbSob;
	CheckBox cbNed;
	Button shrani;
	Menu meni;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tedendnevi);
        cbPon = (CheckBox)  findViewById(R.id.cb_dsajenja_pon);
        cbTor = (CheckBox)  findViewById(R.id.cb_dsajenja_tor);
        cbSre = (CheckBox)  findViewById(R.id.cb_dsajenja_sre);
        cbCet = (CheckBox)  findViewById(R.id.cb_dsajenja_cet);
        cbPet = (CheckBox)  findViewById(R.id.cb_dsajenja_pet);
        cbSob = (CheckBox)  findViewById(R.id.cb_dsajenja_sob);
        cbNed = (CheckBox)  findViewById(R.id.cb_dsajenja_ned);
        shrani = (Button) findViewById(R.id.btn_shrani);
        shrani.setOnClickListener(this);
      
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra(sPON, cbPon.isChecked()); //shrani vrednosti v intent
		intent.putExtra(sTOR, cbTor.isChecked());
		intent.putExtra(sSRE, cbSre.isChecked());
		intent.putExtra(sCET, cbCet.isChecked());
		intent.putExtra(sPET, cbPet.isChecked());
		intent.putExtra(sSOB, cbSob.isChecked());
		intent.putExtra(sNED, cbNed.isChecked());
		
		setResult(RESULT_OK,intent);
	    finish();	
	}
	
}
