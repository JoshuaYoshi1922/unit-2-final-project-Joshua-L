package com.example.pokemon_search_backend.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFavPokemon> favoritePokemons = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(String username, String password, String email, List<UserFavPokemon> favoritePokemons) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserFavPokemon> getFavoritePokemons() {
        return favoritePokemons;
    }

    public void setFavoritePokemons(List<UserFavPokemon> favoritePokemons) {
        this.favoritePokemons = favoritePokemons;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id && Objects.equals(username, userModel.username) && Objects.equals(password, userModel.password) && Objects.equals(email, userModel.email) && Objects.equals(favoritePokemons, userModel.favoritePokemons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, favoritePokemons);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", favoritePokemons=" + favoritePokemons +
                '}';
    }
}

