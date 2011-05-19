package edu.erik.naloga2_1.android;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GlavnoOkno extends Activity implements OnClickListener, TextToSpeech.OnInitListener {
    /** Called when the activity is first created. */
	public static final String PREF_NAME="PREF_STEVCI";  //pref ime, kamor se shranjujejo pref.
	public static final String FILE_NAME_RANDOM="nakljucna.txt";	
	private static final int TEST_START_ACTIVITY_ID = 1;
	private static final int TEST_LIST_ACTIVITY_ID = 2;  //Step 4.12
	private static final int EXIT_DIALOG=3; 
	
	private static final String TAG2 = "TextToSpeechDemo";
	private TextToSpeech mTts;
	
	private static final String TAG = null;
	TextView txt_prikaz1, txt_prikaz2, txt_prikaz3;
	EditText edit, edit_min, edit_max;
	Button gumb;
	Rezultati stej;
	int a, b, min1, max1;
	public PrintWriter myfile;
	public int[] polje;
	ApplicationExample app; //Step 4.3
	Random nakljucno_st = new Random();
	boolean preverjanje=true;
	ProgressDialog dialogWait;
	ProgressDialog progressDialog;
	
	Menu meni;
	
	@Override
    public void onDestroy() {
        // Don't forget to shutdown!
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }

        super.onDestroy();
    }
    // Implements TextToSpeech.OnInitListener.
    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            int result = mTts.setLanguage(Locale.US);
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
               // Lanuage data is missing or the language is not supported.
                Log.e(TAG2, "Language is not available.");
            } else {
                // Check the documentation for other possible result codes.
                // For example, the language may be available for the locale,
                // but not for the specified country and variant.

                // The TTS engine has been successfully initialized.
                // Allow the user to press the button for the app to speak again.
                // Greet the user.
                //sayHello();
            }
        } else {
            // Initialization failed.
            Log.e(TAG2, "Could not initialize TextToSpeech.");
        }
    }
    
    private static final Random RANDOM = new Random();
    private static final String[] HELLOS = {
      //"Hello",
      "Congraduations!"
    };

    private void sayHello() {
        // Select a random hello.
        int helloLength = HELLOS.length;
        String hello = HELLOS[RANDOM.nextInt(helloLength)];
        mTts.speak(hello,
            TextToSpeech.QUEUE_FLUSH,  // Drop all pending entries in the playback queue.
            null);
    }
	
	
	@Override
	public void onStart() {
		super.onStart();
		try {
			myfile = new PrintWriter(openFileOutput(FILE_NAME_RANDOM, Context.MODE_PRIVATE));
		} catch (IOException e) {
			Log.e(TAG,"Datoteka napake "+e.toString());
			e.printStackTrace();
	}
	}
	
	@Override
	public void onResume() { //pref predno user vidi nastavim prave vrednosti
		super.onResume();
		SharedPreferences settings = getSharedPreferences(PREF_NAME,0); //pref odprem preferences
		int tmp = settings.getInt(Rezultati.STEVEC_INC, 0); //pref preberem staro vrednost
		stej.setStanje(tmp); //pref nastavim staro vrednost (od tod naprej �teje)
		//showKlikRandom(); //pref osve�im na ekranu kar se vidi
	}
	
	@Override
	public void onPause() { //pref uporabnik ali OS zapusti pogled, potrebno shranit
		super.onPause();
		SharedPreferences settings = getSharedPreferences(PREF_NAME,0); //pref odprem
		SharedPreferences.Editor editor = settings.edit(); //pref dam v edit mode
		editor.putInt(Rezultati.STEVEC_INC, stej.getPovecaj()); //pref nastavim novo vrednost
		editor.commit(); //pref shranim novo vrednost
		if (myfile!=null){
			myfile.close();
		}
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        polje = new int [200];
        stej = new Rezultati();
        
        mTts = new TextToSpeech(this,
                (OnInitListener) this  // TextToSpeech.OnInitListener
                );
        
        
        gumb = (Button) findViewById(R.id.btn_ugani);
        gumb.setOnClickListener(this);
        edit = (EditText)findViewById(R.id.editText1);
        txt_prikaz1 = (TextView)findViewById(R.id.txt_stevilo);
        txt_prikaz2 = (TextView)findViewById(R.id.txt_ugibal);
        txt_prikaz3 = (TextView)findViewById(R.id.textView1);
        app = (ApplicationExample) getApplication(); //Step 4.4
        a = nakljucno_st.nextInt(app.mojStevec.getTezavnost()) + 0;
    }
    
    public class MojTaskProgressBar extends AsyncTask<Integer, Integer, Long> {
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(GlavnoOkno.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage("Nalagam...");
			progressDialog.setCancelable(true);
			progressDialog.show();
		}
		protected Long doInBackground(Integer... prviArgument) {
			long totalSize = 0;
			int count = prviArgument.length;
			int stevec_v = stej.getPovecaj() ;
			int t1, temp;
			
			for (int i=0; i<stevec_v; i++) 
			{
				totalSize+=polje[i];
			}

			for (int i=0; i<count; i++) {
				t1=prviArgument[i];
				//totalSize+=t1;
				try {
					Thread.sleep(t1);
				} catch (InterruptedException e) {
					Log.e("ERROR", "Thread Interrupted");
				}
				temp = Math.round(((float) (i+1) / count) * 100);
				publishProgress(temp);
			}
			return totalSize;
		}
		protected void onProgressUpdate(Integer... drugiArgument) {
			progressDialog.setProgress(drugiArgument[0]);
		}
		protected void onPostExecute(Long tretjiArgument) {
			Toast.makeText(GlavnoOkno.this,"Dobrodošli v rezultatih",Toast.LENGTH_LONG).show();
			progressDialog.cancel();
		}
	}
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {

            case TEST_START_ACTIVITY_ID:
             //Toast toast = Toast.makeText(this,"resultCode="+resultCode , Toast.LENGTH_LONG);
            // toast.show();
            	if (resultCode == RESULT_CANCELED)
            	{
            		finish();
            	}
             break;
        }
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.izh:
			showDialog(EXIT_DIALOG);
			return true;
			
		case R.id.nast:
			Intent i = new Intent();
			i.setClass(this, MenuPreferences.class);
			startActivityForResult(i, R.id.nast);
			return true;
		case R.id.nova:
			 a = nakljucno_st.nextInt(app.mojStevec.getTezavnost()) + 0;
			 txt_prikaz3.setText("Generirano je bilo novo število med 0 in" + app.mojStevec.getTezavnost());
			 break;
		case R.id.rez:
			Rezultati aa=new Rezultati(app.mojStevec);
			stej.setName(app.mojStevec.getName());
			app.addDB(app.mojStevec);
			app.lista.add(app.lista.size(),aa);
			app.mojStevec.Reset();
			
			MojTaskProgressBar mt2 = new MojTaskProgressBar();
			mt2.execute(100,100,100,200,100,300,100,100,200,300,100,200,300);
			
			Intent moj2=new Intent(this,RezultatiListActivity.class);
			this.startActivityForResult(moj2, TEST_LIST_ACTIVITY_ID);
			
			break;
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
    @Override  //v razredu RezultatiListActivity

    protected Dialog onCreateDialog(int id) {

     switch (id) {

      case EXIT_DIALOG:

       AlertDialog.Builder builder = new AlertDialog.Builder(this);

       builder.setMessage("Ali želiti zapustiti igro?")

        .setCancelable(false)

        .setPositiveButton("Da", new DialogInterface.OnClickListener() {

          public void onClick(DialogInterface dialog, int id) {

            GlavnoOkno.this.setResult(RESULT_CANCELED);

           GlavnoOkno.this.finish();}

           })

        .setNegativeButton("Ne", new DialogInterface.OnClickListener() {

          public void onClick(DialogInterface dialog, int id) {

            GlavnoOkno.this.setResult(RESULT_OK);

            dialog.cancel();

          }

       });

       return builder.create();

      }

      return null;

    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		meni = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, meni);
		return true;
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId()== R.id.btn_ugani ){
			app.mojStevec.Povecaj();
		try {
				b=Integer.parseInt(edit.getText().toString());
				if (myfile!=null) myfile.println(" "+b);
			} 
		catch(NumberFormatException nfe){} 
		
		if(b<a){
			Toast toast = Toast.makeText(this,"Ugibano Število je večje!" , Toast.LENGTH_LONG);
			toast.show();
		}
		if(b>a)
		{
			Toast toast = Toast.makeText(this,"Ugibano število je manjše!" , Toast.LENGTH_LONG);
			toast.show();
		}
		
			if(b==a)
			{
				sayHello();
				txt_prikaz2.setText("BRAVO!!! Uganil si število");
				txt_prikaz1.setText("Stevilo poskusov:" + stej.getPovecaj());
				//stej.Reset();
				Intent moj=new Intent(this,NovoOkno.class);
				//this.startActivity(moj);
				this.startActivityForResult(moj, TEST_START_ACTIVITY_ID);
				//a = nakljucno_st.nextInt(20) + 0;
				stej = new Rezultati();
				stej.setStanje(app.mojStevec.getPovecaj());
				stej.setName(app.mojStevec.getName());
				//stej.setRang(max1-min1);
				app.add(stej);
			}
			else{
				txt_prikaz2.setText("Napačen odgovor");
				txt_prikaz1.setText("Stevilo poskusov:" + stej.getPovecaj());
			}
		}
	}
}
