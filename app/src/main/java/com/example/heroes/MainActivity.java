package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lista;

    String [][] datos = {
            {"Superman", "calando"},
            {"Batman", "calando"},
            {"Flash", "calando"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listahero);

        lista.setAdapter(new Adapter(this, datos));
    }
}
