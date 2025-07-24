import { useState } from "react";


function pokemonQuestion() {
    const [answer, setAnswer] = useState(null);

    const clickYes = () => {
        setAnswer('yes');
    };

    const clickNo = () => {
        setAnswer('no');
    };

    return (
        <div className="pokemon-question">
            <h2>Do you like Pokemon?</h2>
            <button className="yes" onClick={clickYes}>yes</button>
            <button className="no" onClick={clickNo}>no</button>

            {answer === 'yes' && <p> 😁 WooHoo! </p>}
            {answer === 'no' && <p> 😬 You're No Fun </p>}

        </div>
    )
}

export default pokemonQuestion