package edu.erik.vrtnar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class vrtnar extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private static final int TEST_START_ACTIVITY_ID = 1;
	Button dodaj, doloci_dneve;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dodaj = (Button) findViewById(R.id.btn_dodaj);
        dodaj.setOnClickListener(this);
        doloci_dneve = (Button) findViewById(R.id.btn_doloci);
        doloci_dneve.setOnClickListener(this);
    }
    
    public void onClick(View arg0) {
    	
    	if (arg0.getId()== R.id.btn_dodaj ){
    		Intent moj=new Intent(this,Dodaj_Okno.class);
			//this.startActivity(moj);
			this.startActivityForResult(moj, TEST_START_ACTIVITY_ID);
    	}
    
    }
}