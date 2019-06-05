# Laboratory Work Nr. 5

### Objectives 
 - Get familiar with Sockets API;
 - Develop a client-server application which will transfer data, using the TCP and UDP protocols;
 
### Task
 * Create a simple chat application which will implement Sockets, TCP protocol and UDP protocol.
 
### Theory
Sockets are communication points on the same or different computers to exchange data. Sockets allow communication between two different processes on the same or different machines. To be more precise, it's a way to talk to other computers.

There are four types of sockets available to the users. The first two are most commonly used and the last two are rarely used:
 - Stream Sockets;
 - Datagram Sockets;
 - Raw Sockets;
 - Sequenced Packet Sockets;
 
Stream Sockets − Delivery in a networked environment is guaranteed. If you send through the stream socket three items "A, B, C", they will arrive in the same order − "A, B, C". These sockets use TCP (Transmission Control Protocol) for data transmission. If delivery is impossible, the sender receives an error indicator. Data records do not have any boundaries.

Datagram Sockets − Delivery in a networked environment is not guaranteed. They're connectionless because you don't need to have an open connection as in Stream Sockets − you build a packet with the destination information and send it out. They use UDP (User Datagram Protocol).

TCP is a connection oriented protocol and offers end-to-end packet delivery. It acts as back bone for connection. It exhibits the following key features:
 - Transmission Control Protocol (TCP) corresponds to the Transport Layer of OSI Model;
 - TCP ensures reliability by sequencing bytes with a forwarding acknowledgement number that indicates to the destination the next byte the source expect to receive.

UDP is connectionless and unreliable protocol. It doesn’t require making a connection with the host to exchange data. Since UDP is unreliable protocol, there is no mechanism for ensuring that data sent is received. UDP transmits the data in form of a datagram. The UDP datagram consists of five parts as shown in the following diagram:

![UDP](https://github.com/denisdumitras/Network-Programming/blob/master/Lab.5/Screens/udp.PNG)

### Implementation
Firstly, I will describe the implementation of the chat application using TCP protocol. My application consists from 2 parts: Server part and Client part.

The server part has 2 main functionalities to start the server and to wait for the client's request to connect. In order to achieve these goals a object of _ServerSocket_ type should be created.
```java
 ServerSocket serverSocket = new ServerSocket(7888);
```

In order to allow multiple clients to chat at a time each client was associated a thread.
```java
        while(!isStopped){
            Socket clientSocket = serverSocket.accept();
            ServerThread clientThread = new ServerThread(clientSocket);
            clientThread.start();
        }
```

The requeste will be processed inside the _ServerThread_ class which is extended from _Thread_ class. Basically it describes how a thread of _ServerThread_ type should be interpreted.
```java
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
```

In order to create the client part of the application, an instance of _Socket_ class should be created. The constructor of this object takes 2 parameters: host address and port. In such a way a connection with the port where the server is operating will be realised.
```java
Socket socket = new Socket("127.0.0.1",7888);
```

The requests are written and read using the input and outpud data streams.
```java
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
```

The second approach of developing this application has as a purpose using the UDP protocol in stead of TCP. The architecture of the applciation is the same. There are the same parts: Server and Client. In order to make their communication possible a socket on a server port is created. Using it the client will be able to connect to the server.
```java
DatagramSocket socket = new DatagramSocket(9861);
```

In order to send and receive data a _DatagramPacket_ is used. When sending the host address and port should be specified.
```java
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
```

![result](https://github.com/denisdumitras/Network-Programming/blob/master/Lab.5/Screens/result.PNG)

### Conclusion
In this laboratory work I have gained knowledge about what a socket is and why it is used for. There are serveral ways of making a client-server application which will send and receive data. Based on the needs different protocols can be used. During this laboratory work I have implemented 2 of them: TCP and UDP. First uses a mechanism for ensuring that data is received while the second does not.
