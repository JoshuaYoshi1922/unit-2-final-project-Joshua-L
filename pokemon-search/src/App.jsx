import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { useState } from "react";
import About from "./components/About";
import Footer from "./components/Footer";
import Header from "./components/header";
import Home from "./components/HomePage";
import FavPokemon from "./components/FavPokemon";
import { PokemonProvider } from "./contexts/PokemonContexts";
import MorePokeInfo from "./components/MorePokeInfo";

function App() {
  return (
    <>
      <div>
        <PokemonProvider>
          <main className="main">
            <Router>
              <Header />

              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/favpokemon" element={<FavPokemon />} />
                <Route path="/pokemon/:id" element={<MorePokeInfo />} />
                <Route path="/about" element={<About />} />
              </Routes>
              <Footer />
            </Router>
          </main>
        </PokemonProvider>
      </div>
    </>
  );
}

export default App;
