package com.company;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputData = new DataInputStream(socket.getInputStream());
            String string;
            BufferedReader msg = new BufferedReader(new InputStreamReader(System.in));

            while(true)
            {
                string = inputData.readUTF();
                System.out.print("Client:\t" + string);
                System.out.print("Server:\t");
                string = msg.readLine();
                outputData.writeUTF(string);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}