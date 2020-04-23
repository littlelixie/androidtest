package com.content.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class FruitsProvider extends ContentProvider {
	private String TAG = "fruitsProvider";
	private FruitsDBHelper dbHelper;

	private String tableGame = "fruits";

	private static final String AUTHORITIES = "com.mytest.fruit.provider";
	private static final String PATH = "fruits";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/" + PATH);

	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int URI_CODE = 10;

	static {
		sUriMatcher.addURI(AUTHORITIES, PATH, URI_CODE);
	}



	public FruitsProvider() {
		super();
	}


	private static FruitsProvider instance;

	public static synchronized FruitsProvider getInstance() {
		if (instance == null) {
			instance = new FruitsProvider();

		}
		return instance;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new FruitsDBHelper(getContext(), "wotest_data.db", null, 1);
		return false;
	}

	@Override
	public Cursor query(Uri paramUri, String[] paramArrayOfString1,
						String paramString1, String[] paramArrayOfString2,
						String paramString2) {
		// TODO Auto-generated method stub
		int matchCode = sUriMatcher.match(paramUri);
		switch (matchCode) {
			case URI_CODE:
				SQLiteDatabase db = dbHelper.getReadableDatabase();
				return db.query(tableGame, null, null, null,
						null, null,null);

			default:
			 	break;
		}
		return null;
	}

	@Override
	public String getType(Uri paramUri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues paramContentValues) {
		// TODO Auto-generated method stub
		Uri returi = null;
		Log.i("fruitprovider", "uri" + uri.getAuthority());
		int matchCode = sUriMatcher.match(uri);
		switch (matchCode) {
			case URI_CODE:
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				long id = db.insert(tableGame, "name", paramContentValues);
				returi = ContentUris.withAppendedId(uri, id);
				getContext().getContentResolver().notifyChange(CONTENT_URI,null);

			default:
				break;
		}
		return returi;
	}



	@Override
	public int delete(Uri paramUri, String paramString,
					  String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		int count = 0;
		int matchCode = sUriMatcher.match(paramUri);
		switch (matchCode) {
			case URI_CODE:
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				count = db.delete(tableGame, paramString, paramArrayOfString);
				getContext().getContentResolver().notifyChange(CONTENT_URI,null);
			default:
				break;
		}
		return count;
	}

	@Override
	public int update(Uri paramUri, ContentValues paramContentValues,
					  String paramString, String[] paramArrayOfString) {
		// TODO Auto-generated method stub
		int matchCode = sUriMatcher.match(paramUri);
		switch (matchCode) {
			case URI_CODE:
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				return db.update(tableGame, paramContentValues, paramString, paramArrayOfString);
			default:
				break;
		}
		return 0;
	}

}