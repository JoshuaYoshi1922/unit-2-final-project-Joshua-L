package com.example.pokemon_search_backend.Service;

import com.example.pokemon_search_backend.Model.CommentModel;

import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.CommentRepository;
import com.example.pokemon_search_backend.Repository.UserFavPokemonRepo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private UserFavPokemonRepo userFavPokemonRepo;

    public CommentService(CommentRepository commentRepository, UserFavPokemonRepo userFavPokemonRepository) {
        this.commentRepository = commentRepository;
        this.userFavPokemonRepo = userFavPokemonRepo;
    }

    public CommentModel createComment(UserModel user, int pokemonId, String commentText) {
        Optional<UserFavPokemon> userFavPokemon = userFavPokemonRepo.findByUserIdAndPokemonId(user.getId(), pokemonId);

        if(userFavPokemon.isPresent()) {
            CommentModel comment = new CommentModel(user, userFavPokemon.get(), commentText);
            return commentRepository.save(comment);
        }
        throw new RuntimeException("Favorite Pokemon not found for user ID");
    }

    public List<CommentModel> getCommentsByPokemonId(int pokemonId) {
        return commentRepository.findByFavPokemon_PokemonId(pokemonId);
    }

    public List<CommentModel> getCommentsByUserId(int userId) {
        return commentRepository.findByUser_Id(userId);
    }

    public CommentModel updateComment(int commentId, String commentText) {
        Optional<CommentModel> existingComment = commentRepository.findById(commentId);

        if(existingComment.isPresent()) {
            CommentModel comment = existingComment.get();
            comment.setCommentText(commentText);
            return commentRepository.save(comment);
        }
        throw new RuntimeException("Comment not found with ID: ");
    }

    public void deleteComment(int commentId) {
        commentRepository.deleteById(commentId);
    }

}
