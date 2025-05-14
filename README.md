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
docker-compose -f /docker/docker-compose.yml up -d
```

Import database
```bash
docker exec -i mysql_pokemon mysql -u root < database/dump.sql
```