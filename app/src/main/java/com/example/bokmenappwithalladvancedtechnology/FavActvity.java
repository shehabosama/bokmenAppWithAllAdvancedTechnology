package com.example.bokmenappwithalladvancedtechnology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bokmenappwithalladvancedtechnology.adapters.PokemonAdapter;
import com.example.bokmenappwithalladvancedtechnology.pojo.Pokemon;
import com.example.bokmenappwithalladvancedtechnology.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavActvity extends AppCompatActivity {

    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_actvity);
        recyclerView = findViewById(R.id.fav_recyclerView);
        adapter = new PokemonAdapter(FavActvity.this);
        recyclerView.setAdapter(adapter);
        setUpSwipe();

        Button toHomeBtn = findViewById(R.id.to_home_button);
        toHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavActvity.this, MainActivity.class));
            }
        });

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getFavPokemon();
        viewModel.getFavLst().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                ArrayList<Pokemon> list = new ArrayList<>();
                list.addAll(pokemons);
                adapter.setList(list);
            }
        });

    }

    private void setUpSwipe() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition = viewHolder.getAdapterPosition();
                Pokemon swipedPokemon = adapter.getPokemonAt(swipedPokemonPosition);
                viewModel.deletePokemon(swipedPokemon.getName());
                adapter.notifyDataSetChanged();
                Toast.makeText(FavActvity.this, "pokemon deleted from database", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
