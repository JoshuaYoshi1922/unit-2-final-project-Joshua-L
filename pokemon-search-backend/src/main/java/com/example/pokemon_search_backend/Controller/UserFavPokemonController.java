package com.example.pokemon_search_backend.Controller;

import com.example.pokemon_search_backend.DTO.UserFavPokemonDTO;
import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Service.UserFavPokemonService;
import com.example.pokemon_search_backend.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    // Accept both POST and PUT for creating/adding a favorite
    @RequestMapping(value = "/user/{userId}/pokemon/{pokemonId}", method = {RequestMethod.POST, RequestMethod.PUT})
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

    // Accept both POST and PUT for adding/updating a comment
    @RequestMapping(value = "/user/{userId}/pokemon/{pokemonId}/comment", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updateFavoriteComment(
            @PathVariable int userId,
            @PathVariable int pokemonId,
            @RequestBody Map<String, String> body) {
        try {
            String comment = body.get("comment");
            if (comment == null) {
                Map<String, String> err = new HashMap<>();
                err.put("error", "Missing 'comment' in request body");
                return ResponseEntity.badRequest().body(err);
            }

            UserFavPokemonDTO updated = favPokemonService.updateFavoriteComment(userId, pokemonId, comment);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/user/{userId}/pokemon/{pokemonId}/comment")
    public ResponseEntity<?> deleteFavoriteComment(
            @PathVariable int userId,
            @PathVariable int pokemonId) {
        try {
            UserFavPokemonDTO updated = favPokemonService.deleteFavoriteComment(userId, pokemonId);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}