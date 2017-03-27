package com;

import java.util.ArrayList;

/**
 * Created by marti on 27.02.2017.
 */
public class Command {
    private int id;
    private String command;
    /**
     * description
     */
    private String desc;
    /**
     * Possible abbreviations in an ArrayList
     */
    private ArrayList<String> abbrv;

    public Command() {

    }

    public Command(int id, String command, String desc) {

        this.id = id;
        this.command = command;
        this.desc = desc;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean compareCom(String in) {
        boolean ret = false;
        if (in.equalsIgnoreCase(command)) {
            ret = true;
        } else {
            for (int x = 0; x < this.abbrv.size(); x++) {
                if (this.abbrv.get(x).contains(in.toLowerCase())) {
                    ret = true;
                    break;
                }

            }
        }

        return ret;
    }

    public void setAbbrv(String comm) {
        this.abbrv.add(comm);
    }

}
