import { createContext, useState, useContext, useEffect} from "react";
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
    if (!isAuthenticated || !user.id) {
      setFavorites([]);
      return;
    }
    setLoadingFavorites(true);
    setFavoritesError(null);
    try {
      const res = await fetch(`${BACKEND}/api/favorites/user/${user.id}`);
      if (!res.ok) throw new Error(`Favorites fetch failed (${res.status})`);
      const data = await res.json();

      const mapped = await Promise.all(data.map(async (fav) => {
      const id = fav.pokemonId;
      if (!id) return null;

      let details = fav.pokemon;
      if (!details) {
        try {
          const pokeRes = await fetch(`https://pokeapi.co/api/v2/pokemon/${id}`);
          if (pokeRes.ok) {
            const pokeData = await pokeRes.json();
            details = {
              id: pokeData.id,
              name: pokeData.name,
              types: pokeData.types.map(t => t.type.name),
              height: pokeData.height,
              weight: pokeData.weight,
              moves: pokeData.moves.slice(0, 2).map(m => m.move.name),
            };
          }
        } catch (e) {
          console.warn(`Failed to fetch Pokémon ${id}`, e);
        }
      }

      const name = details.name || `Pokemon #${id}`;
      const types = details.types.join(", ");
      const moves = details.moves.join(", ");

      return {
        id,
        name,
        type: types,
        height: details.height,
        weight: details.weight,
        moves,
        sprites: {
          front_default: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png`,
          front_shiny: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${id}.png`,
        },
        comment: fav.comment,
      };
    })
  );

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

  // Clears favorites when user logs out
  useEffect(() => {
  if (!isAuthenticated) {
    setFavorites([]);
    setFavoritesError(null);
    setLoadingFavorites(false);
  }
}, [isAuthenticated]);

  async function addToFavorites(pokemon) {
  if (!isAuthenticated || !user.id || !pokemon.id) return;
  try {
    const res = await fetch(
      `${BACKEND}/api/favorites/user/${user.id}/pokemon/${pokemon.id}`,
      { method: "POST" }
    );
    if (res.status === 409) return;
    if (!res.ok) throw new Error(`Add favorite failed (${res.status})`);
    const dto = await res.json();

    
    const p = pokemon;
    const id = pokemon.id;
    const name = pokemon.name || `Pokemon #${id}`;
    
    
    const types =  pokemon.type || "";
    const moves = pokemon.moves || "";

    const card = {
      id,
      name,
      type: types,
      height: p.height,
      weight: p.weight,
      moves,
      sprites: pokemon.sprites || {
        front_default: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png`,
        front_shiny: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${id}.png`,
      },
      comment: dto.comment || "",
    };
    
    setFavorites((prev) => (prev.some((f) => f.id === id) ? prev : [...prev, card]));
  } catch (e) {
    console.error("Add favorite error", e);
  }
}


  async function removeFromFavorites(id) {
    if (!isAuthenticated || !user.id) return;
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
    if (!isAuthenticated || !user.id) return;
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
      
      setFavorites((prev) =>
        prev.map((f) => (f.id === id ? { ...f, comment: comment } : f))
      );
    } catch (e) {
      console.error("Update comment error", e);
    }
  }

  const addComment = updateComment;

  async function deleteComment(id) {
    if (!isAuthenticated || !user.id) return;
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

