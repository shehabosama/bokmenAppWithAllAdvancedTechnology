package com.example.bokmenappwithalladvancedtechnology.di;

import android.app.Application;

import androidx.room.Room;

import com.example.bokmenappwithalladvancedtechnology.db.PokemonDao;
import com.example.bokmenappwithalladvancedtechnology.db.PokemonDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static PokemonDatabase pokemonDatabase(Application application){
        return Room.databaseBuilder(application , PokemonDatabase.class , "fav_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
    @Provides
    @Singleton
    public static PokemonDao provideDao(PokemonDatabase pokemonDatabase){
        return pokemonDatabase.pokemonDao();
    }
}
