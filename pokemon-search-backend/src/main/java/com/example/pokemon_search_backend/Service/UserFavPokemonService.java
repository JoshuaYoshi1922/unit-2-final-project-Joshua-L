package com.example.pokemon_search_backend.Service;


import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.UserFavPokemonRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavPokemonService {
    private final UserFavPokemonRepo favPokemonRepo;

    @Autowired
    public UserFavPokemonService(UserFavPokemonRepo favPokemonRepo) {
        this.favPokemonRepo = favPokemonRepo;
    }

    @Transactional
    public UserFavPokemon addFavoritePokemon(UserModel user, PokemonModel pokemonModel) {
        if (favPokemonRepo.existsByUser_IdAndPokemonModel(user.getId(), pokemonModel)) {
            throw new RuntimeException("Pokemon already in favorites");
        }

        UserFavPokemon favPokemon = new UserFavPokemon(user, pokemonModel);
        return favPokemonRepo.save(favPokemon);
    }

    @Transactional
    public void removeFavoritePokemon(int userId, PokemonModel pokemonModel) {
        favPokemonRepo.findByUser_IdAndPokemonModel(userId, pokemonModel)
                .ifPresent(favPokemonRepo::delete);
    }

    public List<UserFavPokemon> getUserFavorites(int userId) {
        return favPokemonRepo.findByUser_Id(userId);
    }
}


