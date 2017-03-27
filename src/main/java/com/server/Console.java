package com.server;

import com.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marti on 27.02.2017.
 *
 * @author Martin Galusinski
 */
public class Console extends Thread {
    Scanner sc;
    ArrayList<Command> commands;

    public Console() {
        sc = new Scanner(System.in);
        System.out.print("Console running on local machine. Waiting for input...");

    }


    public void run() {
        while (true) {
            switch (sc.nextLine().toLowerCase()) {
                case "quit":
                        System.out.println("Select connection");
                    try {
                        TSFRServer.connections.get(sc.nextInt()).getSocket().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case "ping":
                    System.out.println("Select connection");
                    TSFRServer.connections.get(sc.nextInt()).getService().ping();

            }


        }

    }


    private void initializer() {
        System.out.println("Initializing console...");

        commands.add(new Command(1, "quit", "closes target connection."));
        commands.add(new Command(2, "sh connections", "show running connections"));

        //TODO display connections command. quit command.


    }

    private void send() {


    }


}
