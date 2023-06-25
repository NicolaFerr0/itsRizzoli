package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// to test against console:
//  /usr/bin/nc 127.0.0.1 1234
// and type in console: server will receive.
// it will NOT block socket (for now..) when timeout.

public class ServerMain
{
    static final int portNumber = 1234;
    static final int maxRetries = 10;

    static Car[] pro(String jsonStr){

        Gson gson = new Gson();
        Car[] cars = gson.fromJson( jsonStr, Car[].class);
        return cars;
    }
    static Boolean readLoop(BufferedReader in,  PrintWriter out ){
        // waits for data and reads it in until connection dies
        // readLine() blocks until the server receives a new line from client
        String s = "";
        try {
            while ((s = in.readLine()) != null) {
                Car[] x= pro(s);
                for(int i=0;i<x.length;i++)
                {

                    System.out.println(x[i].toString());
                }

                out.println(s.toUpperCase());
                out.flush();
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void main( String[] args )
    {
        System.out.println("Server started!");

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket clientSocket = null;

        while(true)
        {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            ClientManager.getInstance().add(clientHandler);

            Thread thread = new Thread(clientHandler);
            thread.start();


        }
    }
}

