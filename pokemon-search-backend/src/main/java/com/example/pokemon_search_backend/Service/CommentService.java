package com.example.pokemon_search_backend.Service;

import com.example.pokemon_search_backend.DTO.CommentDTO;
import com.example.pokemon_search_backend.Model.CommentModel;
import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Repository.CommentRepo;
import com.example.pokemon_search_backend.Repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private PokemonRepository pokemonRepository;
    private CommentRepo commentRepo;

    @Autowired
    public CommentService(PokemonRepository pokemonRepository, CommentRepo commentRepo) {
        this.pokemonRepository = pokemonRepository;
        this.commentRepo = commentRepo;

    }

    public CommentDTO createComment(int pokemonId, CommentDTO commentDTO) {
        CommentModel commentModel = mapToEntity(commentDTO);

        PokemonModel pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokemon not found with ID: " + pokemonId));

        commentModel.setPokemon(pokemon);

        CommentModel newComment = commentRepo.save(commentModel);

        return mapToDto(newComment);
    }

    public List<CommentDTO> getCommentsByPokemonId(int pokemonId) {
        List<CommentModel> comments = commentRepo.findByPokemonId(pokemonId);
        return comments.stream().map(this::mapToDto).toList();
    }

    public Optional<CommentDTO> getCommentById(int pokemonId, int commentId) {
        PokemonModel pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokemon not found with ID: " + pokemonId));
        CommentModel comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + commentId));
        if (comment.getPokemon().getId() != pokemon.getId()) {
            throw new RuntimeException("Comment does not belong to the specified Pokemon");
        }
        return Optional.of(mapToDto(comment));
    }

    public CommentDTO updateComment(int pokemonId, int commentId, CommentDTO commentDTO) {
        PokemonModel pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokemon not found with ID: " + pokemonId));
        CommentModel existingComment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + commentId));
        if (existingComment.getPokemon().getId() != pokemon.getId()) {
            throw new RuntimeException("Comment does not belong to the specified Pokemon");
    }
        existingComment.setTitle(commentDTO.getTitle());
        existingComment.setComment(commentDTO.getComment());

        CommentModel updatedComment = commentRepo.save(existingComment);
        return mapToDto(updatedComment);
    }

    public void deleteComment(int pokemonId, int commentId) {
        PokemonModel pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new RuntimeException("Pokemon not found with ID: " + pokemonId));
        CommentModel existingComment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + commentId));
        if (existingComment.getPokemon().getId() != pokemon.getId()) {
            throw new RuntimeException("Comment does not belong to the specified Pokemon");
    }
        commentRepo.delete(existingComment);
    }


    private CommentDTO mapToDto(CommentModel commentModel){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentModel.getId());
        commentDTO.setTitle(commentModel.getTitle());
        commentDTO.setComment(commentModel.getComment());
        return commentDTO;
    }
    private CommentModel mapToEntity(CommentDTO commentDTO){
        CommentModel commentModel = new CommentModel();
        commentModel.setTitle(commentDTO.getTitle());
        commentModel.setComment(commentDTO.getComment());
        commentModel.setComment(commentDTO.getComment());
        return commentModel;
    }
}
