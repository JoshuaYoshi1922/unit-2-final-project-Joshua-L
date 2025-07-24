import { usePokemonContext } from "../contexts/PokemonContexts";
import PokemonDisplay from "./PokemonDisplay";
import "../css/PokemonDisplay.css";
import { Link } from "react-router";

function FavPokemon() {
  const { favorites } = usePokemonContext();

  if (favorites && favorites.length > 0) {
    return (
      <div>
        <h2>Favorite Pokemon</h2>
        <div className="pokemon-grid">
          {favorites.map((pokemon) => (
            <PokemonDisplay pokemon={pokemon} key={pokemon.id} />
          ))}
        </div>
      </div>
    );
  } else {
    return (
      <div className="favorites-empty">
        <h2>No Favorite Pokemon added</h2>
        <p>Add you favorite Pokemon</p>
        <Link to="/">Back to Home</Link>
      </div>
    );
  }
}

export default FavPokemon;
