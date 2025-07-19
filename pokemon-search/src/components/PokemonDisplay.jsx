import "../css/PokemonDisplay.css";

function PokemonDisplay({ pokemon }) {
  function onFavorite() {
    alert("fav pokemon!");
  }

  return (
    <>
      <div className="pokemon-card">
        <div className="pokemon-image">
          <img
            src={`https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png`}
            alt={pokemon.name}
          />
          <div className="pokemon-overlay">
            <button className="favorite-btn" onClick={onFavorite}></button>
          </div>
        </div>

        <div className="pokemon-info">
          <h2>{pokemon.id}</h2>
          <h3>{pokemon.name}</h3>
          <p>{pokemon.type}</p>
        </div>
      </div>
    </>
  );
}

export default PokemonDisplay;
