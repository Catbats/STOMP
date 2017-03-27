/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import com.Transmitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Catbat on 23.12.2016.
 */
//TODO declare server ip. JavaDoc.
public class TSFRClient {
    static Socket clientSocket;
    static BufferedReader input;
    static PrintStream output;
    static Logger log;
    static Logger logOut;
    public static InetAddress server;
    private static Console con;


    public static void main(String[] args) throws IOException {


        initialize();
        handshake();




/** raw input coming in from the server */

        boolean done = false;

        while (true) {
            String rawin = input.readLine();
            Transmitter in = Transmitter.fromString(rawin);
            if (rawin != null && in != null) {
                log.info("Received: " + in.toString());
                //Check response for message
                switch (in.getMsg().toLowerCase()){
                    case "ping": String out = executeCommand("ping " + clientSocket.getInetAddress() + " -c3");
                        System.out.println(out);
                        done = true;

                }


            }
            if(done == true){
                break;
            }
        }

    }

    private static boolean handshake() throws IOException {


        Logger loghand = Logger.getLogger("client.handshake");
        loghand.setLevel(Level.ALL);

        boolean ack = false;

        //initialize
        try {


            clientSocket = new Socket(server, 4000);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintStream(clientSocket.getOutputStream());


            //Waiting for service to engage handshake
            loghand.info("Waiting for Service...");
            while (true) {
                /** raw input coming in from the server */

                String rawin = input.readLine();
                Transmitter in = Transmitter.fromString(rawin);
                if (rawin != null && in != null) {




                    //Check response for message
                    if (in.getMsg() != null) {
                        loghand.info("Received: " + in.toString());

                        switch (in.getMsg().toUpperCase()) {
                            case "SYN":
                                //Send ACK & wait for another reply
                                send("SYN+ACK");
                            case "ACK":
                                loghand.info("Handshake successful.");
                                ack = true;


                                // TODO Authentication after handshake and proper documentation
                        }


                    }
                }
                if (ack == true) {
                    break;
                }

            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return ack;

    }

    private static void ping(InetAddress ip){
        String cmd = "ping " + ip.toString() + " -c3";
        executeCommand(cmd);
    }

    private static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
    private static void send(String msg) {
        Transmitter req = new Transmitter();
        req.setMsg(msg);
        log.info("Client Sending: " + req);
        output.println(req.toString());

    }
    private static void initialize(){
        log = Logger.getLogger("client");
        logOut = Logger.getLogger("client.output");
        con = new Console();
        con.start();

    }
}
