package com.example.pokemon_search_backend.Controller;

import com.example.pokemon_search_backend.DTO.CommentDTO;
import com.example.pokemon_search_backend.Model.CommentModel;
import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites/comments")
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/user/{userID}/pokemon{pokemonId}")
    public ResponseEntity<?> addOrUpdateComment(
            @PathVariable int userId,
            @PathVariable PokemonModel pokemonModel,
            @RequestBody CommentDTO commentDTO) {
        try {
            CommentModel commentModel = commentService.addOrUpdateComment(
                    userId, pokemonModel, commentDTO.getComment()
            );
            return ResponseEntity.ok(commentModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}/pokemon/{pokemonId}")
    public ResponseEntity<?> getComment(
            @PathVariable int userId,
            @PathVariable PokemonModel pokemonModel) {
        CommentModel comment = commentService.getFavoriteComment(userId, pokemonModel);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(comment);
        }
    }

    @DeleteMapping("/user/{userId}/pokemon/{pokemonId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable int userId,
            @PathVariable PokemonModel pokemonModel) {
        try {
            commentService.deleteComment(userId, pokemonModel);
            return ResponseEntity.ok().body("Comment deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
