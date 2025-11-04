package com.example.pokemon_search_backend.Service;


import com.example.pokemon_search_backend.DTO.UserFavPokemonDTO;
import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Model.UserFavPokemon;


import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.UserFavPokemonRepo;
import com.example.pokemon_search_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFavPokemonService {

    private final UserFavPokemonRepo favPokemonRepo;
    private final UserRepository userRepository;

    @Autowired
    public UserFavPokemonService(UserFavPokemonRepo favPokemonRepository, UserRepository userRepository) {
        this.favPokemonRepo = favPokemonRepository;
        this.userRepository = userRepository;
    }

    public List<UserFavPokemon> getFavoritesByUserId(int userId) {
        List<UserFavPokemon> favorites = favPokemonRepo.findByUser_Id(userId);
        return favPokemonRepo.findByUser_Id(userId);


    }


    public UserFavPokemon addFavorite(UserFavPokemonDTO favPokemonDTO) {
        UserModel user = userRepository.findById(favPokemonDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + favPokemonDTO.getUserId()));

        boolean exists = user.getFavoritePokemons()
                .stream()
                .anyMatch(fav -> fav.getPokemonId() == favPokemonDTO.getPokemonId());

        if (exists) {
            throw new RuntimeException("Favorite already exists for this user and pokemon");
        }

        UserFavPokemon favPokemon = new UserFavPokemon();
        favPokemon.setUser(user);
        favPokemon.setPokemonId(favPokemonDTO.getPokemonId());

        return favPokemonRepo.save(favPokemon);
    }


    public void removeFavorite(int userId, int pokemonId) {
        Optional<UserFavPokemon> favorite = favPokemonRepo.findByUser_IdAndPokemonId(userId, pokemonId);

        favorite.ifPresent(favPokemonRepo::delete);
    }


}

