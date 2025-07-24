import aboutpage from "../images/aboutpage.jpg";
import "../CSS/aboutpage.css";
import { Link } from "react-router";

function About() {
  return (
    <>
      <div className="about">
        <div>
          <h1>About Page</h1>
          <p>
            Hello!! <br />
            <br />
            Welcome to my web application!! <br />
            <br />
            This page is dedicated to my two boys, Zayden and Zaxton, who are
            ages 7 and 5 respectively. You could say they are in their "Pokémon
            Era." Whether it is watching the TV series, collecting Pokémon
            cards, or playing the main series video games, Pokémon is everything
            and everywhere. This reminds me of myself when I was younger. I can
            recall the times when I asked my mom to bring me to the mall when
            Pokémon battle tournaments were being held to play against other
            people via battle link cables on the GameBoy. Oh, the good ol' days.
            <br />
            <br />
            When I first learned of this project, Pokémon came to mind. The
            context of the page came about when my sons frequently asked me
            about various Pokémon's and their unique characteristics or
            features, such as their types, heights, weights and what their shiny
            variant looks like.
            <br />
            <br />
            In the future I hope to add additional features such as adding more
            information about the Pokémon like their habitats in the main series
            games, more details about the Pokémon when you click into a certain
            Pokémon and its unique "cries", which is basically what they sound
            like in the game, filtering each Pokémon region and much much more.
            <br />
            <br />
            I hope you enjoy the web page as much as I did creating it.
            <br />
            <br />
            Thank you for visiting!
            <br />
            <br />
          </p>
        </div>

        <div className="aboutpage-img">
          <img src={aboutpage} alt={"Ash Ketchum and Pokemon"} />
        </div>
        <Link to="/">Back to Home</Link>
      </div>
    </>
  );
}

export default About;
