package edu.erik.vrtnar.android;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class vrtnar extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private static final int TEST_START_ACTIVITY_ID = 1;
	Button dodaj, doloci_dneve;
	ImageView vreme;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dodaj = (Button) findViewById(R.id.btn_dodaj);
        dodaj.setOnClickListener(this);
        doloci_dneve = (Button) findViewById(R.id.btn_doloci);
        doloci_dneve.setOnClickListener(this);
        vreme = (ImageView) findViewById(R.id.vreme_slika);
        Drawable drawable = LoadImageFromWebOperations("http://img.rtvslo.si/_up/vreme_2008.png");    
        vreme.setImageDrawable(drawable);
    }
    
    private Drawable LoadImageFromWebOperations(String url)
    {
    try
    {
    InputStream is = (InputStream) new URL(url).getContent();
    Drawable d = Drawable.createFromStream(is, "src name");
    return d;
    }catch (Exception e) {
    System.out.println("Exc="+e);
    return null;
    }
    }
    
    public void onClick(View arg0) {
    	
    	if (arg0.getId()== R.id.btn_dodaj ){
    		Intent moj=new Intent(this,Dodaj_Okno.class);
			//this.startActivity(moj);
			this.startActivityForResult(moj, TEST_START_ACTIVITY_ID);
    	}
    }
    
    
    
}