package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static com.example.heroes.Adapter.datosHeroe;

public class VisorInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int imgID = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_info);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b != null){
            imgID = b.getInt("IMG");
            ImageView img = (ImageView) findViewById(R.id.heropicBig);
            Picasso.get().load(datosHeroe.get(imgID).getImage().getUrl()).into(img);
            TextView namehero = (TextView) findViewById(R.id.NameVisor);
            TextView nombrefull = (TextView) findViewById(R.id.FullnameVisor);
            TextView genero = (TextView) findViewById(R.id.generoHeroe);
            TextView raza = (TextView) findViewById(R.id.razaHeroe);
            namehero.setText(datosHeroe.get(imgID).getName());
            nombrefull.setText(datosHeroe.get(imgID).getBiography().getFullname());
            genero.setText(datosHeroe.get(imgID).getAppearance().getRace());
            raza.setText(datosHeroe.get(imgID).getAppearance().getGender());
        }
    }
}
