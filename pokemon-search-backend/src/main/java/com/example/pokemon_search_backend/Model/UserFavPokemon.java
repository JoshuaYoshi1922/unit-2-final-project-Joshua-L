package com.example.pokemon_search_backend.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "user_fav_pokemon")
public class UserFavPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user")
    @JsonBackReference
    private UserModel user;

    @Column(name = "pokemonModel")
    private PokemonModel pokemonModel;

    public UserFavPokemon(UserModel user, PokemonModel pokemonModel) {
        this.user = user;
        this.pokemonModel = pokemonModel;
    }

    public UserFavPokemon() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public PokemonModel getPokemonModel() {
        return pokemonModel;
    }

    public void setPokemonModel(PokemonModel pokemonModel) {
        this.pokemonModel = pokemonModel;
    }
}



