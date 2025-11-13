import PokemonQuestion from "./PokemonQuestion";
import { FaGithub, FaLinkedin } from "react-icons/fa"
function Footer() {
  return (
    <>
      <div>
        <footer className="footer">
          <p>&copy;2025 ZBLcustoms</p>
          <a
            href="https://github.com/JoshuaYoshi1922/unit-2-final-project-Joshua-L"
            target="_blank"
            rel="noopener noreferrer"
          >
            <FaGithub style={{ marginRight: 6 }} /> GitHub Repo
          </a>
          <div className="question">
            <PokemonQuestion />
          </div>
          <a
            href="https://linkedin.com/in/joshua-l-649530379"
            target="_blank"
            rel="noopener noreferrer"
          >
            <FaLinkedin style={{ marginRight: 6 }} /> LinkedIn
          </a>
          <p>Kansas City, MO</p>
        </footer>
      </div>
    </>
  );
}

export default Footer;
