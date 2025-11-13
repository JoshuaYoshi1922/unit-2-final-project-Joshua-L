import { useState } from "react";
import { usePokemonContext } from "../contexts/PokemonContexts";
import { capitalize } from "../shared/utils.js";
import "../css/PokemonDisplay.css";

const IMAGE_URL =
  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

function FavoriteCard({ pokemon }) {
  const { removeFromFavorites, addComment, updateComment, deleteComment } = usePokemonContext();
  const [isShiny, setIsShiny] = useState(false);
  const [isEditingComment, setIsEditingComment] = useState(false);
  const [commentText, setCommentText] = useState(pokemon.comment || "");

  const handleRemove = (e) => {
    e.preventDefault();
    removeFromFavorites(pokemon.id);
  };

  const imgSprite = isShiny
    ? pokemon.sprites.front_shiny || `${IMAGE_URL}shiny/${pokemon.id}.png`
    : pokemon.sprites.front_default || `${IMAGE_URL}${pokemon.id}.png`;

  const handleSaveComment = async () => {
    const trimmed = commentText.trim();
    if (!trimmed && !pokemon.comment) {
      setIsEditingComment(false);
      return;
    }
    if (trimmed) {
      if (pokemon.comment) {
        await updateComment(pokemon.id, trimmed);
      } else {
        await addComment(pokemon.id, trimmed);
      }
    }
    setIsEditingComment(false);
  };

  const handleDeleteComment = async () => {
    await deleteComment(pokemon.id);
    setCommentText("");
    setIsEditingComment(false);
  };

  const handleCancelEdit = () => {
    setCommentText(pokemon.comment || "");
    setIsEditingComment(false);
  };

  return (
    <div className="pokemon-card">
      <h2 className="pokenum">#{pokemon.id}</h2>
  <h3>{capitalize(pokemon?.name)}</h3>
      <div className="pokemon-image">
        <img src={imgSprite} alt={pokemon.name} />
        <div className="pokemon-overlay">
          <button className="favorite-btn active" onClick={handleRemove} title="Remove from favorites">
            ♥
          </button>
        </div>
      </div>

      <div className="pokemon-info">
        <ul className="poke-cardinfo">
          <li className="card-item">Height: {pokemon.height}m</li>
          <li className="card-item">Weight: {pokemon.weight}kg</li>
          <li className="card-item">Moves: {capitalize(pokemon?.moves)}</li>
          <li className="card-item">Type: {capitalize(pokemon?.type)}</li>
        </ul>
      </div>

      <div className="shinyT">
        Shiny Toggle<br /><br />
        <button
          className={`shiny-btn ${isShiny ? "isShiny" : ""}`}
          onClick={() => setIsShiny(!isShiny)}
        >
          <div className="thumb"></div>
        </button>
      </div>

      <div className="comment-section" style={{ marginTop: "10px", padding: "10px", borderTop: "1px solid #ccc" }}>
        {!isEditingComment ? (
          <>
            {pokemon.comment ? (
              <>
                <p style={{ fontStyle: "italic", marginBottom: "8px" }}>"{pokemon.comment}"</p>
                <button onClick={() => { setIsEditingComment(true); setCommentText(pokemon.comment); }}>
                  Edit Comment
                </button>
                <button onClick={handleDeleteComment} style={{ marginLeft: "8px" }}>
                  Delete Comment
                </button>
              </>
            ) : (
              <button onClick={() => setIsEditingComment(true)}>Add Comment</button>
            )}
          </>
        ) : (
          <div>
            <textarea
              value={commentText}
              onChange={(e) => setCommentText(e.target.value)}
              placeholder="Write your comment..."
              rows="3"
              style={{ width: "100%", marginBottom: "8px" }}
            />
            <button onClick={handleSaveComment}>Save</button>
            <button onClick={handleCancelEdit} style={{ marginLeft: "8px" }}>
              Cancel
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default FavoriteCard;
