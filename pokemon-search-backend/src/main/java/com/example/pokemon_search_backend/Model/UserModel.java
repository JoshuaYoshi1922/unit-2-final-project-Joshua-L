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

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFavPokemon> favoritePokemons = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "user", nullable = false, unique = true)
//    private TeamModel team;



    public UserModel(String username, String email, String password, List<UserFavPokemon> favoritePokemons) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoritePokemons = favoritePokemons;
//        this.team = team;

    }

    public int getId(int userId) {
        return id;
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

    public List<UserFavPokemon> getFavoritePokemons() {
        return favoritePokemons;
    }

    public void setFavoritePokemons(List<UserFavPokemon> favoritePokemons) {
        this.favoritePokemons = favoritePokemons;
    }

//    public TeamModel getTeam() {
//        return team;
//    }
//    public void setTeam(TeamModel team) {
//        this.team = team;
//    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id && Objects.equals(username, userModel.username) && Objects.equals(email, userModel.email) && Objects.equals(password, userModel.password) && Objects.equals(favoritePokemons, userModel.favoritePokemons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, favoritePokemons);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", favoritePokemons=" + favoritePokemons +

                '}';
    }
}

