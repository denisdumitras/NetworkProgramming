package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7888);
        boolean isStopped = false;
        System.out.println("Server started");

        while(!isStopped){
            Socket clientSocket = serverSocket.accept();
            ServerThread clientThread = new ServerThread(clientSocket);
            clientThread.start();
        }
    }
}