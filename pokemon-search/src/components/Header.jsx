import { Link } from "react-router-dom";

import TeamName from "./TeamName";

function Header() {


  return (
    <>
      <header className="header">

        <div className="pokemon-team">
          <TeamName />
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
