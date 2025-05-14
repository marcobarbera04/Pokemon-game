## Pokémon Terminal Game

A Pokémon-themed terminal-based game written in Java, with Pokémon and move data managed via a MySQL database running in a Docker container.

## Installation

Clone the repository

```bash
git clone https://github.com/marcobarbera04/Pokemon-game.git
cd Pokemon-game
```

Start the docker environment
```bash
cd docker
docker-compose -f docker-compose.yml up -d
```

Import database (you have to import using Git bash)

Move to the repository
```bash
cd path/to/Pokemon-game
```

Create the database
```bash
docker exec -i mysql_pokemon mysql -u root -e "CREATE DATABASE pokemon_game;"
```

Check if the database was created successfully
```bash
docker exec -it mysql_pokemon mysql -u root -e "SHOW DATABASES;"
```

Import the dump
```bash
docker exec -i mysql_pokemon mysql -u root pokemon_game < database/dump.sql
```