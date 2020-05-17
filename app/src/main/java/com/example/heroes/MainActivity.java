package com.example.heroes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;

import com.example.heroes.heroapi.ApiService;
import com.example.heroes.model.Heroe;
import com.example.heroes.model.HeroeRespuesta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.heroes.model.HeroeRespuesta.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HEROES";
    private Retrofit retrofit;
    public int Contador=1;
    Adapter HeroAdapter;

    ListView lista;
    ArrayList<Heroe> datosHeroes = new ArrayList<Heroe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.superheroapi.com/api.php/3073415292695385/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GeneradorLista();

        HeroAdapter = new Adapter(this, datosHeroes);
        lista = (ListView) findViewById(R.id.listahero);
        lista.setAdapter(HeroAdapter);


        //ITEMCLICK LISTENNER
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item = Adapter.getItemAtposition(position);
                int PositionFinal = datosHeroes.get(item).getId();
                Intent visorInfo = new Intent(HeroAdapter.contexto, VisorInfo.class);
                visorInfo.putExtra("IMG", PositionFinal);
                HeroAdapter.contexto.startActivity(visorInfo);
            }
        });

        //SCROLL LISTENNER
        lista.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount - visibleItemCount == firstVisibleItem){
                    View v =  lista.getChildAt(totalItemCount-1);
                    int offset = (v == null) ? 0 : v.getTop();
                    if (offset == 0) {
                        /*AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("Abajo");
                        alert.setMessage("Estás abajo");
                        alert.setPositiveButton("Aceptar",null);
                        alert.show();
                        return;*/
                        GeneradorLista();
                    }
                }
            }
        });
    }
    //MÉTODO QUE LLENA LA LISTA CON LOS DATOS DEL REQUEST
    public void GeneradorLista() {
        int inicio=Contador;
        int fin=Contador+10;

        while (inicio <= fin){
            obtenerDatos(inicio);
            inicio++;
            Contador++;
        }
    }
    //MÉTODO QUE OBTIENE LOS DATOS DEL REQUEST
    private void obtenerDatos(int id) {
            ApiService service = retrofit.create(ApiService.class);
            Call<Heroe> heroeRespuestaCall = service.obtenerListaHeroes(id);

        heroeRespuestaCall.enqueue(new Callback<Heroe>() {
            @Override
            public void onResponse(Call<Heroe> call, Response<Heroe> response) {
                if(response.isSuccessful()){
                    Heroe heroeRespuesta = response.body();
                    datosHeroes.add(heroeRespuesta);
                    HeroAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, " onResponse: "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Heroe> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
 }
