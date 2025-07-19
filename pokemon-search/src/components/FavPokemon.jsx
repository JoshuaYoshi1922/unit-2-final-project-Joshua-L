import { usePokemonContext } from "../contexts/PokemonContexts";
import PokemonDisplay from "./PokemonDisplay";

function FavPokemon() {
  const { favorites } = usePokemonContext();

  if (favorites) {
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
  }

  return (
    <div className="favorites-empty">
      <h2>No Favorite Pokemon added</h2>
      <p>Add you favorite Pokemon</p>
    </div>
  );
}

export default FavPokemon;
