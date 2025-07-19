import "../css/PokemonDisplay.css";
import { usePokemonContext } from "../contexts/PokemonContexts";
import { useState } from "react";

function PokemonDisplay({ pokemon }) {
  const { isFavorite, addToFavorites, removeFromFavorites } =
    usePokemonContext();
  const [isShiny, setIsShiny] = useState(false);
  const favorite = isFavorite(pokemon.id);

  function onFavorite(e) {
    e.preventDefault();
    if (favorite) removeFromFavorites(pokemon.id);
    else addToFavorites(pokemon);
  }

  return (
    <>
      <div className="pokemon-card">
        <h2 className="pokenum">#{pokemon.id}</h2>
        <h3>{pokemon.name}</h3>
        <div className="pokemon-image">
          <img
            src={`https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png`}
            alt={pokemon.name}
          />
          <div className="pokemon-overlay">
            <button
              className={`favorite-btn ${favorite ? "active" : ""}`}
              onClick={onFavorite}
            >
              ♥
            </button>
          </div>
        </div>

        <div className="pokemon-info">
          <p>{pokemon.type}</p>
          <button
            className={`shiny-btn ${isShiny ? "isShiny" : ""}`}
            onClick={() => setIsShiny(!isShiny)}
          >
            <div className="thumb"></div>
          </button>
        </div>
      </div>
    </>
  );
}

export default PokemonDisplay;
