package com.example.pokemon_search_backend.Controller;

import com.example.pokemon_search_backend.DTO.UserFavPokemonDTO;
import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Service.UserFavPokemonService;
import com.example.pokemon_search_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:5173")
public class UserFavPokemonController {

    private final UserFavPokemonService favPokemonService;
    private final UserService userService;

    @Autowired
    public UserFavPokemonController(UserFavPokemonService favPokemonService, UserService userService) {
        this.favPokemonService = favPokemonService;
        this.userService = userService;
    }

    @PostMapping("/user/{userId}/pokemon/{pokemonId}")
    public ResponseEntity<?> addFavoritePokemon(
            @PathVariable int userId,
            @PathVariable int pokemonId) {
        try {
            return userService.getUserById(userId)
                    .map(user -> {
                        UserFavPokemonDTO favPokemon = favPokemonService.addFavoritePokemon(user, pokemonId);
                        return ResponseEntity.ok(favPokemon);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/user/{userId}/pokemon/{pokemonId}")
    public ResponseEntity<?> removeFavoritePokemon(
            @PathVariable int userId,
            @PathVariable int pokemonId) {
        try {
            favPokemonService.removeFavoritePokemon(userId, pokemonId);
            return ResponseEntity.ok().body("Pokemon removed from favorites");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserFavPokemonDTO>> getUserFavorites(@PathVariable int userId) {
        List<UserFavPokemonDTO> favorites = favPokemonService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }
}

