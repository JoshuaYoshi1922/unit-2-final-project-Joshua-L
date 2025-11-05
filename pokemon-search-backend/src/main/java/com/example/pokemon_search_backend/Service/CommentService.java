package com.example.pokemon_search_backend.Service;

import com.example.pokemon_search_backend.Model.CommentModel;
import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final UserService userService;

    @Autowired
    public CommentService(CommentRepo commentRepo, UserService userService) {
        this.commentRepo = commentRepo;
        this.userService = userService;
    }

    public CommentModel addOrUpdateComment(int userId, PokemonModel pokemonModel, String comment) {
        UserModel user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        CommentModel commentModel = commentRepo
                .findByUserIdAndPokemonModelAndIsFavPokemon(userId, pokemonModel, true)
                .orElse(new CommentModel());

        commentModel.setUser(user);
        commentModel.setPokemonModel(pokemonModel);
        commentModel.setComment(comment);
        commentModel.setFavPokemon(true);

        return commentRepo.save(commentModel);

    }

    public void deleteComment(int userId, PokemonModel pokemonModel) {
        commentRepo.findByUserIdAndPokemonModelAndIsFavPokemon(userId,pokemonModel, true)
                .ifPresent(commentRepo::delete);
    }

    public CommentModel getFavoriteComment(int userId, PokemonModel pokemonModel) {
        return commentRepo.findByUserIdAndPokemonModelAndIsFavPokemon(userId, pokemonModel, true)
                .orElse(null);
    }


}
