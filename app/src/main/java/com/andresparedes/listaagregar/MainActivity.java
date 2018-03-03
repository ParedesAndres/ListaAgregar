package com.andresparedes.listaagregar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText busqueda, nombre, telefono;
    Button buscar, agregar;
    ListView contactos;
    Adaptador adaptador;
    int unnamedContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unnamedContador = 1;

        busqueda =  (EditText) findViewById(R.id.busqueda);
        nombre = (EditText) findViewById(R.id.et_nombre);
        telefono = (EditText) findViewById(R.id.et_telefono);

        buscar = (Button) findViewById(R.id.buscar);
        agregar = (Button) findViewById(R.id.btn_agregar);
        contactos = (ListView) findViewById(R.id.lv_contactos);

        adaptador = new Adaptador(this);
        contactos.setAdapter(adaptador);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = telefono.getText().toString(), name = nombre.getText().toString();
                if(!tel.equals("")){
                    if(!name.equals("")){
                        adaptador.addContacto(new Contacto(name,"",tel));
                    }else{
                        adaptador.addContacto(new Contacto("Unname " + unnamedContador,"",tel));
                        unnamedContador++;
                    }
                    nombre.setText("");
                    telefono.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Debes ingresar un número", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoBusqueda = busqueda.getText().toString();
                adaptador.buscarContacto(textoBusqueda);
            }
        });
    }



}
