package com.example.bokmenappwithalladvancedtechnology.di;

import com.example.bokmenappwithalladvancedtechnology.network.PokemonAPIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    @Singleton
    @Provides
    public static PokemonAPIService pokemonAPIService(){
        return new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(PokemonAPIService.class);
    }
}
