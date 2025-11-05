package com.example.pokemon_search_backend.DTO;

import com.example.pokemon_search_backend.Model.UserFavPokemon;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private int id;
    private String username;
    private String email;
    private String password;
    private List<UserFavPokemon> favoritePokemons = new ArrayList<>();
//    private TeamDTO team;

public UserDTO() {}

    public UserDTO(int id, String username, String email, String password, List<UserFavPokemon> favoritePokemons) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoritePokemons = favoritePokemons;
//        this.team = team;

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

    public List<UserFavPokemon> getFavoritePokemons() {
        return favoritePokemons;
    }
    public void setFavoritePokemons(List<UserFavPokemon> favoritePokemons) {
        this.favoritePokemons = favoritePokemons;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public TeamDTO getTeam() {
//        return team;
//    }
//    public void setTeam(TeamDTO team) {
//        this.team = team;
//    }


}
