package com.example.pokemon_search_backend.Controller;



import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Service.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
@CrossOrigin(origins = "http://localhost:5173")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{nameOrId}")
    public PokemonModel getPokemon(@PathVariable String nameOrId) {
        return pokemonService.getPokemon(nameOrId);
    }

    @GetMapping("/")
    public List<PokemonModel> getPokemonList(){
        return pokemonService.getPokemonList();
    }

    @PostMapping("/add/{nameOrId}")
    public String addPokemon(@PathVariable String nameOrId) {
        try {
            PokemonModel pokemon = pokemonService.getPokemon(nameOrId);
            pokemonService.savePokemon(pokemon);
            return "Pokemon added successfully: " + pokemon.getName();
        } catch (Exception e) {
            return "Error adding pokemon: " + e.getMessage();
        }
    }
}
