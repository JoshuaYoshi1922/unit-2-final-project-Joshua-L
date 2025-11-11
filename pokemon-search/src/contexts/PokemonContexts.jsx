import { createContext, useState, useContext, useEffect, useCallback } from "react";
import { useAuth } from "./AuthContext";

const PokemonContext = createContext();
export const usePokemonContext = () => useContext(PokemonContext);

const BACKEND = "http://localhost:8080";

export const PokemonProvider = ({ children }) => {
  const { user, isAuthenticated } = useAuth();
  const [favorites, setFavorites] = useState([]);
  const [loadingFavorites, setLoadingFavorites] = useState(false);
  const [favoritesError, setFavoritesError] = useState(null);

    async function fetchFavorites() {
    if (!isAuthenticated || !user?.id) {
      setFavorites([]);
      return;
    }
    setLoadingFavorites(true);
    setFavoritesError(null);
    try {
      const res = await fetch(`${BACKEND}/api/favorites/user/${user.id}`);
      if (!res.ok) throw new Error(`Favorites fetch failed (${res.status})`);
      const data = await res.json();

      const mapped = (Array.isArray(data) ? data : [])
        .map((fav) => {
          const id = fav?.pokemon?.id ?? fav?.pokemonId;
          if (!id) return null; 
          const name = fav?.pokemon?.name || `Pokemon #${id}`;
          return {
            id,
            name,
            sprites: {
              front_default: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png`,
              front_shiny: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${id}.png`,
            },
            comment: fav?.comment || "",
          };
        })
        .filter(Boolean);

      setFavorites(mapped);
    } catch (e) {
      console.error("Favorites load error", e);
      setFavoritesError(e.message);
      setFavorites([]);
    } finally {
      setLoadingFavorites(false);
    }
  }

  useEffect(() => {
    if (isAuthenticated && user?.id) fetchFavorites();
  }, [isAuthenticated, user?.id]);

  async function addToFavorites(pokemon) {
    if (!isAuthenticated || !user?.id || !pokemon?.id) return;
    try {
      const res = await fetch(
        `${BACKEND}/api/favorites/user/${user.id}/pokemon/${pokemon.id}`,
        { method: "POST" }
      );
      if (res.status === 409) return; // already favorited
      if (!res.ok) throw new Error(`Add favorite failed (${res.status})`);
      const dto = await res.json();
      const id = dto?.pokemon?.id ?? dto?.pokemonId ?? pokemon.id;
      const name = dto?.pokemon?.name || pokemon.name || `Pokemon #${id}`;
      const card = {
        id,
        name,
        sprites: {
          front_default: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png`,
          front_shiny: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${id}.png`,
        },
        comment: dto?.comment || "",
      };
      setFavorites((prev) => (prev.some((f) => f.id === id) ? prev : [...prev, card]));
    } catch (e) {
      console.error("Add favorite error", e);
    }
  }

  async function removeFromFavorites(id) {
    if (!isAuthenticated || !user?.id) return;
    try {
      const res = await fetch(
        `${BACKEND}/api/favorites/user/${user.id}/pokemon/${id}`,
        { method: "DELETE" }
      );
      if (!res.ok && res.status !== 204) throw new Error(`Remove failed (${res.status})`);
      setFavorites((prev) => prev.filter((f) => f.id !== id));
    } catch (e) {
      console.error("Remove favorite error", e);
    }
  }

  async function updateComment(id, comment) {
    if (!isAuthenticated || !user?.id) return;
    try {
      const res = await fetch(
        `${BACKEND}/api/favorites/user/${user.id}/pokemon/${id}/comment`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ comment }),
        }
      );
      if (!res.ok) throw new Error(`Comment update failed (${res.status})`);
      let dto = null;
      try {
        dto = await res.json();
      } catch {}
      setFavorites((prev) =>
        prev.map((f) => (f.id === id ? { ...f, comment: dto?.comment ?? comment } : f))
      );
    } catch (e) {
      console.error("Update comment error", e);
    }
  }

  const addComment = updateComment;

  async function deleteComment(id) {
    if (!isAuthenticated || !user?.id) return;
    try {
      const res = await fetch(
        `${BACKEND}/api/favorites/user/${user.id}/pokemon/${id}/comment`,
        { method: "DELETE" }
      );
      if (!res.ok && res.status !== 204) throw new Error(`Delete comment failed (${res.status})`);
      setFavorites((prev) => prev.map((f) => (f.id === id ? { ...f, comment: "" } : f)));
    } catch (e) {
      console.error("Delete comment error", e);
    }
  }

  function isFavorite(id) {
    return favorites.some((f) => f.id === id);
  }

  return (
    <PokemonContext.Provider
      value={{
        favorites,
        loadingFavorites,
        favoritesError,
        fetchFavorites,
        addToFavorites,
        removeFromFavorites,
        isFavorite,
        addComment,
        updateComment,
        deleteComment,
      }}
    >
      {children}
    </PokemonContext.Provider>
  );
};

