package com.company;

import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws Exception
    {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte sendByte[];
        byte receiveByte[] = new byte[1024];
        while(true)
        {
            //Data Sending
            DataInputStream inputStream = new DataInputStream(System.in);
            System.out.print("Client:");
            String msgSent = inputStream.readLine();
            sendByte = msgSent.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendByte, sendByte.length, ip,9861);
            socket.send(sendPacket);

            //Data Receiving
            DatagramPacket receivePacket = new DatagramPacket(receiveByte, receiveByte.length);
            socket.receive(receivePacket);
            String msgReceived = new String(receivePacket.getData());
            msgReceived = msgReceived.trim();
            System.out.println("Server:" + msgReceived);
        }
    }
}