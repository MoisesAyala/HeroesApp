package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.heroes.heroapi.ApiService;
import com.example.heroes.model.Heroe;
import com.example.heroes.model.HeroeRespuesta;

import java.util.ArrayList;

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

    String [][] datos = {
            {"Superman", "calando"},
            {"Batman", "calando"},
            {"Flash", "calando"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.superheroapi.com/api.php/3073415292695385/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        int inicio=Contador;
        int fin=Contador+10;

        while (inicio <= fin){
            obtenerDatos(inicio);
            inicio++;
            Contador++;
        }

        HeroAdapter = new Adapter(this, datosHeroes);
        lista = (ListView) findViewById(R.id.listahero);
        lista.setAdapter(HeroAdapter); //MANDO LOS DATOS DEL ARREGLO "datos" AL ListView QUE SE LLAMA "lista"
    }

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
