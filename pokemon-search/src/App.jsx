import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import About from "./components/About";
import Footer from "./components/Footer";
import Header from "./components/Header";
import Home from "./components/HomePage";
import FavPokemon from "./components/FavPokemon";
import Login from "./components/Login";
import Register from "./components/Register";
import Profile from "./components/Profile";
import { PokemonProvider } from "./contexts/PokemonContexts";
import { AuthProvider } from "./contexts/AuthContext";
import "./App.css";
import NotFound from "./components/NotFound";


function App() {
  return (
    <>
      <div>
        <AuthProvider>
          <PokemonProvider>
            <main className="main">
              <Router>
                <Header />
                <Routes>
                  <Route path="/" element={<Home />} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/register" element={<Register />} />
                  <Route path="/profile" element={<Profile />} />
                  <Route path="/favpokemon" element={<FavPokemon />} />
                  <Route path="/about" element={<About />} />
                  <Route path="*" element={<NotFound />} />
                </Routes>
                <Footer />
              </Router>
            </main>
          </PokemonProvider>
        </AuthProvider>
      </div>
    </>
  );
}

export default App;
