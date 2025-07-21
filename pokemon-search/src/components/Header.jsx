import { Link } from "react-router-dom";

function Header() {
  return (
    <>
      <header className="header">
        POKEMON ORANGE
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
