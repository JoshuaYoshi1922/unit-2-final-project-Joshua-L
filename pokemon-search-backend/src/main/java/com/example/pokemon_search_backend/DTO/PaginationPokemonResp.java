package com.example.pokemon_search_backend.DTO;

import java.util.List;

public class PaginationPokemonResp {

    private List<PokemonDTO> pokemon;
    private int count;
    private String next;
    private String previous;
    private int totalCount;

    // Constructors, getters, setters
    public PaginationPokemonResp() {}

    public PaginationPokemonResp(List<PokemonDTO> pokemon, int count, String next, String previous, int totalCount) {
        this.pokemon = pokemon;
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.totalCount = totalCount;
    }

    public List<PokemonDTO> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<PokemonDTO> pokemon) {
        this.pokemon = pokemon;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
