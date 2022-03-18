package com.example.bokmenappwithalladvancedtechnology.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bokmenappwithalladvancedtechnology.pojo.Pokemon;

@Database(entities = {Pokemon.class} , version = 1 , exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();

}
