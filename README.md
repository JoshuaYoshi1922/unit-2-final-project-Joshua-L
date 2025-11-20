# Pokemon Orange: Find Your Favorite Pokemon (Full-Stack Web Application)

<div align="center">
  <h2>Browse and save your favorite Pokemon from all nine regions.</h2>
  <br />
  <img src="https://img.shields.io/badge/React-61DBFB?style=for-the-badge&logo=react&logoColor=20232A" />
  <img src="https://img.shields.io/badge/JavaScript-F0DB4F?style=for-the-badge&logo=javascript&logoColor=323330" />
  <img src="https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-00758F?style=for-the-badge" />
</div>

<div align="center">
  <a href="#about">About</a> • 
  <a href="#features">Features</a> • 
  <a href="#snapshots">Snapshots</a> • 
  <a href="#tech">Tech Stack</a> • 
  <a href="#installation">Installation</a> • 
  <a href="#database">Database</a> • 
  <a href="#api">API</a> • 
  <a href="#future">Future Features</a> • 
  <a href="#author">Author</a>
</div>

---

## 💡 About the Project <a name="about"></a>

This project is dedicated to my two boys, Zayden and Zaxton, who are
            ages 7 and 5 respectively. You could say they are in their "Pokémon
            Era." Whether it is watching the TV series, collecting Pokémon
            cards, or playing the main series video games, Pokémon is everything
            and everywhere.

- When entering the page, users are greeted with a list of the first 151 Pokemon in a Pokemon style card. The card displays the Pokemon's image, pokedex number and some basic information about the Pokmeon like Height, Weight, Type and Moves.
- Shiny toggle!
- Users are able to search for their favorite Pokemon by either the name or by the pokedex number of the Pokemon.
- When a user creates an account, they are able to save and view their favorite Pokemon.
- Once a Pokemon is marked a favorite, the user is then able to create a comment about why the Pokemon is a favorite, edit and/or delete thecomment.

Built with a **React + Vite frontend**, a **Java Spring Boot backend**, and a **MySQL database**, Pokemon Orange is a website for every Pokemon fan.

---

---

## 🎨 Features <a name="features"></a>

###  Create An Accout
- Sign up with email.
- Create and edit a Team Name.
- Save favorite Pokemon.
- Make comments on your favorite Pokemon.

### Pokemon Display
- Pokemon from the Kanto region are on the main display.
- Each Pokemon is displayed in their own Pokemon style card.
- Pokedex number, Name, Height, Weight, Type/s, and Moves.
- Shiny toggle to view the Pokemon's shiny variant!


---

<a name="snapshots"></a>
##  Snapshots

### HomePage

<details open>
    <summary>HomePage</summary>
    
</details>

### Favorites

<summary>Favorites</summary>
   
</details>

### Shiny!

<summary>Shiny!</summary>
    
</details>

---

## 🛠️ Tech Stack <a name="tech"></a>

### Frontend
| Technology | Description |
|------------|-------------|
| React      | JavaScript library for building user interfaces (UIs) or UI components |
| JavaScript | Interpreted programming language primarily used to make websites interactive and dynamic |
| Vite       | Next-generation frontend build tool and development server |
| CSS        | Stylesheet language used to describe the presentation of a document written in a markup language |

### Backend & Database
| Technology   | Description |
|--------------|-------------|
| Java         | The language of the backend |
| Spring Boot  | Extension of the Spring Framework in Java that makes it easy to create standalone, production-grade applications with minimal effort |
| Hibernate/JPA| The interaction with a database |
| Maven        | Dependency & build management |
| MySQL        | Relational database to store users, favorited pokemon, and comments |

---

## 🚀 Installation & Setup <a name="installation"></a>

To run **Pokemon Orange** locally, you need:

* **Node.js** (LTS)
* **Java 21+**
* **MySQL 8+**
* **Maven**

---

### Back End Setup (Java/Spring Boot/MySQL)

1.  **Clone the repository:** In the terminal, navigate to the directory where you want the project to live, then execute the following commands:
    ```bash
    git clone https://github.com/JoshuaYoshi1922/unit-2-final-project-Joshua-L.git
    cd unit-2-final-project-Joshua-L/pokemon-search-backend
    ```

2.  **Configure database:** Create a new MySQL database named `pokemonsearch_db`, then create a .env file in the main folder root `pokemon-search-backend\.env` file with your specific MySQL credentials: 
    ```.env
    DB_HOST=localhost
    DB_PORT=3306
    DB_NAME=pokemonsearch_db
    DB_USER={your username}
    DB_PASS={your password}
    ```

