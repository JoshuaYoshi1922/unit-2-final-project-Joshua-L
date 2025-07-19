import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import About from "./components/About";
import Footer from "./components/Footer";
import Header from "./components/header";
import Home from "./components/HomePage";
import PokemonDisplay from "./components/PokemonDisplay";
import FavPokemon from "./components/FavPokemon";
import Pagination from "./components/pagination";

function App() {
  return (
    <>
      <div>
        <main className="main">
          <Router>
            <Header />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/favpokemon" element={<FavPokemon />} />
              <Route path="/about" element={<About />} />
            </Routes>
          </Router>

          <Footer />
        </main>
      </div>
    </>
  );
}

export default App;
