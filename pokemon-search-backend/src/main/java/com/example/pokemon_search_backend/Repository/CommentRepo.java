package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentModel, Integer> {

    List<CommentModel> findByPokemonId(int pokemonId);
}
