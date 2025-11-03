package com.example.pokemon_search_backend.DTO;

import com.example.pokemon_search_backend.Model.UserFavPokemon;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    private int id;
    private String username;
    private String email;
    private String password;
    private Set<UserFavPokemon> favoritePokemons;

    public UserDTO(int id, String username, String email, Set<UserFavPokemon> favoritePokemons) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoritePokemons = favoritePokemons;
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserFavPokemon> getFavoritePokemons() {
        return favoritePokemons;
    }
    public void setFavoritePokemons(Set<UserFavPokemon> favoritePokemons) {
        this.favoritePokemons = favoritePokemons;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
