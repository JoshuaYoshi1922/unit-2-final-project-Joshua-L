package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFavPokemonRepo extends JpaRepository<UserFavPokemon, Integer> {
    List<UserFavPokemon> findByUser_Id(int userId);
    boolean existsByUser_IdAndPokemonId(int userId, int pokemonId);

    Optional<UserFavPokemon> findByUser_IdAndPokemonId(int userId, int pokemonId);

    Optional<UserFavPokemon> findByUserIdAndPokemonId(int userId, int pokemonId);

    Optional<UserFavPokemon> findByUserAndPokemonId(Optional<UserModel> user, int pokemonId);
}
