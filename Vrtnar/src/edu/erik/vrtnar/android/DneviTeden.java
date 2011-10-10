package edu.erik.vrtnar.android;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class DneviTeden extends Activity implements OnClickListener {
	public static final String DNEVI_ALL="DNEVI_ALL";
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
	String dnevi="";
	String[][] checkBoxValues = new String[2][7];
	
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
        
        
        Calendar koledar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        for(int i = 0; i < 7; i++) {
        	checkBoxValues[0][i] = getDan(koledar.get(Calendar.DAY_OF_WEEK));
        	checkBoxValues[1][i] = sdf.format(koledar.getTime());
        	koledar.add(Calendar.DATE, 1);
        }
        
        cbPon.setText(checkBoxValues[0][0]+" "+checkBoxValues[1][0]);
        cbTor.setText(checkBoxValues[0][1]+" "+checkBoxValues[1][1]);
        cbSre.setText(checkBoxValues[0][2]+" "+checkBoxValues[1][2]);
        cbCet.setText(checkBoxValues[0][3]+" "+checkBoxValues[1][3]);
        cbPet.setText(checkBoxValues[0][4]+" "+checkBoxValues[1][4]);
        cbSob.setText(checkBoxValues[0][5]+" "+checkBoxValues[1][5]);
        cbNed.setText(checkBoxValues[0][6]+" "+checkBoxValues[1][6]);

        shrani.setOnClickListener(this);
	}

	private String getDan(int i) {
		if(i==Calendar.MONDAY)
			return "Ponedeljek";
		if(i==Calendar.TUESDAY)
			return "Torek";
		else if(i==Calendar.WEDNESDAY)
			return "Sreda";
		else if(i==Calendar.THURSDAY)
			return "Cetrtek";
		if(i==Calendar.FRIDAY)
			return "Petek";
		if(i==Calendar.SATURDAY)
			return "Sobota";
		return "Nedelja";
	}

	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra(checkBoxValues[1][0], cbPon.isChecked()); //shrani vrednosti v intent
		intent.putExtra(checkBoxValues[1][1], cbTor.isChecked());
		intent.putExtra(checkBoxValues[1][2], cbSre.isChecked());
		intent.putExtra(checkBoxValues[1][3], cbCet.isChecked());
		intent.putExtra(checkBoxValues[1][4], cbPet.isChecked());
		intent.putExtra(checkBoxValues[1][5], cbSob.isChecked());
		intent.putExtra(checkBoxValues[1][6], cbNed.isChecked());
		
		setResult(RESULT_OK,intent);
	    finish();	
	}
	
}
