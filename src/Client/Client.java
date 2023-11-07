package Client;

import Common.Colors;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        }
        catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage(){
        try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()){
                String message = scanner.nextLine();
                bufferedWriter.write(username +": " +message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        }
        catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage(){
        new Thread(() -> {
            String messageFromChat;

            while(socket.isConnected()){
                try{
                    messageFromChat = bufferedReader.readLine();
                    System.out.println(messageFromChat);
                }
                catch(IOException e){
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bf, BufferedWriter bw ){
        try{
            if(socket != null){
                socket.close();
            }
            if(bf != null){
                bf.close();
            }
            if(bw != null){
                bw.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendMessage();

    }
}
