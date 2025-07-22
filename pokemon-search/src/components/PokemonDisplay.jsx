import "../css/PokemonDisplay.css";
import { usePokemonContext } from "../contexts/PokemonContexts";
import { useState } from "react";

const IMAGE_URL =
  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

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

  const imgSprite = isShiny
    ? pokemon.sprites?.front_shiny || `${IMAGE_URL}shiny/${pokemon.id}.png`
    : pokemon.sprites?.front_default || `${IMAGE_URL}${pokemon.id}.png`;

  return (
    <>
      <div className="pokemon-card">
        <h2 className="pokenum">#{pokemon.id}</h2>
        <h3>{pokemon.name}</h3>
        <div className="pokemon-image">
          <img src={imgSprite} alt={pokemon.name} />
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
          <p>Height: {pokemon.height}</p>
          Type:
          {Array.isArray(pokemon.types)
            ? pokemon.types.map((item) => (
                <p key={item.type.name}>{item.type.name}</p>
              ))
            : pokemon.type}
        </div>
        <button
          className={`shiny-btn ${isShiny ? "isShiny" : ""}`}
          onClick={() => setIsShiny(!isShiny)}
        >
          <div className="thumb"></div>
        </button>
      </div>
    </>
  );
}

export default PokemonDisplay;
