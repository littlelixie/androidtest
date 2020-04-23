package com.content.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FruitsDBHelper extends SQLiteOpenHelper {

    public FruitsDBHelper(Context context, String name, CursorFactory factory,
                           int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 db.execSQL("create table if not exists fruits(_id integer primary key autoincrement, fruitName varchar(25));");
    }

	@Override
	public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1,
                          int paramInt2) {
		// TODO Auto-generated method stub
		
	}  
}  
