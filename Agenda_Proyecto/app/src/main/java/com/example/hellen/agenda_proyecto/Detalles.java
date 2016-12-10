package com.example.hellen.agenda_proyecto;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/**
 * creado por Hellen López
 */

public class Detalles extends Activity {

    private NotasDataSource dataSource;
    TextView txtTitulo;
    TextView txtHora;
    TextView txtLugar;
    TextView txtDescrip;
    TextView txtFecha;
    Button btnCerra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        dataSource = new NotasDataSource(this);
        dataSource.open();

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtHora = (TextView) findViewById(R.id.txtHora);
        txtLugar = (TextView) findViewById(R.id.txtLugar);
        txtDescrip = (TextView) findViewById(R.id.txtDescrip);
        txtFecha = (TextView) findViewById(R.id.txtFecha);
        btnCerra = (Button) findViewById(R.id.btnCerrar);

        Agenda persona;
        Intent i = getIntent();
        int id = i.getIntExtra("_id",0);

        persona = dataSource.getAgenda(id);

        //Rellenar los inputs con los valores del cursor
        txtTitulo.setText(persona.getTitulo().toString());
        txtHora.setText(persona.getHora().toString());
        txtLugar.setText(persona.getLugar().toString());
        txtDescrip.setText(persona.getDescripcion().toString());
        //DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        //String dt = format.format(persona.getFecha().toString());
        //txtFecha.setText(dt);
        txtFecha.setText(persona.getFecha().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modificar, menu);
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
}
