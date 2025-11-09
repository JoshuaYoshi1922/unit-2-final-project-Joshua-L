package com.example.pokemon_search_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.cache.annotation.CachePut;

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

    @Transient
    private ArrayList<Type> types;

    public int base_experience;
    public int height;
    public int weight;
    @Transient
    private ArrayList<Move> moves;




    public PokemonModel(int id, String name, ArrayList<Type> types, int base_experience, int height, int weight, ArrayList<Move> moves) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.base_experience = base_experience;
        this.height = height;
        this.weight = weight;
        this.moves = moves;
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

        private String name;

        public Type(String typeName) {
            this.name = typeName;
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

        private String name;

        public Move(String moveName) {
            this.name = moveName;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PokemonModel that = (PokemonModel) o;
        return id == that.id && base_experience == that.base_experience && height == that.height && weight == that.weight && Objects.equals(name, that.name) && Objects.equals(types, that.types) && Objects.equals(moves, that.moves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, types, base_experience, height, weight, moves);
    }
}