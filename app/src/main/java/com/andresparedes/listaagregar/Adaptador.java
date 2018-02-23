package com.andresparedes.listaagregar;

import android.app.Activity;
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
    Activity activity;

    TextView nombreyapellido, tv_telefono;
    Button btn_llamar, btn_editar, btn_eliminar;

    public Adaptador(Activity activity){
        this.activity = activity;
        contactos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int i) {
        return contactos.get(i);
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

        nombreyapellido.setText(contactos.get(i).getNombre());
        tv_telefono.setText(contactos.get(i).getTelefono());

        //Elimina los
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactos.remove(i);
                notifyDataSetChanged();
            }
        });

        return v;
    }

    public void addContacto(Contacto nuevo){
        contactos.add(nuevo);
        notifyDataSetChanged();
    }
}
