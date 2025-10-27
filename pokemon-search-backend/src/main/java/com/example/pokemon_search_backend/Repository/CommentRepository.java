package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentModel, Integer> {
    List<CommentModel> findByFavPokemon_PokemonId(int pokemonId);
    List<CommentModel> findByUser_Id(int userId);
}
