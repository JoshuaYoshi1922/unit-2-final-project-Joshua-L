import { Link } from "react-router-dom";

function Header() {
  return (
    <>
      <header className="header">
        POKEMON ORANGE
        <nav className="navbar">
          <div className="navbar-brand">
            <Link to="/">Pokemon App </Link>
          </div>
          <div className="navbar-links">
            <Link to="/" className="nav-link">
              Home
            </Link>
            <Link to="/favpokemon" className="nav-link">
              Favorites
            </Link>
            <Link to="/about" className="nav-link">
              About
            </Link>
          </div>
        </nav>
      </header>
    </>
  );
}

export default Header;
