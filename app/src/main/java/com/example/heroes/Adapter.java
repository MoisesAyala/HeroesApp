package com.example.heroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.heroes.model.Heroe;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    static ArrayList<Heroe> datosHeroe;
    //int [] datosImg;

    public Adapter (Context conexto, ArrayList<Heroe> datosHeroe/*, int [] imagenes*/){
        this.contexto = conexto;
        this.datosHeroe = datosHeroe;
        //this.datosImg = imagenes;

        inflater = (LayoutInflater) conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }


    public static int getItemAtposition(int id) {
        for (int position=0; position<datosHeroe.size(); position++)
            if (datosHeroe.get(position).getId() == id)
                return position;
            return 0;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.lista_elemento, null);

        TextView nombre = (TextView) vista.findViewById(R.id.heroname);
        ImageView imagen = (ImageView) vista.findViewById(R.id.heropic);
        Picasso.get().load(datosHeroe.get(i).getImage().getUrl()).into(imagen);
        nombre.setText(datosHeroe.get(i).getName());

        return vista;
    }


    @Override
    public int getCount() { return datosHeroe.size(); }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
