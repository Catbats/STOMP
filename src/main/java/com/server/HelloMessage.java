package com.server;

/**
 * Created by Catbat on 18.11.2016.
 */
public class HelloMessage {

    private String name;

    public HelloMessage(){

    }

    public HelloMessage(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
