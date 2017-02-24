/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Catbat on 23.12.2016.
 */

public class TSFRClient {
    static Socket clientSocket;

    public static void main(String[] args) throws IOException {

        Handshake();
    }

    private static void Handshake() throws IOException {
        //initialize
        DatagramSocket toSocket = new DatagramSocket();

        toSocket.connect(InetAddress.getLocalHost(),4711);

    }
}
