package com.example.hellen.agenda_proyecto;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * creado por Hellen López
 */

public class Agregar extends Activity {
    private Date data;
    private NotasDataSource dataSource;
    private int horas;
    private int minutos;
    EditText titulo_input;
    EditText hora_input;
    EditText lugar_input;
    EditText descrip_input;
    EditText fecha_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        dataSource = new NotasDataSource(this);
        dataSource.open();

        titulo_input = (EditText) findViewById(R.id.titulo_input);
        hora_input = (EditText) findViewById(R.id.hora_input);
        lugar_input = (EditText) findViewById(R.id.lugar_input);
        descrip_input = (EditText) findViewById(R.id.descrip_input);
        fecha_input = (EditText) findViewById(R.id.fecha_input);

        ExibeDataListener fecha = new ExibeDataListener();

        fecha_input.setOnClickListener(  fecha );
        fecha_input.setOnFocusChangeListener(fecha);

        TimeListener hora = new TimeListener();

        hora_input.setOnClickListener(hora);
        hora_input.setOnFocusChangeListener(hora);
    }


    /**
     * Agrega los datos de nota a la base de datos 
     */
   public void agregar_clicked(View view){
       if(titulo_input.getText().toString().equals("")){
           Toast.makeText(Agregar.this, "Ingrese el Titulo porfavor", Toast.LENGTH_SHORT).show();
       }else if(hora_input.getText().toString().equals("")){
           Toast.makeText(Agregar.this, "Ingrese el hora porfavor", Toast.LENGTH_SHORT).show();
       }else {
           Agenda agenda = new Agenda(titulo_input.getText().toString(), hora_input.getText().toString(), lugar_input.getText().toString(), descrip_input.getText().toString(), fecha_input.getText().toString());
           dataSource.InsertarNota(agenda);
           limpiarcampos();
            confirmacion();
       }

   }

    /**
     * Limpia los EditText
     */
   public void limpiarcampos(){
       titulo_input.setText("");
       lugar_input.setText("");
       hora_input.setText("");
       descrip_input.setText("");
       fecha_input.setText("");
   }

    /*
     * Metodo que realiza la acción de mostrar al usuario donde le infoma que los datos se an guardado
     */
   public void confirmacion(){

       AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
       dlgAlert.setMessage("Los datos se han guardado ");
       Intent i = new Intent(this, MainActivity1.class);
       startActivity(i);
       dlgAlert.setTitle("Agregar Nota");
       dlgAlert.setPositiveButton("Ok",
               new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
       dlgAlert.create().show();
   }


    private void fecha()
    {
        Calendar calendar = Calendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dlg = new DatePickerDialog(this, new SeleccionaFecha() , año, mes, dia);
        dlg.show();
    }


    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener
    {
        @Override
        public void onClick(View v) {
            fecha();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                fecha();
        }

    }

    /*
     *Obtine la fecha que el usuario selecciono y la muestra en el imput
     */
    private class SeleccionaFecha implements DatePickerDialog.OnDateSetListener
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            data = calendar.getTime();
            DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String dt = format.format(data);
            fecha_input.setText(dt);
            //data.setDate(data.getDate() + 10);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*
     *se encarga de lanzar el reloj para que el usuario selecione la hora corespondiente
     */
    private class TimeListener implements View.OnClickListener, View.OnFocusChangeListener {
        @Override
        public void onClick(View v) {
            Time();
        }
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                Time();
        }

    }

    /*
     metodo que realiza la accion de que el usuario seleccione la hora y
     luego la muestre en un EditText
     */
    public void Time(){
        // Proceso para obtener la hora
        final Calendar c = Calendar.getInstance();
        horas = c.get(Calendar.HOUR);
        minutos = c.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // muestra la hora en el EditText
                        hora_input.setText(hourOfDay + ":" + minute);
                    }
                }, horas, minutos, false);
        tpd.show();
    }

}
