package serverSide;

import shared.rosters.PokemonRoster;

public class Server {
    public static void main(String[] args){
        String dbName = "pokemon_game";
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/pokemon_game";
        DatabaseMySQL db = new DatabaseMySQL(dbName, user, password, url);
        PokemonRoster pokemonRoster = db.retrivePokemonRoster();
        System.out.println(pokemonRoster.list);
    }
}
