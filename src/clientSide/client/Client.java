package clientSide.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import shared.data.GameData;

public class Client{
    private final String serverAddress;
    private final int serverPort;

    public Client(String serverAddress, int port){
        if(serverAddress == null){
            throw new RuntimeException("Server address can't be null");
        }
        this.serverAddress = serverAddress;
        this.serverPort = port;
    }

    public GameData retriveGameData(){
        GameData gameData = null;
        try{
            Socket socket = new Socket(serverAddress, serverPort);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to the server");

            // Read object
            Object receivedData = in.readObject();
            if(receivedData instanceof GameData){
                gameData = (GameData)receivedData;
            }else{
                System.out.println("Unexpected data format from the server");
            }
            socket.close();

        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return gameData;
    }
}
