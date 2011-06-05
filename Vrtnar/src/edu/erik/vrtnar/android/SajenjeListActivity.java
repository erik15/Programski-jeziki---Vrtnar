package edu.erik.vrtnar.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

//Step 4.6
public class SajenjeListActivity extends ListActivity implements OnItemClickListener  {
	ApplicationExample app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Step 4.7  naredite 2 LL enega za Activity z listo in enega za vrstico
		setContentView(R.layout.sajenje_list_activity);
		app = (ApplicationExample) getApplication();
		setListAdapter(app.stevci);
		this.getListView().setOnItemClickListener(this);
		app.lista.clear();
		app.fillFromDB();

	}
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
