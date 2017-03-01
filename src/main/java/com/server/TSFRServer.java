
package com.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server validating incoming connections and starting services
 *
 * @author Martin Galusinski
 * @version 1
 * @see com.server.TSFRService
 * @since 1
 */
public class TSFRServer {

    /**
     * Connection-database used to manage only established and running connections.
     *
     * @see Connection
     */
    public static ArrayList<Connection> connections = new ArrayList();
    static Logger servlog = Logger.getLogger("srv");

    public static void main(String[] args) {

        servlog.setLevel(Level.ALL);
        ServerSocket listen;

        try {
            listen = new ServerSocket(4000);
            try {
                while (true) {

                    servlog.info("EchoServer is running, waiting for incoming on "
                            + InetAddress.getLocalHost().getHostAddress()
                            + ":" + listen.getLocalPort());


                    Socket serviceSocket = listen.accept();
                    Connection temp = new Connection(connections.size(), serviceSocket);
                    connections.add(temp);
                    //TODO display existing connection
                    servlog.info("Current running connections: ");
                }

            } finally {
                listen.close();
            }

        } catch (IOException e) {
            servlog.severe("Critical Error : " + e);
        }
    }

}
