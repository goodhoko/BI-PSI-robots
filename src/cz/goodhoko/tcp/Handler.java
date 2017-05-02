package cz.goodhoko.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Handler implements Runnable{
    Socket socket;
    DataOutputStream out;
    Scanner in;
    StateMachine stateMachine;



    Handler(Socket socket) throws Exception{
        this.socket = socket;
        this.stateMachine = new StateMachine();
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream()))).useDelimiter("\\r\\n");
    }



    public void run(){
        try{
            //send login prompt
            out.writeBytes(stateMachine.getNextResponse("").response);
            out.flush();
       
            while (true) {
                socket.setSoTimeout(stateMachine.getTimeout());
                Response response = null;

                if(in.hasNext() && !in.hasNext(".*\\z")){
                    //recieved whole message -> next tick of the statemachine
                    response = stateMachine.getNextResponse(in.next());
                }else{
                    //recieved only part of the message -> give it to the statemachine to check if it is already too long, if not, we'll wait for the rest
                    response = stateMachine.preValidate(in.next());
                }
                System.out.println(response);
                if (!response.response.equals("")) {
                    out.writeBytes(response.response);
                    out.flush();
                }
                if (response.closeAfter) {
                    System.out.println("closed by the stateMachine");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        close();
    }



    private void close(){
        try {
            System.out.println("Closing connection");
            socket.close();
            out.close();
            in.close();
        }catch (Exception e){
            System.out.println("Can't close all resources: " + e);
        }
    }
}
