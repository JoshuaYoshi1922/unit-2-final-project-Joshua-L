package com.example.pokemon_search_backend.Service;

import com.example.pokemon_search_backend.DTO.PokemonDTO;
import com.example.pokemon_search_backend.Model.PokemonModel;
import com.example.pokemon_search_backend.Repository.PokemonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String url = "https://pokeapi.co/api/v2/pokemon/";
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public PokemonModel savePokemonModel(PokemonModel pokemonModel) {
        return pokemonRepository.save(pokemonModel);
    }


    public PokemonDTO getPokemon(String nameOrId) {
        String response = restTemplate.getForObject(url + nameOrId, String.class);
        try {
            JsonNode root = objectMapper.readTree(response);
            PokemonDTO pokemon = new PokemonDTO();
            pokemon.setId(root.path("id").asInt());
            pokemon.setName(root.path("name").asText());
            pokemon.base_experience = root.path("base_experience").asInt();
            pokemon.height = root.path("height").asInt();
            pokemon.weight = root.path("weight").asInt();

            // Extract types from the API response
            JsonNode typesNode = root.path("types");
            if (typesNode.isArray()) {
                List<String> typesList = new ArrayList<>();
                for (JsonNode typeNode : typesNode) {
                    JsonNode typeInfo = typeNode.path("type");
                    String typeName = typeInfo.path("name").asText();
                    typesList.add(typeName);

                }
                pokemon.setTypes(typesList);
            }
            // Extract moves from the API response
            JsonNode movesNode = root.path("moves");
            if (movesNode.isArray()) {
                List<String> movesList = new ArrayList<>();
                int moveCount = 0;
                for (JsonNode moveNode : movesNode) {
                    if (moveCount >= 3 ) break; //
                    JsonNode moveInfo = moveNode.path("move");
                    String moveName = moveInfo.path("name").asText();
                    movesList.add(moveName);
                    moveCount++;
                }
                pokemon.setMoves(movesList);
            }

            return pokemon;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PokemonDTO> getPokemonList() {
        String response = restTemplate.getForObject(url, String.class);
        List<PokemonDTO> pokemonList = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode results = root.path("results");

            if (results.isArray()) {
                for (JsonNode pokemonNode : results) {
                    String pokemonName = pokemonNode.path("name").asText();
                    PokemonDTO pokemon = getPokemon(pokemonName);
                    if (pokemon != null) {
                        pokemonList.add(pokemon);
                    }
                }
            }

            return pokemonList;
        } catch (Exception e) {
            e.printStackTrace();
            return pokemonList;

        }
    }

}
