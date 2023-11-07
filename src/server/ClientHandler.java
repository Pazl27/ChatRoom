package server;

import common.Colors;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientName;
    private String color;

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientName = bufferedReader.readLine();
            clientHandlers.add(this);
            this.color = Colors.getRandomColor();
            broadcastMessage("server", clientName + " has joined the chat");
        }
        catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }


    }

    @Override
    public void run() {
        String message;

        while(socket.isConnected()){
            try{
                message = bufferedReader.readLine();
                broadcastMessage(clientName, message);
            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String clientName, String message){
        for(ClientHandler clientHandler : clientHandlers){
            try{
                if(!clientHandler.clientName.equals(clientName)){
                    //clientHandler.bufferedWriter.write(this.color +message +Colors.RESET);
                    clientHandler.bufferedWriter.write(clientName+": "+message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("server: ", clientName + " has left the chat");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try{
            if(socket != null){
                socket.close();
            }
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
