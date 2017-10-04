package com.client;

import com.Command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by marti on 27.02.2017.
 *
 * @author Martin Galusinski
 */
public class Console extends Thread {
    String IPADDRESS_PATTERN =
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    boolean stop;
    Scanner sc;
    ArrayList<Command> commands;

    public Console() {
        stop = false;
        sc = new Scanner(System.in);
        initializer();
        System.out.print("Console running on local machine. Waiting for input...");

    }


    public void run() {
        while (true) {
            if(stop == true){

                break;
            }
            switch (sc.nextLine().toLowerCase()) {
                case "quit":
                    stop = true;
                case "ping":

            }
        }
    }


    private void initializer() {
        System.out.println("Initializing console...");
        try {
            System.out.println("Client running on " + InetAddress.getLocalHost() +
                    "\n Declare server ip, if you want to use the default ip address, type 'default' : ");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        while (true) {
            String in = sc.nextLine();

            if (in != "") {

                Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
                Matcher m = pattern.matcher(in);
                if (m.matches()) {
                    try {
                        TSFRClient.server = InetAddress.getByName(m.group());
                        TSFRClient.handshake();
                        break;
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }




            } else if (in.equalsIgnoreCase("default")) {
                byte[] ip = {(byte) 192, (byte) 168, 0, 11};
                try {
                    TSFRClient.server = InetAddress.getByAddress(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            } else if (in.equalsIgnoreCase("quit")) {
                break;
            }

        }
    }



        //TODO display connections command. quit command.




    private void send() {


    }


}
