package com.example.pokemon_search_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PokemonModel {

    private int id;
    private String name;
    private ArrayList<Type> types;
    public int base_experience;
    public int height;
    public int weight;


    public PokemonModel(int id, String name, ArrayList<Type> types, int base_experience, int height, int weight) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.base_experience = base_experience;
        this.height = height;
        this.weight = weight;
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

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
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


        // Added constructor for convenience
        public Type2(String name) {
            this.name = name;

        }

        // Added default constructor
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

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", types=" + types +
                ", base_experience=" + base_experience +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PokemonModel pokemon = (PokemonModel) o;
        return id == pokemon.id && base_experience == pokemon.base_experience && height == pokemon.height && weight == pokemon.weight && Objects.equals(name, pokemon.name) && Objects.equals(types, pokemon.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, types, base_experience, height, weight);
    }
}