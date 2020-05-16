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

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.superheroapi.com/api.php/3073415292695385/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();

        lista = (ListView) findViewById(R.id.listahero);

        lista.setAdapter(new Adapter(this, datos));
    }

    private void obtenerDatos(){
        ApiService service = retrofit.create(ApiService.class);
        Call<HeroeRespuesta> heroeRespuestaCall = service.obtenerListaHeroes();

        heroeRespuestaCall.enqueue(new Callback<HeroeRespuesta>() {
            @Override
            public void onResponse(Call<HeroeRespuesta> call, Response<HeroeRespuesta> response) {
                if(response.isSuccessful()){
                    HeroeRespuesta heroeRespuesta = response.body();
                    ArrayList<Heroe> ListaHeroes = heroeRespuesta.getResults();

                    for (int i=0; i < ListaHeroes.size(); i++){
                        Heroe h = ListaHeroes.get(i);
                        Log.i(TAG, " Heroe: " + h.getName());
                    }

                } else {
                    Log.e(TAG, " onResponse: "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<HeroeRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
}
