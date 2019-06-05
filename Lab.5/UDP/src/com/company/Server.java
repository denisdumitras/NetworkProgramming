package com.company;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9861);
        byte receiveByte[] = new byte[1024];
        byte sendByte[];
        while(true)
        {
            //Data Receiving
            DatagramPacket receivePacket = new DatagramPacket(receiveByte, receiveByte.length);
            socket.receive(receivePacket);
            String msgReceived = new String(receivePacket.getData());
            msgReceived = msgReceived.trim();
            System.out.println("Client:" + msgReceived);

            //Data Sending
            DataInputStream inputStream = new DataInputStream(System.in);
            System.out.print("Server:");
            String msgSent = inputStream.readLine();
            sendByte = msgSent.getBytes();
            InetAddress ip = receivePacket.getAddress();
            int port = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendByte, sendByte.length, ip, port);
            socket.send(sendPacket);
        }
    }
}