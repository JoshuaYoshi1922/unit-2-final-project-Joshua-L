package com.example.pokemon_search_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PokemonSearchBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonSearchBackendApplication.class, args);
	}

}
