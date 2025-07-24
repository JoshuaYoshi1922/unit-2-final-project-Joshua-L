import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import About from "./components/About";
import Footer from "./components/Footer";
import Header from "./components/Header";
import Home from "./components/HomePage";
import FavPokemon from "./components/FavPokemon";
import { PokemonProvider } from "./contexts/PokemonContexts";
import "./App.css";
import NotFound from "./components/NotFound";

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
                <Route path="/about" element={<About />} />
                <Route path="*" element={<NotFound />} />
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
