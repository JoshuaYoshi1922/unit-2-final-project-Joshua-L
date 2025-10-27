package com.example.pokemon_search_backend.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pokemon_comments")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private NewUserModel user;

    @ManyToOne
    @JoinColumn(name = "pokemon_id", nullable = false)
    private PokemonModel pokemon;

    @Column(name = "comment_text", nullable = false, length = 100)
    private String commentText;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime createdAt;

    public CommentModel() {
        this.createdAt = LocalDateTime.now();
    }

    public CommentModel(NewUserModel user, PokemonModel pokemon, String commentText) {
        this.user = user;
        this.pokemon = pokemon;
        this.commentText = commentText;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public NewUserModel getUser() {
        return user;
    }

    public void setUser(NewUserModel user) {
        this.user = user;
    }

    public PokemonModel getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonModel pokemon) {
        this.pokemon = pokemon;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
