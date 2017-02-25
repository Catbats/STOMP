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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Catbat on 23.12.2016.
 */

public class TSFRClient {
    static Socket clientSocket;
    static BufferedReader input;
    static PrintStream output;
    static Logger log;
    static Logger logOut;

    public static void main(String[] args) throws IOException {
        log = Logger.getLogger("client");
        logOut = Logger.getLogger("client.output");

        Handshake();
    }

    private static void Handshake() throws IOException {


        Logger loghand = Logger.getLogger("client.handshake");
        loghand.setLevel(Level.ALL);

        //initialize
        try {


            clientSocket = new Socket("localhost", 4000);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintStream(clientSocket.getOutputStream());


            loghand.info("Waiting for Service...");
            while (true) {
                /** raw input coming in from the server */
                String rawin = input.readLine();
                Transmitter in = Transmitter.fromString(rawin);
                loghand.info(in.toString());


                if (in.getMsg().equalsIgnoreCase("SYN")) {

                    send("ACK");

                } else if (in.getMsg().equalsIgnoreCase("ACK")) {
                    loghand.info("Connection established. Commencing Authentication...");
                    // TODO Authentication after handshake and proper documentation
                }
            }


        } catch (IOException exception) {
            loghand.severe("Handshake failed. \n Error : \n " + exception.getMessage());
        }

    }

    private static void send(String msg) {
        Transmitter req = new Transmitter();
        req.setMsg(msg);
        output.println(req.toString());

    }
}
