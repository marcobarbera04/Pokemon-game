package serverSide;

import shared.rosters.PokemonRoster;

public abstract class Database{
    private final String dbName;
    private final String user;
    private final String password;

    public Database(String dbName, String user, String password){
        if(dbName == null || user == null || password == null){
            throw new RuntimeException("[ERROR]: Database name, user, password invalid");
        }
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }

    public String getDbName(){
        return dbName;
    }
    public String getUser(){
        return user;
    }
    public String getPassword(){
        return password;
    }

    /**
     * This abstract method should retrive all pokemons from the specified database
     * @return      the pokemon roster
     */
    public abstract PokemonRoster retrivePokemonRoster();
}