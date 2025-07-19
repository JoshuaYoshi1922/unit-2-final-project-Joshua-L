import PokemonDisplay from "./PokemonDisplay";
import { useState, useEffect } from "react";
import Pagination from "./pagination";

function Home() {
  const [searchPokemon, setSearchPokemon] = useState("");
  const [pokemons, setPokemons] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [nextUrl, setNextUrl] = useState("");
  const [prevUrl, setPrevUrl] = useState("");
  
  useEffect(() => {
    fetchPokemons("https://pokeapi.co/api/v2/pokemon");
  }, []);

  function gotoNextPage() {
    if (nextUrl) fetchPokemons(nextUrl);
  }
  function gotoPrevPage() {
    if (prevUrl) fetchPokemons(prevUrl);
  }
  async function fetchPokemons(url) {
    setLoading(true);
    try {
      const response = await fetch(url);
      const data = await response.json();
      setNextUrl(data.next);
      setPrevUrl(data.previous);
      const results = await Promise.all(
        data.results.map(async (poke) => {
          const res = await fetch(poke.url);
          const details = await res.json();
          return {
            id: details.id,
            name: details.name,
            type: details.types.map((t) => t.type.name).join(", "),
            sprites: details.sprites.front_default,
          };
        })
      );
      setPokemons(results);
    } catch (error) {
      console.error("Error fetching", error);
    }
    setLoading(false);
  }

  const handlSearch = async (e) => {
    e.preventDefault();
    if (!searchPokemon.trim()) return;
    if (loading) return;
    setLoading(true);

    try {
      const response = await fetch("https://pokeapi.co/api/v2/pokemon?limit=1300");
      const data = await response.json();
      const filtered = data.results.filter((poke) => 
      poke.name.toLowerCase().startsWith(searchPokemon.toLowerCase())
    );
    const searchResults = await Promise.all(
      filtered.map(async (poke) => {
        const res = await fetch(poke.url);
        const details = await res.json();
        return {
          id: details.id,
          name: details.name,
          type: details.types.map((t) => t.type.name).join(", "),
          sprites: details.sprites.front_default,
        }
      })
    )
      setPokemons(searchResults);
      setError(null);
    } catch (err) {
      console.log(err);
      setError("Failed to find Pokemon...");
    } finally {
      setLoading(false);
    }
    setSearchPokemon("");
  };

  const filteredPokemons = pokemons.filter((pokemon) =>
    pokemon.name.toLowerCase().startsWith(searchPokemon.toLowerCase())
  );

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
        <button onClick={handlSearch} type="submit" className="search-btn">
          Search
        </button>
      </form>

      {error && <div className="error-message">{error}</div>}

      <div className="pokemon-grid">
        {loading ? (
          <p className="loading">Loading...</p>
        ) : (
          filteredPokemons.map(
            (pokemon) =>
              pokemon.name.toLowerCase().startsWith(searchPokemon) && (
                <PokemonDisplay pokemon={pokemon} key={pokemon.id} />
              )
          )
        )}
      </div>
      <Pagination gotoNextPage={gotoNextPage} gotoPrevPage={gotoPrevPage} />
    </div>
  );
}

export default Home;
