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
    private int id;


    private int pokemonId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserModel user;


    public UserFavPokemon(int pokemonId, UserModel user) {
        this.pokemonId = pokemonId;
        this.user = user;

    }

    public UserFavPokemon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setUserId(int userId) {
    }

    public void setUserId(UserModel user) {
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserFavPokemon that = (UserFavPokemon) o;
        return id == that.id && pokemonId == that.pokemonId && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pokemonId, user);
    }

    @Override
    public String toString() {
        return "UserFavPokemon{" +
                "id=" + id +
                ", user=" + user +
                ", pokemonId=" + pokemonId +
                '}';
    }


}

