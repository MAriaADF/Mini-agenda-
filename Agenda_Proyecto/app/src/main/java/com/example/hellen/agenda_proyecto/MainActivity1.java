package com.example.hellen.agenda_proyecto;

import android.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * creado por Hellen López
 */

public class MainActivity1 extends Activity implements OnItemClickListener {

    private int requestCode = 1;
    private ListView lvAgenda;
    private NotasDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        // Instanciamos NotasDataSource
        dataSource = new NotasDataSource(this);
        dataSource.open();

        // Se instancia los elementos
        lvAgenda = (ListView) findViewById(R.id.lvlitems);

        // Cargamos la lista de notas disponibles
        List<Agenda> listaNotas = dataSource.GetAllAgenda();
        ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this,
                layout.simple_list_item_1, listaNotas);

        // Establecemos el adapter
        lvAgenda.setAdapter(adapter);

        // Establecemos un Listener para el evento de pulsación
        lvAgenda.setOnItemClickListener(this);

    }

    /*
     * Lleva al usuario a agregar los datos de la agenda
     */
    public void onClick(View view){
        Intent i = new Intent(this, Agregar.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        Log.d("Result", "Se ejecuta onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.requestCode && resultCode == RESULT_OK) {
            // Actualizar el Adapter
            dataSource.open();
            //Actualiza la lista
            CargarLista();
        }
    }

    /*
     *Cuando el usuario seleciona un item se muesta un mensaje.
     * se realiza la creacion de un CharSequence[] donde estan almacendas cada una de las opciones
     * que el ussuario puede selecionar
     */
    @Override
    public void onItemClick(final AdapterView<?> adapterView, View view,
                            final int position, long id) {

        final CharSequence[] items = {"Detalles", "Eliminar", "Cerrar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione:");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            /*
             *Cuando ese muesta el mensaje donde le permitira seleccionar si desea ver los detalles,
              *editar o eliminar. Cuando seleccione una de ellas se realiza la accion correspondiente
             */
            public void onClick(DialogInterface dialog, int item) {
                Agenda nota = (Agenda) adapterView
                    .getItemAtPosition(position);
                int a = nota.getId();
                switch (item){

                    case 0:
                        //Envia el id del item que fue seleccionado, para mostrar los detalles
                        Intent i = new Intent(MainActivity1.this, Detalles.class);
                        i.putExtra("_id" , nota.getId());
                        startActivity(i);
                        finish();
                        break;

                    case 1:

                        //Elimina los datos del id  que pertenece al item que
                        // fue selecionado y actualiza la lista
                        dataSource.BorrarNota(nota);
                        CargarLista();
                        dialog.cancel();
                        break;

                    case 2:
                        //Cierra el dialogo
                        dialog.cancel();
                        break;
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*
     *Obtinen los datos que se encuentan en la base de datos para ser mostrados en ListView
     */
    private void CargarLista() {
        List<Agenda> listaNotas = dataSource.GetAllAgenda();
        ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this,
                layout.simple_list_item_1, listaNotas);
        lvAgenda.setAdapter(adapter);
    }

}
