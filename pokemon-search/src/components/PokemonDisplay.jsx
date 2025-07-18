import "../css/PokemonDisplay.css"

function PokemonDisplay({ pokemon }) {
  function onFavorite() {
    alert("fav pokemon!");
  }

  return (
    <>
      <div className="pokemon-card">
        <div className="pokemon-image">
          <img src={pokemon.sprite} alt={pokemon.name} />
          <div className="pokemon-overlay">
            <button className="favorite-btn" onClick={onFavorite}></button>
          </div>
        </div>
      </div>
      <div className="pokemon-info">
        <h3>{pokemon.name}</h3>
        <p>{pokemon.type}</p>
      </div>
    </>
  );
}

export default PokemonDisplay;
