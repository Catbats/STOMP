
package com.server;


import java.net.Socket;
import java.util.logging.Logger;

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
    private int id;
    private Socket socket;
    private String name;
    private TSFRService service;
    private Logger log = Logger.getLogger("srv.connection");

    public Connection(int id, Socket socket) {
        this.id = id;
        this.socket = socket;
        service = new TSFRService(this.socket);
        startService();
    }

    //gets & sets

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TSFRService getService() {
        return service;
    }

    public void setService(TSFRService service) {
        this.service = service;
    }


    //special service methods

    public void startService() {
        if (this.service.isAlive() == false) {
            this.service.start();
        } else {
            log.warning("service already running.");
        }

    }

    public void stopService() {
        if (this.service.isAlive() == true) {
            this.service.interrupt();
            log.info("Service stop invoked.");
        } else {
            log.warning("service already running.");
        }

    }
}
