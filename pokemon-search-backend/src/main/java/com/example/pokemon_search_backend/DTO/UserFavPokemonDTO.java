package com.example.pokemon_search_backend.DTO;

public class UserFavPokemonDTO {
    private int id;
    private int userId;
    private String username;
    private int pokemonId;
    private String comment;

    public UserFavPokemonDTO(int id, int userId, String username, int pokemonId, String comment) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.pokemonId = pokemonId;
        this.comment = comment;
    }

    public UserFavPokemonDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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