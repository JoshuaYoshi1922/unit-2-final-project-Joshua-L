package com.example.pokemon_search_backend.DTO;



import java.util.List;

public class UserDTO {

    private int id;
    private String username;
    private String email;
    private String password;
    private String teamName;
//    private List<UserFavPokemon> favoritePokemons;


    public UserDTO() {
    }

    public UserDTO(int id, String username, String email, String password, String teamName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.teamName = teamName;
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
}
