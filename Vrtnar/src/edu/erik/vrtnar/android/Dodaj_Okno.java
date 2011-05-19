package edu.erik.vrtnar.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Dodaj_Okno extends Activity implements OnClickListener {

	Button shrani;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugi);
        
        shrani = (Button) findViewById(R.id.btn_shrani);
        shrani.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
