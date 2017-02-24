package com.stomp;


import java.net.InetAddress;

/**
 * Created by Catbat on 24.02.2017.
 */
public class Connection {
    InetAddress ip;
    int port;
    String name;

    public Connection (InetAddress ipaddress, int portnumber, String namein){
        this.ip = ipaddress;
        this.port = portnumber;
        this.name = namein;
    }

    public Connection (InetAddress ipaddress, int portnumber){
        this.ip = ipaddress;
        this.port = portnumber;
        this.name = null;
    }


    public Connection (){

    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
