package com.example.pokemon_search_backend.Service;

import com.example.pokemon_search_backend.Model.PokemonModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String url = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonModel getPokemon(String nameOrId) {
        String response = restTemplate.getForObject(url + nameOrId, String.class);
        try {
            JsonNode root = objectMapper.readTree(response);
            PokemonModel pokemon = new PokemonModel();
            pokemon.setId(root.path("id").asInt());
            pokemon.setName(root.path("name").asText());
            pokemon.base_experience = root.path("base_experience").asInt();
            pokemon.height = root.path("height").asInt();
            pokemon.weight = root.path("weight").asInt();

            // Extract types from the API response
            JsonNode typesNode = root.path("types");
            if (typesNode.isArray()) {
                List<PokemonModel.Type> typesList = new ArrayList<>();
                for (JsonNode typeNode : typesNode) {
                    JsonNode typeInfo = typeNode.path("type");
                    String typeName = typeInfo.path("name").asText();
                    typesList.add(new PokemonModel.Type(typeName));
                }
                pokemon.setTypes(typesList);
            }

            return pokemon;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<PokemonModel> getPokemonList() {
        String response = restTemplate.getForObject(url, String.class);
        List<PokemonModel> pokemonList = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            PokemonModel pokemon = new PokemonModel();
            pokemon.setId(root.path("id").asInt());
            pokemon.setName(root.path("name").asText());
            pokemon.base_experience = root.path("base_experience").asInt();
            pokemon.height = root.path("height").asInt();
            pokemon.weight = root.path("weight").asInt();

            // Extract types from the API response
            JsonNode typesNode = root.path("types");
            if (typesNode.isArray()) {
                List<PokemonModel.Type> typesList = new ArrayList<>();
                for (JsonNode typeNode : typesNode) {
                    JsonNode typeInfo = typeNode.path("type");
                    String typeName = typeInfo.path("name").asText();
                    typesList.add(new PokemonModel.Type(typeName));
                }
                pokemon.setTypes(typesList);
            }

            return pokemonList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
