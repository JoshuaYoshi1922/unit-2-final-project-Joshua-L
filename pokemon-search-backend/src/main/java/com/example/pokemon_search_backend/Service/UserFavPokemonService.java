package com.example.pokemon_search_backend.Service;


import com.example.pokemon_search_backend.DTO.PokemonDTO;
import com.example.pokemon_search_backend.DTO.UserFavPokemonDTO;
import com.example.pokemon_search_backend.Model.UserFavPokemon;
import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.UserFavPokemonRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFavPokemonService {
    private final UserFavPokemonRepo favPokemonRepo;
    private final PokemonService pokemonService;

    @Autowired
    public UserFavPokemonService(UserFavPokemonRepo favPokemonRepo, PokemonService pokemonService) {
        this.pokemonService = pokemonService;
        this.favPokemonRepo = favPokemonRepo;
    }

    @Transactional
    public UserFavPokemonDTO addFavoritePokemon(UserModel user, int pokemonId) {
        if (favPokemonRepo.existsByUser_IdAndPokemonId(user.getId(), pokemonId)) {
            throw new RuntimeException("Pokemon already in favorites");
        }

        UserFavPokemon favPokemon = new UserFavPokemon(user, pokemonId);
        favPokemon.setUser(user);
        favPokemon.setPokemonId(pokemonId);
        favPokemonRepo.save(favPokemon);

        PokemonDTO pokemonDTO = pokemonService.getPokemon(String.valueOf(pokemonId));
        return new UserFavPokemonDTO(favPokemon.getId(), user.getId(), user.getUsername(), pokemonId, favPokemon.getComment());

    }

    @Transactional
    public void removeFavoritePokemon(int userId, int pokemonId) {
        favPokemonRepo.findByUser_IdAndPokemonId(userId, pokemonId)
                .ifPresent(favPokemonRepo::delete);
    }

    @Transactional
    public List<UserFavPokemonDTO> getUserFavorites(int userId) {
        List<UserFavPokemon> favorites = favPokemonRepo.findByUser_Id(userId);
        return favorites.stream()
                .map(fav -> {
                    UserModel u = fav.getUser();
                    return new UserFavPokemonDTO(fav.getId(), u != null ? u.getId() : 0, u != null ? u.getUsername() : null, fav.getPokemonId(), fav.getComment());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public UserFavPokemonDTO updateFavoriteComment(int userId, int pokemonId, String comment) {
        UserFavPokemon fav = favPokemonRepo.findByUser_IdAndPokemonId(userId, pokemonId)
                .orElseThrow(() -> new EntityNotFoundException("Favorite Pokemon not found for user"));
        fav.setComment(comment);
        favPokemonRepo.save(fav);
        UserModel u = fav.getUser();
        return new UserFavPokemonDTO(fav.getId(), u != null ? u.getId() : 0, u != null ? u.getUsername() : null, fav.getPokemonId(), fav.getComment());
    }

    @Transactional
    public UserFavPokemonDTO deleteFavoriteComment(int userId, int pokemonId) {
        UserFavPokemon fav = favPokemonRepo.findByUser_IdAndPokemonId(userId, pokemonId)
                .orElseThrow(() -> new EntityNotFoundException("Favorite Pokemon not found for user"));
        fav.setComment(null);
        favPokemonRepo.save(fav);
        UserModel u = fav.getUser();
        return new UserFavPokemonDTO(fav.getId(), u != null ? u.getId() : 0, u != null ? u.getUsername() : null, fav.getPokemonId(), fav.getComment());
    }

}
