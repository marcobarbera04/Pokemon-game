package serverSide.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.data.GameData;

public class ClientManager extends Thread{
    private Socket clientSocket;
    private GameData gameData;

    public ClientManager(Socket clientSocket, GameData gameData){
        this.clientSocket = clientSocket;
        if(gameData == null){
            throw new RuntimeException("Game data can't be null");
        }
        this.gameData = gameData;
    }

    @Override
    public void run(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            out.writeObject(gameData);
            out.flush();

            clientSocket.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
