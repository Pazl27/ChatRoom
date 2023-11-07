package client.gui;

import client.Client;

import javax.swing.*;

public class MainFrame extends JFrame{
    private JTextField messageField;
    private JTextArea textDisplay;
    private JButton sendButton;
    private JPanel chatPanal;
    private String messageFromChat;

    public MainFrame(Client client){
        setContentPane(chatPanal);
        setVisible(true);
        setTitle("Chat Room-"+client.username);
        setBounds(700, 300, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sendButton.addActionListener(e -> {
            if(!messageField.getText().isEmpty()){
                displayMessage("You: "+messageField.getText());
                client.sendStartMessage(messageField.getText());
            }
        });
    }

    public void displayMessage(String messageFromChat) {
        this.messageFromChat = messageFromChat+" \n";
        textDisplay.append(this.messageFromChat);
    }

}
