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

public class TSFRClientOld {
    public static void main(String[] args) {
        Socket clientSocket;
        try {
            //initialize
            DatagramSocket toSocket = new DatagramSocket();
            clientSocket = new Socket ("localhost", 4000);
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream output = new PrintStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            //Connection information
            System.out.println("EchoClient runs on " + clientSocket.getInetAddress().getHostAddress()
                    + ":" + clientSocket.getLocalPort() + " - type 'quit' to stop.");

            //Authentication
            System.out.println("Username wählen :");
            String username = scanner.nextLine();



            byte[] data = username.getBytes();
            int port = 4711;

            InetAddress ia = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(data, data.length, ia, port );
            toSocket.send(packet);
            System.out.println("Username lautet : " + username);
            while(true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("quit")) {
                    break;
                }
                output.println(line);
                line = input.readLine();
                System.out.println("EchoClient: received " + line);
            }
            output.close();
            input.close();
            clientSocket.close();
            toSocket.close();
        } catch (IOException e) {
            System.err.println("EchoClient: ERR " + e);
        }
    }
}
