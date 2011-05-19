package edu.erik.naloga2_1.android;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class NovoOkno extends Activity implements OnClickListener{
	
	Button novo, nazaj;
	Rezultati stej;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugo);
        stej=new Rezultati();
        
        novo = (Button) findViewById(R.id.Nova);
        novo.setOnClickListener(this);
        
        nazaj = (Button) findViewById(R.id.Izhod);
        nazaj.setOnClickListener(this);   
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId()==R.id.Nova) {
			//stej.Reset();
			//this.stej.Reset();
			setResult(RESULT_OK);
			finish();
		}
		else if (arg0.getId()==R.id.Izhod) {
			setResult(RESULT_CANCELED);
			finish();
		}
	}
    
    
}
