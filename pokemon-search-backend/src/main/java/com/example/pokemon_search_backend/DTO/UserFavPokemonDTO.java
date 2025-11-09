package com.example.pokemon_search_backend.DTO;

import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Model.UserModel;

import java.util.List;
import java.util.Set;

public class UserFavPokemonDTO {
    private int id;
    private int pokemonId;
    private int userId;


    public UserFavPokemonDTO() {}

    public UserFavPokemonDTO(int id, int pokemonId, int userId) {
        this.id = id;
        this.pokemonId = pokemonId;
        this.userId = userId;

    }


    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

}