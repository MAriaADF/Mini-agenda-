package com.example.hellen.agenda_proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hellen.agenda_proyecto.SQLite.TablaAgenda;

import java.util.ArrayList;
import java.util.List;

/**
 * creado por Hellen López
 */

public class NotasDataSource {

	private SQLiteDatabase db;
	private SQLite dbHelper;
	private String[] columnas = {TablaAgenda.COLUMN_ID,
			TablaAgenda.COLUMN_TITULO, TablaAgenda.COLUMN_HORA, TablaAgenda.COLUMN_LUGAR,
			TablaAgenda.COLUMN_DESCRIPCION, TablaAgenda.COLUMN_FECHA};

	public NotasDataSource(Context context) {
		dbHelper = SQLite.getInstance(context);
	}

	/*
	 *Abre la base de datos
	 */
	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	/*
	 *Cierra la base de datos
	 */
	public void close() {
		dbHelper.close();
	}

	/*
	 *Metodo que inserta los datos a la base de datos
	 * @param se recibe el parameto de nota donde contiene todos los datos que se van almacenar
	 */
	public void InsertarNota(Agenda nota) {
		ContentValues values = new ContentValues();
		values.put(TablaAgenda.COLUMN_TITULO, nota.getTitulo());
		values.put(TablaAgenda.COLUMN_HORA, nota.getHora());
		values.put(TablaAgenda.COLUMN_LUGAR, nota.getLugar());
		values.put(TablaAgenda.COLUMN_DESCRIPCION, nota.getDescripcion());
		values.put(TablaAgenda.COLUMN_FECHA, nota.getFecha());
		db.insert(TablaAgenda.TABLA_AGENDA, null, values);
	}

	/*
	 *Agrega las notas que estan en la base de datos a un arraylist
	 * @return la lista de notas
	 */
	public List<Agenda> GetAllAgenda() {
		List<Agenda> listaNotas = new ArrayList<Agenda>();
		Cursor cursor = db.query(TablaAgenda.TABLA_AGENDA, columnas, null, null,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Agenda nuevaNota = CursorToAgenda(cursor);
			listaNotas.add(nuevaNota);
			cursor.moveToNext();
		}

		cursor.close();
		return listaNotas;
	}

	/*
	 *Metodo que elimina la nota que esta asosciada al id seleccionado
	 */
	public void BorrarNota(Agenda nota) {
		int id = nota.getId();
		db.delete(TablaAgenda.TABLA_AGENDA, TablaAgenda.COLUMN_ID + " = " + id, null);
	}

	private Agenda CursorToAgenda(Cursor cursor) {
		Agenda agenda = new Agenda();
		agenda.setId(cursor.getInt(0));
		agenda.setTitulo(cursor.getString(1));
		agenda.setHora(cursor.getString(2));
		agenda.setLugar(cursor.getString(3));
		agenda.setDescripcion(cursor.getString(4));
		agenda.setFecha(cursor.getString(5));
		return agenda;
	}

	/*
	 * Metodo encargado de buscar la nota por el id
	 * @param requiere del id de la nota
	 * @return la nueva nota
	 */
	public Agenda getAgenda(int id) {
		Agenda nuevaNota = new Agenda();
		Cursor cursor = db.query(TablaAgenda.TABLA_AGENDA, columnas,"_id = ?" , new String[]{String.valueOf(id)},
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			nuevaNota = CursorToAgenda(cursor);
			cursor.moveToNext();
		}
		return  nuevaNota;
	}
}
