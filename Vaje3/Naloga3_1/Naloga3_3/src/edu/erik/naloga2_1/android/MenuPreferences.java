package edu.erik.naloga2_1.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;
import android.preference.Preference.OnPreferenceClickListener;

public class MenuPreferences extends PreferenceActivity {
	public static final String TAG = "MenuPreferences";
	SharedPreferences prefs;
	ApplicationExample app;
	public static final String PREF_TEZAVNOST = "Tezavnost";
	public static final String PREF_IME = "PREF_IME";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (ApplicationExample) this.getApplication();
		addPreferencesFromResource(R.xml.menu_preferences);     
	}
	
	
	@Override
	public void onPause() {
		super.onPause();
		SharedPreferences settings =  PreferenceManager.getDefaultSharedPreferences(app); 
		app.mojStevec.setTezavnost(Integer.parseInt(settings.getString(PREF_TEZAVNOST, "10")));
		app.mojStevec.setName(settings.getString(PREF_IME, "Erik")); 	
	}
	 
}