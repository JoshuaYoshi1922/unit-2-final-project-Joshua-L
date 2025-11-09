package com.example.pokemon_search_backend.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@Entity
@Table(name = "user_fav_pokemon")
public class UserFavPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "user")
    @JsonBackReference
    private UserModel user;

    @ManyToOne
    private PokemonModel pokemonModel;

    @JoinColumn(name = "pokemon_Id")
    private int pokemonId;

    public UserFavPokemon(int id, UserModel user, int pokemonId, PokemonModel pokemonModel) {
        this.id = id;
        this.user = user;
        this.pokemonId = pokemonId;
        this.pokemonModel = pokemonModel;
    }

    public UserFavPokemon(UserModel user, int pokemonId) {
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

    public PokemonModel getPokemonModel() {
        return pokemonModel;
    }

    public void setPokemonModel(PokemonModel pokemonModel) {
        this.pokemonModel = pokemonModel;
    }
}



