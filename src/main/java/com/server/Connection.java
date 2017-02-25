
package com.server;


import java.net.InetAddress;

/**
 * Class used to describe connections. Also used by the Connection-database.
 *
 *
 * @see TSFRServer#connections
 * @since 1
 * @author Martin Galusinski
 * @version 1
 *
 */
public class Connection {
    private InetAddress ip;
    private int port;
    private String name;


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
