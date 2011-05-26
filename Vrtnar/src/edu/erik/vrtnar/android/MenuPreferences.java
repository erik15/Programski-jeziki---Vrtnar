package edu.erik.vrtnar.android;

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
	public static final String PREF_TEZAVNOST = "Tezavnost";
	public static final String PREF_IME = "PREF_IME";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.menu_preferences);     
	}
	
	
	 
}