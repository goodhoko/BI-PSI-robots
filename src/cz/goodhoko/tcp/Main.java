package cz.goodhoko.tcp;

import java.io.IOException;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(6655);
        }
        catch (IOException e) {
            System.out.println("Can't open socket: " + e);
            return;
        }
        System.out.println("Waiting for robots at " + socket.getLocalSocketAddress());
        while(true){
            Socket clientSocket = null;
            try {
                clientSocket = socket.accept();
            } catch (IOException e) {
                System.out.println("Can't accept connection: " + e);
                return;
            }
            System.out.println("Accepted connection from " + clientSocket.getInetAddress());
            Handler handler = null;
            try{
                handler = new Handler(clientSocket);
            }catch(Exception e){
                System.out.println("Can't initialize Handler: " + e);
            }

            new Thread(handler).start();
        }
    }
}


