package edu.erik.vrtnar.android;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class vrtnar extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	
	Button dodaj, doloci_dneve_zalivanja, obeti;
	ImageView vreme;
	Menu meni;
	TextView danes, jutri;
	Sajenja s;
	ApplicationExample app;
	private static final int TEST_START_ACTIVITY_ID = 1;
	private static final int OPEN_DNEVI_ZALIVANJA = 10;
	private static final int OPEN_OBETI = 11;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		obeti = (Button) findViewById(R.id.btn_obeti);
		obeti.setOnClickListener(this);
		dodaj = (Button) findViewById(R.id.btn_dodaj);
		dodaj.setOnClickListener(this);		
		doloci_dneve_zalivanja = (Button) findViewById(R.id.btn_dzalivanja);
		doloci_dneve_zalivanja.setOnClickListener(this);		
		vreme = (ImageView) findViewById(R.id.vreme_slika);
		Drawable drawable = LoadImageFromWebOperations("http://img.rtvslo.si/_up/vreme_2008.png");    
		vreme.setImageDrawable(drawable);
        danes = (TextView)findViewById(R.id.txt_danes);
        jutri = (TextView)findViewById(R.id.txt_jutri);
        
        //if(s.getDnevi()=="TOR")
        //{
        	//jutri.setText("DAN:"+s.getDnevi()); 
        	//danes.setText("Danes morate posaditi oz. posejati: "+ s.getName());	
        //}
        //s = new Sajenja();
        //jutri.setText("DAN:"+s.getDnevi());
        Calendar koledar = Calendar.getInstance();
        int dan = koledar.get(Calendar.DAY_OF_WEEK);
        System.out.println(dan);
        
	}
	
	//funkcija za prikaz vremena s strani rtvslo
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
	
//ko kliknemo se nam odpre novo okno za dodajanje sajenja, hkrat pa določamo lahko dneve zalivanja
	@Override
	public void onClick(View arg0) {

		if (arg0.getId()== R.id.btn_dodaj ){
			//Intent i=new Intent(this,SajenjeActivity.class);
			//this.startActivity(moj);
			Intent i = new Intent();
			i.setClass(this, SajenjeActivity.class);
			this.startActivityForResult(i, TEST_START_ACTIVITY_ID);
		}
		if (arg0.getId()== R.id.btn_dzalivanja ){
		Intent i = new Intent();
		i.setClass(this, DneviTeden.class);
		this.startActivityForResult(i, OPEN_DNEVI_ZALIVANJA);
		}
		if (arg0.getId()== R.id.btn_obeti ){
			Intent i = new Intent();
			i.setClass(this, Obeti.class);
			this.startActivityForResult(i, OPEN_OBETI);
		}
	}
	
//izpis za izbran dan ko kliknemo shrani TOAST
	@Override
	 protected void onActivityResult (int requestCode, int resultCode, Intent data) {

		if (resultCode==RESULT_OK) {
			 if (requestCode==OPEN_DNEVI_ZALIVANJA) {
				 String izbrani="";
				 if (data.getBooleanExtra(DneviZalivanja.zPON, false)) { //neki naredis
					 izbrani +="PON "; 
				 }
				 if (data.getBooleanExtra(DneviZalivanja.zTOR, false)) { //neki naredis
					 izbrani +="TOR "; 
				 }
				 if (data.getBooleanExtra(DneviZalivanja.zSRE, false)) { //neki naredis
					 izbrani +="SRE "; 
				 }
				 if (data.getBooleanExtra(DneviZalivanja.zCET, false)) { //neki naredis
					 izbrani +="ČET "; 
				 }
				 if (data.getBooleanExtra(DneviZalivanja.zPET, false)) { //neki naredis
					 izbrani +="PET "; 
				 }
				 if (data.getBooleanExtra(DneviZalivanja.zSOB, false)) { //neki naredis
					 izbrani +="SOB "; 
				 }
				 if (data.getBooleanExtra(DneviZalivanja.zNED, false)) { //neki naredis
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

//odpre se nam novo okno za določanje dni zalivanja
   @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {			
		case R.id.baz:
			Intent i = new Intent();
			i.setClass(this, SajenjeListActivity.class);
			startActivityForResult(i, R.id.baz);
			break;
		
			//return true;
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