3.  **Run the Java/Spring Boot application:** 
    ```bash
    mvn spring-boot:run
    ```

 The API should now be running on `http://localhost:8080`.

>

---

### Front End Setup (React/Vite)

1.  **Navigate to the front end project directory:** 
    ```bash
    cd ../pokemon-search
    ```

2.  **Install dependencies:** 
    ```bash
    npm install
    ```

3.  **Run the React/Vite application:** 
    ```bash
    npm run dev
    ```

 The frontend application will start and can be found in a browser `http://localhost:5173`.

---

<a name="database"></a>
## 🗄️ Database Structure (ERD)

This project utilizes a MySQL database structured around four core entities, managed by Hibernate with the following relationships:

1.  **User** ↔️ **FavoritePokemon**: One-to-Many
2.  **FavoritePokemon** ↔️ **User**: Many-to-One

### Entity Relationship Diagram (ERD)
  
<em>Click on image to view in Figma.</em><br />
<a href="https://www.figma.com/board/dmii5oSakRYDUhwd8dQaEt/Untitled?node-id=0-1&t=1q2RtRHt7A9SYOk9-1"><img src="preview/erd.png" alt="Entity Relationship Diagram" /></a>
</details>

### USERS
| Field        | Type   | Description                 |
|--------------|--------|-----------------------------|
| user_id      | int    | Primary Key                 |
| username     | String | User’s chosen display name  |
| email        | String | User email                  |
| teamName     | String | User's Name of Team
| passwordHash | String | Hashed password          |

### FAVORITE POKEMON
| Field      | Type          | Description                 |
|------------|---------------|-----------------------------|
| id         | int           | Primary Key                 |
| user_id    | int           | FK → USERS                  |
| pokemon_id | int           | Pokedex number            |
| comment    | String        | Comment for specific Pokemon from User |

### POKEMON
| Field      | Type   | Description                 |
|------------|--------|-----------------------------|
| id         | int    | Primary Key/ Pokedex number |
| name       | String | Name of Pokemon               |
| height     | int    | Height of Pokemon  |
| weight     | int    | Weight of Pokemon          |


---

## ⚙️ API Endpoints <a name="api"></a>

<details>
<summary>Click to expand API endpoints</summary>

###  Users
| Method | Endpoint       | Description        |
|--------|----------------|------------------|
| GET    | /api/users     | Get all users     |
| GET    | /api/users/{id}| Get a user by ID  |
| POST   | /api/users/register     | Create a new user |
| POST   | /api/users/login    | User login|
| PUT    | /api/users/{id}| Update a user     |
| DELETE | /api/users/{id}| Delete a user     |

###  UserFavPokemon
| Method | Endpoint                     | Description            |
|--------|-------------------------------|------------------------|
| GET    | /api/favorites/user/{userId}  | Get all user favorite pokemon  |
| POST    | /api/favorites/user/{userId}/pokemon/{pokemonId}/comment      | Post comment on favorite pokemon    |
| PUT    | /api/favorites/user/{userId}/pokemon/{pokemonId}  | Update comment about favorite pokemon    |
| DELETE   | /api/favorites/user/{userId}/pokemon/{pokemonId}    | Delete comment    |
| POST    | /api/favorites/user/{userId}/pokemon/{pokemonId}  | Add Pokemon to favorites   |
| PUT | /api/favorites/user/{userId}/pokemon/{pokemonId} | Update favorite Pokemons |
| DELETE | /api/favorites/user/{userId}/pokemon/{pokemonId}            | Delete Pokemon from favorite Pokemons      |

### Pokemon
| Method | Endpoint                   | Description             |
|--------|-----------------------------|-------------------------|
| GET    | /api/pokemon/                | Get 1-151 Pokemons         |
| GET    | /api/pokemon/{nameOrId}            | Search for specific pokemon by Name or Id number|


</details>

---

##  Future Plans <a name="future"></a>
- Create a button to hear audio of what each Pokemon sounds like in the game
- Drop down menu to view the Pokemon from each region for easier navigation
- Have a Pokemon be your buddy and greet you when logging in
- Have a details button to generate an AI description of a Pokemon


---

##  Designer & Author <a name="author"></a>
Joshua Lopez – Creator of Pokemon Orange

- GitHub: [@JoshuaYoshi1922](https://github.com/JoshuaYoshi1922)  
- LinkedIn: [Joshua Lopez](https://www.linkedin.com/in/joshua-l-649530379)




