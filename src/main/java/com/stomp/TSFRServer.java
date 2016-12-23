
package com.stomp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;



public class TSFRServer {
    public static void main(String[] args) {
        ServerSocket listen;
        try {
            listen = new ServerSocket(4000);
            try {
                while (true) {
                    System.out.println("EchoServer is running, waiting for incoming on "
                            + InetAddress.getLocalHost().getHostAddress()
                            + ":" + listen.getLocalPort());
                    Socket serviceSocket = listen.accept();
                    TSFRService service = new TSFRService(serviceSocket);
                    service.start();
                }

            } finally {
                listen.close();
            }
        }
        catch (IOException e) {
            System.err.println("EchoServer: ERR " + e);
        }
    }

}
