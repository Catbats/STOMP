package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class TSFRService extends Thread {
    private final Socket serviceSocket;
    //TODO Handshake receive. JavaDoc.
    private Logger log = Logger.getLogger("srv.service." + TSFRServer.connections.size());


    public TSFRService(Socket serviceSocket) {
        log.setLevel(Level.WARNING);

        this.serviceSocket = serviceSocket;

        log.info("Service: new service working for client from "
                + serviceSocket.getInetAddress().getHostAddress()
                + ":" + serviceSocket.getPort());

        //Entry in connection Database
        Connection k = new Connection(serviceSocket.getInetAddress(), serviceSocket.getPort());
        TSFRServer.connections.add(k);

    }

    public void run() {
        System.out.println("EchoService: waiting for input from the client...");
        String username = null;

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
            PrintStream output = new PrintStream(serviceSocket.getOutputStream());
            try {


                while (true) {


                    String line = input.readLine();
                    if (line.equalsIgnoreCase("quit")) {
                        break;
                    }
                    System.out.println(username + ": " + line);
                    output.println(line);
                    HashMap test = new HashMap();
                    test.put(1, 2);
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
