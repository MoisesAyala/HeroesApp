package com.example.heroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String [][] datos;
    //int [] datosImg;

    public Adapter (Context conexto, String[][] datos /*, int [] imagenes*/){
        this.contexto = conexto;
        this.datos = datos;
        //this.datosImg = imagenes;

        inflater = (LayoutInflater) conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.lista_elemento, null);

        TextView nombre = (TextView) vista.findViewById(R.id.heroname);
        //ImageView imagen = (ImageView) vista.findViewById(R.id.heropic);

        nombre.setText(datos[i][0]);

        return vista;
    }

    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
