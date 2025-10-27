package com.example.pokemon_search_backend.DTO;

public class UserFavPokemonDTO {
    private int id;
    private int userId;
    private int pokemonId;

    public UserFavPokemonDTO() {
    }

    public UserFavPokemonDTO(int id, int userId, int pokemonId) {
        this.id = id;
        this.userId = userId;
        this.pokemonId = pokemonId;
    }
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
    public int getPokemonId() {
        return pokemonId;
    }
    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }
}
