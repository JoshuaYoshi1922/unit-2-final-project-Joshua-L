import { createContext, useState, useContext, useEffect } from "react";

const PokemonContext = createContext();

export const usePokemonContext = () => useContext(PokemonContext);

export const PokemonProvider = ({ children }) => {
  //provide state to any of the components that are wrapped around it
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    const storedFavs = localStorage.getItem("favorites");

    if (storedFavs) setFavorites(JSON.parse(storedFavs));
  }, []);

  useEffect(() => {
    const storedFavs = localStorage.getItem("favorites");

    if (storedFavs) setFavorites(JSON.parse(storedFavs));
  }, []);

  const addToFavorites = (pokemon) => {
    setFavorites((prev) => [...prev, pokemon]);
  };

  const removeFromFavorites = (pokemonId) => {
    setFavorites((prev) => prev.filter((pokemon) => pokemon.id !== pokemonId));
  };

  const isFavorite = (pokemonId) => {
    return favorites.some((pokemon) => pokemon.id === pokemonId);
  };

  const value = {
    favorites,
    addToFavorites,
    removeFromFavorites,
    isFavorite,
  };

  return (
    <PokemonContext.Provider value={value}>{children}</PokemonContext.Provider>
  );
};
