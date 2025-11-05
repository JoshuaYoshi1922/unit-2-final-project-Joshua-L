package com.example.pokemon_search_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "pokemon")
public class PokemonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private ArrayList<Type> types;
    public int base_experience;
    public int height;
    public int weight;
    private ArrayList<Move> moves;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentModel> comments = new ArrayList<>();


    public PokemonModel(int id, String name, ArrayList<Type> types, int base_experience, int height, int weight, ArrayList<Move> moves, ArrayList<CommentModel> comments) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.base_experience = base_experience;
        this.height = height;
        this.weight = weight;
        this.moves = moves;
        this.comments = comments;
    }

    public PokemonModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = (ArrayList<Type>) types;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> movesList) {
        this.moves = (ArrayList<Move>) movesList;
    }

    public static class Type {

        private String name; // Added name field based on constructor usage

        public Type(String typeName) {
            this.name = typeName; // Initialize the name field
        }
        public Type() {
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Type2 {
        private String name;


        public Type2(String name) {
            this.name = name;

        }


        public Type2() {
        }

        @JsonProperty("name")
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

        public static class Move {

            private String name; // Added name field based on constructor usage

            public Move(String moveName) {
                this.name = moveName; // Initialize the name field
            }
            public Move() {
            }

            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class Move2 {
            private String name;



            public Move2(String name) {
                this.name = name;

            }


            public Move2() {
            }

            @JsonProperty("name")
            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }


    }
    public List<CommentModel> getComments() {
        return comments;
    }
    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PokemonModel that = (PokemonModel) o;
        return id == that.id && base_experience == that.base_experience && height == that.height && weight == that.weight && Objects.equals(name, that.name) && Objects.equals(types, that.types) && Objects.equals(moves, that.moves) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, types, base_experience, height, weight, moves, comments);
    }
}
