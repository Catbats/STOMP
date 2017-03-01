package com.server;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marti on 27.02.2017.
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

                case "sh connections":


            }


        }

    }


    private void initializer() {
        System.out.println("Initializing console...");

        commands.add(new Command(1, "quit", "closes target connection."));
        commands.add(new Command(2, "sh connections", "show running connections"));


    }

    private void send() {


    }


}
