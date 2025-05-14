package serverSide.database;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import shared.data.GameData;
import shared.pokemon.Pokemon;
import shared.pokemon.Move;
import shared.pokemon.MoveDamage;
import shared.pokemon.MoveStatus;
import shared.pokemon.Statistic;

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

    public ArrayList<Pokemon> retrivePokemons(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String query = "SELECT id, name, hp, attack, defense, speed FROM pokemons;";
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
                pokemons.add(pokemon);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return pokemons;
    }

    public ArrayList<Move> retriveMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        String query = "SELECT id, name, accuracy, move_type, power, statistic, percentage, self FROM moves;";
        ResultSet resultSet = retriveResultSet(query);

        try{
            while(resultSet.next()){
                Move move = null;
                //int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double accuracy = resultSet.getDouble("accuracy");
                String move_type = resultSet.getString("move_type");
                if(move_type.compareToIgnoreCase("damage") == 0){
                    double power = resultSet.getDouble("power");
                    move = new MoveDamage(name, accuracy, power);
                }else
                if(move_type.compareToIgnoreCase("status") == 0){
                    Statistic statistic = Statistic.valueOf(resultSet.getString("statistic"));
                    double percentage = resultSet.getDouble("percentage");
                    boolean self = resultSet.getBoolean("self");
                    move = new MoveStatus(name, accuracy, statistic, percentage, self);
                }
                moves.add(move);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return moves;
    }

    public GameData retriveGameData(){
        GameData gameData = new GameData();

        gameData.addPokemons(this.retrivePokemons());
        gameData.addMoves(this.retriveMoves());

        return gameData;
    }
}
