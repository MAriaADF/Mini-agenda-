package com.example.hellen.agenda_proyecto;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

/**
 * creado por Hellen López
 */

public class Editar extends Activity {

    private NotasDataSource dataSource;
    EditText titulo_input;
    EditText hora_input;
    EditText lugar_input;
    EditText descrip_input;
    Button agregar_btn;
    EditText fecha_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        dataSource = new NotasDataSource(this);
        dataSource.open();

        titulo_input = (EditText) findViewById(R.id.titulo_input);
        hora_input = (EditText) findViewById(R.id.hora_input);
        lugar_input = (EditText) findViewById(R.id.lugar_input);
        descrip_input = (EditText) findViewById(R.id.descrip_input);
        fecha_input = (EditText) findViewById(R.id.fecha_input);
        agregar_btn = (Button) findViewById(R.id.agregar_btn);

        Agenda persona;
        Intent i = getIntent(); // gets the previously cre
        int id = i.getIntExtra("_id",0);

        persona = dataSource.getAgenda(id);

        //Rellenar los inputs con los valores del cursor
        titulo_input.setText(persona.getTitulo().toString());
        hora_input.setText(persona.getHora().toString());
        lugar_input.setText(persona.getLugar().toString());
        descrip_input.setText(persona.getDescripcion().toString());
        fecha_input.setText(persona.getFecha().toString());

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
