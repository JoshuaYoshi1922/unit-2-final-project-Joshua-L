package com.example.pokemon_search_backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserModel user;

    @JsonBackReference
    @ManyToOne
    private PokemonModel pokemonModel;

    private boolean isFavPokemon = true;

    public CommentModel(int id, String comment, UserModel user, PokemonModel pokemonModel, boolean isFavPokemon) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.pokemonModel = pokemonModel;
        this.isFavPokemon = isFavPokemon;
    }

    public CommentModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public boolean isFavPokemon() {
        return isFavPokemon;
    }

    public void setFavPokemon(boolean favPokemon) {
        isFavPokemon = favPokemon;
    }
}
