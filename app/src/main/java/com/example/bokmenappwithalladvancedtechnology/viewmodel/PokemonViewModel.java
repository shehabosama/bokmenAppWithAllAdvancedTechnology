package com.example.bokmenappwithalladvancedtechnology.viewmodel;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bokmenappwithalladvancedtechnology.pojo.Pokemon;
import com.example.bokmenappwithalladvancedtechnology.pojo.PokemonResponse;
import com.example.bokmenappwithalladvancedtechnology.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class PokemonViewModel extends ViewModel {
    private Repository repository;

    MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();
    LiveData<List<Pokemon>> favLst = null;

    public LiveData<List<Pokemon>> getFavLst() {
        return favLst;
    }

    @Inject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public void getPokemons() {
        repository.getPokemons().subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Exception {
                        ArrayList<Pokemon> list = pokemonResponse.getResults();
                        for (Pokemon pokemon : list) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return list;

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error -> Log.e(TAG, "getPokemons: " + error.getMessage()));
    }

    public void insetPokemon(Pokemon pokemon) {

        repository.insertPokemon(pokemon);

    }

    public void deletePokemon(String pokemonName) {
        repository.deletePokemon(pokemonName);
    }

    public void getFavPokemon() {
        favLst = repository.getFavPokemon();
    }
}
