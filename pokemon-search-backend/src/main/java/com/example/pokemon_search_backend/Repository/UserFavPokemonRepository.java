package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.UserFavPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserFavPokemonRepository extends JpaRepository<UserFavPokemon, Integer> {
    @Query
    List<UserFavPokemon> findByUserId(int userId);
    Optional<UserFavPokemon> findByUserIdAndPokemonId(int userId, int pokemonId);
}