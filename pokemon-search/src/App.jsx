import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Footer from "./components/Footer";
import Header from "./components/header";
import Home from "./components/HomePage";
import PokemonDisplay from "./components/PokemonDisplay";
import FavPokemon from "./components/FavPokemon";

function App() {
  return (
    <>
      <main className="main">
        <Header />
        <Router>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/favpokemon" element={<FavPokemon />} />
          </Routes>
        </Router>
        <Footer />
      </main>
    </>
  );
}

export default App;
