package com.server;

import com.Command;

/**
 * Created by marti on 28.02.2017.
 */
public class Quit extends Command {


    public Quit() {
        setDesc("Closes target connection. Syntax: 'quit x' whereas x represents the target connection ID. \n" +
                "For more information regarding active connections use 'sh connections'.");
        setCommand("quit"); //TODO Quit command, SH connections command.

    }


}
