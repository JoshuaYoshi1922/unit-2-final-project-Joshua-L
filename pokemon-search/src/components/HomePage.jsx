import PokemonDisplay from "./PokemonDisplay";
import { useState, useEffect } from "react";
import Pagination from "./pagination";
import "../css/homepage.css";

const BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

function Home() {
  const [searchPokemon, setSearchPokemon] = useState("");
  const [pokemons, setPokemons] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [nextUrl, setNextUrl] = useState("");
  const [prevUrl, setPrevUrl] = useState("");

  useEffect(() => {
    fetchAllPokemons(`${BASE_URL}${searchPokemon}`);
  }, []);

  async function fetchAllPokemons(BASE_URL) {
    setLoading(true);
    try {
      const response = await fetch(BASE_URL);
      const data = await response.json();

      setNextUrl(data.next);
      setPrevUrl(data.previous);

      const getAllPokemonsFromAPI = data.results.map(async (poke) => {
        const res = await fetch(poke.url);
        const details = await res.json();
        return {
          id: details.id,
          name: details.name,
          type: details.types.map((t) => t.type.name).join(", "),
          height: details.height,
          weight: details.weight,
          moves: details.moves
            .map((m) => m.move.name)
            .slice(0, 2)
            .join(", "),
          sprites: details.sprites.front_default,
        };
      });

      const results = await Promise.all(getAllPokemonsFromAPI);

      setPokemons(results);
    } catch (error) {
      console.error("Error fetching", error);
    }
    setLoading(false);
  }

  async function fetchSpecificPokemon(BASE_URL) {
    setLoading(true);
    try {
      const response = await fetch(BASE_URL);
      if (!response.ok) {
        throw new Error("Pokemon not found");
      }
      const data = await response.json();

      return [
        {
          id: data.id,
          name: data.name,
          type: data.types.map((t) => t.type.name).join(", "),
          height: data.height,
          weight: data.weight,
          moves: data.moves
            .map((m) => m.move.name)
            .slice(0, 2)
            .join(", "),
        },
      ];
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
      console.error(error);
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
