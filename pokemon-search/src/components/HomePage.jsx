import PokemonDisplay from "./PokemonDisplay";
import { useState, useEffect } from "react";
import Pagination from "./Pagination";
import "../css/homepage.css";

const BASE_URL = "http://localhost:8080/api/pokemon/";

function Home() {
  const [searchPokemon, setSearchPokemon] = useState("");
  const [pokemons, setPokemons] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [nextUrl, setNextUrl] = useState("");
  const [prevUrl, setPrevUrl] = useState("");

  useEffect(() => {
    fetchAllPokemons(`${BASE_URL}`);
  }, []);

  

  async function fetchAllPokemons(url) {
    setLoading(true);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();

      const results = (Array.isArray(data) ? data : []).map((pokemon) => {
        
        const types = pokemon.types ? pokemon.types.map(type => 
          typeof type === 'object' ? type.name || (type.type && type.type.name) : type
        ).join(", ") : "";

        
        const moves = pokemon.moves ? pokemon.moves.map(move => 
          typeof move === 'object' ? move.name || (move.move && move.move.name) : move
        ).slice(0, 2).join(", ") : "";

        return {
          id: pokemon.id,
          name: pokemon.name,
          type: types,
          height: pokemon.height,
          weight: pokemon.weight,
          moves: moves,
          sprites: {
            front_default: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png`,
            front_shiny: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${pokemon.id}.png`,
          },
        };
      });
      setPokemons(results);
    } catch (error) {
      console.error("Error fetching", error);
    }
    setLoading(false);
  }

  async function fetchSpecificPokemon(url) {
    setLoading(true);
    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error("Pokemon not found");
      }
      const data = await response.json();
      
      
      
      const types = data.types ? data.types.map(type => 
        typeof type === 'object' ? type.name || (type.type && type.type.name) : type
      ).join(", ") : "";

      
      const moves = data.moves ? data.moves.map(move => 
        typeof move === 'object' ? move.name || (move.move && move.move.name) : move
      ).slice(0, 2).join(", ") : "";
      
      return [{
        id: data.id,
        name: data.name,
        type: types,
        height: data.height,
        weight: data.weight,
        moves: moves,
        sprites: {
          front_default: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${data.id}.png`,
          front_shiny: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${data.id}.png`,
        },
      }];
    } catch (error) {
      console.error("Error fetching", error);
      throw error;
    } finally {
      setLoading(false);
    }
  }

  const handlSearch = async (searchTerm) => {
    if (!searchTerm) return;
    if (loading) return;

    try {
      const newPokemon = await fetchSpecificPokemon(
        `${BASE_URL}${searchTerm.toLowerCase()}`
      );
      
      setPokemons(newPokemon);
      setError(null);
    } catch (error) {
      
      setError("Pokemon not found. Please try again.");
    } finally {
      setLoading(false);
    }
    setSearchPokemon("");
  };

  function gotoNextPage() {
    if (nextUrl) fetchAllPokemons(nextUrl);
  }
  function gotoPrevPage() {
    if (prevUrl) fetchAllPokemons(prevUrl);
  }

  const pressEnter = (e) => {
    if (e.key === "Enter") {
      handlSearch(searchPokemon);
    }
  };

  return (
    <div className="homePage">
      <div className="poke-search">
        <input
          type="text"
          placeholder="Search by name or ID"
          className="search-poke"
          value={searchPokemon}
          name="search"
          onKeyDown={pressEnter}
          onChange={(e) => setSearchPokemon(e.target.value)}
        />

        <button
          onClick={() => handlSearch(searchPokemon)}
          type="submit"
          className="search-btn"
        >
          Search
        </button>
      </div>
      {error && <div className="error-message">{error}</div>}

      <div className="next-prev">
        <Pagination gotoNextPage={gotoNextPage} gotoPrevPage={gotoPrevPage} />
      </div>
      <div className="pokemon-grid">
        {loading ? (
          <p className="loading">Loading...</p>
        ) : (
          pokemons.map((pokemon) => (
            <PokemonDisplay pokemon={pokemon} key={pokemon.id} />
          ))
        )}
      </div>
      
      <div className="next-prev">
        <Pagination gotoNextPage={gotoNextPage} gotoPrevPage={gotoPrevPage} />
      </div>
    </div>
  );
}

export default Home;
