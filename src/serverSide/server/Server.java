package serverSide.server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

import serverSide.database.DatabaseMySQL;
import shared.data.GameData;

public class Server {
    public static void main(String[] args){
        String dbName = "pokemon_game";
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/pokemon_game";
        DatabaseMySQL db = new DatabaseMySQL(dbName, user, password, url);
        
        GameData gameData = db.retriveGameData();
        System.out.println("Pokemons retrived:");
        gameData.printPokemons();
        System.out.println("Moves retrived");
        gameData.printMoves();

        int port = 12345;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Listening on port " + port);
            while(true){
                Socket client = serverSocket.accept();
                System.out.println("New client connected");

                // Start a new thread to handle the client
                new ClientManager(client, gameData).start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
 