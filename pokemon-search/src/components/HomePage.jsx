import PokemonDisplay from "./PokemonDisplay";
import { useState } from "react";

function Home() {
  const [searchPokemon, setSearchPokemon] = useState("");

  const pokemons = [
    { id: 1, name: "Bulbasaur", type: "Grass and Poison" },
    { id: 2, name: "Ivysaur", type: "Grass and Poison" },
    { id: 3, name: "Venousaur", type: "Grass and Poison" },
    { id: 4, name: "Charmander", type: "Fire" },
  ];

  const handlSearch = (e) => {
    e.preventDefault();
  };

  return (
    <div className="homePage">
      <form onSubmit={handlSearch} className="search-form">
        <input
          type="text"
          placeholder="Who's that Pokemon???"
          className="search-poke"
          value={searchPokemon}
          onChange={(e) => setSearchPokemon(e.target.value)}
        />
        <button type="submit" className="search-btn">
          Search
        </button>
      </form>
      <div className="pokemon-grid">
        {pokemons.map(
          (pokemon) =>
            pokemon.name.toLowerCase().startsWith(searchPokemon) && (
              <PokemonDisplay pokemon={pokemon} key={pokemon.id} />
            )
        )}
      </div>
    </div>
  );
}

export default Home;
