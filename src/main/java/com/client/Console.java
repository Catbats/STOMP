package com.client;

import com.Command;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by marti on 27.02.2017.
 *
 * @author Martin Galusinski
 */
public class Console extends Thread {
    String IPADDRESS_PATTERN =
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    Scanner sc;
    ArrayList<Command> commands;

    public Console() {
        sc = new Scanner(System.in);
        initializer();
        System.out.print("Console running on local machine. Waiting for input...");

    }


    public void run() {




        while (true) {
            switch (sc.nextLine().toLowerCase()) {
                case "quit":

                case "ping":

            }


        }

    }


    private void initializer() {
        System.out.println("Initializing console...");
        try {
            System.out.println("Client running on " + InetAddress.getLocalHost() + "\n Declare server ip, if you want to use the default ip address, leave blank: ");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        if(sc.nextLine() != " "){
            Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
            try {
                TSFRClient.server = InetAddress.getByName(sc.next(pattern));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } else {
            byte[] ip = {(byte) 192, (byte) 168,0,11};
            try {
                TSFRClient.server = InetAddress.getByAddress(ip);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }




        //TODO display connections command. quit command.


    }

    private void send() {


    }


}
