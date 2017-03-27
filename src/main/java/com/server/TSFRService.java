package com.server;

import com.Transmitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class TSFRService extends Thread {
    private final Socket serviceSocket;
    private int id;
    private BufferedReader input;
    private PrintStream output;
    private Logger loghand;
    private Logger log;
    //TODO JavaDoc. Remove Connection from Connection-database. Close if client closes.


    public TSFRService(Socket serviceSocket) {

        log = Logger.getLogger("srv.connection.service." + id);
        log.setLevel(Level.ALL);


        this.serviceSocket = serviceSocket;

        log.info("Service_" + id + ": new service working for client from "
                + serviceSocket.getInetAddress().getHostAddress()
                + ":" + serviceSocket.getPort());


        //Assigning input and output streams
        try {
            input = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
            output = new PrintStream(serviceSocket.getOutputStream());
        } catch (IOException e) {
            log.severe("Service_" + id + ": Failed opening connection \n Error: " + e.getMessage());
        }


    }

    public void run() {


        handshake();

        try {
            this.serviceSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean handshake() {
        log.info("Service: Engaging handshake...");
        loghand = Logger.getLogger("srv.service." + id + ".handshake");

        boolean ack = false;
        try {


            //Engage connection using SYN
            send("SYN");

            //Wait for response
            while (true) {
                String rawin = input.readLine();
                Transmitter in = Transmitter.fromString(rawin);
                if (rawin != null && in != null) {

                    loghand.info("Received: " + in.toString());

                    //Check response for message
                    if (in.getMsg() != null && ack ==false ) {


                        if( in.getMsg().equalsIgnoreCase("SYN+ACK" )){
                            send("ACK");
                            ack = true;
                            loghand.info("Handshake successful.");

                        }

                            //Check if client sent "SYN+ACK", if so -> respond with "ACK"






                    } else {
                        break;
                    }


                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ack;
    }

    private void send(String msg) {
        Transmitter req = new Transmitter();
        req.setMsg(msg);
        log.info("Service_" + id + " sending: " + req);
        output.println(req.toString());

    }

    public void ping(){
        send("ping");
    }
}
