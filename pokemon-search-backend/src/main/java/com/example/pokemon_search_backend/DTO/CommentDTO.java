package com.example.pokemon_search_backend.DTO;

import java.util.Objects;

public class CommentDTO {
    private int id;
    private String title;
    private String comment;

    public CommentDTO() {
    }

    public CommentDTO(int id, String title, String comment) {
        this.id = id;
        this.title = title;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, comment);
    }
}
