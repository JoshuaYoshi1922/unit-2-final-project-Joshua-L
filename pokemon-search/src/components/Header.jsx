import { Link } from "react-router-dom";
import { useState } from "react";
import PokemonQuestion from "./PokemonQuestion";

function Header() {
  const [pokeTeam, setPokeTeam] = useState("Team Name");
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
    <>
      <header className="header">
        <div className="question">
          <PokemonQuestion />
        </div>
        <div className="pokemon-team">
          <div className="team-name">
            <h4>{pokeTeam}</h4>
            <button onClick={openForm} disabled={editing}>
              Edit
            </button>
          </div>
          {editing && (
            <form>
              <input value={pokeTeam} onInput={inputName} />
              <button onClick={closeForm}>Finished</button>
            </form>
          )}
        </div>

        <h1 className="pokemon-orange">
          <Link to="/" style={{ textDecoration: "none", color: "orange" }}>
            POKEMON ORANGE
          </Link>
        </h1>

        <ul className="navbar">
          <li className="nav-link">
            <Link to="/">Home</Link>
          </li>
          <li className="nav-link">
            <Link to="/favpokemon">Favorites</Link>
          </li>
          <li className="nav-link">
            <Link to="/about">About</Link>
          </li>
        </ul>
      </header>
    </>
  );
}

export default Header;
