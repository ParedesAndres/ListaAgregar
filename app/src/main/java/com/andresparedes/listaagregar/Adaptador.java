package com.andresparedes.listaagregar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andres Paredes on 23/02/2018.
 */

public class Adaptador extends BaseAdapter{

    ArrayList<Contacto> contactos;
    ArrayList<Contacto> contactosDinamicos;
    Activity activity;

    TextView nombreyapellido, tv_telefono;
    Button btn_llamar, btn_editar, btn_eliminar;

    public Adaptador(Activity activity){
        this.activity = activity;
        contactos = new ArrayList<>();
        contactosDinamicos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return contactosDinamicos.size();
    }

    @Override
    public Object getItem(int i) {
        return contactosDinamicos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        LayoutInflater li = activity.getLayoutInflater();
        View v = li.inflate(R.layout.renglon,viewGroup, false);

        nombreyapellido = (TextView) v.findViewById(R.id.tv_nombreyapellido);
        tv_telefono = (TextView) v.findViewById(R.id.tv_telefono);

        btn_llamar = (Button)  v.findViewById(R.id.btn_llamar);
        btn_editar = (Button) v.findViewById(R.id.btn_editar);
        btn_eliminar = (Button) v.findViewById(R.id.btn_eliminar);

        nombreyapellido.setText(contactosDinamicos.get(i).getNombre());
        tv_telefono.setText(contactosDinamicos.get(i).getTelefono());

        //Elimina los contactos
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactosDinamicos.remove(i);
                contactos.remove(i);
                notifyDataSetChanged();
            }
        });

       /* btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/



        return v;
    }

    public void addContacto(Contacto nuevo){
        contactos.add(nuevo);
        contactosDinamicos = (ArrayList<Contacto>) contactos.clone();
        notifyDataSetChanged();
    }

    public void buscarContacto(String busqueda){
        for (int i = 0; i < contactosDinamicos.size();i++){
            if (!(contactosDinamicos.get(i).getNombre().contains(busqueda) || contactosDinamicos.get(i).getTelefono().contains(busqueda))){
                contactosDinamicos.remove(i);
                i--;
            }
        }
        notifyDataSetChanged();
    }

    public void llamar(int position){
        Contacto c = (Contacto) contactosDinamicos.get(position);
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + c.getTelefono()));
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 11);
        } else {
            activity.startActivity(i);
        }
    }
}
