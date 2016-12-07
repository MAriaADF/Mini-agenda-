package com.example.hellen.agenda_proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * creado por Hellen López
 */

public class SQLite extends SQLiteOpenHelper {

	private static SQLite mOpenHelper = null;
	
	private static final String DATABASE_NAME = "agenda.db";
	private static final int DATABASE_VERSION = 1;

	public static class TablaAgenda{
		public static String TABLA_AGENDA = "agenda";
		public static String COLUMN_ID = "_id";
		public static String COLUMN_TITULO = "titulo";
		public static String COLUMN_HORA = "hora";
		public static String COLUMN_LUGAR = "lugar";
		public static String COLUMN_DESCRIPCION = "descripcion";
		public static String COLUMN_FECHA = "fecha";
	}

	/*
	 *String que contiene la creacion de la base de datos con su tabla y colunmas correspondientes
	 */
	private static final String DATABASE_CREATE = "CREATE TABLE " + TablaAgenda.TABLA_AGENDA + "(" +
			TablaAgenda.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
		TablaAgenda.COLUMN_TITULO + " TEXT, " +
		TablaAgenda.COLUMN_HORA + " TEXT, " +
		TablaAgenda.COLUMN_LUGAR + " TEXT, " +
		TablaAgenda.COLUMN_DESCRIPCION + " TEXT, " +
		TablaAgenda.COLUMN_FECHA + " TEXT " + ");";
	/*
	 *@return mOpenHelper
	 */
	public static SQLite getInstance(Context context){
		if (mOpenHelper == null){
			mOpenHelper = new SQLite(context.getApplicationContext());
		}
		
		return mOpenHelper;
	}
	
	private SQLite(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("delete table if exists " + TablaAgenda.TABLA_AGENDA);
		onCreate(db);
	}
	/*
	public Cursor Agendaid(int id){
		SQLiteDatabase db = getWritableDatabase();
		String query = "SELECT * FROM " + TablaAgenda.TABLA_AGENDA + " WHERE " + TablaAgenda.COLUMN_ID + " = " + id + ";";
		Cursor c = db.rawQuery(query, null);

		if (c != null) {
			c.moveToFirst();
		}

		return c;
	}*/

}
