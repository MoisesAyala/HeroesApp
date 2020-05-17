package com.example.heroes.heroapi;

import com.example.heroes.model.Heroe;
import com.example.heroes.model.HeroeRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("{id}")
    Call<Heroe> obtenerListaHeroes(@Path("id") int id);
}