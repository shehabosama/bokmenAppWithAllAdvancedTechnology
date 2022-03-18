package com.example.bokmenappwithalladvancedtechnology.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.bokmenappwithalladvancedtechnology.db.PokemonDao;
import com.example.bokmenappwithalladvancedtechnology.db.PokemonDatabase;
import com.example.bokmenappwithalladvancedtechnology.network.PokemonAPIService;
import com.example.bokmenappwithalladvancedtechnology.pojo.Pokemon;
import com.example.bokmenappwithalladvancedtechnology.pojo.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Repository {
    private PokemonAPIService pokemonApiService;
    private PokemonDao pokemonDao;

    @Inject
    public Repository(PokemonAPIService pokemonApiService, PokemonDao pokemonDao) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonDao = pokemonDao;
    }

    public Observable<PokemonResponse> getPokemons(){
        return pokemonApiService.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon){pokemonDao.insertPokemon(pokemon);}

    public void deletePokemon(String pokemonName){pokemonDao.deletePokemon(pokemonName);}

    public LiveData<List<Pokemon>> getFavPokemon(){
        return pokemonDao.getPokemon();
    }
}
