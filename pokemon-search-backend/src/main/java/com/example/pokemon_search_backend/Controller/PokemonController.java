package com.example.pokemon_search_backend.Controller;



import com.example.pokemon_search_backend.DTO.PaginationPokemonResp;
import com.example.pokemon_search_backend.DTO.PokemonDTO;
import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Service.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
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
    public PokemonDTO getPokemon(@PathVariable String nameOrId) {
        return pokemonService.getPokemon(nameOrId);
    }

    @GetMapping("/")
    public List<PokemonDTO> getPokemonList(){
        return pokemonService.getPokemonList(0,151);
    }

    @GetMapping("/pokemon")
    public ResponseEntity<PaginationPokemonResp> getPokemonList(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit) {

        // Validate parameters
        if (limit > 100) limit = 100; // Max limit
        if (offset < 0) offset = 0;

        PaginationPokemonResp response = pokemonService.getPokemonListPaginated(offset, limit);
        return ResponseEntity.ok(response);
    }

}
