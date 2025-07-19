const BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

export const getPokemon = async () => {
  const response = await fetch(`${BASE_URL}`); 
  const data = await response.json();
  console.log("response", data)
  return data.results;
};

