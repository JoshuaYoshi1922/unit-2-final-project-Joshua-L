import { Link } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import TeamName from "./TeamName";

function Header() {
  const { user, isAuthenticated, logout } = useAuth();

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
          
          {isAuthenticated ? (
            <>
              <li className="nav-link">
                <Link to="/profile">Profile</Link>
              </li>
              <li className="nav-link" style={{ color: "#4a90e2" }}>
                Welcome, {user?.username}!
              </li>
              <li className="nav-link">
                <button onClick={logout} style={{ cursor: "pointer", background: "none", border: "none", color: "inherit", fontSize: "inherit" }}>
                  Logout
                </button>
              </li>
            </>
          ) : (
            <>
              <li className="nav-link">
                <Link to="/login">Login</Link>
              </li>
              <li className="nav-link">
                <Link to="/register">Register</Link>
              </li>
            </>
          )}
        </ul>
      </header>
    </>
  );
}

export default Header;
