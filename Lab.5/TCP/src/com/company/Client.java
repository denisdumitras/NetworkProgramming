package com.company;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("127.0.0.1",7888);

        if(socket.isConnected())
        {
            System.out.println("Connected to server");
        }

        BufferedReader msg = new BufferedReader(new InputStreamReader(System.in));
        String string;
        DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputData = new DataInputStream(socket.getInputStream());

        while(true)
        {
            System.out.print("Client:\t");
            string = msg.readLine();
            outputData.writeUTF(string + "\n");
            string = inputData.readUTF();
            System.out.println("Server:\t" + string);
        }
    }
}