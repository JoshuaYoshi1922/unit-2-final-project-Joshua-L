package com.example.pokemon_search_backend.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "user_fav_pokemon")
public class UserFavPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserModel user;

    @Column(name = "pokemonId", nullable = false)
    private int pokemonId;

    @Column(name = "comment", length = 200)
    private String comment;


    public UserFavPokemon() {}

    public UserFavPokemon(int userId, int pokemonId) {
    }

    public UserFavPokemon(int id, UserModel user, int pokemonId, String comment) {
        this.id = id;
        this.user = user;
        this.pokemonId = pokemonId;
        this.comment = comment;
    }

    public UserFavPokemon(UserModel user, int pokemonId) {
        this.user = user;
        this.pokemonId = pokemonId;
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
