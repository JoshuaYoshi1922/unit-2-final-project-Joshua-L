package com.example.pokemon_search_backend.Service;


import com.example.pokemon_search_backend.DTO.UserFavPokemonDTO;
import com.example.pokemon_search_backend.Model.UserFavPokemon;


import com.example.pokemon_search_backend.Model.UserModel;
import com.example.pokemon_search_backend.Repository.UserFavPokemonRepo;
import com.example.pokemon_search_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<UserFavPokemonDTO> getFavoritesByUserId(int userId) {
        List<UserFavPokemon> favorites = favPokemonRepo.findByUserId(userId);

        return favorites.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserFavPokemonDTO addFavorite(UserFavPokemonDTO favPokemonDTO) {
        Optional<UserFavPokemon> existingFavorite = favPokemonRepo.findByUserIdAndPokemonId(favPokemonDTO.getUserId(), favPokemonDTO.getPokemonId());

        if(existingFavorite.isPresent()) {
            return convertToDTO(existingFavorite.get());
        }

        UserModel user = userRepository.findById(favPokemonDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found with ID: " + favPokemonDTO.getUserId()));

        UserFavPokemon newFavorite = new UserFavPokemon();
        newFavorite.setPokemonId(favPokemonDTO.getPokemonId());
        newFavorite.setUser(user);

        UserFavPokemon saved = favPokemonRepo.save(newFavorite);
        return convertToDTO(saved);
    }

    public void removeFavorite(int userId, int pokemonId) {
        Optional<UserFavPokemon> favorite = favPokemonRepo.findByUserIdAndPokemonId(userId, pokemonId);

        favorite.ifPresent(favPokemonRepo::delete);
    }

    private UserFavPokemonDTO convertToDTO(UserFavPokemon entity) {
        return new UserFavPokemonDTO(entity.getId(), entity.getUser().getId(), entity.getPokemonId());
    }


}

