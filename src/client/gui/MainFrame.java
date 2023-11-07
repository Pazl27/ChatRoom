package client.gui;

import client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JTextField messageField;
    private JTextArea textDisplay;
    private JButton sendButton;
    private JPanel chatPanal;
    private String messageFromChat;
    private String messageToSend;

    public MainFrame(Client client){
        setContentPane(chatPanal);
        setVisible(true);
        setTitle("Chat Room-"+client.username);
        setBounds(700, 300, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messageToSend = "";
        client.message = messageToSend;

        sendButton.addActionListener(e -> {
            if(!messageField.getText().isEmpty()){
                messageToSend = messageField.getText();
                displayMessage("You: "+messageToSend);
                messageField.setText("");
                client.message = messageToSend;
            }
            else{
                client.message = "";
            }
        });
    }

    public void displayMessage(String messageFromChat) {
        this.messageFromChat = messageFromChat+" \n";
        textDisplay.append(this.messageFromChat);
    }

    public String getMessage(){
        return messageToSend;
    }

}
