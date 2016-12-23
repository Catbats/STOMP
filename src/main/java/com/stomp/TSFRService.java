package com.stomp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

class TSFRService extends Thread {
    private final Socket serviceSocket;

    public TSFRService(Socket serviceSocket) {
        this.serviceSocket = serviceSocket;
        System.out.println("EchoService: new service working for client from "
                +  serviceSocket.getInetAddress().getHostAddress()
                + ":" + serviceSocket.getPort());

    }
    public void run() {
        System.out.println("EchoService: waiting for input from the client...");
        String username = null;

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
            PrintStream output = new PrintStream(serviceSocket.getOutputStream());
            try {
                byte[] data = new byte [ 1024 ];
                DatagramSocket datasocket = new DatagramSocket(4711);
                DatagramPacket datapacket = new DatagramPacket ( data, data.length);
                datasocket.receive(datapacket);
                String datatext = null;
                try {
                    datatext = new String(datapacket.getData());
                } finally {
                    System.out.println("User " + datatext + " connected.");
                    username = datatext;

                }
                while (true) {


                    String line = input.readLine();
                    if (line.equalsIgnoreCase("quit")) {
                        break;
                    }
                    System.out.println(username + ": " + line);
                    output.println(line);
                }
            } finally {
                output.close();
                input.close();
            }
            serviceSocket.close();
        } catch (IOException ex) {
            System.err.println("EchoService: ERR " + ex);
        }
        System.out.println("EchoService: stopped");
    }
}
