package com.example.pokemon_search_backend.Controller;


import com.example.pokemon_search_backend.DTO.CommentDTO;
import com.example.pokemon_search_backend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentsService) {
        this.commentService = commentsService;
    }

    @PostMapping("/{pokemonId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable int pokemonId, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(pokemonId,commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{pokemonId}/comments")
    public List<CommentDTO> getCommentsByPokemonId(@PathVariable int pokemonId) {
        return commentService.getCommentsByPokemonId(pokemonId);
    }

    @GetMapping("/{pokemonId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable int pokemonId, @PathVariable int commentId) {
        CommentDTO commentDTO = commentService.getCommentById(pokemonId, commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PutMapping("/{pokemonId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int pokemonId, @PathVariable int commentId, @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.updateComment(pokemonId, commentId, commentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{pokemonId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int pokemonId, @PathVariable int commentId) {
        commentService.deleteComment(pokemonId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
