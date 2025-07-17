import PokemonDisplay from "./PokemonDisplay";

function Home() {
  const pokemons = [
    { id: 1, name: "Bulbasaur", type: "Grass and Poison" },
    { id: 2, name: "Ivysaur", type: "Grass and Poison" },
    { id: 3, name: "Venousaur", type: "Grass and Poison" },
    { id: 4, name: "Charmander", type: "Fire" },
  ];

  return (
    <div className="homePage">
      <div className="pokemon-grid">
        {pokemons.map((pokemon) => (
          <PokemonDisplay pokemon={pokemon} key={pokemon.id} />
        ))}
      </div>
    </div>
  );
}

export default Home;