package serverSide;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import shared.pokemon.Pokemon;
import shared.pokemon.Move;
import shared.rosters.PokemonRoster;

public class DatabaseMySQL extends Database{
    private final String url;
    private final Connection connection;

    public DatabaseMySQL(String dbName, String user, String password, String url){
        super(dbName, user, password);

        if(url == null){
            throw new RuntimeException("[ERROR]: url invalid");
        }
        this.url = url;

        // Load JDBC driver and establish connection
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        this.connection = connection;
    }
    
    public String getUrl(){
        return url;
    }
    public Connection getConnection(){
        return connection;
    }
    
    public ResultSet retriveResultSet(String query){
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try{
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public PokemonRoster retrivePokemonRoster(){
        PokemonRoster pokemonRoster = new PokemonRoster();
        
        String query = "SELECT * FROM pokemons;";
        ResultSet resultSet = retriveResultSet(query);
        
        try{
            while(resultSet.next()){
                //int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double hp = resultSet.getDouble("hp");
                double attack = resultSet.getDouble("attack");
                double defense = resultSet.getDouble("defense");
                double speed = resultSet.getDouble("speed");

                ArrayList<Move> moves = new ArrayList<>();
                Pokemon pokemon = new Pokemon(name, hp, attack, defense, speed, moves);
                pokemonRoster.list.add(pokemon);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return pokemonRoster;
    }
}
