import { useState } from "react";

function TeamName() {
  const [pokeTeam, setPokeTeam] = useState("Name");
  const [editing, setEditing] = useState(false);

  const inputName = (event) => {
    setPokeTeam(event.target.value);
  };

  const openForm = () => {
    setEditing(true);
  };

  const closeForm = (event) => {
    event.preventDefault();
    setEditing(false);
  };

  return (
    <div className="pokemon-team">
      <div className="team-name">
        <button className="edtBtn" onClick={openForm} disabled={editing}>
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
