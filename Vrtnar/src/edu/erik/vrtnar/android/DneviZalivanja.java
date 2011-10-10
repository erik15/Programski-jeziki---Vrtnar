package edu.erik.vrtnar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class DneviZalivanja extends Activity implements OnClickListener {
	public static final String zPON="PON";
	public static final String zTOR="TOR";
	public static final String zSRE="SRE";
	public static final String zCET="CET";
	public static final String zPET="PET";
	public static final String zSOB="SOB";
	public static final String zNED="NED";
	CheckBox cbPon;
	CheckBox cbTor;
	CheckBox cbSre;
	CheckBox cbCet;
	CheckBox cbPet;
	CheckBox cbSob;
	CheckBox cbNed;

	Button shrani_zalivanje;
	Menu meni;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tedendnevi);
        cbPon = (CheckBox)  findViewById(R.id.cb_dzalivanja_pon);
        cbTor = (CheckBox)  findViewById(R.id.cb_dzalivanja_tor);
        cbSre = (CheckBox)  findViewById(R.id.cb_dzalivanja_sre);
        cbCet = (CheckBox)  findViewById(R.id.cb_dzalivanja_cet);
        cbPet = (CheckBox)  findViewById(R.id.cb_dzalivanja_pet);
        cbSob = (CheckBox)  findViewById(R.id.cb_dzalivanja_sob);
        cbNed = (CheckBox)  findViewById(R.id.cb_dzalivanja_ned);
        shrani_zalivanje = (Button) findViewById(R.id.btn_shrani_zalivanje);
        shrani_zalivanje.setOnClickListener(this);
      
	}

	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra(zPON, cbPon.isChecked()); //shrani vrednosti v intent
		intent.putExtra(zTOR, cbTor.isChecked());
		intent.putExtra(zSRE, cbSre.isChecked());
		intent.putExtra(zCET, cbCet.isChecked());
		intent.putExtra(zPET, cbPet.isChecked());
		intent.putExtra(zSOB, cbSob.isChecked());
		intent.putExtra(zNED, cbNed.isChecked());
		
		setResult(RESULT_OK,intent);
	    finish();	
	}
	
}
