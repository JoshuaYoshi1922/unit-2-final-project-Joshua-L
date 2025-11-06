package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.CommentModel;
import com.example.pokemon_search_backend.Model.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<CommentModel, Integer> {
    List<CommentModel> findByPokemonModel(PokemonModel pokemonModel);
    List<CommentModel> findByUserId(int userId);
    List<CommentModel> findByPokemonModelAndUserId(PokemonModel pokemonModel, int userId);
    Optional<CommentModel> findByUserIdAndPokemonModelAndIsFavPokemon(int userId, PokemonModel pokemonModel, boolean isFavPokemon);
}
