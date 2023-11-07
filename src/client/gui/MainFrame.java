package client.gui;

import client.Client;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class MainFrame extends JFrame{
    private JTextField messageField;
    private JTextArea textDisplay;
    private JButton sendButton;
    private JPanel chatPanal;
    private String messageFromChat;
    private Client client;

    public MainFrame(Client client){
        this.client = client;
        setContentPane(chatPanal);
        setVisible(true);
        setTitle("Chat Room-"+client.username);
        setBounds(700, 300, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sendButton.addActionListener(e -> {
            if(!messageField.getText().isEmpty()){
                displayMessage("You: "+messageField.getText());
                this.client.sendMessage(messageField.getText());
                messageField.setText("");
            }
        });
    }

    public void displayMessage(String messageFromChat) {
        this.messageFromChat = messageFromChat+" \n";
        textDisplay.append(this.messageFromChat);

        if(!messageFromChat.contains("You") || messageFromChat.contains("joined")){
            //playSound();
        }
    }

    public void playSound(){
        try {
            File audioFile = new File("rsc/sounds/cartoon_dang.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();

            // Sleep for a while to allow sound to play
            Thread.sleep(300);

            clip.stop();
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
