package edu.erik.vrtnar.data;

import edu.erik.vrtnar.android.Sajenja;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBAdapterSajenja implements BaseColumns {
	public static final  String TAG="DBAdapterStevec";

	public static final  String VALUE = "i_value";
	public static final  String NAME = "s_name";
	public static final  int POS__ID=6;
	public static final  int POS_NAME=7;
	//public static final  int POS_VALUE=8;

	public static final  String TABLE="stevec";



	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapterSajenja(Context ctx) 
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}


	//---opens the database---
	public DBAdapterSajenja open() throws SQLException 
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}

	//---closes the database---    
	public void close() 
	{
		DBHelper.close();
	}

	//---insert a stevec
	public long insertStevc(Sajenja stevec) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, stevec.getName()); 
		//initialValues.put(VALUE, stevec.getPovecaj()); 
		return db.insert(TABLE, null, initialValues);
	}

	//---deletes a particular title---
	public boolean deleteStevec(long rowId) 
	{
		return db.delete(TABLE, _ID + "=" + rowId, null) > 0;
	}

	//---retrieves all the titles---
	public Cursor getAll() 
	{
		return db.query(TABLE, new String[] {
				_ID,       //POS__ID=0;
				NAME,      //POS_NAME=1
				//value
				},    //POS_VALUE =2
				null, 
				null, 
				null, 
				null, 
				null);
	}

	//---retrieves a particular title---
	public Cursor getStevec(long rowId) throws SQLException 
	{
		Cursor mCursor =
			db.query(true, TABLE, new String[] {
					_ID, 
					NAME,
					VALUE}, 
					_ID + "=" + rowId, 
					null,
					null, 
					null, 
					null, 
					null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	//---update---
	public boolean updateStevec(Sajenja tmp) 
	{
		ContentValues args = new ContentValues();
		args.put(NAME, tmp.getName());
		//args.put(VALUE, tmp.getPovecaj());
		return db.update(TABLE, args, 
				_ID + "=" + tmp.getDbID(), null) > 0;
	}


}
