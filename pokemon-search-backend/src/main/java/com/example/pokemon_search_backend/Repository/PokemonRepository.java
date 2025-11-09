package com.example.pokemon_search_backend.Repository;

import com.example.pokemon_search_backend.Model.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonModel, Integer> {
Optional<PokemonModel> findByNameOrId(String name, int id);
}
