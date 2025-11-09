import { useEffect, useState } from "react";
import { useAuth } from "../contexts/AuthContext";

function TeamName() {
  const { user, isAuthenticated, updateProfile } = useAuth();
  const [pokeTeam, setPokeTeam] = useState("Name");
  const [editing, setEditing] = useState(false);

  // Sync from context when user loads or changes
  useEffect(() => {
    if (user?.teamName) {
      setPokeTeam(user.teamName);
    } else {
      setPokeTeam("Name");
    }
  }, [user]);

  const inputName = (event) => {
    setPokeTeam(event.target.value);
  };

  const openForm = () => {
    setEditing(true);
  };

  const closeForm = async (event) => {
    event.preventDefault();
    // Persist to backend if logged in and changed
    if (isAuthenticated && user?.id) {
      const trimmed = (pokeTeam || "").trim();
      if (trimmed.length > 0 && trimmed !== user.teamName) {
        await updateProfile(user.id, { teamName: trimmed });
      }
    }
    setEditing(false);
  };

  return (
    <div className="pokemon-team">
      <div className="team-name">
        <button className="edtBtn" onClick={openForm} disabled={editing || !isAuthenticated} title={!isAuthenticated ? "Login to edit team name" : undefined}>
          Edit
        </button>
        <h3>Team {pokeTeam}</h3>
      </div>
      {editing && (
        <form className="teamform">
          <input value={pokeTeam} onInput={inputName} />
          <button className="finishBtn" onClick={closeForm}>
            Finished
          </button>
        </form>
      )}
    </div>
  );
}

export default TeamName;
