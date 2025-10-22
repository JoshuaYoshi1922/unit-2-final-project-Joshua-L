package com.example.pokemon_search_backend.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String url = "https://pokeapi.co/api/v2/pokemon/";

    public Object getPokemon(String nameOrId) {
        return restTemplate.getForObject(url + nameOrId, Object.class);
    }

    public Object getPokemonList() {
        return restTemplate.getForObject(url, Object.class);
    }
}
