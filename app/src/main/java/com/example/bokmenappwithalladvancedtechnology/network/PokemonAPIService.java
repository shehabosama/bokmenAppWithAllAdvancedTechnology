package com.example.bokmenappwithalladvancedtechnology.network;

import com.example.bokmenappwithalladvancedtechnology.pojo.PokemonResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonAPIService {
    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
