package com.example.pokemon_search_backend.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    private String teamName;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFavPokemon> favoritePokemons = new ArrayList<>();


    public UserModel(int id, String username, String email, String password, String teamName, List<UserFavPokemon> favoritePokemons) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.teamName = teamName;
        this.favoritePokemons = favoritePokemons;
    }

    public UserModel() {}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<UserFavPokemon> getFavoritePokemons() {
        return favoritePokemons;
    }

    public void setFavoritePokemons(List<UserFavPokemon> favoritePokemons) {
        this.favoritePokemons = favoritePokemons;
    }
}

