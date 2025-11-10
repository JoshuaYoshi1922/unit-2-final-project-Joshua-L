package com.example.pokemon_search_backend.DTO;

import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Model.UserModel;

import java.util.List;
import java.util.Set;

public class UserFavPokemonDTO {
    private int id;
    private UserModel user;
    private int pokemonId;
    private String comment;

    public UserFavPokemonDTO(int id, UserModel user, int pokemonId, String comment) {
        this.id = id;
        this.user = user;
        this.pokemonId = pokemonId;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}




