package com.example.pokemon_search_backend.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pokemon_comments")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id", nullable = false)
    private UserFavPokemon favPokemon;

    @Column(name = "comment_text", nullable = false, length = 1000)
    private String commentText;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime createdAt;

    public CommentModel() {
        this.createdAt = LocalDateTime.now();
    }

    public CommentModel(UserModel user, UserFavPokemon favPokemon, String commentText) {
        this.user = user;
        this.favPokemon = favPokemon;
        this.commentText = commentText;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserFavPokemon getFavPokemon() {
        return favPokemon;
    }

    public void setFavPokemon(UserFavPokemon favPokemon) {
        this.favPokemon = favPokemon;
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

    @Override
    public String toString() {
        return "CommentModel{" +
                "id=" + id +
                ", user=" + user +
                ", favPokemon=" + favPokemon +
                ", commentText='" + commentText + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommentModel that = (CommentModel) o;
        return id == that.id && Objects.equals(user, that.user) && Objects.equals(favPokemon, that.favPokemon) && Objects.equals(commentText, that.commentText) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, favPokemon, commentText, createdAt);
    }
}
