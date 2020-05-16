package com.example.heroes.heroapi;

import com.example.heroes.model.HeroeRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("10")
    Call<HeroeRespuesta> obtenerListaHeroes();
}
