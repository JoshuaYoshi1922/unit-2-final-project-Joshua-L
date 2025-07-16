

import Header from './components/header'
import PokemonDisplay from './components/PokemonDisplay'

function App() {


  return (
    <>
      <Header />
      <PokemonDisplay pokemon={{name: "Snorlax", type: "Normal"}} />
    </>
  )
}

export default App
