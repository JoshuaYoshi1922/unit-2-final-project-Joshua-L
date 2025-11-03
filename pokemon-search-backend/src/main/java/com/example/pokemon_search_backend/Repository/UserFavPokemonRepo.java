package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.UserFavPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFavPokemonRepo extends JpaRepository<UserFavPokemon, Long> {
    List<UserFavPokemon> findByUserId(int userId);
    boolean existsByUserIdAndPokemonId(int userId, int pokemonId);

    Optional<UserFavPokemon> findByUserIdAndPokemonId(int userId, int pokemonId);
}
