package serverSide.server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

import serverSide.database.DatabaseMySQL;
import shared.data.GameData;
import shared.xmlParser.XMLParser;

public class Server {
    public static void main(String[] args){
        // Load configuration for database connection
        XMLParser parser = new XMLParser("src/serverSide/server/config.xml");
        String dbName = parser.getValue("database", "dbName");
        String user = parser.getValue("database", "user");
        String password = parser.getValue("database", "password");
        String addres = parser.getValue("database", "address");
        String port = parser.getValue("database", "port");
        String url = "jdbc:mysql://" + addres + ":" + port + "/" + dbName;
        DatabaseMySQL db = new DatabaseMySQL(dbName, user, password, url);
        
        GameData gameData = db.retriveGameData();
        System.out.println("Pokemons retrived:");
        gameData.printPokemons();
        System.out.println("Moves retrived");
        gameData.printMoves();

        int serverPort = 12345;
        try(ServerSocket serverSocket = new ServerSocket(serverPort)){
            System.out.println("Listening on port " + serverPort);
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
 