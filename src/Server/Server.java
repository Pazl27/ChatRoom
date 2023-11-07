package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket sSocket;

    public Server(ServerSocket sSocket) {
        this.sSocket = sSocket;
    }

    public void startServer(){
        try{
            while(!sSocket.isClosed()){

                Socket socket = sSocket.accept();
                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }
        catch (IOException e){

        }
    }

    public void closeServerSocket(){
        try{
            if(sSocket != null){
                sSocket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket sSocket = new ServerSocket(1234);
        Server server = new Server(sSocket);
        System.out.println("Server started successfully");
        server.startServer();

    }
}
