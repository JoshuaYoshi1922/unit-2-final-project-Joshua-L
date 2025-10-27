package com.example.pokemon_search_backend.DTO;

import java.time.LocalDateTime;

public class CommentDTO {

    private int id;
    private int userId;
    private String username;
    private int pokemonId;
    private String comment;
    private LocalDateTime createdAt;

    public CommentDTO() {}

    public CommentDTO(int id, int userId, String username, int pokemonId, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.pokemonId = pokemonId;
        this.comment = comment;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
