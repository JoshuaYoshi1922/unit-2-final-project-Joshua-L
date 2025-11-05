package com.example.pokemon_search_backend.Model;


import jakarta.persistence.*;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.util.Objects;

@Entity
@Table(name = "teams")
public class TeamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String teamName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public TeamModel() {
    }
    public TeamModel(String teamName, UserModel user) {
        this.teamName = teamName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamModel teamModel = (TeamModel) o;
        return id == teamModel.id && Objects.equals(teamName, teamModel.teamName) && Objects.equals(user, teamModel.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName, user);
    }

    @Override
    public String toString() {
        return "TeamModel{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", user=" + user +
                '}';
    }
}
