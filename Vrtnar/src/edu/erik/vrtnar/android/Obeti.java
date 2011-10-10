package edu.erik.vrtnar.android;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Obeti extends Activity implements OnClickListener {
	
	TextView txvObeti;
	TextView txvTime;
	Button nazaj;
	
	public String getStringBetween(String startStr, String endStr, String string) {
		int start = string.indexOf(startStr) + startStr.length();
		int end = string.indexOf(endStr);
		return string.substring(start, end);
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obeti);
        
        txvObeti = (TextView)findViewById(R.id.txvObeti);
        txvTime = (TextView)findViewById(R.id.txvTime);
		nazaj = (Button) findViewById(R.id.btn_nazaj);
		nazaj.setOnClickListener(this);
        
        try {
	        HttpClient client = new DefaultHttpClient();
	        URI url = URI.create("http://meteo.arso.gov.si/uploads/probase/www/fproduct/text/sl/fcast_SLOVENIA_d1-d2_text.html");
	        HttpGet request = new HttpGet(url);
	        HttpResponse response;
			
			response = client.execute(request);
	
	        String html = "";
	        InputStream in = response.getEntity().getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        StringBuilder str = new StringBuilder();
	        String line = null;
	        while((line = reader.readLine()) != null)
	        {
	            str.append(line);
	        }
	        in.close();
	        html = str.toString();
	        
	        // parse
	        String result;
	        
	        result = getStringBetween("<body>", "</body>", html);
	        
	        result = result.replace("&#352;", "Š");
	        result = result.replace("&#353;", "š");
	        result = result.replace("&#268;", "È");
	        result = result.replace("&#269;", "è");
	        result = result.replace("&#381;", "Ž");
	        result = result.replace("&#382;", "ž");
	        
	        txvTime.setText(getStringBetween("<i>", "</i>", result));
	        
	        result = getStringBetween("<p>", "<sup>", result);
	        result = result.replace("</p><br/>", "");
	        result = result.replace("</p><p>", "\n\n");
	        
	        txvObeti.setText(result);
	        
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId()==R.id.btn_nazaj) {
			setResult(RESULT_CANCELED);
			finish();
		}
	}
}
