package com.example.pokemon_search_backend.Controller;


import com.example.pokemon_search_backend.DTO.UserFavPokemonDTO;
import com.example.pokemon_search_backend.Service.UserFavPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:5173")
public class UserFavPokemonController {

    private final UserFavPokemonService favPokemonService;

    @Autowired
    public UserFavPokemonController(UserFavPokemonService favPokemonService) {
        this.favPokemonService = favPokemonService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserFavPokemonDTO>> getUserFavorites(@PathVariable int userId) {
        List<UserFavPokemonDTO> favorites = favPokemonService.getFavoritesByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    @PostMapping
    public ResponseEntity<UserFavPokemonDTO> addFavorite(@RequestBody UserFavPokemonDTO favPokemonDTO) {
        UserFavPokemonDTO added = favPokemonService.addFavorite(favPokemonDTO);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{userId}/pokemon/{pokemonId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable int userId, @PathVariable int pokemonId) {
        favPokemonService.removeFavorite(userId, pokemonId);
        return ResponseEntity.noContent().build();
    }

}
