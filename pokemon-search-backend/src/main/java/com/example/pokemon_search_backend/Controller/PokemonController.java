package com.example.pokemon_search_backend.Controller;


import com.example.pokemon_search_backend.Service.PokemonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
@CrossOrigin(origins = "http://localhost:5173")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{nameOrId}")
    public Object getPokemon(@PathVariable String nameOrId) {
        return pokemonService.getPokemon(nameOrId);
    }

    @GetMapping("/")
    public Object getPokemonList() {
        return pokemonService.getPokemonList();
    }
}
